package desafio_picpay.com.dto.usuario;

import desafio_picpay.com.enums.TipoUsuario;

public record UsuarioUpdate(String nome, String email, TipoUsuario tipoUsuario) {
}
