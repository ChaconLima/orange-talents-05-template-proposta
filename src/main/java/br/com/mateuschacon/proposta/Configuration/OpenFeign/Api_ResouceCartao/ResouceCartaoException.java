package br.com.mateuschacon.proposta.Configuration.OpenFeign.Api_ResouceCartao;


import br.com.mateuschacon.proposta.CardResource.Dtos.BlockResponse;
import br.com.mateuschacon.proposta.CardResource.Dtos.DigitalWalletResponse;
import br.com.mateuschacon.proposta.CardResource.Dtos.TravelNoticeResponse;

public class ResouceCartaoException extends RuntimeException{
    
    private BlockResponse blockResponse = new BlockResponse();

    private TravelNoticeResponse travelNoticeResponse = new TravelNoticeResponse();

    private DigitalWalletResponse digitalWalletResponse = new DigitalWalletResponse();

    ResouceCartaoException(String result){
        this.blockResponse.setResultado(result);
    }

    ResouceCartaoException(TravelNoticeResponse travelNoticeResponse) {
        this.travelNoticeResponse = travelNoticeResponse;
    }

    ResouceCartaoException(DigitalWalletResponse digitalWalletResponse) {
        this.digitalWalletResponse = digitalWalletResponse;
    }

    public BlockResponse getBlockResponse() {
        return this.blockResponse;
    }
    public TravelNoticeResponse getTravelNoticeResponse() {
        return this.travelNoticeResponse;
    }
    public DigitalWalletResponse getDigitalWalletResponse() {
        return this.digitalWalletResponse;
    }
}

