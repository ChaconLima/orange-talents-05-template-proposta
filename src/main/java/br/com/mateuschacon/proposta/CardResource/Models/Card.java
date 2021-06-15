package br.com.mateuschacon.proposta.CardResource.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

    @OneToOne(mappedBy = "card", cascade = CascadeType.MERGE, fetch = FetchType.LAZY )
    @JoinColumn(name = "block")
    private Block block;

    @OneToMany(mappedBy = "card", cascade = CascadeType.MERGE)
    private Set<Travel> travels;

    @Deprecated
    public Card(){}

    public Card(@NotBlank String id, @NotNull LocalDateTime issued, @NotNull BigDecimal limitCard) {
        this.id = id;
        this.issued = issued;
        this.limitCard = limitCard;
        this.biometrics = new HashSet<>();
        this.travels = new HashSet<>();
    }

    public boolean isNotBlocked(){
        if(this.block == null){
            return true;
        }
        return false;
    }

    public void addBiometry( Biometry biometry){
        this.biometrics.add(biometry);
    }
    public void addTravel( Travel travel){
        this.travels.add(travel);
    }
    public void addBlock(Block block){
        this.block = block;
    }
    public Set<Biometry> getBiometrics() {
        return this.biometrics;
    }
}
