CREATE TABLE IF NOT EXISTS customers
(
    id bigint(20) NOT NULL AUTO_INCREMENT,
    name  VARCHAR(200) NOT NULL ,
    email VARCHAR(254) NOT NULL,
    PRIMARY KEY (id)

);
