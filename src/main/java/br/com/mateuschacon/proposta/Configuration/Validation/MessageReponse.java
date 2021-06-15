package br.com.mateuschacon.proposta.Configuration.Validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.mateuschacon.proposta.Configuration.Validation.Exceptions.UnprocessableEntity;
@RestControllerAdvice
public class MessageReponse {
    
    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorsResponse handle(MethodArgumentNotValidException methodArgumentNotValidException){
        
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        List<ObjectError>globalErrors= methodArgumentNotValidException.getBindingResult().getGlobalErrors();

        return this.buildErrors(fieldErrors, globalErrors);
    }
    
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorValidator handleException(HttpMessageNotReadableException execption) {
        
        String param= (String) execption.getMessage().subSequence(execption.getMessage().lastIndexOf("[")+1,execption.getMessage().lastIndexOf("]"));
        String mensagem = "Formato Errado";

        return new ErrorValidator( param , mensagem);
    }

    
    @ExceptionHandler(UnprocessableEntity.class)
    public ResponseEntity<ErrorValidator> handleApiErroException(UnprocessableEntity apiErroException) {
        
        ErrorValidator error = new ErrorValidator( apiErroException.getField(), apiErroException.getReason());

        return ResponseEntity.status(apiErroException.getHttpStatus()).body(error);
    }

    private ErrorsResponse buildErrors( List<FieldError> fieldErrors, List<ObjectError> globalErrors){
        
        ErrorsResponse errorsResponse = new ErrorsResponse();

        fieldErrors.forEach(e->{
            String mensagem = this.messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrorValidator errorValidator = new ErrorValidator(e.getField() ,mensagem);
            errorsResponse.addErrorValidator(errorValidator);
        });

        globalErrors.forEach(e->{
            String mensagem = this.messageSource.getMessage(e, LocaleContextHolder.getLocale());
            errorsResponse.addGlobalErrors(mensagem);
        });

        return errorsResponse;
    }
}
