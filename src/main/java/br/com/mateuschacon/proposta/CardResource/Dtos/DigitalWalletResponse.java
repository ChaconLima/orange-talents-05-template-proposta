package br.com.mateuschacon.proposta.CardResource.Dtos;

public class DigitalWalletResponse {
    
    private String resultado;

    private String id;
    
    /**
     * Sets for Jackson 
     * @return
     */
    public void setId(String id) {
        this.id = id;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    /**
     * Gets for Jackson 
     * @return
     */
    public String getId() {
        return this.id;
    }
    public String getResultado() {
        return this.resultado;
    }

}
