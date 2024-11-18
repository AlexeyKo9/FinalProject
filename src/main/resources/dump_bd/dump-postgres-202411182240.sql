--
-- PostgreSQL database cluster dump
--

-- Started on 2024-11-18 22:40:52

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS;

--
-- User Configurations
--








--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2024-11-18 22:40:52

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2024-11-18 22:40:52

--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2024-11-18 22:40:52

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5 (class 2615 OID 24609)
-- Name: postgres; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA postgres;


ALTER SCHEMA postgres OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 35018)
-- Name: operation; Type: TABLE; Schema: postgres; Owner: postgres
--

CREATE TABLE postgres.operation (
    amount numeric(38,2),
    id_operation integer NOT NULL,
    type_operation integer,
    time_operation timestamp(6) without time zone,
    user_id bigint,
    user_transfer_id bigint
);


ALTER TABLE postgres.operation OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 35017)
-- Name: operation_id_operation_seq; Type: SEQUENCE; Schema: postgres; Owner: postgres
--

CREATE SEQUENCE postgres.operation_id_operation_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE postgres.operation_id_operation_seq OWNER TO postgres;

--
-- TOC entry 4911 (class 0 OID 0)
-- Dependencies: 218
-- Name: operation_id_operation_seq; Type: SEQUENCE OWNED BY; Schema: postgres; Owner: postgres
--

ALTER SEQUENCE postgres.operation_id_operation_seq OWNED BY postgres.operation.id_operation;


--
-- TOC entry 221 (class 1259 OID 35025)
-- Name: user_balance; Type: TABLE; Schema: postgres; Owner: postgres
--

CREATE TABLE postgres.user_balance (
    balance numeric(38,2),
    id bigint NOT NULL
);


ALTER TABLE postgres.user_balance OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 35024)
-- Name: user_balance_id_seq; Type: SEQUENCE; Schema: postgres; Owner: postgres
--

CREATE SEQUENCE postgres.user_balance_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE postgres.user_balance_id_seq OWNER TO postgres;

--
-- TOC entry 4912 (class 0 OID 0)
-- Dependencies: 220
-- Name: user_balance_id_seq; Type: SEQUENCE OWNED BY; Schema: postgres; Owner: postgres
--

ALTER SEQUENCE postgres.user_balance_id_seq OWNED BY postgres.user_balance.id;


--
-- TOC entry 217 (class 1259 OID 24797)
-- Name: user_balance_seq; Type: SEQUENCE; Schema: postgres; Owner: postgres
--

CREATE SEQUENCE postgres.user_balance_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE postgres.user_balance_seq OWNER TO postgres;

--
-- TOC entry 4748 (class 2604 OID 35021)
-- Name: operation id_operation; Type: DEFAULT; Schema: postgres; Owner: postgres
--

ALTER TABLE ONLY postgres.operation ALTER COLUMN id_operation SET DEFAULT nextval('postgres.operation_id_operation_seq'::regclass);


--
-- TOC entry 4749 (class 2604 OID 35028)
-- Name: user_balance id; Type: DEFAULT; Schema: postgres; Owner: postgres
--

ALTER TABLE ONLY postgres.user_balance ALTER COLUMN id SET DEFAULT nextval('postgres.user_balance_id_seq'::regclass);


--
-- TOC entry 4903 (class 0 OID 35018)
-- Dependencies: 219
-- Data for Name: operation; Type: TABLE DATA; Schema: postgres; Owner: postgres
--

COPY postgres.operation (amount, id_operation, type_operation, time_operation, user_id, user_transfer_id) FROM stdin;
300.00	1	1	2024-11-18 22:39:50.99675	1	\N
300.00	2	2	2024-11-18 22:39:51.089842	1	\N
100.00	3	3	2024-11-18 22:39:51.121434	1	2
100.00	4	4	2024-11-18 22:39:51.121434	2	1
\.


--
-- TOC entry 4905 (class 0 OID 35025)
-- Dependencies: 221
-- Data for Name: user_balance; Type: TABLE DATA; Schema: postgres; Owner: postgres
--

COPY postgres.user_balance (balance, id) FROM stdin;
500.00	3
0.00	4
500.00	5
0.00	6
500.00	7
0.00	8
400.00	1
100.00	2
500.00	9
0.00	10
\.


--
-- TOC entry 4913 (class 0 OID 0)
-- Dependencies: 218
-- Name: operation_id_operation_seq; Type: SEQUENCE SET; Schema: postgres; Owner: postgres
--

