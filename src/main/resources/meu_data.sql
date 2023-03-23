
CREATE TABLE LOJA(
	ID_LOJA INTEGER PRIMARY KEY AUTO_INCREMENT,
	NOME_LOJA VARCHAR(100),
	CNPJ VARCHAR(14),
	DATA_CADASTRO DATE
);

CREATE TABLE TIPO(
	ID_TIPO INTEGER PRIMARY KEY AUTO_INCREMENT,
	TIPO_VENDEDOR VARCHAR(50)
);

CREATE TABLE VENDEDOR(
	ID INTEGER PRIMARY KEY AUTO_INCREMENT,
	NOME VARCHAR(100),
	LOJAS INTEGER REFERENCES LOJA(ID_LOJA),
	TIPO_VENDEDOR INTEGER REFERENCES TIPO(ID_TIPO),
	DATA_CADASTRO DATE
);
