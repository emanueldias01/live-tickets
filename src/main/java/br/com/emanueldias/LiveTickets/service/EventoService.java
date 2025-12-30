package br.com.emanueldias.LiveTickets.service;

import br.com.emanueldias.LiveTickets.dto.EventoRequestDTO;
import br.com.emanueldias.LiveTickets.dto.EventoResponseDTO;
import br.com.emanueldias.LiveTickets.model.TipoEvento;
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

    public Mono<EventoResponseDTO> createEvento(EventoRequestDTO dto) {
        return eventoRepository.save(dto.toEntity())
                .map(EventoResponseDTO::toDto);
    }

    public Mono<EventoResponseDTO> updateEvento(Long id,EventoRequestDTO dto) {
        return eventoRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .flatMap(evento -> {
                    evento.setNome(dto.nome());
                    evento.setData(dto.data());
                    evento.setTipo(dto.tipoEvento());
                    evento.setDescricao(dto.descricao());
                    return eventoRepository.save(evento);
                })
                .map(EventoResponseDTO::toDto);
    }

    public Mono<Void> deleteEventoById(Long id) {
        return eventoRepository.findById(id)
                .flatMap(eventoRepository::delete);
    }

    public Flux<EventoResponseDTO> getByTipo(String tipo) {
        TipoEvento tipoEvento = TipoEvento.valueOf(tipo.toUpperCase());
        return eventoRepository.findByTipo(tipoEvento)
                .map(EventoResponseDTO::toDto);
    }
}
