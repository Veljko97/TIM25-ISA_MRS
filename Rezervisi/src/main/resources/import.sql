
INSERT INTO USER (id, username, password, first_name, last_name, email, enabled, confirmed) VALUES (1, 'sys', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'user@example.com', true, true);
INSERT INTO USER (id, username, password, first_name, last_name, email, enabled, confirmed) VALUES (2, 'hotel', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'admin@example.com', true, true);
INSERT INTO USER (id, username, password, first_name, last_name, email, enabled, confirmed) VALUES (3, 'airline', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mile', 'Milic', 'mile@example.com', true, true);
INSERT INTO USER (id, username, password, first_name, last_name, email, enabled, confirmed) VALUES (4, 'rentacar', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikolina', 'Nikolic', 'niki@example.com', true, true);
INSERT INTO USER (id, username, password, first_name, last_name, email, enabled, confirmed) VALUES (5, 'user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'user', 'user', 'U@example.com', true, true);
INSERT INTO USER (id, username, password, first_name, last_name, email, enabled, confirmed) VALUES (6, 'user2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'user', 'user', 'U2@example.com', true, true);
INSERT INTO USER (id, username, password, first_name, last_name, email, enabled, confirmed) VALUES (7, 'user3', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'user', 'user', 'U3@example.com', true, true);
INSERT INTO USER (id, username, password, first_name, last_name, email, enabled, confirmed) VALUES (8, 'user4', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'user', 'user', 'U4@example.com', true, true);
INSERT INTO USER (id, username, password, first_name, last_name, email, enabled, confirmed) VALUES (9, 'user5', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'user', 'user', 'U5@example.com', true, true);
INSERT INTO USER (id, username, password, first_name, last_name, email, enabled, confirmed) VALUES (10, 'user6', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'user', 'user', 'U6@example.com', true, true);


INSERT INTO air_line (air_lineid, air_line_address,  air_line_description, air_line_name, image, average_grade) VALUES ('1', '1 Harborside Dr, Boston, MA 02128, USA', 'Good Airport', 'BostonAir', '/assets/images/airlines/1.jpg', '1.20');
INSERT INTO air_line (air_lineid, air_line_address,  air_line_description, air_line_name, image, average_grade) VALUES ('2', '08820 El Prat de Llobregat, Barcelona, Spain, France', 'Good', 'Barcelona–El Prat Airport', '/assets/images/airlines/2.jpg', '3.30');
INSERT INTO air_line (air_lineid, air_line_address,  air_line_description, air_line_name, image, average_grade) VALUES ('3', 'Alameda das Comunidades Portuguesas, 1700-111 Lisboa, Portugal', 'Good One', 'Lisbon Portela Airport', '/assets/images/airlines/3.jpg', '4.40');
INSERT INTO air_line (air_lineid, air_line_address,  air_line_description, air_line_name, image, average_grade) VALUES ('4', 'dubai airport', 'Top', 'Dubai International Airport', '/assets/images/airlines/4.jpg', '4.60');
INSERT INTO air_line (air_lineid, air_line_address,  air_line_description, air_line_name, image, average_grade) VALUES ('5', 'Queens, NY 11430, USA', 'Good','John F. Kennedy International Airport', '/assets/images/airlines/5.jpg', '2.20');
INSERT INTO air_line (air_lineid, air_line_address,  air_line_description, air_line_name, image, average_grade) VALUES ('6', 'Evert van de Beekstraat 202, 1118 CP Schiphol, Netherlands', 'Good','Amsterdam Airport Schiphol', '/assets/images/airlines/6.jpg', '3.10');
INSERT INTO air_line (air_lineid, air_line_address,  air_line_description, air_line_name, image, average_grade) VALUES ('7', '95700 Roissy-en-France, France', 'Good','Charles de Gaulle Airport', '/assets/images/airlines/7.jpg', '2.15');


INSERT INTO destination (id_destination, destination_description, destination_name, air_line_air_lineid, image) VALUES ('1', 'Dobra destinacija', 'Pariz', '7','/assets/images/destinations/1.jpg');
INSERT INTO destination (id_destination, destination_description, destination_name, air_line_air_lineid, image) VALUES ('2', 'Dobra destinacija', 'Amsterdam', '6','/assets/images/destinations/2.jpg');
INSERT INTO destination (id_destination, destination_description, destination_name, air_line_air_lineid, image) VALUES ('3', 'Dobra destinacija', 'Boston', '1','/assets/images/destinations/3.jpg');
INSERT INTO destination (id_destination, destination_description, destination_name, air_line_air_lineid, image) VALUES ('4', 'Dobra destinacija', 'New York', '5','/assets/images/destinations/4.jpg');
INSERT INTO destination (id_destination, destination_description, destination_name, air_line_air_lineid, image) VALUES ('5', 'Dobra destinacija', 'Dubai', '4','/assets/images/destinations/5.jpg');
INSERT INTO destination (id_destination, destination_description, destination_name, air_line_air_lineid, image) VALUES ('6', 'Dobra destinacija', 'Barcelona', '2','/assets/images/destinations/6.jpg');
INSERT INTO destination (id_destination, destination_description, destination_name, air_line_air_lineid, image) VALUES ('7', 'Dobra destinacija', 'Lisabon', '3','/assets/images/destinations/7.jpg');


INSERT INTO rentacar (rentacarid, rentacar_address,  rentacar_description, rentacar_name, image) VALUES ('1', '1403 S 3rd St, Mabank, TX 75147', 'cool', 'Enterprise','/assets/images/rentacars/1.jpg');
INSERT INTO rentacar (rentacarid, rentacar_address,  rentacar_description, rentacar_name, image) VALUES ('2', '1612A Bel Air Rd, Fallston, MD 21047', 'great', 'Rent Point','/assets/images/rentacars/2.png');
INSERT INTO rentacar (rentacarid, rentacar_address,  rentacar_description, rentacar_name, image) VALUES ('3', '686, Burnside Avenue, Inwood, USA, 11096', 'super', 'Rent Smart', '/assets/images/rentacars/3.jpg');
INSERT INTO rentacar (rentacarid, rentacar_address,  rentacar_description, rentacar_name, image) VALUES ('4', '6401 Passyunk Ave, Philadelphia, PA 19153', 'good', 'Rent A Car','/assets/images/rentacars/4.jpeg');
INSERT INTO rentacar (rentacarid, rentacar_address,  rentacar_description, rentacar_name, image) VALUES ('5', '11419 Reisterstown Rd, Owings Mills, MD 21117', 'very good', 'Sixt Rent A Car','/assets/images/rentacars/5.jpeg');
INSERT INTO rentacar (rentacarid, rentacar_address,  rentacar_description, rentacar_name, image) VALUES ('6', '5368 Telephone Rd, Warrenton, VA 20187', 'excellent', 'Student Rent A Car','/assets/images/rentacars/6.jpg');
INSERT INTO rentacar (rentacarid, rentacar_address,  rentacar_description, rentacar_name, image) VALUES ('7', '741 N Broadway, East Providence, RI 02914', 'excellent', 'Leisure Car Rental','/assets/images/rentacars/7.jpg');

INSERT INTO hotel (hotelid, hotel_address, hotel_description, hotel_name, destination_id_destination, image) VALUES ('1', '2 Rue Scribe, 75009 Paris, France', 'valja', 'InterContinental Paris - Le Grand', '2','/assets/images/hotels/1.jpg');
INSERT INTO hotel (hotelid, hotel_address, hotel_description, hotel_name, destination_id_destination, image) VALUES ('2', 'Spaarndammerdijk 302, 1013 ZX Amsterdam, Netherlands', 'valja', 'Art Hotel Amsterdam', '3','/assets/images/hotels/2.jpg');
INSERT INTO hotel (hotelid, hotel_address, hotel_description, hotel_name, destination_id_destination, image) VALUES ('3', '505 Washington Street, Theatre District, Boston, MA 02111, USA', 'valja', 'The Godfrey Hotel Boston', '1','/assets/images/hotels/3.jpg');
INSERT INTO hotel (hotelid, hotel_address, hotel_description, hotel_name, destination_id_destination, image) VALUES ('4', '870 7th Ave, New York, NY 10019, USA', 'valja', 'Park Central Hotel', '4','/assets/images/hotels/4.jpg');
INSERT INTO hotel (hotelid, hotel_address, hotel_description, hotel_name, destination_id_destination, image) VALUES ('5', '3 Sheikh Zayed Rd - Dubai - United Arab Emirates', 'valja', 'Sheraton Grand Hotel, Dubai', '5','/assets/images/hotels/5.jpg');
INSERT INTO hotel (hotelid, hotel_address, hotel_description, hotel_name, destination_id_destination, image) VALUES ('6', 'Avinguda Diagonal, 1, 08019 Barcelona, Spain', 'valja', 'Hotel Barcelona Princess', '6','/assets/images/hotels/6.jpg');
INSERT INTO hotel (hotelid, hotel_address, hotel_description, hotel_name, destination_id_destination, image) VALUES ('7', 'R. Castilho 149, 1099-034 Lisboa, Portugal', 'valja', 'InterContinental Lisbon', '7','/assets/images/hotels/7.jpg');

INSERT INTO air_line_admin(id, air_line_air_lineid) VALUES (3, 1);
INSERT INTO hotel_admin (id, hotel_hotelid) VALUES (2, 1);
INSERT INTO rentacar_admin (id, rentacar_rentacarid) VALUES (4,1);
INSERT INTO standard_user (id, discaunt_points) values (5, 0);
INSERT INTO standard_user (id, discaunt_points) values (6, 0);
INSERT INTO standard_user (id, discaunt_points) values (7, 0);
INSERT INTO standard_user (id, discaunt_points) values (8, 0);
INSERT INTO standard_user (id, discaunt_points) values (9, 0);
INSERT INTO standard_user (id, discaunt_points) values (10, 0);

INSERT INTO air_plane (id, name, number_of_business_class_seats, number_of_economy_class_seats, number_of_first_class_seats, luggage) VALUES ('1', 'Boeing 787', '30', '18', '24', '5.2');
INSERT INTO air_plane (id, name, number_of_business_class_seats, number_of_economy_class_seats, number_of_first_class_seats, luggage) VALUES ('2', 'Airbus A220', '42', '12', '6', '6.4');
INSERT INTO air_plane (id, name, number_of_business_class_seats, number_of_economy_class_seats, number_of_first_class_seats, luggage) VALUES ('3', 'Boeing 777', '48', '18', '6', '7.8');
INSERT INTO air_plane (id, name, number_of_business_class_seats, number_of_economy_class_seats, number_of_first_class_seats, luggage) VALUES ('4', 'Comac C919', '54', '12', '21', '10.2');
INSERT INTO air_plane (id, name, number_of_business_class_seats, number_of_economy_class_seats, number_of_first_class_seats, luggage) VALUES ('5', 'Airbus A330', '60', '18', '6', '12.2');
INSERT INTO air_plane (id, name, number_of_business_class_seats, number_of_economy_class_seats, number_of_first_class_seats, luggage) VALUES ('6', 'Douglas DC-8', '30', '24', '6', '12.1');
INSERT INTO air_plane (id, name, number_of_business_class_seats, number_of_economy_class_seats, number_of_first_class_seats, luggage) VALUES ('7', 'Convair 880', '72', '30', '24', '24.2');

INSERT INTO flight (id_flight,average_grade,additional_services,flight_length,landing_date,air_line_air_lineid,airplane_id,take_off_date,type,final_destination_id_destination,start_destination_id_destination, first_class_price, economy_class_price, business_class_price) VALUES ('1','5.00','Newspaper, Food','1200','19.6.2319','1','1','19.6.2119', '1', '2', '1', '1300', '1000', '1100');
INSERT INTO flight (id_flight,average_grade,additional_services,flight_length,landing_date,air_line_air_lineid,airplane_id,take_off_date,type,final_destination_id_destination,start_destination_id_destination, first_class_price, economy_class_price, business_class_price) VALUES ('2','4.00','Newspaper, Beverages','1200','19.6.1919','1','1','19.6.1818', '2', '3', '1', '500', '300', '400');
INSERT INTO flight (id_flight,average_grade,additional_services,flight_length,landing_date,air_line_air_lineid,airplane_id,take_off_date,type,final_destination_id_destination,start_destination_id_destination, first_class_price, economy_class_price, business_class_price) VALUES ('3','3.50','Newspaper, Food, Beverages', '1200','19.6.2719','1','1','19.6.2617', '1', '6', '3', '600', '500', '500');
INSERT INTO flight (id_flight,average_grade,additional_services,flight_length,landing_date,air_line_air_lineid,airplane_id,take_off_date,type,final_destination_id_destination,start_destination_id_destination, first_class_price, economy_class_price, business_class_price) VALUES ('4','4.40','Food, Beverages','1200','19.6.2919','1','1','19.6.2816', '1', '7', '5', '200', '200', '200');
INSERT INTO flight (id_flight,average_grade,additional_services,flight_length,landing_date,air_line_air_lineid,airplane_id,take_off_date,type,final_destination_id_destination,start_destination_id_destination, first_class_price, economy_class_price, business_class_price) VALUES ('5','2.20','Food, Beverages', '1200','19.6.3019','1','1','19.6.2915', '1', '2', '4', '300', '150', '250');
INSERT INTO flight (id_flight,average_grade,additional_services,flight_length,landing_date,air_line_air_lineid,airplane_id,take_off_date,type,final_destination_id_destination,start_destination_id_destination, first_class_price, economy_class_price, business_class_price) VALUES ('6','4.20','Food, Beverages', '1200','19.6.2519','1','1','19.6.2414', '2', '5', '3', '800', '600', '700');



INSERT INTO AUTHORITY (id, name) VALUES (1, 'ROLE_SYS_ADMIN');
INSERT INTO AUTHORITY (id, name) VALUES (2, 'ROLE_HOTEL_ADMIN');
INSERT INTO AUTHORITY (id, name) VALUES (3, 'ROLE_AIRLINE_ADMIN');
INSERT INTO AUTHORITY (id, name) VALUES (4, 'ROLE_RENTACAR_ADMIN');
INSERT INTO AUTHORITY (id, name) VALUES (5, 'ROLE_USER');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (5, 5);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (6, 5);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (7, 5);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (8, 5);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (9, 5);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (10, 5);