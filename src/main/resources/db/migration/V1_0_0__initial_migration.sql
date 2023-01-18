create table orders
(
       id bigint not null auto_increment,
       created_at datetime,
       updated_at datetime,
       user_id bigint not null,
       primary key (id)
);

create table order_details
(
       id bigint not null auto_increment,
       created_at datetime,
       updated_at datetime,
       product_quantity integer,
       order_id bigint not null,
       product_id bigint not null,
       primary key (id)
);

create table product
(
     id bigint not null auto_increment,
     created_at datetime,
     updated_at datetime,
     category varchar(255) not null,
     name varchar(255) not null,
     sku_no varchar(255) not null,
     primary key (id)
);

create table role
(
      id bigint not null auto_increment,
      created_at datetime,
      updated_at datetime,
      name varchar(255),
      primary key (id)
);

create table user
(
      id bigint not null auto_increment,
      created_at datetime,
      updated_at datetime,
      email varchar(255),
      full_name varchar(255),
      password varchar(255) not null,
      user_name varchar(255) not null,
      primary key (id)
);

create table user_role
(
       user_id bigint not null,
       role_id bigint not null
);


alter table user
    add constraint UQ_User_UserName unique (user_name);

alter table order_details
    add constraint FK_Orders_OrderDetails
    foreign key (order_id)
    references orders (id);

alter table order_details
    add constraint FK_Product_OrderDetails
    foreign key (product_id)
    references product (id);

alter table orders
    add constraint FK_User_Orders
    foreign key (user_id)
    references user (id);

alter table user_role
    add constraint FK_Role_UserRole
    foreign key (role_id)
    references role (id);

alter table user_role
    add constraint FK_User_UserRole
    foreign key (user_id)
    references user (id);

