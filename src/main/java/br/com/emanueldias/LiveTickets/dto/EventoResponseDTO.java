package br.com.emanueldias.LiveTickets.dto;

import br.com.emanueldias.LiveTickets.model.Evento;
import br.com.emanueldias.LiveTickets.model.TipoEvento;

import java.time.LocalDate;

public record EventoResponseDTO(
        Long id,
        TipoEvento tipoEvento,
        String nome,
        LocalDate data,
        String descricao
)
{
    public static EventoResponseDTO toDto(Evento evento) {
        return new EventoResponseDTO(
                evento.getId(),
                evento.getTipo(),
                evento.getNome(),
                evento.getData(),
                evento.getDescricao()
        );
    }
}
