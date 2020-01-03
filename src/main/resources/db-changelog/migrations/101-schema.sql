CREATE TABLE category (category_id bigserial NOT NULL,
created_date TIMESTAMPTZ NOT NULL,
name VARCHAR(255) NOT NULL UNIQUE,
PRIMARY KEY (category_id));