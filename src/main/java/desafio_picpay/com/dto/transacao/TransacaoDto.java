package desafio_picpay.com.dto.transacao;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public record TransacaoDto(Long remetenteId, Long recebedorId, @DecimalMin(value = "0.0", inclusive = false) BigDecimal valor) {}
