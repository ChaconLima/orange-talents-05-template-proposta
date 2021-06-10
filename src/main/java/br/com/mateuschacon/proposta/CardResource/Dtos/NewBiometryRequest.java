package br.com.mateuschacon.proposta.CardResource.Dtos;


import javax.validation.constraints.NotBlank;

import br.com.mateuschacon.proposta.CardResource.Models.Biometry;
import br.com.mateuschacon.proposta.CardResource.Models.Card;
import br.com.mateuschacon.proposta.Configuration.Validation.Custom.Base_64;

public class NewBiometryRequest {
    
    @NotBlank @Base_64
    private String fingerPrint;

    public void setFingerprint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }
    
    public Biometry toModel(Card card){
        return new Biometry(this.fingerPrint, card);
    }
}
