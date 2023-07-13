ALTER TABLE tab_restaurants ADD active tinyint(1) not null;
UPDATE tab_restaurants SET active = true;