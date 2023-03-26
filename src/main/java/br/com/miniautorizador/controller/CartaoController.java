package br.com.miniautorizador.controller;

import br.com.miniautorizador.domain.Cartao;
import br.com.miniautorizador.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<Cartao> criarCartao(@RequestBody Cartao cartao) {
        Cartao cartaoCriado = cartaoService.criarCartao(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartaoCriado);
    }

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<Double> obterSaldo(@PathVariable String numeroCartao) {
        Double saldo = cartaoService.obterSaldo(numeroCartao);
        return ResponseEntity.ok(saldo);
    }
}