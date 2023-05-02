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
	urlTermsAndConditionsES VARCHAR(100) NULL,
	urlTermsAndConditionsEN VARCHAR(100) NULL
);

CREATE TABLE verification (
	id INTEGER AUTO_INCREMENT NOT NULL,    
	code CHAR(9) NOT NULL,
	CONSTRAINT PRIMARY KEY (id)
);

CREATE TABLE credential (
	email VARCHAR(255) NOT NULL,
    passwd VARCHAR(50) NOT NULL,
    id_contact INTEGER NOT NULL,
	CONSTRAINT PRIMARY KEY (email, id_contact)
);

CREATE TABLE contact (
    id INTEGER AUTO_INCREMENT NOT NULL,
    name VARCHAR(25) NOT NULL,
    description VARCHAR(255) NULL,
    image LONGBLOB NULL,
    anonymous BOOLEAN NOT NULL,
    lastConnection TIMESTAMP NULL,
    isConnected BOOLEAN NOT NULL,
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


INSERT INTO app VALUES ("https://h6tkxluzxw8nwqic3crz9c.on.drv.tw/pagina/chataim/es/emailVerificationCode.html",
						"https://h6tkxluzxw8nwqic3crz9c.on.drv.tw/pagina/chataim/en/emailVerificationCode.html",
						"https://h6tkxluzxw8nwqic3crz9c.on.drv.tw/pagina/chataim/es/termsAndConditions.html",
						"https://h6tkxluzxw8nwqic3crz9c.on.drv.tw/pagina/chataim/en/termsAndConditions.html");

					
					
# Este comando deshabilita temporalmente la verificaci�n de las caracter�sticas DETERMINISTIC, NO SQL y READS SQL DATA al crear funciones
SET GLOBAL log_bin_trust_function_creators = 1;


/*
 * FUNCTIONs
 */

DELIMITER //
CREATE FUNCTION createVerificationCode(code CHAR(9))
RETURNS INT
NOT DETERMINISTIC
BEGIN
    INSERT INTO verification (code) VALUES (code);
    RETURN LAST_INSERT_ID();
END //
DELIMITER ;

DELIMITER //
CREATE FUNCTION createCredential(
	email VARCHAR(100),
	passwd VARCHAR(100),
	id_contact INTEGER
)
RETURNS INT
NOT DETERMINISTIC
BEGIN
	INSERT INTO credential (email, passwd, id_contact) VALUES (email, passwd, id_contact);
    RETURN LAST_INSERT_ID();
END //
DELIMITER ;

DELIMITER //
CREATE FUNCTION createContact(
	name VARCHAR(25),
	description VARCHAR(255),
	image LONGBLOB,
	anonymous BOOLEAN,
	email VARCHAR(100),
	passwd VARCHAR(100)
)
RETURNS INT
NOT DETERMINISTIC
BEGIN
	DECLARE id INTEGER;
   
	INSERT INTO contact (name, description, image, anonymous, lastConnection, isConnected) VALUES (name, description, image, anonymous, NULL, TRUE);
	
	SET id = LAST_INSERT_ID();
    
    DO createCredential(email, passwd, id);
   
	RETURN id;
END //
DELIMITER ;


# EVENTO PARA ELIMINAR CODIGO DE VERIFICACION
-- CREATE EVENT insertion_event
-- ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 10 MINUTE
-- DO DELETE FROM verification WHERE id = idToRemove
































