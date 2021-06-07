package br.com.mateuschacon.proposta.ProposedResource.Dtos;

import javax.validation.constraints.NotBlank;

public class FinancialAssessmentResponse {
    
    @NotBlank
    private String documento;

    @NotBlank 
    private String nome;

    @NotBlank
    private String resultadoSolicitacao;

    @NotBlank 
    private String idProposta;

    /**
     * Sets for Jackson 
     * @return
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public void setIdProposta(String idProposta) {
        this.idProposta = idProposta;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setResultadoSolicitacao(String resultadoSolicitacao) {
        this.resultadoSolicitacao = resultadoSolicitacao;
    }
    /**
     * Gets
     * @return
     */

    public String getResultadoSolicitacao() {
        return this.resultadoSolicitacao;
    }
}

