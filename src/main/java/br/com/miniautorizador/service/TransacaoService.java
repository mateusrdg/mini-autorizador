package br.com.miniautorizador.service;

import br.com.miniautorizador.domain.Cartao;
import br.com.miniautorizador.domain.Transacao;
import br.com.miniautorizador.domain.enums.Errors;
import br.com.miniautorizador.exception.TransacaoException;
import br.com.miniautorizador.repository.CartaoRepository;
import br.com.miniautorizador.repository.TransacaoRepository;
import br.com.miniautorizador.validator.ValidadorDeTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ValidadorDeTransacao validadorDeTransacao;

    public void realizarTransacao(Transacao transacao) {
        Cartao cartao = buscaCartao(transacao.getNumeroCartao());

        validadorDeTransacao.validar(cartao, transacao);

        atualizaSaldo(cartao, transacao);

        cartaoRepository.save(cartao);

        transacaoRepository.save(transacao);
    }

    private void atualizaSaldo(Cartao cartao, Transacao transacao) {
        cartao.setSaldo(cartao.getSaldo() - transacao.getValor());
    }

    public Cartao buscaCartao (String numeroCartao){
        return cartaoRepository.findByNumeroCartao(numeroCartao)
                .orElseThrow(() -> new TransacaoException(Errors.CARTAO_INEXISTENTE.getValue()));
    }
}
