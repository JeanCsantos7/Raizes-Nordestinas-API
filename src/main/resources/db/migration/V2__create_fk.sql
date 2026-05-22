CREATE TABLE estoque (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,

                         produto_id BIGINT NOT NULL,
                         unidade_id BIGINT NOT NULL,

                         quantidade INTEGER NOT NULL,

                         CONSTRAINT fk_estoque_produto
                             FOREIGN KEY (produto_id)
                                 REFERENCES produto(id),

                         CONSTRAINT fk_estoque_unidade
                             FOREIGN KEY (unidade_id)
                                 REFERENCES unidade(id)
);