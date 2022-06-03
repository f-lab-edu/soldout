CREATE TABLE sale (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  product_id INT NOT NULL,
  size INT,
  price INT,
  sale_type VARCHAR(255),
  expiration_day DATETIME,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user (user_id),
  FOREIGN KEY (product_id) REFERENCES product (product_id)
);