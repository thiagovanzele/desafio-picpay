package desafio_picpay.com.controller;

import desafio_picpay.com.dto.transacao.TransacaoDto;
import desafio_picpay.com.service.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Void> criarTransacao(@RequestBody TransacaoDto transacaoDto) {
        transacaoService.criarTransacao(transacaoDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
