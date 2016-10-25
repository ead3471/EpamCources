DROP ALL OBJECTS;
CREATE TABLE   Users (id   INT     NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    password  VARCHAR(20) NOT NULL,
                    role VARCHAR(10),
                    UNIQUE (name));

//Users Table
INSERT INTO Users (name, password,role) VALUES ('admin', '12345','admin');
INSERT INTO Users (name, password,role) VALUES ('user1', '1234','user');
INSERT INTO Users (name, password,role) VALUES ('user2', '1234','user');
INSERT INTO Users (name, password,role) VALUES ('user3', '123','user');
INSERT INTO Users (name, password,role) VALUES ('user4', '12345','user');
INSERT INTO Users (name, password,role) VALUES ('user5', '1235','guest');
//
