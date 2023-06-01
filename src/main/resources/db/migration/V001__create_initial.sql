
    create table tab_cities (
       id bigint not null auto_increment,
        name varchar(100) not null,
        state_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table tab_groups (
       id bigint not null auto_increment,
        name varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tab_groups_permissions (
       group_id bigint not null,
        permission_id bigint not null
    ) engine=InnoDB;

    create table tab_kitchens (
       id bigint not null auto_increment,
        name varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tab_payments_type (
       id bigint not null auto_increment,
        description varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tab_permissions (
       id bigint not null auto_increment,
        description varchar(255) not null,
        name varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tab_products (
       id bigint not null auto_increment,
        active bit not null,
        description varchar(255) not null,
        name varchar(100) not null,
        price decimal(38,2) not null,
        restaurant_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table tab_restaurants (
       id bigint not null auto_increment,
        complement varchar(60),
        neighborhood varchar(60),
        number varchar(255),
        public_place varchar(60),
        zip_code varchar(8),
        created_at datetime not null,
        name varchar(100) not null,
        shipping_fee decimal(38,2) not null,
        updated_at datetime not null,
        city_id bigint,
        kitchen_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table tab_restaurants_payments_types (
       restaurant_id bigint not null,
        payment_type_id bigint not null
    ) engine=InnoDB;

    create table tab_states (
       id bigint not null auto_increment,
        name varchar(60) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tab_users (
       id bigint not null auto_increment,
        created_at datetime not null,
        name varchar(100) not null,
        password varchar(40) not null,
        updated_at datetime not null,
        primary key (id)
    ) engine=InnoDB;

    create table tab_users_groups (
       user_id bigint not null,
        group_id bigint not null
    ) engine=InnoDB;

    alter table tab_products
       add constraint UK_lj6fxq6m3hw7np9ody1rrgkr7 unique (name);

    alter table tab_cities
       add constraint FKly5fu1bua1x6dxp70ka976jbm
       foreign key (state_id)
       references tab_states (id);

    alter table tab_groups_permissions
       add constraint FKi862obsy0glq24iinq775pfvt
       foreign key (permission_id)
       references tab_permissions (id);

    alter table tab_groups_permissions
       add constraint FK1bfspltwg7u26rq92f0swj6yu
       foreign key (group_id)
       references tab_groups (id);

    alter table tab_products
       add constraint FKgs9b88pm5qvua6q0yd0vtxnfa
       foreign key (restaurant_id)
       references tab_restaurants (id);

    alter table tab_restaurants
       add constraint FK4pb3v08m1fgu1o6ejpoctywp
       foreign key (city_id)
       references tab_cities (id);

    alter table tab_restaurants
       add constraint FK8bmfs4fhfxffsks7ek277ypr5
       foreign key (kitchen_id)
       references tab_kitchens (id);

    alter table tab_restaurants_payments_types
       add constraint FK6uy90bcrtr9enwxy2e5rx2uga
       foreign key (payment_type_id)
       references tab_payments_type (id);

    alter table tab_restaurants_payments_types
       add constraint FK5ya5p9682ua4i22i692vqettv
       foreign key (restaurant_id)
       references tab_restaurants (id);

    alter table tab_users_groups
       add constraint FKdebfmfi76jamu8ouwbxv59luu
       foreign key (group_id)
       references tab_groups (id);

    alter table tab_users_groups
       add constraint FKg5ce5cgtvj1sd2qauw68ycf97
       foreign key (user_id)
       references tab_users (id);
