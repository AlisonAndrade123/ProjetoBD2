-- Sincroniza o contador da tabela de categorias
SELECT setval('categorias_id_seq', (SELECT MAX(id) FROM categorias));

-- Sincroniza o contador da tabela de produtos
SELECT setval('produtos_id_seq', (SELECT MAX(id) FROM produtos));