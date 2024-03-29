BEGIN;

DROP TABLE IF EXISTS users,processor_production,user_processor,controller_type,controller_production,controller_location,user_controller CASCADE;

CREATE TABLE users(
  id SERIAL PRIMARY KEY,
  user_name VARCHAR(50) NOT NULL UNIQUE,
  email VARCHAR(50) NOT NULL UNIQUE,
  password TEXT NOT NULL,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  image VARCHAR(255),
  mobile VARCHAR(15),
  is_admin BOOLEAN DEFAULT false,
  create_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- processor
CREATE TABLE processor_production(
  id SERIAL PRIMARY KEY,
  create_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_processor(
  id SERIAL PRIMARY KEY,
  processor_id INTEGER REFERENCES processor_production(id)  NOT NULL,
  user_id INTEGER REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE NOT NULL 
);

-- Controller
CREATE TABLE controller_production(
  id SERIAL PRIMARY KEY,
  create_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE controller_type(
  id SERIAL PRIMARY KEY,
  label VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE controller_location(
  id SERIAL PRIMARY KEY,
  label VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_controller(
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  status BOOLEAN DEFAULT false,
  processor_id INTEGER REFERENCES user_processor(id)  ON UPDATE CASCADE ON DELETE CASCADE NOT NULL,
  typeId INTEGER REFERENCES controller_type(id) ON UPDATE CASCADE,
  location_id INTEGER REFERENCES controller_location(id) ON UPDATE CASCADE,
  controller_id INTEGER REFERENCES controller_production(id),
  create_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMIT;