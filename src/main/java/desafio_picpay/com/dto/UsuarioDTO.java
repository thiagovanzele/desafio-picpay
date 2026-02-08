package desafio_picpay.com.dto;

import desafio_picpay.com.enums.TipoUsuario;
import jakarta.validation.constraints.*;

public record UsuarioDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @Pattern(regexp = "^\\d{11}$", message = "CPF deve ter 11 caracteres numéricos")
        String cpf,

        @NotBlank
        @Email

        String email,
        String senha,
        TipoUsuario tipoUsuario) {}
