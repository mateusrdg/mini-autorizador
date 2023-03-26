package br.com.miniautorizador.controller;

import br.com.miniautorizador.domain.Transacao;
import br.com.miniautorizador.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<String> realizarTransacao(@RequestBody Transacao transacao) {
        transacaoService.realizarTransacao(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }
}