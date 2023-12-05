DELETE FROM tab_restaurants_responsible;
DELETE FROM tab_restaurants_payments_types;
DELETE FROM tab_groups_permissions;
DELETE FROM tab_users_groups;
DELETE FROM tab_orders_items;
DELETE FROM tab_orders;
DELETE FROM tab_products_photos;
DELETE FROM tab_products;
DELETE FROM tab_payments_type;
DELETE FROM tab_permissions;
DELETE FROM tab_restaurants;
DELETE FROM tab_cities;
DELETE FROM tab_states;
DELETE FROM tab_kitchens;
DELETE FROM tab_groups;
DELETE FROM tab_users;

ALTER TABLE tab_kitchens AUTO_INCREMENT = 1;
ALTER TABLE tab_states AUTO_INCREMENT = 1;
ALTER TABLE tab_cities AUTO_INCREMENT = 1;
ALTER TABLE tab_restaurants AUTO_INCREMENT = 1;
ALTER TABLE tab_payments_type AUTO_INCREMENT = 1;
ALTER TABLE tab_permissions AUTO_INCREMENT = 1;
ALTER TABLE tab_products AUTO_INCREMENT = 1;
ALTER TABLE tab_groups AUTO_INCREMENT = 1;
ALTER TABLE tab_users AUTO_INCREMENT = 1;
ALTER TABLE tab_orders_items AUTO_INCREMENT = 1;
ALTER TABLE tab_orders AUTO_INCREMENT = 1;

INSERT IGNORE INTO tab_kitchens (id, name) VALUES (1, "Japonês");
INSERT IGNORE INTO tab_kitchens (id, name) VALUES (2, "Indiana");
INSERT IGNORE INTO tab_kitchens (id, name) VALUES (3, "Chines");

INSERT IGNORE INTO tab_states (id, name) VALUE (1, "Minas Gerais");
INSERT IGNORE INTO tab_states (id, name) VALUE (2, "São Paulo");
INSERT IGNORE INTO tab_states (id, name) VALUE (3, "Ceara");

INSERT IGNORE INTO tab_cities (id, name, state_id) VALUES (1, "Uberlância", 1);
INSERT IGNORE INTO tab_cities (id, name, state_id) VALUES (2, "Belo Horizonte", 1);
INSERT IGNORE INTO tab_cities (id, name, state_id) VALUES (3, "São Paulo", 2);
INSERT IGNORE INTO tab_cities (id, name, state_id) VALUES (4, "Campinas", 2);
INSERT IGNORE INTO tab_cities (id, name, state_id) VALUES (5, "Fortaleza", 3);

INSERT IGNORE INTO tab_restaurants (name, shipping_fee, kitchen_id, zip_code, public_place, number, complement, neighborhood, city_id, created_at, updated_at, active, open) VALUES ("Restaurant 1", 5, 1, "12345678", "logradouro tal", "123", "apto 25", "santana", 1, utc_timestamp, utc_timestamp, true, false);
INSERT IGNORE INTO tab_restaurants (name, shipping_fee, kitchen_id, created_at, updated_at, active, open) VALUES ("Restaurant 2", 10, 2, utc_timestamp, utc_timestamp, true, false);
INSERT IGNORE INTO tab_restaurants (name, shipping_fee, kitchen_id, created_at, updated_at, active, open) VALUES ("Restaurant 3", 0, 3, utc_timestamp, utc_timestamp, true, false);
INSERT IGNORE INTO tab_restaurants (name, shipping_fee, kitchen_id, created_at, updated_at, active, open) VALUES ("Restaurant 4", 0, 3, utc_timestamp, utc_timestamp, true, false);

INSERT IGNORE INTO tab_payments_type (id, description, updated_at) VALUES (1, "Cartão de crédito", utc_timestamp);
INSERT IGNORE INTO tab_payments_type (id, description, updated_at) VALUES (2, "Cartão de débito", utc_timestamp);
INSERT IGNORE INTO tab_payments_type (id, description, updated_at) VALUES (3, "Dinheiro", utc_timestamp);

INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (1, "CONSULT_KITCHEN", "Permit consult kitchens");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (2, "EDIT_KITCHEN", "Permit edit kitchens");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (3, "CONSULT_PAYMENT_TYPE", "Permit consult type payments");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (4, "EDIT_PAYMENT_TYPE", "Permit edit type payments");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (5, "CONSULT_CITIES", "Permit consult cities");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (6, "EDIT_CITIES", "Permit edit cities");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (7, "CONSULT_STATES", "Permit consult states");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (8, "EDIT_STATES", "Permit edit states");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (9, "CONSULT_USERS", "Permit consult users");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (10, "EDIT_USERS", "Permit edit users");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (11, "CONSULT_RESTAURANTS", "Permit consult restaurants");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (12, "EDIT_RESTAURANTS", "Permit edit restaurants");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (13, "CONSULT_PRODUCTS", "Permit consult products");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (14, "EDIT_PRODUCTS", "Permit edit products");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (15, "CONSULT_ORDERS", "Permit consult orders");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (16, "MANAGER_ORDERS", "Permit manager orders");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (17, "GENERATE_REPORTS", "Permit generate reports");

INSERT IGNORE INTO tab_restaurants_payments_types (restaurant_id, payment_type_id) VALUES (1, 1), (1, 2), (1, 3), (2, 1), (2, 2), (2, 3), (3, 1), (3, 2), (3, 3), (4, 1), (4, 2), (4, 3);

INSERT IGNORE INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 1.01', 'Description product 01', 15, true, 1);
INSERT IGNORE INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 1.02', 'Description product 02', 10, false, 1);
INSERT IGNORE INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 1.03', 'Description product 03', 25, true, 1);
INSERT IGNORE INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 2.01', 'Description product 01', 15, true, 2);
INSERT IGNORE INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 2.02', 'Description product 02', 10, true, 2);
INSERT IGNORE INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 3.01', 'Description product 01', 15, true, 3);
INSERT IGNORE INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 3.02', 'Description product 02', 10, true, 3);
INSERT IGNORE INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 3.03', 'Description product 03', 25, true, 3);
INSERT IGNORE INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 4.01', 'Description product 01', 15, true, 4);
INSERT IGNORE INTO tab_products (name, description, price, active, restaurant_id) VALUES ('Product 4.02', 'Description product 02', 10, true, 4);

INSERT IGNORE INTO tab_groups (id, name) VALUES (1, "Gerente"), (2, "Vendedor"), (3, "Secretária"), (4, "Cadastradir");

# Adiciona todas as permissões no grupo do gerente
INSERT IGNORE INTO tab_groups_permissions (group_id, permission_id)
SELECT 1, id from tab_permissions;

# Adiciona todas as permissões no grupo do vendedor
INSERT IGNORE INTO tab_groups_permissions (group_id, permission_id)
SELECT 2, id from tab_permissions WHERE name LIKE "CONSULT_%";

INSERT IGNORE INTO tab_groups_permissions (group_id, permission_id) VALUES (2, 14);

# Adiciona todas as permissões no grupo do auxiliar
INSERT IGNORE INTO tab_groups_permissions (group_id, permission_id)
SELECT 3, id from tab_permissions WHERE name LIKE "CONSULT_%";

# Adiciona todas as permissões no grupo do cadastrador
INSERT IGNORE INTO tab_groups_permissions (group_id, permission_id)
SELECT 4, id from tab_permissions WHERE name LIKE "%_RESTAURANTS" OR name LIKE "%_PRODUCTS";

INSERT IGNORE INTO tab_users (name, email, password, created_at, updated_at) VALUES ('Administrador', 'admin@algafood.com.br', '$2a$12$GJwKIon9D9kabY/BxFtrv.5WIlLBw6BEkshXH87RvESLuTV75NTWm', utc_timestamp, utc_timestamp);
INSERT IGNORE INTO tab_users (name, email, password, created_at, updated_at) VALUES ('Contato', 'contato@algafood.com.br', '$2a$12$GJwKIon9D9kabY/BxFtrv.5WIlLBw6BEkshXH87RvESLuTV75NTWm', utc_timestamp, utc_timestamp);
INSERT IGNORE INTO tab_users (name, email, password, created_at, updated_at) VALUES ('Edson S Kokado', 'edsonskokado@algafood.com.br', '$2a$12$GJwKIon9D9kabY/BxFtrv.5WIlLBw6BEkshXH87RvESLuTV75NTWm', utc_timestamp, utc_timestamp);
INSERT IGNORE INTO tab_users (name, email, password, created_at, updated_at) VALUES ('João Silva', 'email.test.joaosilva@gmail.com', '$2a$12$GJwKIon9D9kabY/BxFtrv.5WIlLBw6BEkshXH87RvESLuTV75NTWm', utc_timestamp, utc_timestamp);

