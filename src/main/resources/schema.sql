-- CREATE SEQUENCE users_id_seq;
DROP TABLE IF EXISTS users;
CREATE TABLE users(
    id integer primary key default nextval('users_id_seq'),
    createdDate timestamp not null,
    modifiedDate timestamp not null,
    deleted int not null,
    first_name varchar(50) not null,
    lastName varchar(50) not null
);
-- ALTER SEQUENCE idwebuser_id_seq OWNED BY webuser.idwebuser;