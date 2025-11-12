package br.edu.ifpb.es.bd.controller;

import br.edu.ifpb.es.bd.model.Comentario;
import br.edu.ifpb.es.bd.model.ProdutoMongo;
import br.edu.ifpb.es.bd.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoMongo> criarProduto(@RequestBody ProdutoMongo produto) {
        ProdutoMongo salvo = produtoService.salvarProduto(produto);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoMongo> buscarPorId(@PathVariable String id) {
        ProdutoMongo produto = produtoService.buscarProduto(id);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoMongo>> listarTodosOsProdutos() {
        List<ProdutoMongo> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoMongo> atualizarProduto(@PathVariable String id, @RequestBody ProdutoMongo produto) {
        ProdutoMongo produtoAtualizado = produtoService.atualizarProduto(id, produto);
        if (produtoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable String id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/comentarios")
    public ResponseEntity<ProdutoMongo> adicionarComentario(
            @PathVariable String id,
            @RequestBody Comentario comentario) {

        ProdutoMongo produtoAtualizado = produtoService.adicionarComentario(id, comentario);

        if (produtoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produtoAtualizado);
    }

    @PostMapping(value = "/{id}/imagem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProdutoMongo> uploadImagem(
            @PathVariable String id,
            @RequestParam("arquivo") MultipartFile arquivo) {

        ProdutoMongo produtoAtualizado = produtoService.salvarImagem(id, arquivo);

        if (produtoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produtoAtualizado);
    }
}