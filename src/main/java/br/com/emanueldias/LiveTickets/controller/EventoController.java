package br.com.emanueldias.LiveTickets.controller;

import br.com.emanueldias.LiveTickets.dto.EventoRequestDTO;
import br.com.emanueldias.LiveTickets.dto.EventoResponseDTO;
import br.com.emanueldias.LiveTickets.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.awt.*;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;
    private final Sinks.Many<EventoResponseDTO> eventosSink;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
        this.eventosSink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventoResponseDTO> getAllEventos() {
        return Flux.merge(eventoService.getAllEventos(), eventosSink.asFlux());
    }

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<EventoResponseDTO> getEventoById(
            @PathVariable Long id
    )
    {
        return eventoService.getEventoById(id);
    }

    @GetMapping(value = "/tipo/{tipo}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventoResponseDTO> getEventoByTipo(
            @PathVariable String tipo
    )
    {
        return Flux.merge(eventoService.getByTipo(tipo), eventosSink.asFlux());
    }

    @PostMapping
    public Mono<EventoResponseDTO> createEvento(
            @RequestBody EventoRequestDTO dto
    )
    {
        return eventoService.createEvento(dto)
                .doOnSuccess(e -> eventosSink.tryEmitNext(e));
    }

    @PutMapping("/{id}")
    public Mono<EventoResponseDTO> updateEvento(
            @PathVariable Long id,
            @RequestBody EventoRequestDTO dto
    )
    {
        return eventoService.updateEvento(id, dto)
                .doOnSuccess(e -> eventosSink.tryEmitNext(e));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteEventoById(@PathVariable Long id) {
        return eventoService.deleteEventoById(id);
    }
}