INSERT IGNORE INTO tab_users_groups (user_id, group_id) VALUES (1, 1), (2, 1), (2, 2), (2, 3), (2, 4), (4, 1);

INSERT IGNORE INTO tab_restaurants_responsible (restaurant_id, user_id) VALUES (1,1), (2,2), (3,3);

INSERT INTO tab_orders
(code, complement, neighborhood, `number`, public_place, zip_code, cancellation_date, confirmation_date, created_at, delivery_date, order_status, shipping_fee, subtotal, value_total, city_id, customer_id, payment_type_id, restaurant_id)
VALUES(uuid(), 'Apto x', 'Bairro Tal', '123', null, '12345789', null, null, utc_timestamp, utc_timestamp, 'CREATED', 100, 110, 1, 1, 4, 1, 1);

INSERT INTO tab_orders_items
(observation, price_total, price_unit, quantity, order_id, product_id)
VALUES('Tal', 40, 40, 1, 1, 1);

INSERT INTO tab_orders_items
(observation, price_total, price_unit, quantity, order_id, product_id)
VALUES('Tal x', 60, 60, 1, 1, 2);

INSERT INTO tab_orders
(code, complement, neighborhood, `number`, public_place, zip_code, cancellation_date, confirmation_date, created_at, delivery_date, order_status, shipping_fee, subtotal, value_total, city_id, customer_id, payment_type_id, restaurant_id)
VALUES(uuid(), 'Apto y', 'Bairro Tal y', '123', null, '12345789', null, null, utc_timestamp, utc_timestamp, 'CREATED', 100, 100, 2, 2, 2, 2, 2);

INSERT INTO tab_orders_items
(observation, price_total, price_unit, quantity, order_id, product_id)
VALUES('Tal y', 40, 40, 1, 2, 3);

INSERT INTO tab_orders_items
(observation, price_total, price_unit, quantity, order_id, product_id)
VALUES('Tal y', 60, 60, 1, 2, 4);

INSERT INTO tab_orders
(code, complement, neighborhood, `number`, public_place, zip_code, cancellation_date, confirmation_date, created_at, delivery_date, order_status, shipping_fee, subtotal, value_total, city_id, customer_id, payment_type_id, restaurant_id)
VALUES(uuid(), 'Apto x', 'Bairro Tal', '123', null, '12345789', null, null, utc_timestamp, utc_timestamp, 'CONFIRMED', 100, 110, 4, 1, 1, 1, 1);

INSERT INTO tab_orders_items
(observation, price_total, price_unit, quantity, order_id, product_id)
VALUES('Tal', 40, 40, 1, 1, 1);

INSERT INTO tab_orders_items
(observation, price_total, price_unit, quantity, order_id, product_id)
VALUES('Tal x', 60, 60, 1, 1, 2);

INSERT INTO tab_orders
(code, complement, neighborhood, `number`, public_place, zip_code, cancellation_date, confirmation_date, created_at, delivery_date, order_status, shipping_fee, subtotal, value_total, city_id, customer_id, payment_type_id, restaurant_id)
VALUES(uuid(), 'Apto y', 'Bairro Tal y', '123', null, '12345789', null, null, utc_timestamp, utc_timestamp, 'DELIVERED', 100, 100, 3, 2, 2, 2, 2);

INSERT INTO tab_orders_items
(observation, price_total, price_unit, quantity, order_id, product_id)
VALUES('Tal y', 40, 40, 1, 2, 3);

INSERT INTO tab_orders_items
(observation, price_total, price_unit, quantity, order_id, product_id)
VALUES('Tal y', 60, 60, 1, 2, 4);
