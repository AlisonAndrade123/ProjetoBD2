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

    private static final String CACHE_PREFIX = "produto:";

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Produto salvarProduto(Produto produto) {
        Produto salvo = produtoRepository.save(produto);
        redisTemplate.opsForValue().set(CACHE_PREFIX + salvo.getId(), salvo, 10, TimeUnit.MINUTES);
        return salvo;
    }

    public Optional<Produto> buscarPorId(String id) {
        String key = CACHE_PREFIX + id;
        Produto produtoCacheado = (Produto) redisTemplate.opsForValue().get(key);

        if (produtoCacheado != null) {
            System.out.println("🔹 Produto encontrado no cache Redis");
            return Optional.of(produtoCacheado);
        }

        System.out.println("🔸 Produto não encontrado no cache, buscando no MongoDB...");
        Optional<Produto> produtoBanco = produtoRepository.findById(id);
        produtoBanco.ifPresent(p -> redisTemplate.opsForValue().set(key, p, 10, TimeUnit.MINUTES));
        return produtoBanco;
    }
}
