ALTER TABLE tab_restaurants ADD open tinyint(1) not null;
UPDATE tab_restaurants SET open = false;