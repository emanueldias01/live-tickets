package br.com.emanueldias.LiveTickets.controller;

import br.com.emanueldias.LiveTickets.dto.IngressoRequestDTO;
import br.com.emanueldias.LiveTickets.dto.IngressoResponseDTO;
import br.com.emanueldias.LiveTickets.service.IngressoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.awt.*;

@RestController
@RequestMapping("/ingressos")
public class IngressoController {

    private final IngressoService ingressoService;
    private final Sinks.Many<IngressoResponseDTO> ingressosSink;

    public IngressoController(IngressoService ingressoService) {
        this.ingressoService = ingressoService;
        this.ingressosSink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<IngressoResponseDTO> getIngressosByEventoId(
            @PathVariable Long id
    )
    {
        return Flux.merge(ingressoService.getIngressosFromEventoId(id),
                ingressosSink.asFlux()
                        .filter(i -> i.eventoId().equals(id)));
    }

    @PostMapping
    public Mono<IngressoResponseDTO> createIngresso(
            @RequestBody IngressoRequestDTO dto
    )
    {
        return ingressoService.createIngresso(dto)
                .doOnSuccess(i -> ingressosSink.tryEmitNext(i));
    }

    @PostMapping("/sale/{id}")
    public Mono<IngressoResponseDTO> saleIngresso(
            @PathVariable Long id
    )
    {
        return ingressoService.saleIngresso(id)
                .doOnSuccess(i -> ingressosSink.tryEmitNext(i));
    }
}
