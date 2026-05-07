CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    senha_hash VARCHAR(300),
    perfil ENUM('CLIENTE', 'ATENDENTE', 'GERENTE')
);

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
    canal_pedido ENUM('APP', 'WEB', 'TOTEM', 'BALCAO'),
    total DECIMAL(10,2),
    data_pedido DATETIME(6)
);

CREATE TABLE pagamento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_id BIGINT,
    status ENUM('APROVADO', 'NEGADO', 'PENDENTE')
);

CREATE TABLE itemPedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_id BIGINT,
    produto_id BIGINT,
    quantidade INTEGER,
    preco_unitario DECIMAL(10,2)
);

CREATE TABLE produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    preco DECIMAL(10,2)
);

CREATE TABLE unidade (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    endereco VARCHAR(150)
);

CREATE TABLE estoque (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    produto_id BIGINT,
    unidade_id BIGINT,
    quantidade INTEGER
);