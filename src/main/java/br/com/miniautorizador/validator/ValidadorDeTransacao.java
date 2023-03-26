package br.com.miniautorizador.validator;

import br.com.miniautorizador.domain.Cartao;
import br.com.miniautorizador.domain.Transacao;
import br.com.miniautorizador.validator.impl.ValidadorDeSaldoImpl;
import br.com.miniautorizador.validator.impl.ValidadorDeSenhaImpl;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ValidadorDeTransacao {

    private List<Validador> validadores;

    public ValidadorDeTransacao() {
        this.validadores = Arrays.asList(new ValidadorDeSenhaImpl(), new ValidadorDeSaldoImpl());
    }

    public void validar(Cartao cartao, Transacao transacao){
       validadores.stream()
                .filter(validador -> validador.validar(cartao, transacao))
                .findFirst()
                .ifPresent(Validador::lancarExcecao);
    }
}
