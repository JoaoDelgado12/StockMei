CREATE SCHEMA IF NOT EXISTS estoque_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE estoque_db ;

CREATE TABLE funcao_perfilUsuario(
  id int AUTO_INCREMENT PRIMARY KEY,
  funcao VARCHAR(100)
);

CREATE TABLE grupoPermissao_perfilUsuario(
  id int AUTO_INCREMENT PRIMARY KEY,
  grupoPermissao VARCHAR(100)
);

CREATE TABLE perfilUsuario(
  id int AUTO_INCREMENT PRIMARY KEY,
  usuario VARCHAR(100), 
  senha VARCHAR(100),
  funcao int,
  CONSTRAINT fk_funcao FOREIGN KEY (funcao) REFERENCES funcao_perfilUsuario(id) on update cascade,
  grupoPermissao int,
  CONSTRAINT fk_grupoPermissao FOREIGN KEY (grupoPermissao) REFERENCES grupoPermissao_perfilUsuario(id) on update cascade,
  natureza enum('CLIENTE', 'COLABORADOR')
);

CREATE TABLE estado(
  estado VARCHAR(15) PRIMARY KEY
);

CREATE TABLE cidade(
  cidade VARCHAR(11) PRIMARY KEY
);
CREATE TABLE cepUsuario(
  id int AUTO_INCREMENT PRIMARY KEY,
  cep VARCHAR(11),
  estado VARCHAR(15),
  CONSTRAINT fk_estado FOREIGN KEY (estado) REFERENCES estado(estado) on update cascade,
  cidade VARCHAR(11),
  CONSTRAINT fk_cidade FOREIGN KEY (cidade) REFERENCES cidade(cidade) on update cascade,
  endereco VARCHAR(50)
);

CREATE TABLE dadosUsuario(
  id int PRIMARY KEY,
  nome VARCHAR(100), 
  sobrenome VARCHAR(100),
  dtaNascimento VARCHAR(8),
  sexo enum('masculino','feminino', 'trans', 'outros'),
  cpf VARCHAR(11),
  cep int,
  CONSTRAINT fk_cep FOREIGN KEY (cep) REFERENCES cepUsuario(id) on update cascade,
  email VARCHAR(120),
  telefone VARCHAR(11)
);

