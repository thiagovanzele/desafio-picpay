package desafio_picpay.com.dto;

import desafio_picpay.com.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        String cpf,

        @NotBlank
        @Email

        String email,
        String senha,
        TipoUsuario tipoUsuario) {}
