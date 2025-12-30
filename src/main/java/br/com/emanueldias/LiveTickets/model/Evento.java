package br.com.emanueldias.LiveTickets.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("eventos")
public class Evento {

    @Id
    private Long id;
    private TipoEvento tipo;
    private String nome;
    private LocalDate data;
    private String descricao;

    public Evento() {
    }

    public Evento(TipoEvento tipo, String nome, LocalDate data, String descricao) {
        this.tipo = tipo;
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
    }

    public Evento(Long id, TipoEvento tipo, String nome, LocalDate data, String descricao) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }
}
