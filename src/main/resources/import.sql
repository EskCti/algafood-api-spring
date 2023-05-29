INSERT INTO tab_kitchens (id, name) VALUES (1, "Japonês");
INSERT INTO tab_kitchens (id, name) VALUES (2, "Indiana");
INSERT INTO tab_kitchens (id, name) VALUES (3, "Chines");

INSERT INTO tab_restaurants (name, shipping_fee, kitchen_id) VALUES ("Restaurant 1", 5, 1);
INSERT INTO tab_restaurants (name, shipping_fee, kitchen_id) VALUES ("Restaurant 2", 10, 2);
INSERT INTO tab_restaurants (name, shipping_fee, kitchen_id) VALUES ("Restaurant 3", 0, 3);
INSERT INTO tab_restaurants (name, shipping_fee, kitchen_id) VALUES ("Restaurant 4", 0, 3);

INSERT INTO tab_states (id, name) VALUE (1, "Minas Gerais");
INSERT INTO tab_states (id, name) VALUE (2, "São Paulo");
INSERT INTO tab_states (id, name) VALUE (3, "Ceara");

INSERT INTO tab_cities (id, name, state_id) VALUES (1, "Uberlância", 1);
INSERT INTO tab_cities (id, name, state_id) VALUES (2, "Belo Horizonte", 1);
INSERT INTO tab_cities (id, name, state_id) VALUES (3, "São Paulo", 2);
INSERT INTO tab_cities (id, name, state_id) VALUES (4, "Campinas", 2);
INSERT INTO tab_cities (id, name, state_id) VALUES (5, "Fortaleza", 3);

INSERT INTO tab_payments_type (id, description) VALUES (1, "Cartão de crédito");
INSERT INTO tab_payments_type (id, description) VALUES (2, "Cartão de débito");
INSERT INTO tab_payments_type (id, description) VALUES (3, "Dinheiro");

INSERT INTO tab_permissions (id, name, description) VALUES (1, "LIST_KITCHEN", "Permit list kitchen")
INSERT INTO tab_permissions (id, name, description) VALUES (2, "EDIT_KITCHEN", "Permit edit kitchen")