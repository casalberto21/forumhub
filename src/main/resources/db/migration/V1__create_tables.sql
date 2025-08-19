CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) DEFAULT 'NAO_RESPONDIDO',
    autor_id BIGINT NOT NULL,
    curso VARCHAR(100),
    CONSTRAINT fk_topico_usuario FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);

CREATE TABLE respostas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensagem TEXT NOT NULL,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    autor_id BIGINT NOT NULL,
    topico_id BIGINT NOT NULL,
    CONSTRAINT fk_resposta_usuario FOREIGN KEY (autor_id) REFERENCES usuarios(id),
    CONSTRAINT fk_resposta_topico FOREIGN KEY (topico_id) REFERENCES topicos(id)
);

-- Usuário inicial
INSERT INTO usuarios (login, senha)
VALUES ('admin', '$2a$10$7hF6w1PfgjSxM4QW.HVn2OxQwTszY4l2XCK5oNf1u.2F.KXQq1.L6');
