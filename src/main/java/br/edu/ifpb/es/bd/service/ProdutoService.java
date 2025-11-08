package br.edu.ifpb.es.bd.service;

import br.edu.ifpb.es.bd.model.Produto;
import br.edu.ifpb.es.bd.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String PREFIXO = "produto:";

    public Produto buscarProduto(String id) {
        String chave = PREFIXO + id;

        Produto produtoCache = (Produto) redisTemplate.opsForValue().get(chave);
        if (produtoCache != null) {
            System.out.println("✅ Produto encontrado no Redis!");
            return produtoCache;
        }

        Optional<Produto> produtoBanco = produtoRepository.findById(id);
        if (produtoBanco.isEmpty()) {
            return null;
        }

        Produto produto = produtoBanco.get();

        redisTemplate.opsForValue().set(chave, produto, 10, TimeUnit.MINUTES);
        System.out.println("Produto salvo no Redis por 10 minutos.");

        return produto;
    }

    public Produto salvarProduto(Produto produto) {
        Produto salvo = produtoRepository.save(produto);

        // Remove do cache (caso já exista)
        String chave = PREFIXO + salvo.getId();
        redisTemplate.delete(chave);
        System.out.println("🗑️ Cache limpo após atualização de produto.");

        return salvo;
    }

}

