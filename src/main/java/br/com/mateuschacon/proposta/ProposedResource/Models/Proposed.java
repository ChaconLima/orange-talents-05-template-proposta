package br.com.mateuschacon.proposta.ProposedResource.Models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Proposed {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;

    @NotBlank
    private String document;

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull
    private BigDecimal wage;

    @Deprecated
    Proposed(){}

    public Proposed(@NotBlank String document, @NotBlank String email, @NotBlank String name, @NotBlank String address,
            @NotNull BigDecimal wage) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.wage = wage;
    }
    public String getId() {
        return this.id;
    }
}
