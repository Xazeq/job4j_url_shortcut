CREATE TABLE IF NOT EXISTS sites (
    id serial PRIMARY KEY,
    name varchar(250) UNIQUE NOT NULL,
    login varchar(20) UNIQUE NOT NULL,
    password varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS urls (
    id serial PRIMARY KEY,
    url varchar(2000) NOT NULL,
    encoded_url varchar(7) UNIQUE NOT NULL,
    call_count int,
    site_id int REFERENCES sites(id)
);