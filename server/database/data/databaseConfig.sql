-- psql -U postgres
CREATE DATABASE hmsydev;
CREATE USER hmsyuser WITH superuser password '123456';
ALTER DATABASE hmsydev OWNER TO hmsyuser;
-- test db
CREATE DATABASE hmsytest;
CREATE USER  hmsyuser2 WITH superuser password '123456';
ALTER DATABASE  hmsytest OWNER TO hmsyuser2;