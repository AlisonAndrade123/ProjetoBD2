package br.edu.ifpb.es.bd.service;

import br.edu.ifpb.es.bd.model.Comentario;
import br.edu.ifpb.es.bd.model.ProdutoMongo;
import br.edu.ifpb.es.bd.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private MinioService minioService;

    private static final String PREFIXO = "produto:";

    public ProdutoMongo buscarProduto(String id) {
        String chave = PREFIXO + id;

        ProdutoMongo produtoCache = (ProdutoMongo) redisTemplate.opsForValue().get(chave);
        if (produtoCache != null) {
            System.out.println("✅ Produto encontrado no Redis!");
            return produtoCache;
        }

        Optional<ProdutoMongo> produtoBanco = produtoRepository.findById(id);
        if (produtoBanco.isEmpty()) {
            return null;
        }

        ProdutoMongo produto = produtoBanco.get();

        redisTemplate.opsForValue().set(chave, produto, 10, TimeUnit.MINUTES);
        System.out.println("Produto salvo no Redis por 10 minutos.");

        return produto;
    }

    public ProdutoMongo salvarProduto(ProdutoMongo produto) {
        ProdutoMongo salvo = produtoRepository.save(produto);

        String chave = PREFIXO + salvo.getId();
        redisTemplate.delete(chave);
        System.out.println("🗑️ Cache limpo para o produto: " + salvo.getId());

        return salvo;
    }

    public List<ProdutoMongo> listarTodos() {
        return produtoRepository.findAll();
    }

    public ProdutoMongo atualizarProduto(String id, ProdutoMongo produtoAtualizado) {
        Optional<ProdutoMongo> produtoExistenteOpt = produtoRepository.findById(id);

        if (produtoExistenteOpt.isPresent()) {
            ProdutoMongo produtoExistente = produtoExistenteOpt.get();
            produtoExistente.setNome(produtoAtualizado.getNome());
            produtoExistente.setDescricao(produtoAtualizado.getDescricao());
            produtoExistente.setPreco(produtoAtualizado.getPreco());
            produtoExistente.setQuantidade(produtoAtualizado.getQuantidade());

            ProdutoMongo salvo = produtoRepository.save(produtoExistente);

            String chave = PREFIXO + salvo.getId();
            redisTemplate.delete(chave);
            System.out.println("🗑️ Cache limpo para o produto atualizado: " + salvo.getId());

            return salvo;
        }
        return null;
    }

    public void deletarProduto(String id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);

            String chave = PREFIXO + id;
            redisTemplate.delete(chave);
            System.out.println("🗑️ Produto deletado e cache limpo para o ID: " + id);
        }
    }

    public ProdutoMongo adicionarComentario(String produtoId, Comentario comentario) {
        Optional<ProdutoMongo> produtoOpt = produtoRepository.findById(produtoId);

        if (produtoOpt.isPresent()) {
            ProdutoMongo produto = produtoOpt.get();

            produto.getComentarios().add(comentario);

            ProdutoMongo produtoSalvo = produtoRepository.save(produto);

            String chave = PREFIXO + produtoId;
            redisTemplate.delete(chave);
            System.out.println("🗑️ Cache limpo para o produto (novo comentário): " + produtoId);

            return produtoSalvo;
        }

        return null;
    }

    public ProdutoMongo salvarImagem(String id, MultipartFile arquivo) {
        Optional<ProdutoMongo> produtoOpt = produtoRepository.findById(id);

        if (produtoOpt.isEmpty()) {
            return null;
        }

        String imageUrl = minioService.uploadFile(arquivo);

        ProdutoMongo produto = produtoOpt.get();
        produto.setImageUrl(imageUrl);

        ProdutoMongo produtoSalvo = produtoRepository.save(produto);

        String chave = PREFIXO + id;
        redisTemplate.delete(chave);
        System.out.println("🗑️ Cache limpo para o produto (nova imagem): " + id);

        return produtoSalvo;
    }
}