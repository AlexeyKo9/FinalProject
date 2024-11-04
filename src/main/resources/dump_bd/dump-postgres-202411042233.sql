PGDMP  !    !            
    |            postgres    17.0    17.0                 0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            !           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            "           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            #           1262    5    postgres    DATABASE     |   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE postgres;
                     postgres    false            $           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                        postgres    false    4899                        2615    24609    postgres    SCHEMA        CREATE SCHEMA postgres;
    DROP SCHEMA postgres;
                     postgres    false            �            1259    24897    user_balance    TABLE     Z   CREATE TABLE postgres.user_balance (
    balance numeric(38,2),
    id bigint NOT NULL
);
 "   DROP TABLE postgres.user_balance;
       postgres         heap r       postgres    false    5            �            1259    24896    user_balance_id_seq    SEQUENCE     ~   CREATE SEQUENCE postgres.user_balance_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE postgres.user_balance_id_seq;
       postgres               postgres    false    219    5            %           0    0    user_balance_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE postgres.user_balance_id_seq OWNED BY postgres.user_balance.id;
          postgres               postgres    false    218            �            1259    24797    user_balance_seq    SEQUENCE     |   CREATE SEQUENCE postgres.user_balance_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE postgres.user_balance_seq;
       postgres               postgres    false    5            �           2604    24900    user_balance id    DEFAULT     v   ALTER TABLE ONLY postgres.user_balance ALTER COLUMN id SET DEFAULT nextval('postgres.user_balance_id_seq'::regclass);
 @   ALTER TABLE postgres.user_balance ALTER COLUMN id DROP DEFAULT;
       postgres               postgres    false    218    219    219                      0    24897    user_balance 
   TABLE DATA           5   COPY postgres.user_balance (balance, id) FROM stdin;
    postgres               postgres    false    219   �       &           0    0    user_balance_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('postgres.user_balance_id_seq', 3, true);
          postgres               postgres    false    218            '           0    0    user_balance_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('postgres.user_balance_seq', 51, true);
          postgres               postgres    false    217            �           2606    24902    user_balance user_balance_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY postgres.user_balance
    ADD CONSTRAINT user_balance_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY postgres.user_balance DROP CONSTRAINT user_balance_pkey;
       postgres                 postgres    false    219                  x�3�30�4�2 Q�\�`�!W� >R     