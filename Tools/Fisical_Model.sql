/* RAILWAY */

/* GET .pem */
# https://curl.se/ca/

/* CAMBIAR ENCODING */
# ALTER DATABASE <database_name> CHARACTER SET utf8 COLLATE utf8_general_ci;


-- CREATE DATABASE chataim
-- 	CHARACTER SET utf8
-- 	COLLATE UTF8_SPANISH_CI;
-- 	
-- USE chataim;


CREATE TABLE app (
	urlEmailVerificationCodeES VARCHAR(100) NULL,
	urlEmailVerificationCodeEN VARCHAR(100) NULL,
	urlVerificationCode VARCHAR(100) NULL
);

CREATE TABLE verification (
	id INTEGER AUTO_INCREMENT NOT NULL,    
	code CHAR(9) NOT NULL,
	CONSTRAINT PRIMARY KEY (id)
);

CREATE TABLE credential (
	id INTEGER AUTO_INCREMENT NOT NULL,
	email VARCHAR(100) NOT NULL,
    passwd VARCHAR(100) NOT NULL,
    id_contact INTEGER NOT NULL,
	CONSTRAINT PRIMARY KEY (id, id_contact)
);

CREATE TABLE contact (
    id INTEGER AUTO_INCREMENT NOT NULL,
    name VARCHAR(25) NOT NULL,
    description VARCHAR(255) NULL,
    image BLOB NULL,
    anonymous BOOLEAN NOT NULL,
    last_connection TIMESTAMP NOT NULL,
    CONSTRAINT PRIMARY KEY (id)
);

CREATE TABLE ggroup (
    id INTEGER AUTO_INCREMENT NOT NULL,
    name VARCHAR(100),
    description VARCHAR(255),
    id_chat INTEGER NOT NULL,
    CONSTRAINT PRIMARY KEY (id, id_chat)
);

CREATE TABLE chat (
    id INTEGER AUTO_INCREMENT NOT NULL,
    CONSTRAINT PRIMARY KEY (id)
);

CREATE TABLE message (
    id INTEGER AUTO_INCREMENT NOT NULL,
    content TEXT NOT NULL,
    file BLOB NOT NULL,
    send_datetime TIMESTAMP,
    id_contact INTEGER NOT NULL,
    id_chat INTEGER NOT NULL,
    CONSTRAINT PRIMARY KEY (id, id_contact, id_chat)
);

CREATE TABLE ascii (
	id INTEGER AUTO_INCREMENT NOT NULL,
	content VARCHAR(255) NOT NULL,	
	CONSTRAINT PRIMARY KEY (id)
);


CREATE TABLE belong (
    id_contact INTEGER NOT NULL,
    id_group INTEGER NOT NULL,
    CONSTRAINT PRIMARY KEY (id_contact, id_group)
);

CREATE TABLE added_contact (
    id_contact_propietary INTEGER NOT NULL,
    id_contact_contact INTEGER NOT NULL,
    id_chat INTEGER NOT NULL,
    name VARCHAR(25) NULL,
    last_message INTEGER NULL,
    CONSTRAINT PRIMARY KEY (id_contact_propietary, id_contact_contact, id_chat)
);

CREATE TABLE contains_ascii (
	id_ascii INTEGER NOT NULL,
	id_message INTEGER NOT NULL,
	position INTEGER NOT NULL,
	CONSTRAINT PRIMARY KEY (id_ascii, id_message, position)
);


ALTER TABLE credential ADD CONSTRAINT FOREIGN KEY (id_contact) REFERENCES contact(id);

ALTER TABLE ggroup ADD CONSTRAINT FOREIGN KEY (id_chat) REFERENCES chat(id);

ALTER TABLE message ADD CONSTRAINT FOREIGN KEY (id_contact) REFERENCES contact(id);
ALTER TABLE message ADD CONSTRAINT FOREIGN KEY (id_chat) REFERENCES chat(id);

ALTER TABLE belong ADD CONSTRAINT FOREIGN KEY (id_contact) REFERENCES contact(id);
ALTER TABLE belong ADD CONSTRAINT FOREIGN KEY (id_group) REFERENCES ggroup(id);

ALTER TABLE added_contact ADD CONSTRAINT FOREIGN KEY (id_contact_propietary) REFERENCES contact(id);
ALTER TABLE added_contact ADD CONSTRAINT FOREIGN KEY (id_contact_contact) REFERENCES contact(id);
ALTER TABLE added_contact ADD CONSTRAINT FOREIGN KEY (id_chat) REFERENCES chat(id);

ALTER TABLE contains_ascii ADD CONSTRAINT FOREIGN KEY (id_ascii) REFERENCES ascii(id);
ALTER TABLE contains_ascii ADD CONSTRAINT FOREIGN KEY (id_message) REFERENCES message(id);


INSERT INTO app VALUES ("https://chataim.000webhostapp.com/es/emailVerificationCode.html",
						"https://chataim.000webhostapp.com/en/emailVerificationCode.html",
						"NULL");

					
					
# Este comando deshabilita temporalmente la verificación de las características DETERMINISTIC, NO SQL y READS SQL DATA al crear funciones
SET GLOBAL log_bin_trust_function_creators = 1;


DELIMITER //
CREATE FUNCTION createVerificationCode(code CHAR(9))
RETURNS INT
NOT DETERMINISTIC
BEGIN
    INSERT INTO verification (code) VALUES (code);
    RETURN LAST_INSERT_ID();
END //
DELIMITER ;


# EVENTO PARA ELIMINAR CODIGO DE VERIFICACION
-- CREATE EVENT insertion_event
-- ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 10 MINUTE
-- DO DELETE FROM verification WHERE id = idToRemove
































