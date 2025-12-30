package br.com.emanueldias.LiveTickets.controller;

import br.com.emanueldias.LiveTickets.dto.EventoResponseDTO;
import br.com.emanueldias.LiveTickets.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventoResponseDTO> getAllEventos() {
        return eventoService.getAllEventos();
    }

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<EventoResponseDTO> getEventoById(@PathVariable Long id) {
        return eventoService.getEventoById(id);
    }
}
