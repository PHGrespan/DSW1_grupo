USE DB_PACOTES_TURISTICOS;

INSERT INTO TB_CLIENTE(EMAIL, SENHA, CPF, IS_ADM, NOME, TELEFONE, SEXO, DATA_NASC) 
VALUES 
    ('useradmin@gmail.com', 'senha', '28375611187', 'SIM','Usuário Administrador', '19 123456789', 'Masculino', '2000-01-01'),
    ('usercomum@gmail.com', 'senha', '12876899967', 'NAO','Usuário Comum', '19 987654321', 'Masculino', '1999-02-03');

INSERT INTO TB_AGENCIA_TURISMO(EMAIL, SENHA, CNPJ, NOME, DESCRICAO) 
VALUES 
    ('agencia1@gmail.com', 'senha', '09756743254', 'Agencia 1', ''),
    ('agencia2@gmail.com', 'senha', '11234512331', 'Agencia 2', '');

INSERT INTO TB_PACOTE_TURISTICO(NOME, CNPJ, DATA_PARTIDA, DURACAO_DIAS, VALOR, DESCRICAO, DESTINOS, FOTOS) 
VALUES 
    ("Estados Unidos", '09756743254', '2021-2-01', 10, 1000.00,'Pacote de viagem para os Estados Unidos, 10 dias de viagem, vamos às compras', 'California, Florida', 'url.foto1, url.foto2'),
    ("Canadá", '11234512331', '2026-10-20', 7, 500.00,'Pacote de viagem para o Canadáo, 7 dias de viagem, vamos às compras', 'Toronto', 'url.foto1, url.foto2');

INSERT INTO TB_COMPRA(CPF, NOME_PACOTE) 
VALUES 
    ('12876899967', 'Estados Unidos');