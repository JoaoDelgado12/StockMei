use estoque_db;

INSERT INTO funcao (funcao) VALUES
('admin');

INSERT INTO grupoPermissao (grupoPermissao) VALUES
('admin');

INSERT INTO perfilUsuario (usuario, senha, funcao, grupoPermissao) VALUES
('admin', 'admin', 'admin', 'admin');

INSERT INTO estado (estado) VALUES
('Bahia');

INSERT INTO cidade (cidade) VALUES
('Salvador');

INSERT INTO cepUsuario 
(idPerfil, cep, estado, cidade, logradouro, numero, bairro, complemento)
VALUES
(1, '40000000', 'BA', 'Salvador', 'Rua A', 100, 'Centro', 'Apto 101')

INSERT INTO dadosUsuario
(idPerfil, nome, sobrenome, dtaNascimento, sexo, cpf, email, telefone)
VALUES
(1, 'João', 'Silva', '1990-05-10', 'M', '12345678901', 'joao@email.com', '71999999999');