package br.com.miniautorizador.exception;

public class TransacaoException extends RuntimeException{
    public TransacaoException(String mensagem) {
        super(mensagem);
    }
}
