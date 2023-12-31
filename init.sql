CREATE DATABASE IF NOT EXISTS DBCode;
USE DBCode;

CREATE TABLE IF NOT EXISTS `USER` (
     ID BIGINT PRIMARY KEY AUTO_INCREMENT,
     EMAIL VARCHAR(255) NOT NULL UNIQUE,
     USERNAME VARCHAR(255) NOT NULL UNIQUE,
     PASSWORD VARCHAR(255) NOT NULL,
     PROFILE VARCHAR(255) NOT NULL,
     RESET_CODE VARCHAR(12),
        INDEX IDX_EMAIL(EMAIL),
        INDEX IDX_USERNAME(USERNAME)
);

CREATE TABLE IF NOT EXISTS `PROJETO` (
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    TITULO VARCHAR(255) NOT NULL UNIQUE,
    DESCRICAO VARCHAR(255) NOT NULL,
    PARTICIPANTES VARCHAR(255) NOT NULL,
    DATA_INICIO VARCHAR(255) NOT NULL,
    DATA_FIM VARCHAR(255) NOT NULL,
    ESTIMATIVAS VARCHAR(255) NOT NULL,
     INDEX IDX_TITULO(TITULO)
);

CREATE TABLE IF NOT EXISTS `REQUIREMENT` (
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    PROJETO_ID BIGINT NOT NULL,
    `NAME` VARCHAR(255) NOT NULL UNIQUE,
    QUALIFICACAO VARCHAR(255) NOT NULL UNIQUE,
    DESCRICAO VARCHAR(255) NOT NULL,
    ARQUIVO_ESPECIFICACAO VARCHAR(20),
    ARQUIVO_DESENHO VARCHAR(255) NOT NULL,
        INDEX IDX_NAME(`NAME`),
        FOREIGN KEY (PROJETO_ID) REFERENCES PROJETO(ID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Cria a tabela de cadastro de bugs
CREATE TABLE IF NOT EXISTS `BUG` (
        ID BIGINT PRIMARY KEY AUTO_INCREMENT,
        PROJETO_ID BIGINT NOT NULL,
        TITULO VARCHAR(25) NOT NULL,
        STATUS VARCHAR(25) NOT NULL,
        DESCRICAO VARCHAR(100) NOT NULL,
        REPRODUCAO VARCHAR(200) NOT NULL,
        FILE varchar(255),
        CLASSIFICACAO VARCHAR(25) NOT NULL,
        PRIORIDADE VARCHAR(25) NOT NULL,
        DATA_CADASTRO date NOT NULL,
            INDEX IDX_TITULO(TITULO),
            FOREIGN KEY (PROJETO_ID) REFERENCES PROJETO(ID)ON DELETE CASCADE ON UPDATE CASCADE

);
CREATE TABLE IF NOT EXISTS PLANO (
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    PROJETO_ID BIGINT NOT NULL,
    `NAME` VARCHAR(255) NOT NULL UNIQUE,
    INCLUDE_DATE VARCHAR(255) NOT NULL UNIQUE,
    RESPONSABILITE VARCHAR(255) NOT NULL UNIQUE,
        INDEX IDX_NAME(`NAME`),
        FOREIGN KEY (PROJETO_ID) REFERENCES PROJETO(ID) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `CENARIO` (
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    PLANO_ID BIGINT NOT NULL,
    `NAME` VARCHAR(255) NOT NULL UNIQUE,
    `TIME` VARCHAR(255) NOT NULL,
    STATUS VARCHAR(255) NOT NULL,
        INDEX IDX_NAME(`NAME`),
        FOREIGN KEY (PLANO_ID) REFERENCES PLANO(ID) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS CASO (
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    CENCARIO_ID BIGINT NOT NULL,
    `NAME` VARCHAR(255) NOT NULL UNIQUE,
    REQUIREMENT VARCHAR(255) NOT NULL UNIQUE,
    COMPLEXITY VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(255) NOT NULL,
        INDEX IDX_NAME(`NAME`),
        FOREIGN KEY (CENCARIO_ID) REFERENCES CENARIO(ID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS CASO_DETALHADO (
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    CASO_ID BIGINT NOT NULL,
    STEP VARCHAR(255) NOT NULL,
    `ACTION` VARCHAR(255) NOT NULL,
    EXPECTED_RESULT VARCHAR(255) NOT NULL,
    `PASS` VARCHAR(255) NOT NULL,
        FOREIGN KEY (CASO_ID) REFERENCES CASO(ID) ON DELETE CASCADE ON UPDATE CASCADE
);






