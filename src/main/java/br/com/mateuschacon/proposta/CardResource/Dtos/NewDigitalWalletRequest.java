package br.com.mateuschacon.proposta.CardResource.Dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.mateuschacon.proposta.CardResource.Models.Card;
import br.com.mateuschacon.proposta.CardResource.Models.DigitalWallet;
import br.com.mateuschacon.proposta.CardResource.Models.Enums.DigitalWalletEnum;

public class NewDigitalWalletRequest {

    @NotBlank @Email
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public DigitalWalletRequest toRequest( DigitalWalletEnum wallet) {
        return new DigitalWalletRequest( this.email, wallet);
    }

    public DigitalWallet toModel(String id, DigitalWalletEnum digitalWalletEnum,Card card) {
        return new DigitalWallet(id, this.email, digitalWalletEnum, card);
    }

}