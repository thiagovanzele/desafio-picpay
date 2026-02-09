package desafio_picpay.com.entity.usuario;

import desafio_picpay.com.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private boolean ativo;
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;
}
