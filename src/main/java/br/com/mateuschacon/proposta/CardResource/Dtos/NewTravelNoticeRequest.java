package br.com.mateuschacon.proposta.CardResource.Dtos;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.mateuschacon.proposta.CardResource.Models.Card;
import br.com.mateuschacon.proposta.CardResource.Models.Travel;

public class NewTravelNoticeRequest {
    
    /**
     *
     */
    private static final String DD_MM_YYYY = "dd/MM/yyyy";

    @NotBlank
    private String destination;

    @NotNull @Future @JsonFormat(pattern = NewTravelNoticeRequest.DD_MM_YYYY, shape = Shape.STRING)
    private LocalDate returnDate;

    public void setDestination(@NotBlank String destination) {
        this.destination = destination;
    }
    public void setReturnDate( @NotNull LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Travel toModel(Card card, HttpServletRequest request){

        String userAgent = request.getHeader("User-Agent");
        String ip = request.getRemoteAddr();

        return new Travel(this.destination, this.returnDate, ip, userAgent, card);
    }

}
