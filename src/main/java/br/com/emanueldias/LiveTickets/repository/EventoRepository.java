package br.com.emanueldias.LiveTickets.repository;

import br.com.emanueldias.LiveTickets.model.Evento;
import br.com.emanueldias.LiveTickets.model.TipoEvento;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface EventoRepository extends ReactiveCrudRepository<Evento, Long> {
    Flux<Evento> findByTipo(TipoEvento tipo);
}
