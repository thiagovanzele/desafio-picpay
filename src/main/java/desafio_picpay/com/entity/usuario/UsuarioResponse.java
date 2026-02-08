package desafio_picpay.com.entity.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

    private Long usuarioId;
    private String nome;
    private String cpf;
    private String email;
    private String tipoUsuario;

    public static UsuarioResponse fromEntity(Usuario usuario) {
        return builder()
                .usuarioId(usuario.getId())
                .cpf(usuario.getCpf())
                .email(usuario.getEmail())
                .nome(usuario.getNome())
                .tipoUsuario(usuario.getTipoUsuario().getCodigo())
                .build();
    }
}
