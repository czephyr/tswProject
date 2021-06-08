DROP SCHEMA tsw;

CREATE SCHEMA tsw;

use tsw;
CREATE TABLE `User` (
                        `userID` INT NOT NULL AUTO_INCREMENT,
                        `userEmail` varchar(255) NOT NULL,
                        `userName` varchar(255) NOT NULL,
                        `userSurname` varchar(255) NOT NULL,
                        `userPassword` varchar(255) NOT NULL,
                        `userIsAdmin` BINARY NOT NULL,
                        PRIMARY KEY (`userID`)
);

CREATE TABLE `Order` (
                         `orderID` INT NOT NULL AUTO_INCREMENT,
                         `user_id` INT NOT NULL,
                         `orderDate` DATE NOT NULL,
                         `orderTotal` DECIMAL NOT NULL,
                         PRIMARY KEY (`orderID`)
);

CREATE TABLE `Cart` (
                        `cartID` INT NOT NULL AUTO_INCREMENT,
                        `user_id` INT NOT NULL,
                        PRIMARY KEY (`cartID`)
);

CREATE TABLE `Product` (
                           `productID` INT NOT NULL AUTO_INCREMENT,
                           `productName` varchar(255) NOT NULL,
                           `productPrice` DECIMAL NOT NULL,
                           `productText` varchar(255) NOT NULL,
                           `productQuantity` INT NOT NULL,
                           `productImg` varchar(255) NOT NULL,
                           `productCategory` varchar(255) NOT NULL,
                           PRIMARY KEY (`productID`)
);

CREATE TABLE `product_order` (
                                 `order_id` INT NOT NULL,
                                 `product_id` INT NOT NULL,
                                 `quantity` INT NOT NULL
);

CREATE TABLE `product_cart` (
                                `cart_id` INT NOT NULL,
                                `product_id` INT NOT NULL,
                                `quantity` INT NOT NULL
);

ALTER TABLE `Order` ADD CONSTRAINT `Order_fk0` FOREIGN KEY (`user_id`) REFERENCES `User`(`userID`);

ALTER TABLE `Cart` ADD CONSTRAINT `Cart_fk0` FOREIGN KEY (`user_id`) REFERENCES `User`(`userID`);

ALTER TABLE `product_order` ADD CONSTRAINT `product_order_fk0` FOREIGN KEY (`order_id`) REFERENCES `Order`(`orderID`);

ALTER TABLE `product_order` ADD CONSTRAINT `product_order_fk1` FOREIGN KEY (`product_id`) REFERENCES `Product`(`productID`);

ALTER TABLE `product_cart` ADD CONSTRAINT `product_cart_fk0` FOREIGN KEY (`cart_id`) REFERENCES `Cart`(`cartID`);

ALTER TABLE `product_cart` ADD CONSTRAINT `product_cart_fk1` FOREIGN KEY (`product_id`) REFERENCES `Product`(`productID`);
