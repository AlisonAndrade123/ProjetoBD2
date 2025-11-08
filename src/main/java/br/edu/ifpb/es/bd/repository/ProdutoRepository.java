package br.edu.ifpb.es.bd.repository;

import br.edu.ifpb.es.bd.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import br.edu.ifpb.es.bd.mongo.AvaliacaoProduto;
import br.edu.ifpb.es.bd.mongo.AvaliacaoRepository;


@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {
}

