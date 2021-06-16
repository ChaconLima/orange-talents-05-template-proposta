package br.com.mateuschacon.proposta.CardResource.Dtos;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.mateuschacon.proposta.CardResource.Models.Enums.DigitalWalletEnum;

public class DigitalWalletRequest {
    
    @NotBlank @JsonProperty
    private String email;

    @NotBlank @JsonProperty
    private String carteira;

    public DigitalWalletRequest(@NotBlank String email, @NotBlank DigitalWalletEnum wallet) {
        this.email = email;
        this.carteira = wallet.toString();
    }


}
