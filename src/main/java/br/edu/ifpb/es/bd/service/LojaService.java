package br.edu.ifpb.es.bd.service;

import br.edu.ifpb.es.bd.model.Loja;
import br.edu.ifpb.es.bd.repository.LojaRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LojaService {

    @Autowired
    private LojaRepository lojaRepository;

    private final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    public Loja criarLoja(String nome, double latitude, double longitude) {
        Loja novaLoja = new Loja();
        novaLoja.setNome(nome);

        Point localizacao = geometryFactory.createPoint(new Coordinate(longitude, latitude));
        novaLoja.setLocalizacao(localizacao);

        return lojaRepository.save(novaLoja);
    }

    public List<Loja> encontrarLojasProximas(double latitude, double longitude, double distanciaEmMetros) {
        Point pontoCentral = geometryFactory.createPoint(new Coordinate(longitude, latitude));

        return lojaRepository.findLojasProximas(pontoCentral, distanciaEmMetros);
    }
}