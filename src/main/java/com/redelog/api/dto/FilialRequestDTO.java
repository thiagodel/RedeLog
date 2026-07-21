package com.redelog.api.dto;


import com.redelog.api.model.entities.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilialRequestDTO {

    private String nome;
    private EnderecoRequestDTO endereco;
    private String numeroFilial;


}
