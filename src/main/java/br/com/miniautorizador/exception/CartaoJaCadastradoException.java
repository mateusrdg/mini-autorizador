package br.com.miniautorizador.exception;

import br.com.miniautorizador.domain.Cartao;

public class CartaoJaCadastradoException extends RuntimeException {

    private final Cartao cartao;
    public CartaoJaCadastradoException(Cartao cartao) {
        this.cartao = cartao;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
