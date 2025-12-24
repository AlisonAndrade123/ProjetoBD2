package br.edu.ifpb.es.bd.model;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Point; // Este é o tipo de dado para localização

@Entity
@Table(name = "lojas")
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    // Coluna para armazenar a localização geográfica.
    // O tipo 'Point' vem da biblioteca hibernate-spatial.
    @Column(columnDefinition = "geography(Point, 4326)")
    private Point localizacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Point getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Point localizacao) {
        this.localizacao = localizacao;
    }
}