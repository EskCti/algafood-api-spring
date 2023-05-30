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

INSERT INTO tab_restaurants (name, shipping_fee, kitchen_id, zip_code, public_place, number, complement, neighborhood, city_id, created_at, updated_at) VALUES ("Restaurant 1", 5, 1, "12345678", "logradouro tal", "123", "apto 25", "santana", 1, utc_timestamp, utc_timestamp);
INSERT INTO tab_restaurants (name, shipping_fee, kitchen_id, created_at, updated_at) VALUES ("Restaurant 2", 10, 2, utc_timestamp, utc_timestamp);
INSERT INTO tab_restaurants (name, shipping_fee, kitchen_id, created_at, updated_at) VALUES ("Restaurant 3", 0, 3, utc_timestamp, utc_timestamp);
INSERT INTO tab_restaurants (name, shipping_fee, kitchen_id, created_at, updated_at) VALUES ("Restaurant 4", 0, 3, utc_timestamp, utc_timestamp);

INSERT INTO tab_payments_type (id, description) VALUES (1, "Cartão de crédito");
INSERT INTO tab_payments_type (id, description) VALUES (2, "Cartão de débito");
INSERT INTO tab_payments_type (id, description) VALUES (3, "Dinheiro");

INSERT INTO tab_permissions (id, name, description) VALUES (1, "LIST_KITCHEN", "Permit list kitchen");
INSERT INTO tab_permissions (id, name, description) VALUES (2, "EDIT_KITCHEN", "Permit edit kitchen");

INSERT INTO tab_restaurants_payments_types (restaurant_id, payment_type_id) VALUES (1, 1), (1, 2), (1, 3), (2, 1), (2, 2), (2, 3), (3, 1), (3, 2), (3, 3), (4, 1), (4, 2), (4, 3);
