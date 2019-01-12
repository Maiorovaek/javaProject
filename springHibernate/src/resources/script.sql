create table customer
(
  id       serial           not null
    constraint "Customer_pkey"
      primary key,
  lastname varchar(50)      not null,
  location varchar(100)     not null,
  discount double precision not null
);

alter table customer
  owner to postgres;

create table shop
(
  name_shop     varchar(100),
  location_shop varchar(100),
  commision     double precision,
  idshop        serial not null
    constraint "Shop_pkey"
      primary key
);

alter table shop
  owner to postgres;

create table books
(
  id_book   serial not null
    constraint "Books_pkey"
      primary key,
  name_book varchar(100),
  stock     varchar(100),
  quantity  integer,
  price     double precision
);

alter table books
  owner to postgres;

create table purchase
(
  number_oder serial not null
    constraint purchase_pkey
      primary key,
  date        date,
  quantity    integer,
  seller      serial not null
    constraint seller
      references shop,
  customer    serial not null
    constraint customer
      references customer,
  book        serial not null
    constraint book
      references books,
  sum         integer
);

alter table purchase
  owner to postgres;

create index fki_seller
  on purchase (seller);

create index fki_customer
  on purchase (customer);

create index fki_book
  on purchase (book);


