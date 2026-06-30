USE estoque_db;

-- -----------------------------------------------------
-- 1. POPULAR TABELAS DE APOIO (Funções, Permissões, Estados, Cidades, Marcas, Empresas)
-- -----------------------------------------------------
INSERT INTO funcao (funcao) VALUES
('Gerente'), ('Vendedor'), ('Estoquista')
ON DUPLICATE KEY UPDATE funcao=VALUES(funcao);
-- (ANSI) erros de duplicacao de chave, atualiza o campo funcao e cola o novo valor inserido

INSERT INTO grupoPermissao (grupoPermissao) VALUES
('Gerência'), ('Vendas'), ('Estoque')
ON DUPLICATE KEY UPDATE grupoPermissao=VALUES(grupoPermissao); 

INSERT INTO estado (estado) VALUES
('Bahia'), ('Pernambuco'), ('Minas Gerais')
ON DUPLICATE KEY UPDATE estado=VALUES(estado); 

INSERT INTO cidade (cidade) VALUES
('Salvador'),('Recife'), ('Belo Horizonte')
ON DUPLICATE KEY UPDATE cidade=VALUES(cidade); 

INSERT INTO Marca (marca) VALUES
('Nike'), ('Adidas'), ('Samsung'), ('Apple'), ('Sony')
ON DUPLICATE KEY UPDATE marca=VALUES(marca); 

INSERT INTO EmpresaNome (empresaNome) VALUES
('Distribuidora Alfa LTDA'), ('Logística Beta S/A'), ('Tech Fornecimentos')
ON DUPLICATE KEY UPDATE empresaNome=VALUES(empresaNome); 

-- -----------------------------------------------------
-- 2. POPULAR FORNECEDORES
-- -----------------------------------------------------
INSERT INTO Fornecedor (nome, telefone, empresaNome) VALUES
('Carlos', '11988887777', 'Distribuidora Alfa LTDA'),
('Roberto', '21977776666', 'Logística Beta S/A'),
('Ana', '31966665555', 'Tech Fornecimentos');

-- -----------------------------------------------------
-- 3. POPULAR PERFIS DE USUÁRIOS
-- -----------------------------------------------------
INSERT INTO perfilUsuario (usuario, senha, funcao, grupoPermissao) VALUES
('gerente.ana', 'senha123', 'Gerente', 'Gerência'),
('gerente.lucas', 'senha123', 'Gerente', 'Gerência'),
('vendedor.marcos', 'senha123', 'Vendedor', 'Vendas'),
('vendedor.julia', 'senha123', 'Vendedor', 'Vendas'),
('vendedor.larissa', 'senha123', 'Vendedor', 'Vendas'),
('estoquista.tiago', 'senha123', 'Estoquista', 'Estoque'),
('estoquista.vanessa', 'senha123', 'Estoquista', 'Estoque');

-- -----------------------------------------------------
-- 4. POPULAR ENDEREÇOS DOS USUÁRIOS (cepUsuario)
-- -----------------------------------------------------
INSERT INTO cepUsuario (idPerfil, cep, estado, cidade, logradouro, numero, bairro, complemento) VALUES
(1,'41610-000', 'Bahia', 'Salvador', 'Avenida Octávio Mangabeira', '100', 'Itapuã', 'Apto 202'),
(2,'41830-000', 'Bahia', 'Salvador', 'Rua Piauí', '45', 'Pituba', NULL),
(3,'50010-000', 'Pernambuco', 'Recife', 'Avenida Agamenon Magalhães', '1500', 'Boa Vista', 'Sala 402'),
(4,'51020-010', 'Pernambuco', 'Recife', 'Avenida Boa Viagem', '3500', 'Boa Viagem', 'Bloco A'),
(5,'30130-010', 'Minas Gerais', 'Belo Horizonte', 'Avenida Afonso Pena', '1200', 'Centro', 'Andar 10'),
(6,'30140-060', 'Minas Gerais', 'Belo Horizonte', 'Rua da Bahia', '980', 'Lourdes', 'Casa'),
(7,'30130-010', 'Minas Gerais', 'Belo Horizonte', 'Avenida Afonso Pena', '1200', 'Centro', 'Andar 11'),
(8,'30140-061', 'Minas Gerais', 'Belo Horizonte', 'Rua do Mario', '780', 'Lourdes', 'Casa');

