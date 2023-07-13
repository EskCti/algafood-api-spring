alter table tab_orders add column code varchar(36) not null after id;
update tab_orders set code = uuid();
alter table tab_orders add constraint uk_order_uuid_unique unique (code);