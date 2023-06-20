-- MySql
-- Usuário: bancomvc
-- Senha: 12345678

INSERT INTO Usuario (id_usuario, nome_usuario, login_usuario, senha_usuario) VALUES
                                                                                 (1, 'João Silva', 'jsilva', 'senha123'),
                                                                                 (2, 'Maria Fernanda', 'mfernanda', 'senha123'),
                                                                                 (3, 'Carlos Alberto', 'calberto', 'senha123'),
                                                                                 (4, 'Ana Paula', 'apaula', 'senha123');

INSERT INTO Item (codigo_item, descricao_item, id_usuario) VALUES
                                                               (1, 'Aluguel', 1),
                                                               (2, 'Salário', 1),
                                                               (3, 'Compra de Livro', 1),
                                                               (4, 'Conta de Internet', 1),
                                                               (5, 'Compra de Bicicleta', 1),
                                                               (6, 'Aluguel', 2),
                                                               (7, 'Salário', 2),
                                                               (8, 'Curso Online', 2),
                                                               (9, 'Conta de Luz', 2),
                                                               (10, 'Viagem de Férias', 2),
                                                               (11, 'Aluguel', 3),
                                                               (12, 'Salário', 3),
                                                               (13, 'Aulas de Inglês', 3),
                                                               (14, 'Conta de Água', 3),
                                                               (15, 'Compra de Computador', 3),
                                                               (16, 'Aluguel', 4),
                                                               (17, 'Salário', 4),
                                                               (18, 'Academia', 4),
                                                               (19, 'Conta de Gás', 4),
                                                               (20, 'Compra de Televisão', 4);

INSERT INTO Lancamento (tipo_lancamento, valor_lancamento, data_lancamento, tipo_recorrencia, codigo_item, id_usuario) VALUES
                                                                                                                           ('Despesa', 1500, '2023-06-01', 'Mensal', 1, 1),
                                                                                                                           ('Receita', 3500, '2023-06-05', 'Mensal', 2, 1),
                                                                                                                           ('Despesa', 50, '2023-06-10', 'Unica', 3, 1),
                                                                                                                           ('Despesa', 100, '2023-06-20', 'Mensal', 4, 1),
                                                                                                                           ('Despesa', 1200, '2023-06-30', 'Unica', 5, 1),
                                                                                                                           ('Despesa', 1400, '2023-06-01', 'Mensal', 6, 2),
                                                                                                                           ('Receita', 3000, '2023-06-05', 'Mensal', 7, 2),
                                                                                                                           ('Despesa', 200, '2023-06-10', 'Unica', 8, 2),
                                                                                                                           ('Despesa', 90, '2023-06-20', 'Mensal', 9, 2),
                                                                                                                           ('Despesa', 2500, '2023-06-30', 'Unica', 10, 2),
                                                                                                                           ('Despesa', 1300, '2023-06-01', 'Mensal', 11, 3),
                                                                                                                           ('Receita', 2500, '2023-06-05', 'Mensal', 12, 3),
                                                                                                                           ('Despesa', 500, '2023-06-10', 'Mensal', 13, 3),
                                                                                                                           ('Despesa', 60, '2023-06-20', 'Mensal', 14, 3),
                                                                                                                           ('Despesa', 3500, '2023-06-30', 'Unica', 15, 3),
                                                                                                                           ('Despesa', 1200, '2023-06-01', 'Mensal', 16, 4),
                                                                                                                           ('Receita', 2800, '2023-06-05', 'Mensal', 17, 4),
                                                                                                                           ('Despesa', 150, '2023-06-10', 'Mensal', 18, 4),
                                                                                                                           ('Despesa', 50, '2023-06-20', 'Mensal', 19, 4),
                                                                                                                           ('Despesa', 2000, '2023-06-30', 'Unica', 20, 4);
