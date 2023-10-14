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
INSERT INTO DB_MENSAGERIA.MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA)
VALUES (1, 2, 'Saudações de Boas-Vindas', 'Olá, como você está? Espero que esteja tendo um ótimo dia. Estou ansioso para trabalhar juntos neste novo projeto. Vamos alcançar grandes feitos!', '2023-10-14 10:00:00');
INSERT INTO DB_MENSAGERIA.MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA)
VALUES (3, 4, 'Reunião Importante na Quinta-feira', 'Lembre-se da reunião agendada para amanhã às 14:00. Temos muitos tópicos importantes para discutir, incluindo o andamento do projeto e planos futuros.', '2023-10-15 14:00:00');
INSERT INTO DB_MENSAGERIA.MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA)
VALUES (5, 1, 'Convite para o Evento Especial', 'Você está convidado para o nosso evento especial que acontecerá no próximo sábado às 19:30. Esperamos vê-lo lá!', '2023-10-16 19:30:00');
INSERT INTO DB_MENSAGERIA.MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA)
VALUES (2, 3, 'Projeto Concluído', 'Finalizamos o projeto com sucesso! Agradeço a todos pelo trabalho árduo e dedicação. Vamos celebrar nossa conquista!', '2023-10-17 16:45:00');
INSERT INTO DB_MENSAGERIA.MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA)
VALUES (4, 5, 'Feliz Aniversário', 'Parabéns! Hoje é o seu dia especial. Desejamos a você muita alegria e sucesso em mais um ano de vida.', '2023-10-18 08:30:00');
INSERT INTO DB_MENSAGERIA.MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA)
VALUES (1, 4, 'Agradecimento', 'Obrigado por toda a ajuda e apoio. Sua contribuição é inestimável para a equipe.', '2023-10-19 12:15:00');
INSERT INTO DB_MENSAGERIA.MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA)
VALUES (3, 5, 'Atualização do Projeto', 'Aqui estão as últimas atualizações do projeto. Analise os detalhes e compartilhe suas observações.', '2023-10-20 09:20:00');
INSERT INTO DB_MENSAGERIA.MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA)
VALUES (2, 1, 'Convocação para Reunião', 'Você está convocado para uma reunião importante na sexta-feira. Sua presença é essencial.', '2023-10-21 16:00:00');
INSERT INTO DB_MENSAGERIA.MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA)
VALUES (4, 2, 'Pedido de Desconto', 'Solicito um desconto especial na próxima compra. Vamos discutir os detalhes.', '2023-10-22 14:30:00');
INSERT INTO DB_MENSAGERIA.MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA)
VALUES (5, 3, 'Agradecimento', 'Agradecemos por ser um colega de trabalho excepcional. Sua dedicação é inspiradora.', '2023-10-23 11:10:00');
INSERT INTO DB_MENSAGERIA.MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA)
VALUES (1, 3, 'Novo Projeto Em Andamento', 'Estamos iniciando um novo projeto emocionante. Sua participação é fundamental. Vamos fazer acontecer!', '2023-10-24 10:30:00');
INSERT INTO DB_MENSAGERIA.MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA)
VALUES (2, 4, 'Aproveite o Feriado', 'Aproveite o feriado prolongado e descanse. Você merece!', '2023-10-25 17:20:00');
INSERT INTO DB_MENSAGERIA.MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA)
VALUES (3, 1, 'Feedback de Desempenho', 'Precisamos discutir seu desempenho e traçar planos para o crescimento. Vamos agendar uma reunião.', '2023-10-26 15:45:00');
INSERT INTO DB_MENSAGERIA.MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA)
VALUES (4, 5, 'Feliz Natal', 'Desejando a você e sua família um Natal repleto de alegria, paz e harmonia. Que esta época seja mágica para todos.', '2023-12-25 12:00:00');
INSERT INTO DB_MENSAGERIA.MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA)
VALUES (5, 2, 'Parabéns pela Promoção', 'Parabéns pela sua promoção bem merecida. Seu trabalho duro e dedicação trouxeram resultados incríveis.', '2023-12-28 09:30:00');

    
    SELECT * FROM MENSAGEM;
    SELECT * FROM USUARIO;

