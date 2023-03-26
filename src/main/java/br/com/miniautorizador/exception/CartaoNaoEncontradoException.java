package br.com.miniautorizador.exception;

public class CartaoNaoEncontradoException extends RuntimeException{
    public CartaoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
