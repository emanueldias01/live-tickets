package br.com.emanueldias.LiveTickets.dto;


import br.com.emanueldias.LiveTickets.model.Evento;
import br.com.emanueldias.LiveTickets.model.TipoEvento;

import java.time.LocalDate;

public record EventoRequestDTO(
        TipoEvento tipoEvento,
        String nome,
        LocalDate data,
        String descricao
) {
    public Evento toEntity() {
        return new Evento(this.tipoEvento(), this.nome(), this.data(), this.descricao);
    }
}
