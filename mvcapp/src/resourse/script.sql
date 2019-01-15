create table clients
(
  id_client serial not null
    constraint client_pkey
      primary key,
  lastname  varchar(50),
  area      varchar(50),
  discount  integer
);

alter table clients
  owner to postgres;

INSERT INTO public.clients (id_client, lastname, area, discount) VALUES (1, 'Sorin', 'Autozavodsky', 50);
INSERT INTO public.clients (id_client, lastname, area, discount) VALUES (3, 'Denisov', 'Sormovskiy', 30);
INSERT INTO public.clients (id_client, lastname, area, discount) VALUES (4, 'Merzlyakov', 'Priokskiy', 40);
INSERT INTO public.clients (id_client, lastname, area, discount) VALUES (5, 'Latyshev', 'Moskovskiy', 20);
INSERT INTO public.clients (id_client, lastname, area, discount) VALUES (2, 'Ivanov', 'Nizhegorodkiy', 15);
