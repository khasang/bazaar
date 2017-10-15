--DROP TABLE roles;

-- CREATE TABLE public.roles (
--   id        INTEGER                NOT NULL,
--   isactive  BOOLEAN                NOT NULL DEFAULT TRUE,
--   role_id   INTEGER                NOT NULL,
--   superuser BOOLEAN                NOT NULL DEFAULT FALSE,
--   role_name CHARACTER VARYING(100) NOT NULL,
--   CONSTRAINT roles_pkey PRIMARY KEY (id)
-- );

--DROP TABLE users;

-- CREATE TABLE public.users (
--   id       INTEGER      NOT NULL,
--   username VARCHAR(100) NOT NULL,
--   password VARCHAR(100) NOT NULL,
--   CONSTRAINT users_pkey PRIMARY KEY (id)
-- );

-- CREATE TABLE user_roles (
--   user_id INTEGER NOT NULL,
--   role_id INTEGER NOT NULL,
--
--   FOREIGN KEY (user_id) REFERENCES users (id),
--   FOREIGN KEY (role_id) REFERENCES roles (id),
--
--   UNIQUE (user_id, role_id)
-- );

-- INSERT INTO roles VALUES (1, 'ROLE_USER');
-- INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles VALUES (1, 2);