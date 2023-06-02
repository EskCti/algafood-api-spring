    create table tab_orders (
       id bigint not null auto_increment,
        complement varchar(60),
        neighborhood varchar(60),
        number varchar(255),
        public_place varchar(60),
        zip_code varchar(8),
        cancellation_date datetime(6),
        confirmation_date datetime,
        created_at datetime not null,
        delivery_date datetime(6),
        order_status smallint,
        shipping_fee decimal(38,2) not null,
        subtotal decimal(38,2) not null,
        value_total decimal(38,2) not null,
        city_id bigint,
        customer_id bigint not null,
        payment_type_id bigint not null,
        restaurant_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table tab_orders_items (
       id bigint not null auto_increment,
        observation varchar(255),
        price_total decimal(38,2) not null,
        price_unit decimal(38,2) not null,
        quantity integer not null,
        order_id bigint not null,
        product_id bigint not null,
        primary key (id)
    ) engine=InnoDB;


    alter table tab_orders
       add constraint FKm8plwtxgielxof28ugf54w3gd
       foreign key (city_id)
       references tab_cities (id);

    alter table tab_orders
       add constraint FKhq4hgc0pmund88kbftair2jt2
       foreign key (customer_id)
       references tab_users (id);

    alter table tab_orders
       add constraint FKkjiurqrlu05ch6leoodop6562
       foreign key (payment_type_id)
       references tab_payments_type (id);

    alter table tab_orders
       add constraint FKkya0nqop0lukmqqcg53bjyli5
       foreign key (restaurant_id)
       references tab_restaurants (id);

    alter table tab_orders_items
       add constraint FKkf8l0vt2wd6qui82vr1rk36m9
       foreign key (order_id)
       references tab_orders (id);

    alter table tab_orders_items
       add constraint FKtbp882m3oraln9jfdjll0xspg
       foreign key (product_id)
       references tab_products (id);
