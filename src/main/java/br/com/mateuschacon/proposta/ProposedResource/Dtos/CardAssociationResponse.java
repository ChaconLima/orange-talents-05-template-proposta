package br.com.mateuschacon.proposta.ProposedResource.Dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.mateuschacon.proposta.CardResource.Models.Card;

public class CardAssociationResponse {
    
    @NotBlank
    private String id;

    @NotNull
    private LocalDateTime emitidoEm;

    @NotNull
    private BigDecimal limite;

    public CardAssociationResponse(@NotBlank String id, @NotNull LocalDateTime emitidoEm, @NotNull BigDecimal limite) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.limite = limite;
    }

    public Card toModel(){

        return new Card(this.id, this.emitidoEm, this.limite);
    }


    
}
