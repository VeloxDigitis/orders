# --- !Ups

CREATE TABLE ORDERS (
  id BIGINT(20) AUTO_INCREMENT,
  date BIGINT(20),
  name VARCHAR(255),
  age INTEGER(20),
  PRIMARY KEY(id)
);

CREATE TABLE ITEMS (
  orderId BIGINT(20),
  color VARCHAR (255),
  size VARCHAR (255)
);

# --- !Downs

DROP TABLE ORDERS if EXISTS ;
DROP TABLE ITEMS if EXISTS ;