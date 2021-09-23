CREATE DATABASE DB_PACOTES_TURISTICOS;

USE DB_PACOTES_TURISTICOS;

CREATE TABLE TB_USER(
    ID_USER BIGINT NOT NULL AUTO_INCREMENT,
    EMAIL VARCHAR(255) NOT NULL, 
    SENHA VARCHAR(128) NOT NULL, 
    IS_ADM BOOLEAN NOT NULL, 
    PRIMARY KEY (ID_USER)
);

CREATE TABLE TB_CLIENTE(
    ID_CLIENTE BIGINT NOT NULL AUTO_INCREMENT,
    ID_USER BIGINT NOT NULL,
    CPF VARCHAR(14) NOT NULL UNIQUE,
    NOME VARCHAR(300) NOT NULL, 
    TELEFONE VARCHAR(15),
    SEXO VARCHAR(50), 
    DATA_NASC date, 
    PRIMARY KEY (ID_CLIENTE),
    FOREIGN KEY (ID_USER) REFERENCES TB_USER(ID_USER)
);

CREATE TABLE TB_AGENCIA_TURISMO(
    ID_AGENCIA_TURISMO BIGINT NOT NULL AUTO_INCREMENT,
    ID_USER BIGINT NOT NULL,
    CNPJ VARCHAR(50) NOT NULL UNIQUE, 
    NOME VARCHAR(300) NOT NULL, 
    DESCRICAO VARCHAR(255), 
    PRIMARY KEY (ID_AGENCIA_TURISMO),
    FOREIGN KEY (ID_USER) REFERENCES TB_USER(ID_USER)
);

CREATE TABLE TB_PACOTE_TURISTICO(
    ID_PACOTE_TURISTICO BIGINT NOT NULL AUTO_INCREMENT,
    ID_AGENCIA BIGINT NOT NULL,
    DATA_PARTIDA DATE NOT NULL, 
    DATA_CHEGADA VARCHAR(255) NOT NULL, 
    VALOR DOUBLE(10,2) NOT NULL, 
    DESCRICAO VARCHAR(255),    
    PRIMARY KEY (ID_PACOTE_TURISTICO), 
    FOREIGN KEY (ID_AGENCIA) REFERENCES TB_AGENCIA_TURISMO(ID_AGENCIA_TURISMO)
);

CREATE TABLE TB_DESTINO(
    ID_DESTINO BIGINT NOT NULL AUTO_INCREMENT,
    NOME VARCHAR(255) NOT NULL, 
    ID_PACOTE BIGINT NOT NULL, 
    PRIMARY KEY (ID_DESTINO)
    FOREIGN KEY (ID_PACOTE) REFERENCES TB_PACOTE_TURISTICO(ID_PACOTE_TURISTICO)
);

CREATE TABLE TB_FOTO(
    ID_FOTO BIGINT NOT NULL AUTO_INCREMENT,
    ID_DESTINO BIGINT NOT NULL,
    LINK VARCHAR(255) NOT NULL, 
    PRIMARY KEY (ID_FOTO), 
    FOREIGN KEY (ID_DESTINO) REFERENCES TB_DESTINO(ID_DESTINO)
);


CREATE TABLE TB_COMPRA(
    ID_COMPRA BIGINT NOT NULL AUTO_INCREMENT, 
    ID_CLIENTE BIGINT NOT NULL, 
    ID_AGENCIA BIGINT NOT NULL, 
    ID_PACOTE_TURISTICO BIGINT NOT NULL,
    VALOR DOUBLE(10,2) NOT NULL, 
    PRIMARY KEY (ID_COMPRA), 
    FOREIGN KEY (ID_CLIENTE) REFERENCES TB_CLIENTE(ID_CLIENTE),
    FOREIGN KEY (ID_AGENCIA) REFERENCES TB_AGENCIA_TURISMO(ID_AGENCIA_TURISMO),
    FOREIGN KEY (ID_PACOTE_TURISTICO) REFERENCES TB_PACOTE_TURISTICO(ID_PACOTE_TURISTICO)
);

