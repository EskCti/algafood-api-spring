create table tab_restaurants_responsible (
   restaurant_id bigint not null,
   user_id bigint not null
) engine=InnoDB;

alter table tab_restaurants_responsible
   add constraint restaurant_id_constraint
   foreign key (restaurant_id)
   references tab_restaurants (id);

alter table tab_restaurants_responsible
   add constraint user_id_constraint
   foreign key (user_id)
   references tab_users (id);