SELECT pg_catalog.setval('postgres.operation_id_operation_seq', 4, true);


--
-- TOC entry 4914 (class 0 OID 0)
-- Dependencies: 220
-- Name: user_balance_id_seq; Type: SEQUENCE SET; Schema: postgres; Owner: postgres
--

SELECT pg_catalog.setval('postgres.user_balance_id_seq', 10, true);


--
-- TOC entry 4915 (class 0 OID 0)
-- Dependencies: 217
-- Name: user_balance_seq; Type: SEQUENCE SET; Schema: postgres; Owner: postgres
--

SELECT pg_catalog.setval('postgres.user_balance_seq', 51, true);


--
-- TOC entry 4751 (class 2606 OID 35023)
-- Name: operation operation_pkey; Type: CONSTRAINT; Schema: postgres; Owner: postgres
--

ALTER TABLE ONLY postgres.operation
    ADD CONSTRAINT operation_pkey PRIMARY KEY (id_operation);


--
-- TOC entry 4753 (class 2606 OID 35030)
-- Name: user_balance user_balance_pkey; Type: CONSTRAINT; Schema: postgres; Owner: postgres
--

ALTER TABLE ONLY postgres.user_balance
    ADD CONSTRAINT user_balance_pkey PRIMARY KEY (id);


--
-- TOC entry 4754 (class 2606 OID 35031)
-- Name: operation fk4l8pb9ad84dcgbn3s4kd95j3k; Type: FK CONSTRAINT; Schema: postgres; Owner: postgres
--

ALTER TABLE ONLY postgres.operation
    ADD CONSTRAINT fk4l8pb9ad84dcgbn3s4kd95j3k FOREIGN KEY (user_id) REFERENCES postgres.user_balance(id);


--
-- TOC entry 4755 (class 2606 OID 35036)
-- Name: operation fkgwt83soyfp5y15768av05vt8i; Type: FK CONSTRAINT; Schema: postgres; Owner: postgres
--

ALTER TABLE ONLY postgres.operation
    ADD CONSTRAINT fkgwt83soyfp5y15768av05vt8i FOREIGN KEY (user_transfer_id) REFERENCES postgres.user_balance(id);


-- Completed on 2024-11-18 22:40:53

--
-- PostgreSQL database dump complete
--

--
-- Database "tgbot-replica" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2024-11-18 22:40:53

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4924 (class 1262 OID 24694)
-- Name: tgbot-replica; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "tgbot-replica" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';


ALTER DATABASE "tgbot-replica" OWNER TO postgres;

\connect -reuse-previous=on "dbname='tgbot-replica'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5 (class 2615 OID 24609)
-- Name: postgres; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA postgres;


ALTER SCHEMA postgres OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 24696)
-- Name: active_chat; Type: TABLE; Schema: postgres; Owner: postgres
--

CREATE TABLE postgres.active_chat (
    id bigint NOT NULL,
    chat_id bigint
);


ALTER TABLE postgres.active_chat OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24695)
-- Name: active_chat_id_seq; Type: SEQUENCE; Schema: postgres; Owner: postgres
--

ALTER TABLE postgres.active_chat ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME postgres.active_chat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 222 (class 1259 OID 24702)
-- Name: income; Type: TABLE; Schema: postgres; Owner: postgres
--

CREATE TABLE postgres.income (
    id bigint NOT NULL,
    chat_id bigint,
    income numeric(19,2)
);


ALTER TABLE postgres.income OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 24701)
-- Name: income_id_seq; Type: SEQUENCE; Schema: postgres; Owner: postgres
--

ALTER TABLE postgres.income ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME postgres.income_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 224 (class 1259 OID 24708)
-- Name: spend; Type: TABLE; Schema: postgres; Owner: postgres
--

CREATE TABLE postgres.spend (
    id bigint NOT NULL,
    chat_id bigint,
    spend numeric(19,2)
);


ALTER TABLE postgres.spend OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 24707)
-- Name: spend_id_seq; Type: SEQUENCE; Schema: postgres; Owner: postgres
--

ALTER TABLE postgres.spend ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME postgres.spend_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 218 (class 1259 OID 24688)
-- Name: user_balance; Type: TABLE; Schema: postgres; Owner: postgres
--

CREATE TABLE postgres.user_balance (
    balance numeric(10,2) NOT NULL,
    id bigint NOT NULL
);


ALTER TABLE postgres.user_balance OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24687)
-- Name: user_balance_id_seq; Type: SEQUENCE; Schema: postgres; Owner: postgres
--

