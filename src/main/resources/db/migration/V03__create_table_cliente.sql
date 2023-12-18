CREATE TABLE tb_cliente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    documento VARCHAR(255) UNIQUE NOT NULL, 
    numero VARCHAR(255) NOT NULL, 
    rua VARCHAR(255) NOT NULL

);

ALTER TABLE produtos_comprados ADD COLUMN client_id BIGINT;
ALTER TABLE produtos_comprados ADD CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES tb_cliente(id);