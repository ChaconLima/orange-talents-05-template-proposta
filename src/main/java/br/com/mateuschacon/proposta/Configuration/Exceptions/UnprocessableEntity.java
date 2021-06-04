package br.com.mateuschacon.proposta.Configuration.Exceptions;

import org.springframework.http.HttpStatus;

public class UnprocessableEntity extends RuntimeException {
    private final HttpStatus httpStatus;

    private final String reason;
    private final String field;

    public UnprocessableEntity(HttpStatus httpStatus, String reason,  String field) {
        super(reason);
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.field = field;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getReason() {
        return reason;
    }
    
    public String getField() {
        return field;
    }
}
