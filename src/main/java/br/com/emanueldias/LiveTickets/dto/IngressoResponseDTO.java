package br.com.emanueldias.LiveTickets.dto;

import br.com.emanueldias.LiveTickets.model.Ingresso;
import br.com.emanueldias.LiveTickets.model.TipoIngresso;

public record IngressoResponseDTO(
         Long id,
         Long eventoId,
         TipoIngresso tipo,
         Double valor,
         int total
) {

    public static IngressoResponseDTO toDto(Ingresso ingresso) {
        return new IngressoResponseDTO(ingresso.getId(), ingresso.getEventoId(), ingresso.getTipo(), ingresso.getValor(), ingresso.getTotal());
    }
}
