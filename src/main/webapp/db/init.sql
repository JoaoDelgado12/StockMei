CREATE SCHEMA IF NOT EXISTS estoque_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;

USE estoque_db ;

CREATE TABLE IF NOT EXISTS usuario(
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100),
  sobrenome VARCHAR(100),
  dtaNasc INT,
  CONSTRAINT fk_dataNasc FOREIGN KEY (dtaNasc) REFERENCES dta_nascimento_usuario(dta_nascimento),
  sexo INT DEFAULT 0.
  CONSTRAINT fk_sexo FOREIGN KEY (sexo) REFERENCES sexo_usuario(sexo),
  cpf VARCHAR(11) UNIQUE,
  cep INT,
  CONSTRAINT fk_cepUsuario FOREIGN KEY (cep) REFERENCES cep_usuario(cep),
  email VARCHAR(120),
  telefone VARCHAR(11)
  )

CREATE TABLE IF NOT EXISTS dta_nascimento_usuario(
  id INT AUTO_INCREMENT PRIMARY KEY,
  dta_nascimento DATE NOT NULL
)

CREATE TABLE IF NOT EXISTS sexo_usuario(
  id INT AUTO_INCREMENT PRIMARY KEY,
  sexo VARCHAR(15) NOT NULL
)

CREATE TABLE IF NOT EXISTS cep_usuario(
  id INT AUTO_INCREMENT PRIMARY KEY,
  cep INT NOT NULL,
  CONSTRAINT fk_cep FOREIGN KEY (cep) REFERENCES cep(cep),
  estado INT NOT NULL,
  CONSTRAINT fk_estado FOREIGN KEY (estado) REFERENCES estado(estado),
  bairro VARCHAR(30) NOT NULL,
  cidade INT NOT NULL,
  CONSTRAINT fk_cidade FOREIGN KEY (cidade) REFERENCES cidade(cidade),
  numero VARCHAR(5),
  complemento VARCHAR(30)
)

CREATE TABLE IF NOT EXISTS cep(
  id INT AUTO_INCREMENT PRIMARY KEY,
  cep VARCHAR(15) NOT NULL
)
CREATE TABLE IF NOT EXISTS estado(
  id INT AUTO_INCREMENT PRIMARY KEY,
  estado VARCHAR(15) NOT NULL
)
CREATE TABLE IF NOT EXISTS cidade(
  id INT AUTO_INCREMENT PRIMARY KEY,
  cidade VARCHAR(15) NOT NULL
)

CREATE TABLE IF NOT EXISTS perfil_colaborador(
  id INT PRIMARY KEY,
  CONSTRAINT fk_perfilColaborador FOREIGN KEY id REFERENCES usuario(id) on cascade update on cascade delete,
  usuario VARCHAR(100) NOT NULL,
  senha VARCHAR(100) NOT NULL,
  funcao INT DEFAULT 0,
  CONSTRAINT fk_funcao_perfil_colaborador FOREIGN KEY id REFERENCES funcao_perfil_colaborador(id),
  grupoPermissao INT DEFAULT 0,
  CONSTRAINT fk_funcao_perfil_colaborador FOREIGN KEY id REFERENCES grupoPermissao_perfil_colaborador(id),
  ativo enum['0','1']
)

CREATE TABLE IF NOT EXISTS funcao_perfil_colaborador(
  id INT AUTO_INCREMENT PRIMARY KEY,
  funcao VARCHAR(20) NOT NULL
)

CREATE TABLE IF NOT EXISTS grupoPermissao_perfil_colaborador(
  id INT AUTO_INCREMENT PRIMARY KEY,
  grupoPermissao VARCHAR(20) NOT NULL
)

CREATE TABLE IF NOT EXISTS perfil_cliente(
  id INT PRIMARY KEY,
  CONSTRAINT fk_perfilCliente FOREIGN KEY id REFERENCES perfil_colaborador() on cascade update on cascade delete,
  usuario VARCHAR(100) NOT NULL,
  senha VARCHAR(100) NOT NULL,
)
