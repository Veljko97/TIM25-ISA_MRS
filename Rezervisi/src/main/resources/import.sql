INSERT INTO USER (id, username, password, first_name, last_name, email, enabled, confirmed) VALUES (1, 'sys', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'user@example.com', true, true);
INSERT INTO USER (id, username, password, first_name, last_name, email, enabled, confirmed) VALUES (2, 'hotel', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'admin@example.com', true, true);
INSERT INTO USER (id, username, password, first_name, last_name, email, enabled, confirmed) VALUES (3, 'airline', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mile', 'Milic', 'mile@example.com', true, true);
INSERT INTO USER (id, username, password, first_name, last_name, email, enabled, confirmed) VALUES (4, 'rentacar', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikolina', 'Nikolic', 'niki@example.com', true, true);
INSERT INTO USER (id, username, password, first_name, last_name, email, enabled, confirmed) VALUES (5, 'user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'user', 'user', 'U@example.com', true, true);

INSERT INTO air_line (air_lineid, air_line_address, air_line_average_grade, air_line_description, air_line_name, airline_earning) VALUES ('1', 'fasf', '0', 'fasf', 'Name', '0');
INSERT INTO rentacar (rentacarid, rentacar_address, rentacar_average_grade, rentacar_description, rentacar_earning, rentacar_name) VALUES ('1', 'neka', '0', 'super', '0', 'Ime');
INSERT INTO hotel (hotelid, hotel_address, hotel_average_grade, hotel_description, hotel_earning, hotel_name) VALUES ('1', 'adresa', '0', 'valja', '0', 'ime');


INSERT INTO air_line_admin(id, air_line_air_lineid) VALUES (3, 1);
INSERT INTO hotel_admin (id, hotel_hotelid) VALUES (2, 1);
INSERT INTO rentacar_admin (id, rentacar_rentacarid) VALUES (4,1);



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