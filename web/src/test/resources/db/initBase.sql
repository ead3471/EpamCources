DROP ALL OBJECTS;
CREATE TABLE   DEVICES (id   INT     NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    serial  VARCHAR(20) NOT NULL,
                    mount_place VARCHAR(30),
                    last_verification DATE,
                    next_verification DATE,
                    passport_url VARCHAR (100),
                    UNIQUE (serial,name));

//Devices Table
INSERT INTO DEVICES (name, serial,mount_place,last_verification,next_verification) VALUES ('DEVICE_TYPE_1', '1','PLACE_1','2012-01-01','2016-12-20');
INSERT INTO DEVICES (name, serial,mount_place,last_verification,next_verification) VALUES ('DEVICE_TYPE_1', '2','PLACE_1','2013-01-01','2021-10-30');
INSERT INTO DEVICES (name, serial,mount_place,last_verification,next_verification) VALUES ('DEVICE_TYPE_2', '3','PLACE_2','2017-01-01','2020-05-20');
INSERT INTO DEVICES (name, serial,mount_place,last_verification,next_verification) VALUES ('DEVICE_TYPE_3', '4','PLACE_2','2014-01-01','2016-01-01');
INSERT INTO DEVICES (name, serial,mount_place,last_verification,next_verification) VALUES ('DEVICE_TYPE_3', '5','PLACE_1','2012-01-01','2019-04-15');
INSERT INTO DEVICES (name, serial,mount_place,last_verification,next_verification) VALUES ('DEVICE_TYPE_3', '6','PLACE_1','2014-01-01','2018-03-15');
INSERT INTO DEVICES (name, serial,mount_place,last_verification,next_verification) VALUES ('DEVICE_TYPE_3', '7','PLACE_5','2011-01-01','2016-08-15');
INSERT INTO DEVICES (name, serial,mount_place,last_verification,next_verification) VALUES ('DEVICE_TYPE_3', '8','PLACE_3','2015-01-01','2018-03-15');
INSERT INTO DEVICES (name, serial,mount_place,last_verification,next_verification) VALUES ('DEVICE_TYPE_3', '9','PLACE_2','2009-01-01','2016-09-15');
//
