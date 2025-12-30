package br.com.emanueldias.LiveTickets.repository;

import br.com.emanueldias.LiveTickets.model.Evento;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EventoRepository extends ReactiveCrudRepository<Evento, Long> {
}
