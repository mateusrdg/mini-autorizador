package br.com.miniautorizador.service;

import br.com.miniautorizador.domain.Cartao;
import br.com.miniautorizador.exception.CartaoJaCadastradoException;
import br.com.miniautorizador.exception.CartaoNaoEncontradoException;
import br.com.miniautorizador.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository repository;

    public Cartao criarCartao(Cartao cartao) {
        validaInsercao(cartao);
        return repository.save(cartao);
    }

    private void validaInsercao(Cartao cartao) {
        repository.findByNumeroCartao(cartao.getNumeroCartao()).ifPresent(x -> {throw new CartaoJaCadastradoException(cartao);});
    }

    public Double obterSaldo(String numeroCartao) {
        return repository.findByNumeroCartao(numeroCartao)
                .map(Cartao::getSaldo)
                .orElseThrow(() -> new CartaoNaoEncontradoException("Cartão não encontrado."));
    }

}
