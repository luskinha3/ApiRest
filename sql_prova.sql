CREATE DATABASE IF NOT EXISTS estados_cidades;

USE estados_cidades;

CREATE TABLE estados (

id_estado INT  AUTO_INCREMENT, 
nome_estado VARCHAR(25) NOT NULL,
PRIMARY KEY (id_estado)
);

CREATE TABLE cidades (
id_cidade INT  AUTO_INCREMENT, 
nome_cidade VARCHAR(50) NOT NULL,
populacao INT NOT NULL,
area DOUBLE(6,3) NOT NULL,
estado INT NOT NULL,
PRIMARY KEY (id_cidade),
FOREIGN KEY (estado)
REFERENCES estados (id_estado) 
);




INSERT INTO estados (nome_estado) VALUES ('PB');
INSERT INTO cidades (nome_cidade,populacao,area,estado) VALUES ('nova floresta',10000,58.839,1);
INSERT INTO cidades (nome_cidade,populacao,area,estado) VALUES ('cuité',20334,741.840,1);
INSERT INTO estados (nome_estado) VALUES ('RN');
INSERT INTO cidades (nome_cidade,populacao,area,estado) VALUES ('jaçanã',9341,54.561,2);
INSERT INTO cidades (nome_cidade,populacao,area,estado) VALUES ('coronel',5405,185.752,2);

SELECT nome_cidade from cidades where cidades.populacao > 10000;

SELECT  estados.nome_estado, SUM(area) AS area
FROM cidades INNER JOIN estados
	ON cidades.estado = estados.id_estado
GROUP BY estado;

SELECT estados.nome_estado, COUNT(nome_cidade) AS nome_cidade FROM cidades INNER JOIN estados
	ON cidades.estado = estados.id_estado
GROUP BY estado;

SELECT id_cidade, nome_cidade, populacao, area, estados.nome_estado FROM cidades INNER JOIN estados on cidades.estado = estados.id_estado WHERE populacao  BETWEEN 9000 AND 21000;

SELECT * FROM cidades;
SELECT * FROM estados;
	 		




