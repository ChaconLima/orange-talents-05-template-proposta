package br.com.mateuschacon.proposta.ProposedResource.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Card {
    
    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(32)")
    @NotBlank
    private String id;

    @NotNull
    private LocalDateTime issued;

    @NotNull
    private BigDecimal limitCard;

    @Deprecated
    public Card(){}

    public Card(@NotBlank String id, @NotNull LocalDateTime issued, @NotNull BigDecimal limitCard) {
        this.id = id;
        this.issued = issued;
        this.limitCard = limitCard;
    }
}
