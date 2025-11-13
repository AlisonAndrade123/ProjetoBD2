CREATE OR REPLACE FUNCTION calcular_valor_total_estoque_por_categoria(id_categoria BIGINT)
RETURNS NUMERIC AS $$
DECLARE
    valor_total NUMERIC := 0;
BEGIN
    -- Calcula a soma de (preço * quantidade) para todos os produtos
    -- que pertencem à categoria informada.
    SELECT INTO valor_total SUM(preco * quantidade)
    FROM produtos
    WHERE categoria_id = id_categoria;

    -- Se não houver produtos na categoria, o valor_total será NULL.
    -- Neste caso, retornamos 0.
    RETURN COALESCE(valor_total, 0);
END;
$$ LANGUAGE plpgsql;