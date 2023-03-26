package br.com.miniautorizador.controller;

import br.com.miniautorizador.domain.Cartao;
import br.com.miniautorizador.service.CartaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CartaoControllerTest {
    @InjectMocks
    private CartaoController controller;

    @Mock
    private CartaoService service;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void criaCartaoTeste() throws Exception {
        Cartao cartao = new Cartao();
        cartao.setNumeroCartao("123456789");

        when(service.criarCartao(any(Cartao.class))).thenReturn(cartao);

        mockMvc.perform(MockMvcRequestBuilders.post("/cartoes")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(cartao)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numeroCartao").value(cartao.getNumeroCartao()));
    }

    @Test
    public void obterSaldoTeste() throws Exception {
        Cartao cartao = new Cartao();

        when(service.obterSaldo(anyString())).thenReturn(500.00);

        mockMvc.perform(MockMvcRequestBuilders.get("/cartoes/123456789")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(cartao)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(500.00));
    }


}