CREATE TABLE  IF NOT EXISTS  City
(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR (200),
    country_id BIGINT,
    CONSTRAINT country_FK foreign key (country_id) REFERENCES Country(id)
);