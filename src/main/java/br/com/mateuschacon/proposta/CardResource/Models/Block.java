package br.com.mateuschacon.proposta.CardResource.Models;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.mateuschacon.proposta.CardResource.Dtos.BlockResponse;
import br.com.mateuschacon.proposta.CardResource.Models.Enums.StatusBlockEnum;

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

    @Enumerated(EnumType.STRING) @NotNull
    private StatusBlockEnum statusBlockEnum;
    
    public Block( @NotBlank String ip, @NotBlank String userAgent,
            Card card, @NotNull BlockResponse blockResponse ) {
        
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.issued  = LocalDateTime.now();
        this.ip = ip;
        this.userAgent = userAgent;
        this.card = card;
        this.statusBlockEnum = StatusBlockEnum.valueOf(blockResponse.getResultado().toUpperCase());
    }

    @Deprecated
    public Block(){}

    public StatusBlockEnum getStatusBlockEnum() {
        return this.statusBlockEnum;
    }
}
