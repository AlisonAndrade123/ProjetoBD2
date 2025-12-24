package br.edu.ifpb.es.bd.dto;

import br.edu.ifpb.es.bd.model.Loja;

public class LojaResponseDTO {

    private Long id;
    private String nome;
    private double latitude;
    private double longitude;

    public LojaResponseDTO(Loja loja) {
        this.id = loja.getId();
        this.nome = loja.getNome();
        this.longitude = loja.getLocalizacao().getX();
        this.latitude = loja.getLocalizacao().getY();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
}