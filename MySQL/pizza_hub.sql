CREATE DATABASE pizza_hub;
USE pizza_hub;
CREATE TABLE pizzas(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
    price DECIMAL NOT NULL,
    size CHAR NOT NULL
    );
    
    