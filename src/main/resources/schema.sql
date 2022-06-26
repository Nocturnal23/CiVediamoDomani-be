-- DROP SEQUENCE IF EXISTS users_id_seq;
DROP TABLE IF EXISTS users;
-- CREATE SEQUENCE users_id_seq;
CREATE TABLE IF NOT EXISTS users(
    id serial primary key,
    created_date timestamp not null,
    modified_date timestamp not null,
    deleted int not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(50) not null,
    password_digest varchar(50) not null,
    password_salt varchar(50) not null,
    phone_num varchar(50) not null,
    birthday date not null,
    fiscal_code varchar(50) not null,
    document_id varchar(50) not null,
    city varchar(50) not null,
    country varchar(50) not null,
    role numeric(19) not null
);
-- ALTER SEQUENCE idwebuser_id_seq OWNED BY webuser.idwebuser;