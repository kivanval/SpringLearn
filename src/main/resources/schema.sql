create schema if not exists TACO_CLOUD;

create table if not exists TACO_CLOUD.Taco_Order
(
    id              identity primary key,
    delivery_Name   varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City   varchar(50) not null,
    delivery_State  varchar(2)  not null,
    delivery_Zip    varchar(10) not null,
    cc_number       varchar(16) not null,
    cc_expiration   varchar(5)  not null,
    cc_cvv          varchar(3)  not null,
    placed_at       timestamp   not null,
    primary key (id)
);
create table if not exists TACO_CLOUD.Taco
(
    id            identity,
    name          varchar(50) not null,
    taco_order_id bigint      not null,
    created_at    timestamp   not null,
    primary key (id)
);
create table if not exists TACO_CLOUD.Ingredient_Ref
(
    ingredient_id bigint not null,
    taco_id       bigint not null,
    primary key (ingredient_id, taco_id)
);
create table if not exists TACO_CLOUD.Ingredient
(
    id   identity,
    name varchar(25) not null,
    type varchar(10) not null,
    primary key (id)
);

alter table TACO_CLOUD.Taco
    add foreign key (taco_order_id) references TACO_CLOUD.Taco_Order (id);
alter table TACO_CLOUD.Ingredient_Ref
    add foreign key (ingredient_id) references TACO_CLOUD.Ingredient (id);
alter table TACO_CLOUD.Ingredient_Ref
    add foreign key (taco_id) references TACO_CLOUD.Taco (id);