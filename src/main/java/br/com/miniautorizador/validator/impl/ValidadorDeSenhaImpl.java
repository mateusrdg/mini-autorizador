package br.com.miniautorizador.validator.impl;

import br.com.miniautorizador.domain.Cartao;
import br.com.miniautorizador.domain.Transacao;
import br.com.miniautorizador.domain.enums.Errors;
import br.com.miniautorizador.exception.TransacaoException;
import br.com.miniautorizador.validator.Validador;

public class ValidadorDeSenhaImpl implements Validador {
    @Override
    public boolean validar(Cartao cartao, Transacao transacao) {
        return !cartao.getSenha().equals(transacao.getSenhaCartao());
    }

    @Override
    public void lancarExcecao() {
        throw new TransacaoException(Errors.SENHA_INVALIDA.getValue());
    }
}
