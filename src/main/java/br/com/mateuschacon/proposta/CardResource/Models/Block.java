package br.com.mateuschacon.proposta.CardResource.Models;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Block {
    
    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(32)")
    @NotBlank
    private String id;

    @NotNull
    private LocalDateTime issued;

    @NotBlank
    private String ip;

    @NotBlank
    private String userAgent;

    @OneToOne
    private Card card;
    
    public Block( @NotBlank String ip, @NotBlank String userAgent,
            Card card) {
        
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.issued  = LocalDateTime.now();
        this.ip = ip;
        this.userAgent = userAgent;
        this.card = card;
    }

    @Deprecated
    public Block(){}
}
