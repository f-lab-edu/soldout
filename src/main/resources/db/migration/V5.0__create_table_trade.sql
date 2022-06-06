CREATE TABLE trade (
  id INT NOT NULL AUTO_INCREMENT,
  product_id INT NOT NULL,
  order_id INT NOT NULL,
  sale_id INT NOT NULL,
  size INT,
  price INT,
  trade_status VARCHAR(255),
  signing_day DATETIME,
  PRIMARY KEY (id),
  FOREIGN KEY (product_id) REFERENCES product (product_id),
  FOREIGN KEY (order_id) REFERENCES `order` (order_id),
  FOREIGN KEY (sale_id) REFERENCES sale (id)
);