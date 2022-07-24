drop database if exists Turismo;

create database Turismo;

use Turismo;

create table Usuario(
	id bigint not NULL auto_increment, 
	tipo ENUM('Admin', 'Cliente', 'Agencia') NOT NULL,
	email varchar(256) not null,
	senha varchar(8) not null,
	nome varchar(256) not null, 
	CONSTRAINT usuario_pk PRIMARY KEY (id)
);

create table Cliente(
	cpf varchar(14) not null,
	telefone varchar(14), 
	sexo char,
	dataNascimento date not null, 
	id bigint not null,
	CONSTRAINT cliente_pk primary key (cpf), 
	CONSTRAINT sexo_check CHECK (sexo IN ('M', 'F', 'X')), 
	FOREIGN KEY (id) REFERENCES Usuario(id)
);

create table Agencia(
	cnpj varchar(18) not null, 
	descricao varchar(500), 
	id bigint not null,
	CONSTRAINT agencia_pk PRIMARY KEY(cnpj),
	FOREIGN KEY (id) REFERENCES Usuario(id)
);


create table PacoteTuristico(
	Pacoteid bigint NOT NULL auto_increment, 
	cnpjResponsavel varchar(18) NOT NULL, 
	duracao bigint NOT NULL,
	dataPartida date not null,
	valor float NOT NULL,
	descricao varchar(500), 
	CONSTRAINT duracao_valido CHECK (duracao >= 0), 
	CONSTRAINT valor_valido CHECK (valor >=0), 
	CONSTRAINT id_pk PRIMARY KEY(Pacoteid), 
	FOREIGN KEY (cnpjResponsavel) REFERENCES Agencia(cnpj) ON UPDATE CASCADE
);

create table Foto(
	caminho varchar(100) NOT null,
	pacote_id bigint NOT NULL,
	FOREIGN KEY (pacote_id) REFERENCES PacoteTuristico(Pacoteid)
);

create table Destino(
	Destinoid bigint NOT NULL auto_increment, 
	cidade varchar(30) NOT NULL, 
	estado varchar(30) NOT NULL, 
	pais varchar(30) NOT NULL, 
	CONSTRAINT destino_pk PRIMARY KEY(Destinoid)
);

create table PacoteDestino(
	pacote_id bigint NOT NULL, 
	destino_id bigint NOT NULL, 
	FOREIGN KEY (pacote_id) REFERENCES PacoteTuristico(Pacoteid) ON UPDATE CASCADE, 
	FOREIGN KEY (destino_id) REFERENCES Destino(Destinoid) ON UPDATE CASCADE, 
	CONSTRAINT pacote_destino_pk PRIMARY KEY (pacote_id, destino_id)
);

create table PacotesAdquiridos(
	pacote_id bigint NOT NULL,
	usuario_id bigint NOT NULL,
	status ENUM('Comprado', 'Realizado', 'Cancelado') NOT NULL,
	FOREIGN KEY (pacote_id) REFERENCES PacoteTuristico(Pacoteid) ON UPDATE CASCADE,
	FOREIGN KEY (usuario_id) REFERENCES Usuario(id) ON UPDATE CASCADE
);
	

/*
Exemplo de inserção de Cliente

*/
/*
INSERT INTO Cliente(cpf, nome, email, telefone, sexo, senha, dataNascimento)
VALUES ('123.456.789-10', 'Fulano', 'fulano@gmail.com', '(16)98765-4321', 'M', '12345678', '01/01/0001');
*/
/*
----------------------------------------
Exemplo de inserção de Agencia
*/
/*
INSERT INTO Agencia(cnpj, nome, email, descricao)
VALUES ('00.000.000/0001-00', 'Loja', 'exemplo@loja.com', 'Esta é uma descrição de exemplo');
*/
/*
---------------------------------------
Exemplo de inserção de PacoteTuristico, perceba que há 3 tabelas para esse requisito:
uma para o pacote, outra para o destino e uma terceira que representa o relacionamento N:M das duas primeiras
obs: condições de busca mais específicas devem ser usadas quando há vários pacotes com o mesmo cnpjResponsável
*/
/*
INSERT INTO PacoteTuristico(cnpjResponsavel, duracao, valor, descricao)
VALUES ('00.000.000/0001-00', 15, 999.99, 'Esta é uma descrição de exemplo');

INSERT INTO Destino(cidade, estado, pais)
VALUES ('Orlando', 'Florida', 'Estados Unidos');

INSERT INTO PacoteDestino(pacote_id, destino_id)
SELECT Pacoteid, Destinoid FROM PacoteTuristico, Destino
WHERE cnpjResponsavel='00.000.000/0001-00' OR cidade='Orlando';
*/
