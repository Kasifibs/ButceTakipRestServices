CREATE DATABASE butce_takip;

CREATE TABLE users (
  user_id SERIAL PRIMARY KEY,
  user_name varchar(50) NOT NULL,
  password varchar(200) NOT NULL,
  enabled boolean DEFAULT NULL
);

INSERT INTO users (user_name, password, enabled) VALUES
	('appuser', '$2a$10$.f3W.GCUemcrOcE.4OlWbubpGNgOwEWTHGjYLaFiJz1SflyysFlSS', true),
	('a', '$2a$10$HLmpu6MVeMYrVfeXKbcAvuBaKa4TrWEwVUD2hQ.Ggp2oJfeFNjv3a', true);

CREATE TABLE user_roles (
  user_role_id SERIAL PRIMARY KEY,
  user_id integer NOT NULL,
  authority varchar(45) NOT NULL,
  CONSTRAINT fk_user_roles_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT INTO user_roles (user_id, authority) VALUES
	(1, 'ROLE_USER'),
	(2, 'ROLE_USER');

CREATE TABLE resource_item (
  id SERIAL PRIMARY KEY,
  user_id integer NOT NULL,
  name varchar(300) NOT NULL,
  CONSTRAINT fk_resource_item_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE income_item (
  id SERIAL PRIMARY KEY,
  user_id integer NOT NULL,
  name varchar(300) NOT NULL,
  CONSTRAINT fk_income_item_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE expense_item (
  id SERIAL PRIMARY KEY,
  user_id integer NOT NULL,
  name varchar(300) NOT NULL,
  CONSTRAINT fk_expense_item_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE period (
  id SERIAL PRIMARY KEY,
  begin_date date NOT NULL,
  end_date date NOT NULL,
  user_id integer NOT NULL,
  name varchar(300) DEFAULT NULL,
  CONSTRAINT fk_period_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE resource (
  id SERIAL PRIMARY KEY,
  user_id integer NOT NULL,
  res_item_id integer NOT NULL,
  period_id integer NOT NULL,
  amount decimal(13,2) NOT NULL,
  CONSTRAINT fk_resource_period FOREIGN KEY (period_id) REFERENCES period(id),
  CONSTRAINT fk_resource_resource_item FOREIGN KEY (res_item_id) REFERENCES resource_item(id),
  CONSTRAINT fk_resource_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE income (
  id SERIAL PRIMARY KEY,
  user_id integer NOT NULL,
  income_item_id integer NOT NULL,
  period_id integer NOT NULL,
  amount decimal(13,2) NOT NULL,
  CONSTRAINT fk_income_period FOREIGN KEY (period_id) REFERENCES period(id),
  CONSTRAINT fk_income_resource_item FOREIGN KEY (income_item_id) REFERENCES income_item(id),
  CONSTRAINT fk_income_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);


CREATE TABLE expense (
  id SERIAL PRIMARY KEY,
  user_id integer NOT NULL,
  expense_item_id integer NOT NULL,
  period_id integer NOT NULL,
  amount decimal(13,2) NOT NULL,
  CONSTRAINT fk_expense_period FOREIGN KEY (period_id) REFERENCES period(id),
  CONSTRAINT fk_expense_resource_item FOREIGN KEY (expense_item_id) REFERENCES expense_item(id),
  CONSTRAINT fk_expense_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

ALTER TABLE period ADD COLUMN begin_amount decimal(13,2) NOT NULL;