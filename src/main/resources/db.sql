DROP TABLE if exists OFFER;
DROP TABLE if exists PRODUCT;
DROP TABLE if exists USER_APP;
DROP TABLE if exists ADDRESS;
DROP TABLE if exists CATEGORY;


CREATE TABLE ADDRESS
(
    id_address  INT AUTO_INCREMENT PRIMARY KEY,
    street_name VARCHAR(255),
    city_name   VARCHAR(255),
    state_name  VARCHAR(255),
    nb_street   INT,
    zip_code    VARCHAR(255)
);

CREATE TABLE USER_APP
(
    id_user_app   INT AUTO_INCREMENT PRIMARY KEY,
    pseudo        VARCHAR(255),
    firstname     VARCHAR(255),
    lastname      VARCHAR(255),
    email         VARCHAR(255),
    phone_number  VARCHAR(255),
    password      VARCHAR(255),
    role_user     VARCHAR(255),
    accountWallet FLOAT,
    id_address INT,
    CONSTRAINT fk_user_address FOREIGN KEY (id_address) REFERENCES ADDRESS(id_address)
);
CREATE TABLE CATEGORY
(
    id_category   INT AUTO_INCREMENT PRIMARY KEY,
    name_category VARCHAR(255)
);

CREATE TABLE PRODUCT (
                         id_product INT AUTO_INCREMENT PRIMARY KEY,
                         name_product VARCHAR(255),
                         description VARCHAR(255),
                         starting_value FLOAT,
                         path_to_image VARCHAR(255),
                         start_date TIMESTAMP,
                         end_date TIMESTAMP,
                         id_seller INT,
                         id_buyer INT,
                         category_id INT,
                         CONSTRAINT fk_product_user FOREIGN KEY (id_seller) REFERENCES USER_APP(id_user_app),
                         CONSTRAINT fk_category_product FOREIGN KEY (category_id) REFERENCES CATEGORY(id_category)
CREATE TABLE PRODUCT
(
    id_product     INT AUTO_INCREMENT PRIMARY KEY,
    name_product   VARCHAR(255),
    description    VARCHAR(255),
    starting_value FLOAT,
    path_to_image  VARCHAR(255),
    start_date     TIMESTAMP,
    end_date       TIMESTAMP,
    id_seller      INT,
    category_id    INT,
    CONSTRAINT fk_product_user FOREIGN KEY (id_seller) REFERENCES USER_APP (id_user_app) ON DELETE CASCADE,
    CONSTRAINT fk_category_product FOREIGN KEY (category_id) REFERENCES CATEGORY (id_category)
);


CREATE TABLE OFFER
(
    id_offer            INT AUTO_INCREMENT PRIMARY KEY,
    value_offer         FLOAT,
    date_offer          TIMESTAMP,
    id_user_app         INT,
    id_product          INT,
    id_delivery_address INT,
    CONSTRAINT fk_offer_user_app FOREIGN KEY (id_user_app) REFERENCES USER_APP (id_user_app) ON DELETE CASCADE,
    CONSTRAINT fk_offer_product FOREIGN KEY (id_product) REFERENCES PRODUCT (id_product) ON DELETE CASCADE,
    CONSTRAINT fk_offer_delivery_address FOREIGN KEY (id_delivery_address) REFERENCES ADDRESS (id_address)
);



