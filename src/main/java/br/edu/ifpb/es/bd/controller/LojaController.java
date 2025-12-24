package br.edu.ifpb.es.bd.controller;

import br.edu.ifpb.es.bd.model.Loja;
import br.edu.ifpb.es.bd.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lojas")
public class LojaController {

    @Autowired
    private LojaService lojaService;

    public record LojaRequest(String nome, double latitude, double longitude) {}

    public record LojaResponse(Long id, String nome, double latitude, double longitude) {
        public LojaResponse(Loja loja) {
            this(
                    loja.getId(),
                    loja.getNome(),
                    loja.getLocalizacao().getY(),
                    loja.getLocalizacao().getX()
            );
        }
    }


    @PostMapping
    public ResponseEntity<LojaResponse> criarLoja(@RequestBody LojaRequest request) {
        Loja lojaSalva = lojaService.criarLoja(request.nome(), request.latitude(), request.longitude());
        return ResponseEntity.ok(new LojaResponse(lojaSalva));
    }

    @GetMapping("/proximas")
    public ResponseEntity<List<LojaResponse>> buscarLojasProximas(
                                                                   @RequestParam double lat,
                                                                   @RequestParam double lon,
                                                                   @RequestParam double distancia) {

        List<Loja> lojasEncontradas = lojaService.encontrarLojasProximas(lat, lon, distancia);

        List<LojaResponse> resposta = lojasEncontradas.stream()
                .map(LojaResponse::new)
                .toList();

        return ResponseEntity.ok(resposta);
    }
}