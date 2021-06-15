package br.com.mateuschacon.proposta.CardResource.Dtos;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.mateuschacon.proposta.CardResource.Models.Card;
import br.com.mateuschacon.proposta.CardResource.Models.Travel;

public class NewTravelNoticeRequest {
    
    /**
     *
     */
    private static final String DD_MM_YYYY = "yyyy-MM-dd";

    @NotBlank @JsonProperty
    private String destino;

    @NotNull @Future @JsonFormat(pattern = NewTravelNoticeRequest.DD_MM_YYYY, shape = Shape.STRING) @JsonProperty
    private LocalDate validoAte;

    public void setDestination(@NotBlank String destination) {
        this.destino = destination;
    }
    public void setReturnDate( @NotNull LocalDate returnDate) {
        this.validoAte = returnDate;
    }

    public Travel toModel(Card card, HttpServletRequest request){

        String userAgent = request.getHeader("User-Agent");
        String ip = request.getRemoteAddr();

        return new Travel(this.destino, this.validoAte, ip, userAgent, card);
    }

}
