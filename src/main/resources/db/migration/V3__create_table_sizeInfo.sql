CREATE TABLE sizeInfo (
  sizeInfo_id INT NOT NULL AUTO_INCREMENT,
  product_id INT NOT NULL,
  min INT,
  max INT,
  unit INT,
  PRIMARY KEY (sizeInfo_id),
  FOREIGN KEY (product_id) REFERENCES product (product_id)
);