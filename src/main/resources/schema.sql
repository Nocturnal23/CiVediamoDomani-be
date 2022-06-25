-- DROP SEQUENCE IF EXISTS users_id_seq;
DROP TABLE IF EXISTS users;
-- CREATE SEQUENCE users_id_seq;
CREATE TABLE users(
    id serial primary key,
    created_date timestamp not null,
    modified_date timestamp not null,
    deleted int not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null
);
-- ALTER SEQUENCE idwebuser_id_seq OWNED BY webuser.idwebuser;