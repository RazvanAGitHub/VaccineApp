-- populating the table user_roles
insert into user_roles (name) values ('ADMIN');
insert into user_roles (name) values ('CLIENT');

-- populating the table users
insert into users (id, address, cnp, email, family_doctor, phone_number, profession, username)
values (1, 'Cluj Napoca', '1234567891231', 'aaa1@a.com', 'm1', '0770 0000001', 'prof1', 'user1');

insert into users (id, address, cnp, email, family_doctor, phone_number, profession, username)
values (2, 'Bucuresti', '1234567891232', 'aaa2@a.com', 'm2', '0770 0000002', 'prof2', 'user2');

insert into users (id, address, cnp, email, family_doctor, phone_number, profession, username)
values (3, 'Cluj Napoca', '1234567891233', 'aaa3@a.com', 'm3', '0770 0000003', 'prof3', 'user3');

-- populating the table vaccines
insert into vaccines(
    id, min_age, priority1amount_initial, priority1amount_remaining, priority2amount_initial, priority2amount_remaining, priority3amount_initial, priority3amount_remaining, producer, rappel, storage_temperature)
values (1, 16, 100, 100, 0, 0, 0, 0, 'Phizer and BioNTech', 21, -80);

insert into vaccines(
    id, min_age, priority1amount_initial, priority1amount_remaining, priority2amount_initial, priority2amount_remaining, priority3amount_initial, priority3amount_remaining, producer, rappel, storage_temperature)
values (2, 18, 30, 30, 0, 0, 0, 0, 'Moderna', 28, -20);