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

INSERT INTO public.books (id_book, name_book, stock, quantity, price) VALUES (1, 'Brave New World', 'Point1', 3, 200);
INSERT INTO public.books (id_book, name_book, stock, quantity, price) VALUES (2, 'East Of Eden', 'Point2', 10, 2110);
INSERT INTO public.books (id_book, name_book, stock, quantity, price) VALUES (3, 'Pride and Prejudice', 'Point1', 10, 215);
INSERT INTO public.books (id_book, name_book, stock, quantity, price) VALUES (5, 'The Talented Mr. Ripley', 'Point3', 2, 900);
INSERT INTO public.books (id_book, name_book, stock, quantity, price) VALUES (6, 'Tailor Swift', 'Point2', 5, 255);
INSERT INTO public.books (id_book, name_book, stock, quantity, price) VALUES (8, 'New book', 'NewPoint', 5, 2500);
INSERT INTO public.books (id_book, name_book, stock, quantity, price) VALUES (9, 'Sever', 'NewPoint', 5, 2500);
INSERT INTO public.books (id_book, name_book, stock, quantity, price) VALUES (4, 'Jane Eyre', 'Point1', 4, 21000);
INSERT INTO public.books (id_book, name_book, stock, quantity, price) VALUES (7, 'New book', 'Point2', 2, 50000);
INSERT INTO public.books (id_book, name_book, stock, quantity, price) VALUES (10, 'New book', 'NewPoint', 1, 20005);
INSERT INTO public.books (id_book, name_book, stock, quantity, price) VALUES (11, 'Taddy', 'Point1', 2, 2400);
INSERT INTO public.books (id_book, name_book, stock, quantity, price) VALUES (12, 'Taddy', 'Point1', 2, 2400);
INSERT INTO public.books (id_book, name_book, stock, quantity, price) VALUES (13, 'Taddy', 'Point1', 2, 2400);
INSERT INTO public.books (id_book, name_book, stock, quantity, price) VALUES (14, 'Taddy', 'Point1', 2, 2400);
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

INSERT INTO public.customer (id, lastname, location, discount) VALUES (1, 'Ivanov', 'nizhegorodskiy', 0.3);
INSERT INTO public.customer (id, lastname, location, discount) VALUES (2, 'Labdjanidze', 'priokskiy', 0.05);
INSERT INTO public.customer (id, lastname, location, discount) VALUES (3, 'Serov', 'nizhegorodskiy', 0.08);
INSERT INTO public.customer (id, lastname, location, discount) VALUES (4, 'Shiryaev', 'sormovskiy', 0.09);
INSERT INTO public.customer (id, lastname, location, discount) VALUES (5, 'Smolov', 'nizhegorodskiy', 0.08);
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

INSERT INTO public.purchase (number_oder, date, quantity, seller, customer, book, sum) VALUES (1, '2018-01-20', 5, 1, 2, 1, 2000);
INSERT INTO public.purchase (number_oder, date, quantity, seller, customer, book, sum) VALUES (2, '2011-10-05', 3, 2, 1, 2, 1990);
INSERT INTO public.purchase (number_oder, date, quantity, seller, customer, book, sum) VALUES (3, '2011-11-04', 2, 1, 5, 3, 50000);
INSERT INTO public.purchase (number_oder, date, quantity, seller, customer, book, sum) VALUES (4, '2000-11-05', 4, 3, 2, 4, 80000);
INSERT INTO public.purchase (number_oder, date, quantity, seller, customer, book, sum) VALUES (5, '2012-12-06', 2, 4, 3, 5, 90000);
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

INSERT INTO public.shop (name_shop, location_shop, commision, idshop) VALUES ('Pishi_chitay', 'avtozavod', 0.05, 1);
INSERT INTO public.shop (name_shop, location_shop, commision, idshop) VALUES ('Read', 'avtozavod', 0.07, 2);
INSERT INTO public.shop (name_shop, location_shop, commision, idshop) VALUES ('Labirint', 'sormovskiy', 0.05, 3);
INSERT INTO public.shop (name_shop, location_shop, commision, idshop) VALUES ('Dirizhabl', 'priokskiy', 0.02, 4);
INSERT INTO public.shop (name_shop, location_shop, commision, idshop) VALUES ('Dom_knigi', 'nizhegorodskiy', 0.04, 5);