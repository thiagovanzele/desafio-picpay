package desafio_picpay.com.repository;

import desafio_picpay.com.entity.transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    @Query("""
        SELECT t FROM Transacao t
        WHERE t.remetente.id = :usuarioId
        OR t.recebedor.id = :usuarioId
    """)
    public List<Transacao> buscarTransacoesPorIdUsuario(Long usuarioId);
}
