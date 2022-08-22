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
	sexo varchar(14),
	dataNascimento date not null, 
	id bigint not null,
	CONSTRAINT cliente_pk primary key (cpf) 
);

create table Agencia(
	cnpj varchar(18) not null unique, 
	descricao varchar(500), 
	id int not null,
	PRIMARY KEY (id)
);

create table PacoteTuristico(
	id INT NOT NULL AUTO_INCREMENT,
	cnpj_agencia varchar(255) NOT NULL,
	destino_cidade varchar(255) NOT NULL,
	destino_estado varchar(255) NOT NULL,
	destino_pais varchar(255) NOT NULL,
	data_partida TIMESTAMP NOT NULL,
	duracao_dias INT NOT NULL,
	valor FLOAT NOT NULL,
	descricao varchar(255) NOT NULL,
	qtd_foto varchar(255) NOT NULL,
	PRIMARY KEY (id)
);

create table Destino(
	Destinoid bigint NOT NULL auto_increment, 
	cidade varchar(30) NOT NULL, 
	estado varchar(30) NOT NULL, 
	pais varchar(30) NOT NULL, 
	CONSTRAINT destino_pk PRIMARY KEY(Destinoid)
);

CREATE TABLE Foto (
	id_pacote INT NOT NULL,
	url varchar(255) NOT NULL
);

CREATE TABLE Compra (
	id INT NOT NULL AUTO_INCREMENT,
	id_cliente INT NOT NULL,
	id_pacote INT NOT NULL,
	PRIMARY KEY (id)
);

INSERt INTO Usuario(nome, email, senha, tipo) values ('administrador', 'admin@admin.com', 'admin', 'admin');

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
