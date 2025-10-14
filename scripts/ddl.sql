-- Apagando as tabelas (se existirem) para garantir um ambiente limpo
-- A ordem do DROP é a inversa da criação para respeitar as chaves estrangeiras
DROP TABLE IF EXISTS usuarios_papeis CASCADE;
DROP TABLE IF EXISTS itens_pedido CASCADE;
DROP TABLE IF EXISTS pedidos CASCADE;
DROP TABLE IF EXISTS produtos CASCADE;
DROP TABLE IF EXISTS categorias CASCADE;
DROP TABLE IF EXISTS enderecos CASCADE;
DROP TABLE IF EXISTS usuarios CASCADE;
DROP TABLE IF EXISTS papeis CASCADE;


CREATE TABLE categorias (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE produtos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    descricao TEXT,
    preco NUMERIC(10, 2) NOT NULL,
    quantidade INTEGER,
    imagem_url VARCHAR(255),
    categoria_id BIGINT NOT NULL,

    CONSTRAINT fk_produto_categoria FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);



CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE papeis (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE usuarios_papeis (
    usuario_id BIGINT NOT NULL,
    papel_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, papel_id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_papel FOREIGN KEY (papel_id) REFERENCES papeis(id)
);

CREATE TABLE enderecos (
    id BIGSERIAL PRIMARY KEY,
    rua VARCHAR(255) NOT NULL,
    numero VARCHAR(20),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_endereco_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE pedidos (
    id BIGSERIAL PRIMARY KEY,
    data_do_pedido TIMESTAMP NOT NULL,
    status VARCHAR(50),
    valor_total NUMERIC(10, 2) NOT NULL,
    usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_pedido_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE itens_pedido (
    id BIGSERIAL PRIMARY KEY,
    quantidade INTEGER NOT NULL,
    preco_unitario NUMERIC(10, 2) NOT NULL,
    pedido_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    CONSTRAINT fk_item_pedido FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    CONSTRAINT fk_item_produto FOREIGN KEY (produto_id) REFERENCES produtos(id)
);