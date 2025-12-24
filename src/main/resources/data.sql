-- Limpa os dados das tabelas antes de inserir, para garantir um ambiente limpo a cada inicialização
DELETE FROM produtos;
DELETE FROM categorias;
DELETE FROM lojas;


-- A função ('categorias_id_seq') pega o último ID gerado para a tabela categorias
INSERT INTO categorias (id, nome, descricao) VALUES (1, 'Ferramentas', 'Ferramentas manuais e elétricas') ON CONFLICT (id) DO NOTHING;
INSERT INTO categorias (id, nome, descricao) VALUES (2, 'Eletrônicos', 'Dispositivos eletrônicos e acessórios') ON CONFLICT (id) DO NOTHING;
INSERT INTO categorias (id, nome, descricao) VALUES (3, 'Jardinagem', 'Equipamentos e suprimentos para jardim') ON CONFLICT (id) DO NOTHING;
-- Reseta a sequência para o próximo valor correto se necessário
SELECT setval('categorias_id_seq', (SELECT MAX(id) FROM categorias));


INSERT INTO produtos (nome, descricao, preco, quantidade, categoria_id) VALUES ('Martelo', 'Martelo de unha com cabo de madeira', 35.50, 50, 1);
INSERT INTO produtos (nome, descricao, preco, quantidade, categoria_id) VALUES ('Furadeira de Impacto', '500W, com maleta e acessórios', 250.00, 20, 1);
INSERT INTO produtos (nome, descricao, preco, quantidade, categoria_id) VALUES ('Smartphone Pro', '128GB, Tela de 6.5 polegadas', 1800.00, 30, 2);


-- A função ST_SetSRID(ST_MakePoint(longitude, latitude), 4326) cria o ponto geográfico
INSERT INTO lojas (nome, localizacao) VALUES ('Loja A - Ponto de Cem Réis', ST_SetSRID(ST_MakePoint(-34.8610, -7.1153), 4326));
INSERT INTO lojas (nome, localizacao) VALUES ('Loja B - Orla de Tambaú', ST_SetSRID(ST_MakePoint(-34.8256, -7.1150), 4326));
INSERT INTO lojas (nome, localizacao) VALUES ('Loja C - Bessa Shopping', ST_SetSRID(ST_MakePoint(-34.8300, -7.0783), 4326));