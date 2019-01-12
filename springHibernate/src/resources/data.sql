PGDMP         !                 w            postgres    11.1    11.1 5    1           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            2           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            3           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            4           1262    13012    postgres    DATABASE     �   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE postgres;
             postgres    false            5           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                  postgres    false    2868                        3079    16384 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                  false            6           0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
                       false    1            �            1259    16418    books    TABLE     �   CREATE TABLE public.books (
    id_book integer NOT NULL,
    name_book character varying(100),
    stock character varying(100),
    quantity integer,
    price double precision
);
    DROP TABLE public.books;
       public         postgres    false            �            1259    16416    Books_id_book_seq    SEQUENCE     �   CREATE SEQUENCE public."Books_id_book_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public."Books_id_book_seq";
       public       postgres    false    201            7           0    0    Books_id_book_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public."Books_id_book_seq" OWNED BY public.books.id_book;
            public       postgres    false    200            �            1259    16395    customer    TABLE     �   CREATE TABLE public.customer (
    id integer NOT NULL,
    lastname character varying(50) NOT NULL,
    location character varying(100) NOT NULL,
    discount double precision NOT NULL
);
    DROP TABLE public.customer;
       public         postgres    false            �            1259    16393    Customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Customer_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public."Customer_id_seq";
       public       postgres    false    198            8           0    0    Customer_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public."Customer_id_seq" OWNED BY public.customer.id;
            public       postgres    false    197            �            1259    16401    shop    TABLE     �   CREATE TABLE public.shop (
    name_shop character varying(100),
    location_shop character varying(100),
    commision double precision,
    idshop integer NOT NULL
);
    DROP TABLE public.shop;
       public         postgres    false            �            1259    16424    Shop_idshop_seq    SEQUENCE     �   CREATE SEQUENCE public."Shop_idshop_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public."Shop_idshop_seq";
       public       postgres    false    199            9           0    0    Shop_idshop_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public."Shop_idshop_seq" OWNED BY public.shop.idshop;
            public       postgres    false    202            �            1259    16434    purchase    TABLE     �   CREATE TABLE public.purchase (
    number_oder integer NOT NULL,
    date date,
    quantity integer,
    seller integer NOT NULL,
    customer integer NOT NULL,
    book integer NOT NULL,
    sum integer
);
    DROP TABLE public.purchase;
       public         postgres    false            �            1259    16510    purchase_book_seq    SEQUENCE     �   CREATE SEQUENCE public.purchase_book_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.purchase_book_seq;
       public       postgres    false    204            :           0    0    purchase_book_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.purchase_book_seq OWNED BY public.purchase.book;
            public       postgres    false    207            �            1259    16496    purchase_customer_seq    SEQUENCE     �   CREATE SEQUENCE public.purchase_customer_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.purchase_customer_seq;
       public       postgres    false    204            ;           0    0    purchase_customer_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.purchase_customer_seq OWNED BY public.purchase.customer;
            public       postgres    false    206            �            1259    16432    purchase_number_oder_seq    SEQUENCE     �   CREATE SEQUENCE public.purchase_number_oder_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.purchase_number_oder_seq;
       public       postgres    false    204            <           0    0    purchase_number_oder_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.purchase_number_oder_seq OWNED BY public.purchase.number_oder;
            public       postgres    false    203            �            1259    16483    purchase_seller_seq    SEQUENCE     �   CREATE SEQUENCE public.purchase_seller_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.purchase_seller_seq;
       public       postgres    false    204            =           0    0    purchase_seller_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.purchase_seller_seq OWNED BY public.purchase.seller;
            public       postgres    false    205            �
           2604    16421    books id_book    DEFAULT     p   ALTER TABLE ONLY public.books ALTER COLUMN id_book SET DEFAULT nextval('public."Books_id_book_seq"'::regclass);
 <   ALTER TABLE public.books ALTER COLUMN id_book DROP DEFAULT;
       public       postgres    false    201    200    201            �
           2604    16398    customer id    DEFAULT     l   ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public."Customer_id_seq"'::regclass);
 :   ALTER TABLE public.customer ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    197    198    198            �
           2604    16437    purchase number_oder    DEFAULT     |   ALTER TABLE ONLY public.purchase ALTER COLUMN number_oder SET DEFAULT nextval('public.purchase_number_oder_seq'::regclass);
 C   ALTER TABLE public.purchase ALTER COLUMN number_oder DROP DEFAULT;
       public       postgres    false    203    204    204            �
           2604    16485    purchase seller    DEFAULT     r   ALTER TABLE ONLY public.purchase ALTER COLUMN seller SET DEFAULT nextval('public.purchase_seller_seq'::regclass);
 >   ALTER TABLE public.purchase ALTER COLUMN seller DROP DEFAULT;
       public       postgres    false    205    204            �
           2604    16498    purchase customer    DEFAULT     v   ALTER TABLE ONLY public.purchase ALTER COLUMN customer SET DEFAULT nextval('public.purchase_customer_seq'::regclass);
 @   ALTER TABLE public.purchase ALTER COLUMN customer DROP DEFAULT;
       public       postgres    false    206    204            �
           2604    16512    purchase book    DEFAULT     n   ALTER TABLE ONLY public.purchase ALTER COLUMN book SET DEFAULT nextval('public.purchase_book_seq'::regclass);
 <   ALTER TABLE public.purchase ALTER COLUMN book DROP DEFAULT;
       public       postgres    false    207    204            �
           2604    16426    shop idshop    DEFAULT     l   ALTER TABLE ONLY public.shop ALTER COLUMN idshop SET DEFAULT nextval('public."Shop_idshop_seq"'::regclass);
 :   ALTER TABLE public.shop ALTER COLUMN idshop DROP DEFAULT;
       public       postgres    false    202    199            (          0    16418    books 
   TABLE DATA               K   COPY public.books (id_book, name_book, stock, quantity, price) FROM stdin;
    public       postgres    false    201   ,7       %          0    16395    customer 
   TABLE DATA               D   COPY public.customer (id, lastname, location, discount) FROM stdin;
    public       postgres    false    198   8       +          0    16434    purchase 
   TABLE DATA               \   COPY public.purchase (number_oder, date, quantity, seller, customer, book, sum) FROM stdin;
    public       postgres    false    204   �8       &          0    16401    shop 
   TABLE DATA               K   COPY public.shop (name_shop, location_shop, commision, idshop) FROM stdin;
    public       postgres    false    199   �8       >           0    0    Books_id_book_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public."Books_id_book_seq"', 9, true);
            public       postgres    false    200            ?           0    0    Customer_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public."Customer_id_seq"', 1, false);
            public       postgres    false    197            @           0    0    Shop_idshop_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public."Shop_idshop_seq"', 1, false);
            public       postgres    false    202            A           0    0    purchase_book_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.purchase_book_seq', 1, false);
            public       postgres    false    207            B           0    0    purchase_customer_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.purchase_customer_seq', 1, false);
            public       postgres    false    206            C           0    0    purchase_number_oder_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.purchase_number_oder_seq', 1, false);
            public       postgres    false    203            D           0    0    purchase_seller_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.purchase_seller_seq', 1, false);
            public       postgres    false    205            �
           2606    16423    books Books_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.books
    ADD CONSTRAINT "Books_pkey" PRIMARY KEY (id_book);
 <   ALTER TABLE ONLY public.books DROP CONSTRAINT "Books_pkey";
       public         postgres    false    201            �
           2606    16400    customer Customer_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT "Customer_pkey" PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.customer DROP CONSTRAINT "Customer_pkey";
       public         postgres    false    198            �
           2606    16431    shop Shop_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.shop
    ADD CONSTRAINT "Shop_pkey" PRIMARY KEY (idshop);
 :   ALTER TABLE ONLY public.shop DROP CONSTRAINT "Shop_pkey";
       public         postgres    false    199            �
           2606    16439    purchase purchase_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT purchase_pkey PRIMARY KEY (number_oder);
 @   ALTER TABLE ONLY public.purchase DROP CONSTRAINT purchase_pkey;
       public         postgres    false    204            �
           1259    16525    fki_book    INDEX     =   CREATE INDEX fki_book ON public.purchase USING btree (book);
    DROP INDEX public.fki_book;
       public         postgres    false    204            �
           1259    16509    fki_customer    INDEX     E   CREATE INDEX fki_customer ON public.purchase USING btree (customer);
     DROP INDEX public.fki_customer;
       public         postgres    false    204            �
           1259    16495 
   fki_seller    INDEX     A   CREATE INDEX fki_seller ON public.purchase USING btree (seller);
    DROP INDEX public.fki_seller;
       public         postgres    false    204            �
           2606    16520    purchase book    FK CONSTRAINT     n   ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT book FOREIGN KEY (book) REFERENCES public.books(id_book);
 7   ALTER TABLE ONLY public.purchase DROP CONSTRAINT book;
       public       postgres    false    201    204    2722            �
           2606    16504    purchase customer    FK CONSTRAINT     t   ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT customer FOREIGN KEY (customer) REFERENCES public.customer(id);
 ;   ALTER TABLE ONLY public.purchase DROP CONSTRAINT customer;
       public       postgres    false    198    2718    204            �
           2606    16490    purchase seller    FK CONSTRAINT     p   ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT seller FOREIGN KEY (seller) REFERENCES public.shop(idshop);
 9   ALTER TABLE ONLY public.purchase DROP CONSTRAINT seller;
       public       postgres    false    199    2720    204            (   �   x�e�=�0Fg�>A��?+R$��J,,��"P%(�"nOZ(�,?��,�n	��Ľ���[�HAr�$�����ܐ�0	�����`�v�@���'�C�)(τ���5dp��[M��\��<�&Pj[������A�@*�fХ;z��$��aG-�Q?��v��+��d1�h����D�HL<��\�C�{�cS      %   y   x�3��,K��/��ˬ�HM�/�O)�ά�4�3�D�\F�>�I)Y�y�)U��E���P����Ȃ˘38���h�͹L8�32�*S�8��r���jќaf�e����C��1z\\\ Bf@      +   S   x�-���0Cѳم�&�Jv��s4�Jp���ZNy	!�IZ��Egb|;�7m�"��<Y��)1;�XMYU�9�=������:�      &   �   x�u�A
�0����a$Mc� .�(]dl����AO_7.����=89�ߎ3�@K����*U��t|�dϬ)ح�w8rȐ$β$���+v���FX]Xe�`'s�Oao�I��#0e�B��
ot=     