CREATE DATABASE TrabalhoWEB;

USE TrabalhoWEB;

CREATE TABLE usuario (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`tipo` INT NOT NULL,
`email` VARCHAR(255),
`nome` VARCHAR(255),
`senha` VARCHAR(255)
);

CREATE TABLE noticia (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
conteudo varchar(255),
`id_categoria` int NOT NULL,
CONSTRAINT fk_categoria FOREIGN KEY ( `id_categoria` ) REFERENCES `categoria` ( `id` )
);

CREATE TABLE categoria (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nome varchar(255)
);

CREATE TABLE comentario (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`id_noticia` INT NOT NULL,
conteudo varchar(255),
`id_usuario` INT NOT NULL,
CONSTRAINT fk_noticia FOREIGN KEY ( `id_noticia` ) REFERENCES `noticia` ( `id` )
);

ALTER TABLE comentario ADD CONSTRAINT fk_usuario_comentario FOREIGN KEY (`id_usuario`) REFERENCES `usuario`(`id`);

CREATE TABLE classificado (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
conteudo varchar(255)
);

CREATE TABLE oferta_compra (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 `id_classificado` INT NOT NULL,
 CONSTRAINT fk_classificado FOREIGN KEY ( `id_classificado` ) REFERENCES `classificado` ( `id` ) ,
 `id_usuario` INT NOT NULL,
 CONSTRAINT fk_usuario FOREIGN KEY ( `id_usuario` ) REFERENCES `usuario` ( `id` ) ,
conteudo varchar(255)
);
