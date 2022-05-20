CREATE TABLE user (
  user_id INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(255),
  password VARCHAR(255),
  name VARCHAR(255),
  phone VARCHAR(255),
  address VARCHAR(255),
  PRIMARY KEY (user_id)
  );

CREATE TABLE product (
  product_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  brand VARCHAR(255),
  modelName VARCHAR(255),
  releaseDay DATE,
  color VARCHAR(255),
  PRIMARY KEY (product_id)
);

CREATE TABLE image (
  image_id INT NOT NULL AUTO_INCREMENT,
  product_id INT,
  link VARCHAR(255),
  PRIMARY KEY (image_id),
  FOREIGN KEY (product_id) REFERENCES product (product_id)
);
