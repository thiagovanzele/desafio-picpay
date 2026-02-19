package desafio_picpay.com.controller;

import desafio_picpay.com.dto.transacao.TransacaoDto;
import desafio_picpay.com.dto.transacao.TransacaoResponse;
import desafio_picpay.com.service.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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

    @GetMapping("/usuario/{usuarioId}/buscar")
    public ResponseEntity<List<TransacaoResponse>> buscarTransacoesPorUsuario(@PathVariable Long usuarioId) {
        List<TransacaoResponse> transacaoResponses = transacaoService.buscarTransacaoUsuarioId(usuarioId);

        return ResponseEntity.ok().body(transacaoResponses);
    }
}
