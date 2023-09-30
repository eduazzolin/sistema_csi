-- Desabilitar SQL_SAFE_UPDATES para evitar problemas com atualizações perigosas
SET SQL_SAFE_UPDATES = 0;

-- Dropar o banco de dados se ele já existir
DROP DATABASE IF EXISTS DB_MENSAGERIA;
CREATE DATABASE DB_MENSAGERIA;
USE DB_MENSAGERIA;

-- Criar a tabela USUARIO
CREATE TABLE DB_MENSAGERIA.USUARIO (
    ID_USUARIO INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NOME_USUARIO VARCHAR(20) NOT NULL UNIQUE,
    SENHA VARCHAR(40) NOT NULL
);

-- Criar a tabela MENSAGEM
CREATE TABLE DB_MENSAGERIA.MENSAGEM (
    ID_MENSAGEM INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ID_REMETENTE INTEGER NOT NULL,
    ID_DESTINATARIO INTEGER NOT NULL,
    MENSAGEM_TITULO VARCHAR(100) NOT NULL,
    MENSAGEM_CORPO TEXT,
    MENSAGEM_DATA DATETIME,
    CONSTRAINT FK_ID_REMETENTE FOREIGN KEY (ID_REMETENTE) REFERENCES USUARIO(ID_USUARIO) ON DELETE CASCADE,
    CONSTRAINT FK_ID_DESTINATARIO FOREIGN KEY (ID_DESTINATARIO) REFERENCES USUARIO(ID_USUARIO) ON DELETE CASCADE
);

-- Inserir valores na tabela USUARIO. 
INSERT INTO DB_MENSAGERIA.USUARIO (NOME_USUARIO, SENHA) VALUES
    ('admin', MD5('admin')),
    ('user1', MD5('user1')),
    ('user2', MD5('user2')),
    ('user3', MD5('user3')),
    ('user4', MD5('user4'));
    
 -- Inserir valores na tabela MENSAGEM
INSERT INTO DB_MENSAGERIA.MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA) VALUES
    (1, 2, 'Hello!', 'Hello World!', '2023-09-30 11:00:00'),
    (2, 1, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum', '2023-09-30 12:00:00'),
    (2, 1, 'Título 2', 'Corpo da mensagem 1', '2023-09-30 13:00:00'),
    (2, 1, 'Título 3', 'Corpo da mensagem 1', '2023-09-30 14:00:00'),
    (2, 1, 'Título 4', 'Corpo da mensagem 1', '2023-09-30 15:00:00'),
    (2, 1, 'Título 5', 'Corpo da mensagem 1', '2023-09-30 17:00:00'),
    (2, 1, 'Título 6', 'Corpo da mensagem 1', '2023-09-30 16:00:00'),
    (2, 1, 'Título 7', 'Corpo da mensagem 1', '2023-09-30 18:00:00'),
    (3, 4, 'Título 2', 'Corpo da mensagem 2', '2023-09-30 11:00:00'),
    (4, 3, 'Título 3', 'Corpo da mensagem 3', '2023-09-30 11:00:00'),
    (5, 2, 'Título 4', 'Corpo da mensagem 4', '2023-09-30 11:00:00');
    
    SELECT * FROM MENSAGEM;
    SELECT * FROM USUARIO;

