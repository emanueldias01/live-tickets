package br.com.emanueldias.LiveTickets.repository;

import br.com.emanueldias.LiveTickets.model.Ingresso;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IngressoRepository extends ReactiveCrudRepository<Ingresso, Long> {
    Flux<Ingresso> findByEventoId(Long id);

    @Query("""
    UPDATE Ingresso i
    SET i.total = i.total - 1
    WHERE i.id = :id AND i.total > 0
    """)
    Mono<Integer> decrementIfAvailable(Long id);
}
