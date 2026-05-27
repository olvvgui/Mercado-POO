create table produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    valor NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    quantidade_estoque int NOT NULL DEFAULT 0,
    em_falta BOOLEAN DEFAULT FALSE
);

CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(60) NOT NULL,
    perfil_usuario VARCHAR (20) NOT NULL,
    ativo BOOLEAN DEFAULT true
);

CREATE TABLE venda (
    id SERIAL PRIMARY KEY,
    data_hora TIMESTAMPTZ DEFAULT NOW(),
    valor NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    descricao VARCHAR (255),
    -- Vincula a venda ao usuário que a realizou:
    usuario_id INT NOT NULL REFERENCES usuario(id)
);


CREATE TABLE item_venda(
    id SERIAL PRIMARY KEY,
    -- Vincula o item à venda correspondente:
    venda_id INT NOT NULL REFERENCES venda(id) ON DELETE CASCADE,
    -- Vincula o item ao produto vendido:
    produto_id INT NOT NULL REFERENCES produto(id),
    quantidade INTEGER NOT NULL DEFAULT 1,
    preco_unitario NUMERIC(10, 2) NOT NULL DEFAULT 0.00
);