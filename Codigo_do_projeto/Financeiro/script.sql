DROP TABLE IF EXISTS Lancamento;
DROP TABLE IF EXISTS Item;
DROP TABLE IF EXISTS Usuario;

CREATE TABLE Usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome_usuario VARCHAR(100),
    login_usuario VARCHAR(100),
    senha_usuario VARCHAR(100)
);

CREATE TABLE Item (
    codigo_item INT AUTO_INCREMENT PRIMARY KEY,
    descricao_item VARCHAR(255),
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Lancamento (
    id_lancamento INT AUTO_INCREMENT PRIMARY KEY,
    tipo_lancamento VARCHAR(50),
    valor_lancamento DECIMAL(10, 2),
    data_lancamento DATE,
    tipo_recorrencia VARCHAR(50),
    codigo_item INT,
    id_usuario INT,
    FOREIGN KEY (codigo_item) REFERENCES Item(codigo_item),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);
