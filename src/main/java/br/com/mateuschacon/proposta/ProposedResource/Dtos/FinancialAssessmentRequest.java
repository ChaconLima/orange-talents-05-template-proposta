package br.com.mateuschacon.proposta.ProposedResource.Dtos;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinancialAssessmentRequest {
    
    @NotBlank @JsonProperty
    private String documento;

    @NotBlank @JsonProperty
    private String nome;

    @NotBlank @JsonProperty
    private String idProposta;

    public FinancialAssessmentRequest(@NotBlank String documento, @NotBlank String nome, @NotBlank String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    
}
