CREATE TABLE tab_products_photos (
    product_id bigint not null,
    name_file varchar(150) not null,
    description varchar(150),
    content_type varchar(80) not null,
    file_size int not null,

    primary key (product_id),
    constraint fk_product_photo_product foreign key (product_id) references tab_products (id)
) engine=InnoDB;
