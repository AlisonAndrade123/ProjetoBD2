package br.edu.ifpb.es.bd.repository;

import br.edu.ifpb.es.bd.model.ProdutoMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<ProdutoMongo, String> {
}