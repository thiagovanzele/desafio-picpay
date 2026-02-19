package desafio_picpay.com.service;

import desafio_picpay.com.dto.transacao.TransacaoDto;
import desafio_picpay.com.dto.transacao.TransacaoResponse;
import desafio_picpay.com.entity.transacao.Transacao;
import desafio_picpay.com.entity.usuario.Usuario;
import desafio_picpay.com.enums.TipoUsuario;
import desafio_picpay.com.exception.AcaoProibidaException;
import desafio_picpay.com.repository.TransacaoRepository;
import desafio_picpay.com.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class TransacaoService {

    private TransacaoRepository transacaoRepository;
    private UsuarioService usuarioService;
    private AutorizadorService autorizadorService;
    private UsuarioRepository usuarioRepository;

    public TransacaoService(TransacaoRepository transacaoRepository,
                            UsuarioService usuarioService,
                            AutorizadorService autorizadorService,
                            UsuarioRepository usuarioRepository) {
        this.transacaoRepository = transacaoRepository;
        this.usuarioService = usuarioService;
        this.autorizadorService = autorizadorService;
        this.usuarioRepository = usuarioRepository;
    }

    public List<TransacaoResponse> buscarTransacaoUsuarioId(Long idUsuario) {
        List<Transacao> transacoes = transacaoRepository.buscarTransacoesPorIdUsuario(idUsuario);

        if (transacoes.isEmpty()) {
            return Collections.emptyList();
        }

        return TransacaoResponse.fromEntities(transacoes);
    }

    public void criarTransacao(TransacaoDto transacaoDto) {
        Usuario remetente = usuarioService.buscarUsuarioPorId(transacaoDto.remetenteId());
        Usuario recebedor = usuarioService.buscarUsuarioPorId(transacaoDto.recebedorId());

        if (remetente.getTipoUsuario().equals(TipoUsuario.LOJISTA)) {
            throw new AcaoProibidaException("Usuário do tipo lojista não pode enviar transação");
        }

        if (remetente.equals(recebedor)) {
            throw new AcaoProibidaException("Não é possível enviar transação para você mesmo");
        }

        if (!autorizadorService.autorizarTransacao()) {
            throw new AcaoProibidaException("Transação não autorizada pelo serviço externo");
        }

        if (remetente.getSaldo().compareTo(transacaoDto.valor()) < 0) {
            throw new AcaoProibidaException("Remetente não possui saldo suficiente");
        }

        remetente.setSaldo(remetente.getSaldo().subtract(transacaoDto.valor()));
        recebedor.setSaldo(recebedor.getSaldo().add(transacaoDto.valor()));

        Transacao transacao = new Transacao();
        transacao.setRecebedor(recebedor);
        transacao.setRemetente(remetente);
        transacao.setDataHora(LocalDateTime.now());
        transacao.setValor(transacaoDto.valor());

        transacaoRepository.save(transacao);
    }
}
