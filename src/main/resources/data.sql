DROP TABLE if exists OFFER;
DROP TABLE if exists PRODUCT;
DROP TABLE if exists USER_APP;
DROP TABLE if exists ADDRESS;


CREATE TABLE ADDRESS (
    id_address INT AUTO_INCREMENT PRIMARY KEY,
    street_name VARCHAR(255),
    city_name VARCHAR(255),
    state_name VARCHAR(255),
    nb_street INT,
    zip_code VARCHAR(255)
);

CREATE TABLE USER_APP (
    id_user_app INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(255),
    password VARCHAR(255),
    role_user VARCHAR(255),
    accountWallet FLOAT,
    id_address INT,
    CONSTRAINT fk_user_address FOREIGN KEY (id_address) REFERENCES ADDRESS(id_address)
);

CREATE TABLE PRODUCT (
    id_product INT AUTO_INCREMENT PRIMARY KEY,
    name_product VARCHAR(255),
    description VARCHAR(255),
    starting_value FLOAT,
    path_to_image VARCHAR(255),
    date_final DATETIME,
    id_seller INT,
    CONSTRAINT fk_product_user FOREIGN KEY (id_seller) REFERENCES USER_APP(id_user_app)
);


CREATE TABLE OFFER (
                       id_offer INT AUTO_INCREMENT PRIMARY KEY,
                       value_offer FLOAT,
                       date_offer TIMESTAMP,
                       id_user_app INT,
                       id_product INT,
                       id_delivery_address INT,
                       CONSTRAINT fk_offer_user_app FOREIGN KEY (id_user_app) REFERENCES USER_APP(id_user_app),
                       CONSTRAINT fk_offer_product FOREIGN KEY (id_product) REFERENCES PRODUCT(id_product),
                       CONSTRAINT fk_offer_delivery_address FOREIGN KEY (id_delivery_address) REFERENCES ADDRESS(id_address)
);

INSERT INTO ADDRESS (street_name, city_name, state_name, nb_street, zip_code)
 VALUES ('street_name', 'city_name', 'state_name', 1, '35000');
INSERT INTO USER_APP (firstname,lastname,email,phone_number, password, role_user, accountWallet, id_address)
 VALUES ('firstname', 'lastname', 'email', '0600000000', 'password', 'user,admin', 1, 1);
INSERT INTO USER_APP (firstname,lastname,email,phone_number, password, role_user, accountWallet, id_address)
VALUES ('firstname2', 'lastname2', 'email2', '2', 'password', 'user,admin', 1, 1);
INSERT INTO PRODUCT (name_product, description, starting_value, path_to_image, date_final, id_seller)
 VALUES ('name_product', 'description', 1, 'pathToImg', CURRENT_TIMESTAMP, 1);
INSERT INTO OFFER (value_offer, date_offer, id_user_app, id_product, id_delivery_address)
VALUES (26,NOW(),1,1,1);

