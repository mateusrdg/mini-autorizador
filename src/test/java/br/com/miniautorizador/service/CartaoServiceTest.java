package br.com.miniautorizador.service;

import br.com.miniautorizador.domain.Cartao;
import br.com.miniautorizador.exception.CartaoJaCadastradoException;
import br.com.miniautorizador.exception.CartaoNaoEncontradoException;
import br.com.miniautorizador.repository.CartaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CartaoServiceTest {

    @InjectMocks
    private CartaoService cartaoService;
    @Mock
    private CartaoRepository cartaoRepository;

    private Cartao cartao;

    @BeforeEach
    void setUp(){
        cartao = new Cartao(1L, "1234", "senha", 100.0);
    }

    @Test
    public void testCriarCartao() {
        Cartao novoCartao = new Cartao(1L,"5678", "senha", 200.0);
        Mockito.when(cartaoRepository.findByNumeroCartao("5678")).thenReturn(Optional.empty());
        Mockito.when(cartaoRepository.save(novoCartao)).thenReturn(novoCartao);

        Cartao cartaoCriado = cartaoService.criarCartao(novoCartao);

        assertEquals(novoCartao, cartaoCriado);
        Mockito.verify(cartaoRepository, Mockito.times(1)).findByNumeroCartao(novoCartao.getNumeroCartao());
        Mockito.verify(cartaoRepository, Mockito.times(1)).save(novoCartao);
    }

    @Test
    void testCriarCartaoJaCadastrado() {
        Mockito.when(cartaoRepository.findByNumeroCartao("1234")).thenReturn(Optional.of(cartao));
        assertThrows(CartaoJaCadastradoException.class, () -> cartaoService.criarCartao(cartao));
        Mockito.verify(cartaoRepository, Mockito.times(1)).findByNumeroCartao(cartao.getNumeroCartao());
    }

    @Test
    void testObterSaldo() {
        Mockito.when(cartaoRepository.findByNumeroCartao("1234")).thenReturn(Optional.of(cartao));
        Double saldo = cartaoService.obterSaldo("1234");

        assertEquals(100.0, saldo);
        Mockito.verify(cartaoRepository, Mockito.times(1)).findByNumeroCartao("1234");
    }

    @Test
    void testObterSaldoCartaoNaoEncontrado() {
        Mockito.when(cartaoRepository.findByNumeroCartao("5678")).thenReturn(Optional.empty());

        assertThrows(CartaoNaoEncontradoException.class, () -> cartaoService.obterSaldo("5678"));
        Mockito.verify(cartaoRepository, Mockito.times(1)).findByNumeroCartao("5678");
    }
}