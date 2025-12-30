package br.com.emanueldias.LiveTickets.controller;

import br.com.emanueldias.LiveTickets.dto.EventoRequestDTO;
import br.com.emanueldias.LiveTickets.dto.EventoResponseDTO;
import br.com.emanueldias.LiveTickets.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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
        return eventoService.getByTipo(tipo);
    }

    @PostMapping
    public Mono<EventoResponseDTO> createEvento(
            @RequestBody EventoRequestDTO dto
    )
    {
        return eventoService.createEvento(dto);
    }

    @PutMapping("/{id}")
    public Mono<EventoResponseDTO> updateEvento(
            @PathVariable Long id,
            @RequestBody EventoRequestDTO dto
    )
    {
        return eventoService.updateEvento(id, dto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteEventoById(@PathVariable Long id) {
        return eventoService.deleteEventoById(id);
    }
}
