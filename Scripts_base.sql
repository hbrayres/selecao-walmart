CREATE DATABASE selecao;

CREATE TABLE usuario (
    ID INT NOT NULL AUTO_INCREMENT,
    LOGIN VARCHAR(50) NOT NULL,
    PASSWORD VARCHAR(255),
    PRIMARY KEY (ID) 
);

CREATE TABLE produto (
    ID INT NOT NULL AUTO_INCREMENT,
    PRODUTO VARCHAR(50) NOT NULL,
    DESCRICAO VARCHAR(200),
    PRIMARY KEY (ID) 
);

INSERT INTO produto(id, nome, descricao) VALUES
(null, 'produto 1', 'descricao produto um'),
(null, 'produto 2', 'descricao produto dois'),
(null, 'produto 3', 'descricao produto tres');

INSERT INTO usuario(id, login, password) VALUES
(null, 'administrator', '');
