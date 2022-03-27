drop schema if exists example;
create schema example;
-- create user if not exists example_user password 'example' admin;

create table users (
    id identity ,
    name varchar(500)
);

create table products (
    id identity,
    name varchar(500),
    price_cents integer,
    on_hand integer
);

create table carts (
    id identity,
    user_id integer
);
alter table carts add foreign key (user_id) references users (id);
create table cart_items (
    id identity,
    cart_id integer,
    product_id integer,
    quantity integer
);
alter table cart_items add foreign key (cart_id) references carts (id);
alter table cart_items add foreign key (product_id) references products (id);

create table orders (
    id identity,
    user_id integer,
    order_total integer,
    amount_paid integer,
    paid_at timestamp
);
alter table orders add foreign key (user_id) references users (id);

create table order_items (
    id identity,
    order_id integer,
    product_id integer,
    quantity integer,
    price_each integer,
    line_item_price integer
);
alter table order_items add foreign key (order_id) references orders (id);
alter table order_items add foreign key (product_id) references products (id);
