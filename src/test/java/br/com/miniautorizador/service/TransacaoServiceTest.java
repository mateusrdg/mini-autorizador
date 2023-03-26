package br.com.miniautorizador.service;

import br.com.miniautorizador.domain.Cartao;
import br.com.miniautorizador.domain.Transacao;
import br.com.miniautorizador.exception.TransacaoException;
import br.com.miniautorizador.repository.CartaoRepository;
import br.com.miniautorizador.repository.TransacaoRepository;
import br.com.miniautorizador.validator.ValidadorDeTransacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.miniautorizador.domain.enums.Errors.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {
    @InjectMocks
    private TransacaoService transacaoService;

    @Mock
    private CartaoRepository cartaoRepository;

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private ValidadorDeTransacao validadorDeTransacao;

    private Cartao cartao;

    @BeforeEach
    void setUp() throws Exception {
        cartao = new Cartao(1L,"1234", "senha", 100.0);
    }

    @Test
    public void testRealizarTransacao() {
        Transacao transacao = new Transacao(1L, "1234", "senha", 50.0);
        mockBuscaCartao();

        transacaoService.realizarTransacao(transacao);

        assertEquals(50.0, cartao.getSaldo(), 0.001);
        Mockito.verify(cartaoRepository, Mockito.times(1)).save(cartao);
        Mockito.verify(transacaoRepository, Mockito.times(1)).save(transacao);
    }

    @Test
    public void testRealizarTransacaoComSenhaInvalida() {
        Transacao transacao = new Transacao(1L, "1234", "senha_errada", 50.0);
        mockBuscaCartao();

        try {
            transacaoService.realizarTransacao(transacao);
            fail();
        } catch (TransacaoException exception) {
            assertEquals(SENHA_INVALIDA.getValue(), exception.getMessage());
        }
    }

    @Test
    public void testRealizarTransacaoComSaldoInsuficiente() {
        Transacao transacao = new Transacao(1L, "1234", "senha", 150.0);
        mockBuscaCartao();

        try {
            transacaoService.realizarTransacao(transacao);
            fail();
        } catch (TransacaoException exception) {
            assertEquals(SALDO_INSUFICIENTE.getValue(), exception.getMessage());
        }

    }

    @Test
    public void testBuscaCartao() {
        mockBuscaCartao();

        Cartao cartaoEncontrado = transacaoService.buscaCartao("1234");
        assertEquals(cartao, cartaoEncontrado);
    }

    @Test
    public void testBuscaCartaoInexistente() {
        Mockito.when(cartaoRepository.findByNumeroCartao("5678")).thenReturn(Optional.empty());

        try {
            transacaoService.buscaCartao("5678");
            fail();
        } catch (TransacaoException exception) {
            assertEquals(CARTAO_INEXISTENTE.getValue(), exception.getMessage());
        }
    }
    private void mockBuscaCartao(){
        Mockito.when(cartaoRepository.findByNumeroCartao("1234")).thenReturn(Optional.of(cartao));
    }
}