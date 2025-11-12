// Código ATUALIZADO para o arquivo: ProdutoMongo.java
package br.edu.ifpb.es.bd.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "produtos")
public class ProdutoMongo {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidade;
    private List<Comentario> comentarios = new ArrayList<>();

    // <<< NOVO CAMPO PARA A URL DA IMAGEM DO MINIO >>>
    private String imageUrl;

    // --- Getters e Setters ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    // <<< GETTER E SETTER PARA O NOVO CAMPO >>>
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}