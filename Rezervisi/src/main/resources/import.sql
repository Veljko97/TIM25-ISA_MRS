
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

INSERT INTO air_line (air_lineid, air_line_address,  air_line_description, air_line_name, airline_earning) VALUES ('1', 'fasf', 'fasf', 'Name', '0');
INSERT INTO destination (id_destination, destination_description, destination_name, air_line_air_lineid) VALUES ('1', 'Description neki', 'Destinacija', '1');
INSERT INTO rentacar (rentacarid, rentacar_address,  rentacar_description, rentacar_earning, rentacar_name) VALUES ('1', 'neka', 'super', '0', 'Ime');
INSERT INTO hotel (hotelid, hotel_address, hotel_description, hotel_earning, hotel_name, destination_id_destination) VALUES ('1', 'adresa', 'valja', '0', 'ime', '1');


INSERT INTO air_line_admin(id, air_line_air_lineid) VALUES (3, 1);
INSERT INTO hotel_admin (id, hotel_hotelid) VALUES (2, 1);
INSERT INTO rentacar_admin (id, rentacar_rentacarid) VALUES (4,1);
INSERT INTO standard_user (id) values (5);
INSERT INTO standard_user (id) values (6);
INSERT INTO standard_user (id) values (7);
INSERT INTO standard_user (id) values (8);
INSERT INTO standard_user (id) values (9);
INSERT INTO standard_user (id) values (10);

INSERT INTO air_plane (id, name, number_of_seats) VALUES ('1', 'Boeing 787', '30');
INSERT INTO air_plane (id, name, number_of_seats) VALUES ('2', 'Airbus A220', '42');
INSERT INTO air_plane (id, name, number_of_seats) VALUES ('3', 'Boeing 777', '48');
INSERT INTO air_plane (id, name, number_of_seats) VALUES ('4', 'Comac C919', '54');
INSERT INTO air_plane (id, name, number_of_seats) VALUES ('5', 'Airbus A330', '60');
INSERT INTO air_plane (id, name, number_of_seats) VALUES ('6', 'Douglas DC-8', '66');
INSERT INTO air_plane (id, name, number_of_seats) VALUES ('7', 'Convair 880', '72');

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