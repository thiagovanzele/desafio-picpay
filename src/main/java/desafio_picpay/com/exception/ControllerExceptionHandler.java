package desafio_picpay.com.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjetoNaoEncontrado.class)
    public ResponseEntity<StandardError> handlerObjetoNaoEncontrado(ObjetoNaoEncontrado ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = buildError(status, ex.getMessage(), request);

        return ResponseEntity
                .status(status)
                .body(error);
    }

    @ExceptionHandler(AcaoProibidaException.class)
    public ResponseEntity<StandardError> handlerAcaoProibidaException(AcaoProibidaException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        StandardError error = buildError(status, ex.getMessage(), request);

        return ResponseEntity
                .status(status)
                .body(error);
    }

    @ExceptionHandler(ArgumentoInvalidoException.class)
    public ResponseEntity<StandardError> handlerArgumentoInvalidoException(ArgumentoInvalidoException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = buildError(status, ex.getMessage(), request);

        return ResponseEntity
                .status(status)
                .body(error);
    }

    private StandardError buildError(HttpStatus status, String message, HttpServletRequest request) {
        return new StandardError(
                Instant.now(),
                status.value(),
                message,
                request.getRequestURI()
        );
    }

}