CREATE SEQUENCE postgres.user_balance_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE postgres.user_balance_id_seq OWNER TO postgres;

--
-- TOC entry 4925 (class 0 OID 0)
-- Dependencies: 217
-- Name: user_balance_id_seq; Type: SEQUENCE OWNED BY; Schema: postgres; Owner: postgres
--

ALTER SEQUENCE postgres.user_balance_id_seq OWNED BY postgres.user_balance.id;


--
-- TOC entry 4757 (class 2604 OID 24691)
-- Name: user_balance id; Type: DEFAULT; Schema: postgres; Owner: postgres
--

ALTER TABLE ONLY postgres.user_balance ALTER COLUMN id SET DEFAULT nextval('postgres.user_balance_id_seq'::regclass);


--
-- TOC entry 4914 (class 0 OID 24696)
-- Dependencies: 220
-- Data for Name: active_chat; Type: TABLE DATA; Schema: postgres; Owner: postgres
--

COPY postgres.active_chat (id, chat_id) FROM stdin;
\.


--
-- TOC entry 4916 (class 0 OID 24702)
-- Dependencies: 222
-- Data for Name: income; Type: TABLE DATA; Schema: postgres; Owner: postgres
--

COPY postgres.income (id, chat_id, income) FROM stdin;
\.


--
-- TOC entry 4918 (class 0 OID 24708)
-- Dependencies: 224
-- Data for Name: spend; Type: TABLE DATA; Schema: postgres; Owner: postgres
--

COPY postgres.spend (id, chat_id, spend) FROM stdin;
10	2	3000.00
\.


--
-- TOC entry 4912 (class 0 OID 24688)
-- Dependencies: 218
-- Data for Name: user_balance; Type: TABLE DATA; Schema: postgres; Owner: postgres
--

COPY postgres.user_balance (balance, id) FROM stdin;
\.


--
-- TOC entry 4926 (class 0 OID 0)
-- Dependencies: 219
-- Name: active_chat_id_seq; Type: SEQUENCE SET; Schema: postgres; Owner: postgres
--

SELECT pg_catalog.setval('postgres.active_chat_id_seq', 1, false);


--
-- TOC entry 4927 (class 0 OID 0)
-- Dependencies: 221
-- Name: income_id_seq; Type: SEQUENCE SET; Schema: postgres; Owner: postgres
--

SELECT pg_catalog.setval('postgres.income_id_seq', 1, false);


--
-- TOC entry 4928 (class 0 OID 0)
-- Dependencies: 223
-- Name: spend_id_seq; Type: SEQUENCE SET; Schema: postgres; Owner: postgres
--

SELECT pg_catalog.setval('postgres.spend_id_seq', 1, false);


--
-- TOC entry 4929 (class 0 OID 0)
-- Dependencies: 217
-- Name: user_balance_id_seq; Type: SEQUENCE SET; Schema: postgres; Owner: postgres
--

SELECT pg_catalog.setval('postgres.user_balance_id_seq', 1, false);


--
-- TOC entry 4761 (class 2606 OID 24700)
-- Name: active_chat active_chat_pkey; Type: CONSTRAINT; Schema: postgres; Owner: postgres
--

ALTER TABLE ONLY postgres.active_chat
    ADD CONSTRAINT active_chat_pkey PRIMARY KEY (id);


--
-- TOC entry 4763 (class 2606 OID 24706)
-- Name: income income_pkey; Type: CONSTRAINT; Schema: postgres; Owner: postgres
--

ALTER TABLE ONLY postgres.income
    ADD CONSTRAINT income_pkey PRIMARY KEY (id);


--
-- TOC entry 4765 (class 2606 OID 24712)
-- Name: spend spend_pkey; Type: CONSTRAINT; Schema: postgres; Owner: postgres
--

ALTER TABLE ONLY postgres.spend
    ADD CONSTRAINT spend_pkey PRIMARY KEY (id);


--
-- TOC entry 4759 (class 2606 OID 24693)
-- Name: user_balance user_balance_pkey; Type: CONSTRAINT; Schema: postgres; Owner: postgres
--

ALTER TABLE ONLY postgres.user_balance
    ADD CONSTRAINT user_balance_pkey PRIMARY KEY (id);


-- Completed on 2024-11-18 22:40:53

--
-- PostgreSQL database dump complete
--

-- Completed on 2024-11-18 22:40:53

--
-- PostgreSQL database cluster dump complete
--

