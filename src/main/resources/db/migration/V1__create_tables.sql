CREATE TABLE usuario (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         senha_hash VARCHAR(300) NOT NULL,
                         perfil ENUM('CLIENTE', 'ATENDENTE', 'GERENTE') NOT NULL,
                         pontos INT DEFAULT 0
);

CREATE TABLE unidade (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         endereco VARCHAR(150) NOT NULL
);

CREATE TABLE produto (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         preco DECIMAL(10,2) NOT NULL
);

CREATE TABLE pedido (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,

                        cliente_id BIGINT NOT NULL,
                        unidade_id BIGINT NOT NULL,

                        status ENUM(
        'AGUARDANDO',
        'EM_PREPARO',
        'PRONTO',
        'CONCLUIDO',
        'CANCELADO',
        'PAGAMENTO_NEGADO'
    ) NOT NULL,

                        canal_pedido ENUM(
        'APP',
        'WEB',
        'TOTEM',
        'BALCAO'
    ) NOT NULL,

                        total DECIMAL(10,2) NOT NULL,

                        data_pedido DATETIME(6),

                        CONSTRAINT fk_pedido_cliente
                            FOREIGN KEY (cliente_id)
                                REFERENCES usuario(id),

                        CONSTRAINT fk_pedido_unidade
                            FOREIGN KEY (unidade_id)
                                REFERENCES unidade(id)
);

CREATE TABLE item_pedido (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,

                             pedido_id BIGINT NOT NULL,
                             produto_id BIGINT NOT NULL,

                             quantidade INT NOT NULL,
                             preco_unitario DECIMAL(10,2) NOT NULL,

                             CONSTRAINT fk_item_pedido
                                 FOREIGN KEY (pedido_id)
                                     REFERENCES pedido(id),

                             CONSTRAINT fk_item_produto
                                 FOREIGN KEY (produto_id)
                                     REFERENCES produto(id)
);

CREATE TABLE estoque (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,

                         produto_id BIGINT NOT NULL,
                         unidade_id BIGINT NOT NULL,

                         quantidade INT NOT NULL,

                         CONSTRAINT fk_estoque_produto
                             FOREIGN KEY (produto_id)
                                 REFERENCES produto(id),

                         CONSTRAINT fk_estoque_unidade
                             FOREIGN KEY (unidade_id)
                                 REFERENCES unidade(id)
);

CREATE TABLE pagamento (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,

                           pedido_id BIGINT NOT NULL,

                           status ENUM(
        'APROVADO',
        'NEGADO',
        'PENDENTE'
    ) NOT NULL,

                           data_pagamento DATETIME(6),

                           metodo ENUM(
        'CARTAO',
        'PIX',
        'DINHEIRO',
        'OUTRO'
    ),

                           CONSTRAINT fk_pagamento_pedido
                               FOREIGN KEY (pedido_id)
                                   REFERENCES pedido(id)
);



INSERT INTO usuario (
    nome,
    email,
    senha_hash,
    perfil,
    pontos
)
VALUES (
           'Administrador',
           'gerente@raizes.com',
           '$2a$12$bXD47SFVSPQYgo05hqZOh.1AOb7z4ryiX0OpizLKL8L7dnGNGbeA6',
           'GERENTE',
           0
       );