-- DROP SEQUENCE IF EXISTS users_id_seq;
-- CREATE SEQUENCE users_id_seq;
-- ALTER SEQUENCE idwebuser_id_seq OWNED BY webuser.idwebuser;
DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users(
    id serial primary key,
    created_date timestamp not null,
    modified_date timestamp not null,
    deleted numeric(2) not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(50) not null,
    password_digest varchar(50) not null,
    password_salt varchar(50) not null,
    phone_num varchar(50) not null,
    birthday date,
    city varchar(50),
    country varchar(50),
    app_role numeric(2) not null
);

DROP TABLE IF EXISTS review;
CREATE TABLE IF NOT EXISTS review(
    id serial primary key,
    created_date timestamp not null,
    modified_date timestamp not null,
    deleted numeric(2) not null,
    sender Integer not null,
    recipient_id Integer not null,
    rating numeric(5) not null,
    description varchar(50)
);

DROP TABLE IF EXISTS category;
CREATE TABLE IF NOT EXISTS category(
    id serial primary key,
    created_date timestamp not null,
    modified_date timestamp not null,
    deleted numeric(2) not null,
    category_name varchar(50) not null
);

DROP TABLE IF EXISTS advert;
CREATE TABLE IF NOT EXISTS advert(
    id serial primary key,
    created_date timestamp not null,
    modified_date timestamp not null,
    deleted numeric(2) not null,
    address varchar(50) not null,
    city varchar(50) not null,
    country varchar(50) not null,
    construction_year date,
    place_condition varchar(50),
    floor numeric(3),
    square_meters double not null,
    no_locals numeric(3),
    no_bathrooms numeric(3),
    title varchar(50) not null,
    description varchar(255) not null,
    price double not null,
    discount_price double,
    adv_type numeric(2) not null,
    seller_type numeric(2) not null,
    parking varchar(50),
    media varchar(50),
    heating_type numeric(2),
    energeticClass numeric(2) not null,
    details varchar(55),
);