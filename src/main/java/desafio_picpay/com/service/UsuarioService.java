package desafio_picpay.com.service;

import desafio_picpay.com.dto.usuario.UsuarioDTO;
import desafio_picpay.com.dto.usuario.UsuarioUpdate;
import desafio_picpay.com.entity.usuario.Usuario;
import desafio_picpay.com.dto.usuario.UsuarioResponse;
import desafio_picpay.com.exception.ArgumentoInvalidoException;
import desafio_picpay.com.exception.ObjetoNaoEncontrado;
import desafio_picpay.com.repository.UsuarioRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    public UsuarioResponse buscarUsuario(Long id) {
        Usuario usuario = buscarUsuarioPorId(id);
        return UsuarioResponse.fromEntity(usuario);
    }

    public List<UsuarioResponse> buscarTodosUsuarios() {
        return usuarioRepository.findAll().stream().filter(Usuario::isAtivo).map(UsuarioResponse::fromEntity).toList();
    }

    public void criarUsuario(UsuarioDTO usuarioDTO) {
        boolean usuarioExistente = usuarioRepository.findByCpf(usuarioDTO.cpf()).isPresent();

        if (usuarioExistente) {
            throw new ArgumentoInvalidoException("CPF já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setCpf(usuarioDTO.cpf());
        usuario.setEmail(usuarioDTO.email());
        usuario.setSenha(usuarioDTO.senha());
        usuario.setNome(usuarioDTO.nome());
        usuario.setAtivo(true);
        usuario.setSaldo(usuarioDTO.saldo());
        usuario.setTipoUsuario(usuarioDTO.tipoUsuario());

        usuarioRepository.save(usuario);
    }

    public void adicionarSaldo(Long usuarioId, BigDecimal valor) {
        Usuario usuario = buscarUsuarioPorId(usuarioId);
        usuario.setSaldo(usuario.getSaldo().add(valor));
        usuarioRepository.save(usuario);
    }

    public void atualizarUsuario(Long id, UsuarioUpdate usuarioUpdate) {
        Usuario usuario = buscarUsuarioPorId(id);

        if (StringUtils.hasText(usuarioUpdate.nome())) {
            usuario.setNome(usuarioUpdate.nome());
        }

        if (StringUtils.hasText(usuarioUpdate.email())) {
            usuario.setEmail(usuarioUpdate.email());
        }

        if (usuarioUpdate.tipoUsuario() != null) {
            usuario.setTipoUsuario(usuarioUpdate.tipoUsuario());
        }

        usuarioRepository.save(usuario);
    }

    public void excluirUsuario(Long id) {
        Usuario usuario = buscarUsuarioPorId(id);
        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontrado("Usuário não encontrado"));
    }
}
