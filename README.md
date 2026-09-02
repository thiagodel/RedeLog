# RedeLog API

API REST do sistema RedeLog para o gerenciamento de clientes, entregadores,
filiais e entregas. O backend é desenvolvido em Java 17 com Spring Boot e
persiste os dados em MySQL.

## Arquitetura

```text
Clientes web, desktop ou mobile
             |
             v
      API REST (Spring Boot)
             |
             v
        MySQL / JPA
```

O código segue uma separação por responsabilidades:

```text
src/main/java/com/redelog/api
├── controller  # Endpoints HTTP
├── service     # Regras de negócio
├── repository  # Acesso aos dados com JPA
├── model       # Entidades e enums do domínio
├── dto         # Objetos de entrada e saída da API
├── mapper      # Conversão entre entidades e DTOs
└── config      # Configurações, como CORS
```

## Tecnologias

- Java 17
- Spring Boot 3
- Spring Web, Validation e Data JPA
- MySQL e H2 (testes)
- Lombok
- Maven

## Configuração

As configurações padrão ficam em
`src/main/resources/application.properties`. Para executar com MySQL, defina
as variáveis de ambiente abaixo (ou os valores equivalentes nas propriedades
do Spring):

```powershell
$env:DB_USERNAME = "seu_usuario"
$env:DB_PASSWORD = "sua_senha"
```

O servidor inicia na porta `8081`. O perfil padrão é `dev`; suas propriedades
ficam em `application-dev.properties`. O perfil `test` utiliza H2 em memória.

### CORS

As origens permitidas são configuradas pela variável `CORS_ALLOWED_ORIGINS`,
em lista separada por vírgulas. O padrão atende aplicações locais em portas
comuns de desenvolvimento:

```powershell
$env:CORS_ALLOWED_ORIGINS = "http://localhost:3000,http://localhost:5173"
```

Em produção, informe apenas os domínios efetivamente utilizados pelo cliente,
por exemplo `https://app.exemplo.com`. A API não libera qualquer origem e não
envia credenciais em requisições CORS.

## Execução

Execute pelo Maven Wrapper versionado no repositório:

```powershell
.\mvnw.cmd spring-boot:run
```

Para rodar os testes:

```powershell
.\mvnw.cmd test
```

## Endpoints disponíveis

### Entregas

- `GET /entregas` — lista paginada
- `GET /entregas/{id}` — consulta por identificador
- `POST /entregas` — cria uma entrega
- `PUT /entregas/{id}` — atualiza uma entrega
- `DELETE /entregas/{id}` — remove uma entrega
- `PATCH /entregas/{id}/despachar`
- `PATCH /entregas/{id}/sairParaEntrega`
- `PATCH /entregas/{id}/finalizarEntrega`
- `PATCH /entregas/{id}/registrarFalha?motivo=...`

### Entregadores

- `GET /entregadores`
- `GET /entregadores/{id}`
- `POST /entregadores`
- `PUT /entregadores/{id}`
- `DELETE /entregadores/{id}`
- `PATCH /entregadores/{id}/ativar`
- `PATCH /entregadores/{id}/desativar`

### Clientes

O serviço de clientes oferece listagem, consulta, cadastro, atualização e
remoção. O controlador correspondente ainda precisa ser corrigido para que
esses endpoints fiquem disponíveis.

### Filiais

- `GET /filiais`
- `GET /filiais/{id}`
- `POST /filiais`
- `PUT /filiais/{id}`
- `DELETE /filiais/{id}`

O cadastro valida a unicidade de CNPJ e número da filial.

## Estados de entrega

```text
CRIADA -> ENVIADA -> EM_ROTA -> ENTREGUE
                         |
                         -> FALHA
```

## Situação atual

Em Desenvolvimento

## Autores

Thiago Delmiro, Guilherme Augusto
