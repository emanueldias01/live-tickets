package br.com.emanueldias.LiveTickets.dto;

import br.com.emanueldias.LiveTickets.model.Ingresso;
import br.com.emanueldias.LiveTickets.model.TipoIngresso;

public record IngressoRequestDTO(
        Long eventoId,
        TipoIngresso tipo,
        Double valor,
        int total
) {
    //int total, Double valor, TipoIngresso tipo, Long eventoId
    public Ingresso toEntity() {
        return new Ingresso(this.total, this.valor, this.tipo, this.eventoId);
    }
}
