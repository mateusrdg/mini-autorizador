package br.com.miniautorizador.validator.impl;

import br.com.miniautorizador.domain.Cartao;
import br.com.miniautorizador.domain.Transacao;
import br.com.miniautorizador.domain.enums.Errors;
import br.com.miniautorizador.exception.TransacaoException;
import br.com.miniautorizador.validator.Validador;

public class ValidadorDeSaldoImpl implements Validador {
    @Override
    public boolean validar(Cartao cartao, Transacao transacao) {
        double novoSaldo = cartao.getSaldo() - transacao.getValor();
        return novoSaldo < 0;
    }

    @Override
    public void lancarExcecao() {
        throw new TransacaoException(Errors.SALDO_INSUFICIENTE.getValue());
    }
}
