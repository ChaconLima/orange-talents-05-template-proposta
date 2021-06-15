package br.com.mateuschacon.proposta.CardResource.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Travel {
    
    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(32)")
    @NotBlank
    private String id;

    @NotBlank
    private String destination;

    @NotNull
    private LocalDate returnDate;

    @NotNull
    private LocalDateTime issued;

    @NotBlank
    private String ip;

    @NotBlank
    private String userAgent;

    @NotNull @ManyToOne
    private Card card;

    @Deprecated
    public Travel(){}

    public Travel( 
        @NotBlank String destination, @NotNull LocalDate returnDate,
        @NotBlank String ip, @NotBlank String userAgent, @NotNull Card card) 
    {
        
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.issued = LocalDateTime.now();

        this.destination = destination;
        this.returnDate = returnDate;
        this.ip = ip;
        this.userAgent = userAgent;
        this.card = card;
    }

}
