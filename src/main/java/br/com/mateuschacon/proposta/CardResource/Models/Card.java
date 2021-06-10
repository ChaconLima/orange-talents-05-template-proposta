package br.com.mateuschacon.proposta.CardResource.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy = "card", cascade = CascadeType.MERGE)
    private Set<Biometry>biometrics;

    @Deprecated
    public Card(){}

    public Card(@NotBlank String id, @NotNull LocalDateTime issued, @NotNull BigDecimal limitCard) {
        this.id = id;
        this.issued = issued;
        this.limitCard = limitCard;
        this.biometrics = new HashSet<>();
    }

    public void addBiometry( Biometry biometry){
        this.biometrics.add(biometry);
    }
    public Set<Biometry> getBiometrics() {
        return this.biometrics;
    }
}
