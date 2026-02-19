package desafio_picpay.com.dto.transacao;

import desafio_picpay.com.entity.transacao.Transacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoResponse {

    private LocalDate data;
    private String nomeRemetente;
    private String nomeRecebedor;
    private BigDecimal valor;

    public static TransacaoResponse fromEntity(Transacao transacao) {
        return builder()
                .data(transacao.getDataHora().toLocalDate())
                .nomeRecebedor(transacao.getRecebedor().getNome())
                .nomeRemetente(transacao.getRemetente().getNome())
                .valor(transacao.getValor())
                .build();
    }

    public static List<TransacaoResponse> fromEntities(List<Transacao> transacoes) {
        return transacoes.stream().map(TransacaoResponse::fromEntity).toList();
    }
}
