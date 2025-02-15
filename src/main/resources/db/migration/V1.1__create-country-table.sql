CREATE TABLE IF NOT EXISTS Country
(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    country_phone_code VARCHAR(5),
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    flag VARCHAR (255),
    country_iso_code  VARCHAR(3),
    country_iso_code_2 VARCHAR(2),
    name VARCHAR (200),
    currency_code VARCHAR(3),
    phone_length INTEGER
);