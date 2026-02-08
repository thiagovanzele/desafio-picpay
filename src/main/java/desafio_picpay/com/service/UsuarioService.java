package desafio_picpay.com.service;

import desafio_picpay.com.dto.UsuarioDTO;
import desafio_picpay.com.dto.UsuarioUpdate;
import desafio_picpay.com.entity.Usuario;
import desafio_picpay.com.enums.TipoUsuario;
import desafio_picpay.com.exception.ArgumentoInvalidoException;
import desafio_picpay.com.exception.ArgumentoObrigatorioException;
import desafio_picpay.com.repository.UsuarioRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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
        usuario.setTipoUsuario(usuarioDTO.tipoUsuario());

        usuarioRepository.save(usuario);
    }

    public void atualizarUsuario(Long id, UsuarioUpdate usuarioUpdate) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(Usuario.class, "Usuário não encontrado"));

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
}
