package br.com.mateuschacon.proposta.CardResource.Models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.mateuschacon.proposta.CardResource.Models.Enums.DigitalWalletEnum;

@Entity
public class DigitalWallet {

    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    @NotBlank
    private String id;

    @NotBlank
    private String email;

    @NotNull @Enumerated(EnumType.STRING)
    private DigitalWalletEnum digitalWalletEnum;

    @ManyToOne
    private Card card;

    @Deprecated
    public DigitalWallet(){}

    public DigitalWallet(@NotBlank String id, @NotBlank String email, @NotNull DigitalWalletEnum digitalWalletEnum,
                            @NotNull Card card) {
        this.id = id;
        this.email = email;
        this.digitalWalletEnum = digitalWalletEnum;
        this.card = card;
    }

    public DigitalWalletEnum getDigitalWalletEnum() {
        return this.digitalWalletEnum;
    }

    public String getId() {
        return this.id;
    }
}
