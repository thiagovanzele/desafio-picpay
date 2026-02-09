package desafio_picpay.com.repository;

import desafio_picpay.com.entity.transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
