package br.com.mateuschacon.proposta.CardResource.Models;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Biometry {
    
    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(32)")
    @NotBlank
    private String id;

    @NotNull
    private LocalDateTime creation;

    @NotBlank
    private String fingerprint;

    @ManyToOne @NotNull
    private Card card;

    @Deprecated
    public Biometry(){}

    public Biometry( @NotBlank String fingerprint, Card card) {

        this.id = UUID.randomUUID().toString().replace("-", "");
        this.creation = LocalDateTime.now();
        this.fingerprint =  fingerprint;
        this.card = card;
        
    }

    public String getId() {
        return this.id;
    }
}
