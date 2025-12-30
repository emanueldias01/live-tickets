package br.com.emanueldias.LiveTickets.service;

import br.com.emanueldias.LiveTickets.dto.EventoResponseDTO;
import br.com.emanueldias.LiveTickets.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Flux<EventoResponseDTO> getAllEventos() {
        return eventoRepository.findAll()
                .map(EventoResponseDTO::toDto);
    }

    public Mono<EventoResponseDTO> getEventoById(Long id) {
        return eventoRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(EventoResponseDTO::toDto);
    }
}