-- -----------------------------------------------------
-- 5. POPULAR DADOS PESSOAIS DOS USUÁRIOS (dadosUsuario)
-- -----------------------------------------------------
INSERT INTO dadosUsuario (idPerfil, nome, sobrenome, dtaNascimento, sexo, cpf, email, telefone) VALUES
(2, 'Ana', 'Oliveira', '1985-03-15', 'F', '23456789012', 'ana@email.com', '11911112222'),
(3, 'Lucas', 'Santos', '1988-07-22', 'M', '34567890123', 'lucas@email.com', '11922223333'),
(4, 'Marcos', 'Costa', '1992-11-05', 'M', '45678901234', 'marcos@email.com', '11933334444'),
(5, 'Julia', 'Lima', '1995-01-25', 'F', '56789012345', 'julia@email.com', '21944445555'),
(6, 'Larissa', 'Almeida', '1993-09-18', 'F', '67890123456', 'larissa@email.com', '21955556666'),
(7, 'Tiago', 'Lima', '1995-01-25', 'M', '56789232345', 'Tiago@email.com', '2193345555'),
(8, 'Vanessa', 'Almeida', '1993-09-18', 'F', '67456723456', 'Vanessa@email.com', '21956556666');

-- -----------------------------------------------------
-- 6. POPULAR 10 PRODUTOS
-- -----------------------------------------------------
INSERT INTO Produto (nome, quantidade, precoVendaUni, marca, fornecedor) VALUES
('Tênis Air Max', 50, 599.90, 'Nike', 1),
('Camiseta Run', 120, 119.90, 'Nike', 1),
('Chuteira Predator', 40, 450.00, 'Adidas', 1),
('Moletom Essentials', 35, 299.90, 'Adidas', 2),
('Smartphone Galaxy S23', 15, 4999.00, 'Samsung', 3),
('Smart TV 55 QLED', 10, 3500.00, 'Samsung', 3),
('iPhone 14 Pro Max', 8, 7999.00, 'Apple', 3),
('AirPods Pro', 25, 1899.00, 'Apple', 3),
('Console PlayStation 5', 12, 4299.00, 'Sony', 2),
('Fone Bluetooth WH-1000XM4', 20, 1699.00, 'Sony', 2);

-- -----------------------------------------------------
-- 7. HISTÓRICO DE ESTOQUE (Entradas de Mercadorias)
-- -----------------------------------------------------
INSERT INTO HistEstoque (produto, quantidade, dataPrecoCompra, precoCompraUni, precoCompraTotal) VALUES
(1, 60, '2026-01-10', 300.00, 18000.00),
(2, 150, '2026-01-10', 50.00, 7500.00),
(3, 50, '2026-01-12', 200.00, 10000.00),
(4, 40, '2026-01-12', 130.00, 5200.00),
(5, 20, '2026-01-15', 3100.00, 62000.00),
(6, 12, '2026-01-15', 2200.00, 26400.00),
(7, 10, '2026-01-15', 5000.00, 50000.00);

-- -----------------------------------------------------
-- 8. HISTÓRICO DE VENDA (SAÍDA de Mercadorias)
-- -----------------------------------------------------
INSERT INTO Venda (precoTotal, desconto, dataVenda) VALUES
(119.90, 0.00, '2026-01-12'),
(299.90, 50.00, '2026-01-01'),
(239.80, 0.00, '2026-02-12'),
(599.90, 0.00, '2026-06-25');

-- -----------------------------------------------------
-- 8. HISTÓRICO DE ITENS VENDA (SAÍDA de Mercadorias)
-- -----------------------------------------------------

INSERT INTO ItensVenda (quantidade, dataVenda, precoTotal, desconto, produto, venda) VALUES
-- Item 1: 1 Camiseta Run (119.90) para a Venda 1
(1, '2026-01-12', 119.90, 0.00, 2, 1),

-- Item 2: 1 Moletom Essentials (299.90) para a Venda 2
(1, '2026-01-01', 299.90, 50.00, 4, 2),

-- Item 3: 2 Camisetas Run (2x 119.90 = 239.80) para a Venda 3
(2, '2026-02-12', 239.80, 0.00, 2, 3),

(1, '2026-06-25', 599.90, 0.00, 1, 4);









