package desafio_picpay.com.enums;

import lombok.Getter;

@Getter
public enum TipoUsuario {

    LOJISTA("Lojista"),
    COMUM("Comum");

    private final String codigo;

    TipoUsuario(String codigo) {
        this.codigo = codigo;
    }

}
