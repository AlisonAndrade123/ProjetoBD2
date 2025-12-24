package br.edu.ifpb.es.bd.repository;

import br.edu.ifpb.es.bd.model.Loja;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {

    /**
     * Encontra todas as lojas dentro de uma distância específica de um ponto geográfico.
     *
     * @param ponto Ponto de referência (ex: localização do usuário).
     * @param distancia Distância em metros.
     * @return Uma lista de lojas que estão dentro do raio especificado.
     */
    @Query(value = "SELECT * FROM lojas l WHERE ST_DWithin(l.localizacao, :ponto, :distancia)", nativeQuery = true)
    List<Loja> findLojasProximas(@Param("ponto") Point ponto, @Param("distancia") double distancia);
}