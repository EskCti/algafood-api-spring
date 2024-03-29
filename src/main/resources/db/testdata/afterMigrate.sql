set foreign_key_checks = 0;

lock tables
    tab_restaurants_responsible write,
    tab_restaurants_payments_types write,
    tab_groups_permissions write,
    tab_users_groups write,
    tab_orders_items write,
    tab_orders write,
    tab_products_photos write,
    tab_products write,
    tab_payments_type write,
    tab_permissions write,
    tab_restaurants write,
    tab_cities write,
    tab_states write,
    tab_kitchens write,
    tab_groups write,
    tab_users write,
    oauth2_authorization write,
    oauth2_registered_client write;

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
# DELETE FROM oauth_client_details;
DELETE FROM oauth2_authorization;
DELETE FROM oauth2_registered_client;

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

INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (1, "CONSULT_KITCHENS", "Permit consult kitchens");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (2, "EDIT_KITCHENS", "Permit edit kitchens");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (3, "CONSULT_PAYMENTS_TYPE", "Permit consult type payments");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (4, "EDIT_PAYMENTS_TYPE", "Permit edit type payments");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (5, "CONSULT_CITIES", "Permit consult cities");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (6, "EDIT_CITIES", "Permit edit cities");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (7, "CONSULT_STATES", "Permit consult states");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (8, "EDIT_STATES", "Permit edit states");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (9, "CONSULT_USERS_GROUPS_PERMISSIONS", "Permit consult users, groups and permissions");
INSERT IGNORE INTO tab_permissions (id, name, description) VALUES (10, "EDIT_USERS_GROUPS_PERMISSIONS", "Permit edit users, groups and permissions");
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
INSERT IGNORE INTO tab_users (name, email, password, created_at, updated_at) VALUES ('Vendedor', 'vendedor@algafood.com.br', '$2a$12$GJwKIon9D9kabY/BxFtrv.5WIlLBw6BEkshXH87RvESLuTV75NTWm', utc_timestamp, utc_timestamp);
INSERT IGNORE INTO tab_users (name, email, password, created_at, updated_at) VALUES ('Auxiliar', 'auxiliar@algafood.com.br', '$2a$12$GJwKIon9D9kabY/BxFtrv.5WIlLBw6BEkshXH87RvESLuTV75NTWm', utc_timestamp, utc_timestamp);
INSERT IGNORE INTO tab_users (name, email, password, created_at, updated_at) VALUES ('Cadastrador', 'cadastrador@algafood.com', '$2a$12$GJwKIon9D9kabY/BxFtrv.5WIlLBw6BEkshXH87RvESLuTV75NTWm', utc_timestamp, utc_timestamp);
INSERT IGNORE INTO tab_users (name, email, password, created_at, updated_at) VALUES ('Edson Shideki', 'edson.shideki@algafood.com', '$2a$12$GJwKIon9D9kabY/BxFtrv.5WIlLBw6BEkshXH87RvESLuTV75NTWm', utc_timestamp, utc_timestamp);
INSERT IGNORE INTO tab_users (name, email, password, created_at, updated_at) VALUES ('João da Silva', 'joao.silva@algafood.com', '$2a$12$GJwKIon9D9kabY/BxFtrv.5WIlLBw6BEkshXH87RvESLuTV75NTWm', utc_timestamp, utc_timestamp);
INSERT IGNORE INTO tab_users (name, email, password, created_at, updated_at) VALUES ('Nenhum Pedido', 'nenhum.pedido@algafood.com', '$2a$12$GJwKIon9D9kabY/BxFtrv.5WIlLBw6BEkshXH87RvESLuTV75NTWm', utc_timestamp, utc_timestamp);

INSERT IGNORE INTO tab_users_groups (user_id, group_id) VALUES (1, 1), (2, 2), (3, 3), (4, 4);

INSERT IGNORE INTO tab_restaurants_responsible (restaurant_id, user_id) VALUES (1,1), (2,2), (3,3), (1,5);

INSERT INTO tab_orders
(code,         complement, neighborhood, `number`, public_place, zip_code,   cancellation_date, confirmation_date, created_at,    delivery_date, order_status, shipping_fee, subtotal, value_total, city_id, customer_id, payment_type_id, restaurant_id)
VALUES(uuid(), 'Apto x',   'Bairro Tal', '123',    null,         '12345789', null,              null,              utc_timestamp, utc_timestamp, 'CREATED',    100,          110,      1,           1,       6,           1,               1);

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

# INSERT INTO oauth_client_details (
#     client_id, resource_ids, client_secret,
#     scope, authorized_grant_types, web_server_redirect_uri, authorities,
#     access_token_validity, refresh_token_validity, autoapprove
# ) VALUES (
#     'algafood-web', null, '$2a$12$C/FRk0qQl.stPW8VBkeYeuohjJWMCy3UssVOGSeM6cJKRTfbRGifi',
#     'READ,WRITE', 'password', null, null,
#     60 * 60 * 6, 60 * 24 * 60 * 60, null
# );

# INSERT INTO oauth_client_details (
#     client_id, resource_ids, client_secret,
#     scope, authorized_grant_types, web_server_redirect_uri, authorities,
#     access_token_validity, refresh_token_validity, autoapprove
# ) VALUES (
#     'foodanalytics', null, '$2a$12$x6eufoFvhbzpj.vo8qJCNOusvmGVvVioDJERYB/1SygZma7N1sVp.',
#     'READ,WRITE', 'authorization_code', 'http://www.foodanalytics.local:8082', null,
#     null, null, null
# );

# INSERT INTO oauth_client_details (
#     client_id, resource_ids, client_secret,
#     scope, authorized_grant_types, web_server_redirect_uri, authorities,
#     access_token_validity, refresh_token_validity, autoapprove
# ) VALUES (
#     'faturamento', null, '$2a$12$fSG1uM9XlfWhdIWpv7P2iO/VuRga2N9l5FTUdgwYOS81hW73ZIz9.',
#     'READ,WRITE', 'client_credentials', null, 'CONSULT_ORDERS,GENERATE_REPORTS',
#     null, null, null
# );

INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('1', 'algafood-backend', '2023-12-14 09:16:38', '$2a$10$LYPEC6nmWpPpDKe3xDG2n.A5yRNb3B56yuu7Jtyq6wg3AWmTa/KR.', NULL, 'Algafood Backend', 'client_secret_basic', 'client_credentials', '', 'READ', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('2', 'algafood-web', '2023-12-14 09:16:38', '$2a$10$8sAVr8Vj/b8R18lvbozAbez82OH7cnaElRHzM2xy2E16kbEAyxp42', NULL, 'Algafood Web', 'client_secret_basic', 'refresh_token,authorization_code', 'http://127.0.0.1/swagger-ui/oauth2-redirect.html,http://127.0.0.1/authorized', 'READ,WRITE', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":false,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",86400.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('3', 'foodanalytics', '2023-12-14 09:16:38', '$2a$10$lIeKhXRllilZNjYNZqBhqOgsFJvi1i3f.yJAqjch7DDzQJ4eBp5qa', NULL, 'Food Analytics', 'client_secret_basic', 'authorization_code', 'http://www.foodanalytics.local:8082', 'READ,WRITE', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

unlock tables;