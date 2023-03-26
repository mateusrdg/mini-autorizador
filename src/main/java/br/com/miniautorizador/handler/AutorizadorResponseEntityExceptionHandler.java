package br.com.miniautorizador.handler;

import br.com.miniautorizador.domain.Cartao;
import br.com.miniautorizador.exception.CartaoJaCadastradoException;
import br.com.miniautorizador.exception.CartaoNaoEncontradoException;
import br.com.miniautorizador.exception.TransacaoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AutorizadorResponseEntityExceptionHandler {

    @ExceptionHandler(CartaoJaCadastradoException.class)
    public ResponseEntity<Cartao> handleCartaoJaCadastradoException(CartaoJaCadastradoException ex) {
         return ResponseEntity.unprocessableEntity().body(ex.getCartao());
    }

    @ExceptionHandler(CartaoNaoEncontradoException.class)
    public ResponseEntity<Void> handleCartaoNaoEncontradoException(CartaoNaoEncontradoException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(TransacaoException.class)
    public ResponseEntity<String> handleResponseStatusException(TransacaoException ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }
}
