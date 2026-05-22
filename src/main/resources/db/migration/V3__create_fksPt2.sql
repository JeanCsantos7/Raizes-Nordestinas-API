CREATE TABLE pedido (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,

                        cliente_id BIGINT,
                        unidade_id BIGINT,

                        status ENUM(
        'AGUARDANDO',
        'EM_PREPARO',
        'PRONTO',
        'CONCLUIDO',
        'CANCELADO',
        'PAGAMENTO_NEGADO'
    ),

                        canal_pedido ENUM(
        'APP',
        'WEB',
        'TOTEM',
        'BALCAO'
    ),

                        total DECIMAL(10,2),
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

                             pedido_id BIGINT,
                             produto_id BIGINT,

                             quantidade INTEGER,
                             preco_unitario DECIMAL(10,2),

                             CONSTRAINT fk_itemPedido_pedido
                                 FOREIGN KEY (pedido_id)
                                     REFERENCES pedido(id),

                             CONSTRAINT fk_itemPedido_produto
                                 FOREIGN KEY (produto_id)
                                     REFERENCES produto(id)
);


CREATE TABLE pagamento (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,

                           pedido_id BIGINT,

                           status ENUM(
        'APROVADO',
        'NEGADO',
        'PENDENTE'
    ),

                           CONSTRAINT fk_pagamento_pedido
                               FOREIGN KEY (pedido_id)
                                   REFERENCES pedido(id)
);


