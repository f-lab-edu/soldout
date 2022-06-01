CREATE TABLE `order` (
  order_id INT NOT NULL AUTO_INCREMENT,
  user_id INT,
  product_id INT,
  size INT,
  price INT,
	order_type VARCHAR(255),
	expiration_period VARCHAR(255),
	expiration_day DATE,
  PRIMARY KEY (order_id),
  FOREIGN KEY (user_id) REFERENCES user (user_id),
  FOREIGN KEY (product_id) REFERENCES product (product_id)
);