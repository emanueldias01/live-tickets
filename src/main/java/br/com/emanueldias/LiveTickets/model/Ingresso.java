package br.com.emanueldias.LiveTickets.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("ingressos")
public class Ingresso {


    @Id
    private Long id;
    private Long eventoId;
    private TipoIngresso tipo;
    private Double valor;
    private int total;


    public Ingresso() {

    }

    public Ingresso(int total, Double valor, TipoIngresso tipo, Long eventoId) {
        this.total = total;
        this.valor = valor;
        this.tipo = tipo;
        this.eventoId = eventoId;
    }

    public Ingresso(Long id, Long eventoId, TipoIngresso tipo, Double valor, int total) {
        this.id = id;
        this.eventoId = eventoId;
        this.tipo = tipo;
        this.valor = valor;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

    public TipoIngresso getTipo() {
        return tipo;
    }

    public void setTipo(TipoIngresso tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
