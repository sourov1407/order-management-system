create table user (
                      id bigint not null auto_increment,
                      created_at datetime,
                      updated_at datetime,
                      email varchar(255),
                      full_name varchar(255),
                      password varchar(255) not null,
                      user_name varchar(255) not null unique,
                      primary key (id)
);
create table orders (
                        id bigint not null auto_increment,
                        created_at datetime,
                        updated_at datetime,
                        user_id bigint not null,
                        primary key (id),
                        foreign key FK_User_Orders (user_id) references user (id)
);

create table product (
                         id bigint not null auto_increment,
                         created_at datetime,
                         updated_at datetime,
                         category varchar(255) not null,
                         quantity integer not null,
                         issue_date varchar(255),
                         name varchar(255) not null,
                         sku_no varchar(255) not null,
                         primary key (id)
);

create table order_details (
                               id bigint not null auto_increment,
                               created_at datetime,
                               updated_at datetime,
                               product_quantity integer,
                               order_id bigint,
                               product_id bigint,
                               primary key (id),
                               foreign key FK_Orders_OrderDetails (order_id) references orders (id),
                               foreign key FK_Product_OrderDetails (product_id) references product (id)
);


create table review (
                        id bigint not null auto_increment,
                        created_at datetime,
                        updated_at datetime,
                        rating double precision not null,
                        review varchar(255) not null,
                        product_id bigint,
                        user_id bigint,
                        primary key (id),
                        foreign key FK_User_Reviews (user_id) references user (id),
                        foreign key FK_Product_Reviews (product_id) references product (id)
);

create table role (
                      id bigint not null auto_increment,
                      created_at datetime,
                      updated_at datetime,
                      name varchar(255),
                      primary key (id)
);

create table user_role (
                           user_id bigint not null,
                           role_id bigint not null,
                           foreign key FK_Role_UserRole (role_id) references role (id),
                           foreign key FK_User_UserRole (user_id) references user (id)
);