package br.com.mateuschacon.proposta.CardResource.Dtos;

import javax.validation.constraints.NotBlank;

public class BlockResponse {

    @NotBlank
    private String resultado;

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return this.resultado;
    }
}
