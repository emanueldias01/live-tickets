package br.com.emanueldias.LiveTickets.service;

import br.com.emanueldias.LiveTickets.dto.EventoResponseDTO;
import br.com.emanueldias.LiveTickets.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Flux<EventoResponseDTO> getAllEventos() {
        return eventoRepository.findAll()
                .map(EventoResponseDTO::toDto);
    }
}
