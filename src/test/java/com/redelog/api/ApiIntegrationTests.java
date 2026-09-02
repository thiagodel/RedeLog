package com.redelog.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redelog.api.repository.ClienteRepository;
import com.redelog.api.repository.EntregadorRepository;
import com.redelog.api.repository.EntregaRepository;
import com.redelog.api.repository.FilialRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ApiIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Autowired
    private FilialRepository filialRepository;

    @AfterEach
    void cleanDatabase() {
        entregaRepository.deleteAll();
        entregadorRepository.deleteAll();
        clienteRepository.deleteAll();
        filialRepository.deleteAll();
    }

    @Test
    void shouldManageResourcesAndDeliveryWorkflow() throws Exception {
        long clienteId = createCliente("Ana Silva", "ana@redelog.com");
        long entregadorId = createEntregador();
        long filialId = createFilial();

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(clienteId));

        mockMvc.perform(put("/clientes/{id}", clienteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nome":"Ana Souza","telefone":"11999990000","email":"ana@redelog.com","cep":"01001-000","endereco":"Rua A"}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Ana Souza"));

        mockMvc.perform(get("/entregadores/{id}", entregadorId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ATIVO"));

        mockMvc.perform(get("/filiais/{id}", filialId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cnpj").value("12345678000190"));

        long entregaId = createEntrega(clienteId, entregadorId, filialId);

        mockMvc.perform(patch("/entregas/{id}/despachar", entregaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ENVIADA"));

        mockMvc.perform(get("/entregas/{id}/historico", entregaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].status").value("CRIADA"))
                .andExpect(jsonPath("$[0].observacao").value("Entrega criada"))
                .andExpect(jsonPath("$[0].entrega").doesNotExist())
                .andExpect(jsonPath("$[1].status").value("ENVIADA"));

        mockMvc.perform(patch("/entregas/{id}/sairParaEntrega", entregaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("EM_ROTA"));

        mockMvc.perform(patch("/entregas/{id}/finalizarEntrega", entregaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ENTREGUE"));

        mockMvc.perform(delete("/entregas/{id}", entregaId))
                .andExpect(status().isNoContent());

        mockMvc.perform(delete("/clientes/{id}", clienteId))
                .andExpect(status().isNoContent());
        mockMvc.perform(delete("/entregadores/{id}", entregadorId))
                .andExpect(status().isNoContent());
        mockMvc.perform(delete("/filiais/{id}", filialId))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnStandardErrorResponses() throws Exception {
        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.path").value("/clientes"))
                .andExpect(jsonPath("$.fieldErrors.nome").exists());

        mockMvc.perform(get("/clientes/{id}", 999L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value(containsString("Cliente não encontrado")));

        long clienteId = createCliente("João Lima", "joao@redelog.com");
        long entregadorId = createEntregador();
        long filialId = createFilial();
        long entregaId = createEntrega(clienteId, entregadorId, filialId);

        mockMvc.perform(patch("/entregas/{id}/finalizarEntrega", entregaId))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.message").value("Entrega não está em rota"));
    }

    private long createCliente(String nome, String email) throws Exception {
        MvcResult result = mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nome":"%s","telefone":"11999990000","email":"%s","cep":"01001-000","endereco":"Rua A"}
                                """.formatted(nome, email)))
                .andExpect(status().isCreated())
                .andReturn();
        return responseId(result);
    }

    private long createEntregador() throws Exception {
        MvcResult result = mockMvc.perform(post("/entregadores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nome":"Carlos Lima","telefone":"11988887777","email":"carlos@redelog.com","placaVeiculo":"ABC1D23"}
                                """))
                .andExpect(status().isCreated())
                .andReturn();
        return responseId(result);
    }

    private long createFilial() throws Exception {
        MvcResult result = mockMvc.perform(post("/filiais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nome":"Filial Centro","numeroFilial":"CENTRO-01","cnpj":"12345678000190","endereco":{"rua":"Rua Central","numero":"100","bairro":"Centro","cidade":"São Paulo","estado":"SP","cep":"01001-000"}}
                                """))
                .andExpect(status().isCreated())
                .andReturn();
        return responseId(result);
    }

    private long createEntrega(long clienteId, long entregadorId, long filialId) throws Exception {
        MvcResult result = mockMvc.perform(post("/entregas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"clienteId":%d,"entregadorId":%d,"filialOrigemId":%d,"enderecoEntrega":{"rua":"Rua da Entrega","numero":"20","bairro":"Jardins","cidade":"São Paulo","estado":"SP","cep":"01415-000"}}
                                """.formatted(clienteId, entregadorId, filialId)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("CRIADA"))
                .andReturn();
        return responseId(result);
    }

    private long responseId(MvcResult result) throws Exception {
        JsonNode response = objectMapper.readTree(result.getResponse().getContentAsString());
        return response.get("id").asLong();
    }
}
