package br.com.mateuschacon.proposta.CardResource.Dtos;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlockRequest {

    @NotBlank @JsonProperty
    private String sistemaResponsavel;

    public BlockRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }


    
}
