package br.com.mateuschacon.proposta.CardResource.Dtos;

public class TravelNoticeResponse {
    
 
    private String resultado;

    /**
     * Sets for Jackson 
     * @return
     */
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    /**
     * Gets
     * @return
     */

    public String getResultado() {
        return this.resultado;
    }
}
