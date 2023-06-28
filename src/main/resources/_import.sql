DELETE FROM tab_restaurants_payments_types;
DELETE FROM tab_products;
DELETE FROM tab_payments_type;
DELETE FROM tab_permissions;
DELETE FROM tab_restaurants;
DELETE FROM tab_cities;
DELETE FROM tab_states;
DELETE FROM tab_kitchens;

ALTER TABLE tab_kitchens AUTO_INCREMENT = 1;
ALTER TABLE tab_states AUTO_INCREMENT = 1;
ALTER TABLE tab_cities AUTO_INCREMENT = 1;
ALTER TABLE tab_restaurants AUTO_INCREMENT = 1;
ALTER TABLE tab_payments_type AUTO_INCREMENT = 1;
ALTER TABLE tab_permissions AUTO_INCREMENT = 1;
ALTER TABLE tab_products AUTO_INCREMENT = 1;


INSERT INTO tab_kitchens (id, name) VALUES (1, "Japonês");
INSERT INTO tab_kitchens (id, name) VALUES (2, "Indiana");
INSERT INTO tab_kitchens (id, name) VALUES (3, "Chines");

INSERT INTO tab_states (id, name) VALUE (1, "Minas Gerais");
INSERT INTO tab_states (id, name) VALUE (2, "São Paulo");
INSERT INTO tab_states (id, name) VALUE (3, "Ceara");

INSERT INTO tab_cities (id, name, state_id) VALUES (1, "Uberlância", 1);
INSERT INTO tab_cities (id, name, state_id) VALUES (2, "Belo Horizonte", 1);
INSERT INTO tab_cities (id, name, state_id) VALUES (3, "São Paulo", 2);
INSERT INTO tab_cities (id, name, state_id) VALUES (4, "Campinas", 2);
INSERT INTO tab_cities (id, name, state_id) VALUES (5, "Fortaleza", 3);

INSERT INTO tab_restaurants (name, shipping_fee, kitchen_id, zip_code, public_place, number, complement, neighborhood, city_id, created_at, updated_at, active) VALUES ("Restaurant 1", 5, 1, "12345678", "logradouro tal", "123", "apto 25", "santana", 1, utc_timestamp, utc_timestamp, true);
INSERT INTO tab_restaurants (name, shipping_fee, kitchen_id, created_at, updated_at, active) VALUES ("Restaurant 2", 10, 2, utc_timestamp, utc_timestamp, true);
INSERT INTO tab_restaurants (name, shipping_fee, kitchen_id, created_at, updated_at, active) VALUES ("Restaurant 3", 0, 3, utc_timestamp, utc_timestamp, true);
INSERT INTO tab_restaurants (name, shipping_fee, kitchen_id, created_at, updated_at, active) VALUES ("Restaurant 4", 0, 3, utc_timestamp, utc_timestamp, true);

INSERT INTO tab_payments_type (id, description) VALUES (1, "Cartão de crédito");
INSERT INTO tab_payments_type (id, description) VALUES (2, "Cartão de débito");
INSERT INTO tab_payments_type (id, description) VALUES (3, "Dinheiro");

INSERT INTO tab_permissions (id, name, description) VALUES (1, "LIST_KITCHEN", "Permit list kitchen");
INSERT INTO tab_permissions (id, name, description) VALUES (2, "EDIT_KITCHEN", "Permit edit kitchen");

INSERT INTO tab_restaurants_payments_types (restaurant_id, payment_type_id) VALUES (1, 1);
INSERT INTO tab_restaurants_payments_types (restaurant_id, payment_type_id) VALUES (1, 2);
INSERT INTO tab_restaurants_payments_types (restaurant_id, payment_type_id) VALUES (1, 3);
INSERT INTO tab_restaurants_payments_types (restaurant_id, payment_type_id) VALUES (2, 1);
INSERT INTO tab_restaurants_payments_types (restaurant_id, payment_type_id) VALUES (2, 2);
INSERT INTO tab_restaurants_payments_types (restaurant_id, payment_type_id) VALUES (2, 3);
INSERT INTO tab_restaurants_payments_types (restaurant_id, payment_type_id) VALUES (3, 1);
INSERT INTO tab_restaurants_payments_types (restaurant_id, payment_type_id) VALUES (3, 2);
INSERT INTO tab_restaurants_payments_types (restaurant_id, payment_type_id) VALUES (3, 3);
INSERT INTO tab_restaurants_payments_types (restaurant_id, payment_type_id) VALUES (4, 1);
INSERT INTO tab_restaurants_payments_types (restaurant_id, payment_type_id) VALUES (4, 2);
INSERT INTO tab_restaurants_payments_types (restaurant_id, payment_type_id) VALUES (4, 3);

INSERT INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 1.01', 'Description product 01', 15, true, 1);
INSERT INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 1.02', 'Description product 02', 10, true, 1);
INSERT INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 1.03', 'Description product 03', 25, true, 1);
INSERT INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 2.01', 'Description product 01', 15, true, 2);
INSERT INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 2.02', 'Description product 02', 10, true, 2);
INSERT INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 3.01', 'Description product 01', 15, true, 3);
INSERT INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 3.02', 'Description product 02', 10, true, 3);
INSERT INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 3.03', 'Description product 03', 25, true, 3);
INSERT INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 4.01', 'Description product 01', 15, true, 4);
INSERT INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 4.02', 'Description product 02', 10, true, 4);