-- DROP TABLE roles CASCADE;
--
-- CREATE TABLE public.roles (
--   id               INTEGER                NOT NULL,
--   role_name        CHARACTER VARYING(100) NOT NULL,
--   role_id          INTEGER                NOT NULL,
--   isactive         INTEGER                NOT NULL,
--   connection_limit INTEGER                NOT NULL,
--   CONSTRAINT roles_pkey PRIMARY KEY (id)
-- );

-- CREATE TABLE public.roles (
--   id        BIGINT                 NOT NULL,
--   role_name CHARACTER VARYING(100) NOT NULL,
--   CONSTRAINT roles_pkey PRIMARY KEY (id)
-- );

--DROP TABLE users;

-- CREATE TABLE public.users (
--   id       INTEGER      NOT NULL,
--   login    VARCHAR(100) NOT NULL,
--   password VARCHAR(100) NOT NULL,
--   CONSTRAINT users_pkey PRIMARY KEY (id)
-- );

INSERT INTO users VALUES (1, 'user1', '123456');
INSERT INTO users VALUES (2, 'seller1', 'qwerty');
INSERT INTO users VALUES (3, 'admin', 'qwerty123456');

INSERT INTO roles VALUES (1, -1, 1, 10, 'USER');
INSERT INTO roles VALUES (2, -1, 1, 0, 'ADMIN');
INSERT INTO roles VALUES (3, -1, 1, 11, 'SELLER');
INSERT INTO roles VALUES (4, -1, 1, 1, 'SUPPORT');
INSERT INTO roles VALUES (5, -1, 1, 2, 'ARBITRATOR');

-- DROP TABLE user_roles;
--
-- CREATE TABLE user_roles (
--   user_id INTEGER NOT NULL,
--   role_id INTEGER NOT NULL,
--
--   FOREIGN KEY (user_id) REFERENCES users (id),
--   FOREIGN KEY (role_id) REFERENCES roles (id),
--
--   UNIQUE (user_id, role_id)
-- );

INSERT INTO user_roles VALUES (1, 1);
INSERT INTO user_roles VALUES (2, 3);
INSERT INTO user_roles VALUES (3, 2);
