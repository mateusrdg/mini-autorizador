package br.com.miniautorizador.validator;

import br.com.miniautorizador.domain.Cartao;
import br.com.miniautorizador.domain.Transacao;

public interface Validador {

    boolean validar(Cartao cartao, Transacao transacao);

    void lancarExcecao();
}
