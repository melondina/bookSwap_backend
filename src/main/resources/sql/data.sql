
delete from wait_line;
delete from history;
delete from books;
delete from users;
delete from categories;
delete from city;
delete from languages;

SELECT setval('public.books_book_id_seq', 1, false);
SELECT setval('public.history_id_seq', 1, false);
SELECT setval('public.wait_line_line_id_seq', 1, false);
SELECT setval('public.users_user_id_seq', 1, false);
SELECT setval('public.categories_category_id_seq', 1, false);
SELECT setval('public.city_id_seq', 1, false);
SELECT setval('public.languages_language_id_seq', 1, false);

insert into categories (title_category) values ('Esse');
insert into categories (title_category) values ('Roman');
insert into categories (title_category) values ('Detective');
insert into categories (title_category) values ('Fantasy');
insert into categories (title_category) values ('Poetry');
insert into categories (title_category) values ('Stories');
insert into categories (title_category) values ('Biography');
insert into categories (title_category) values ('History');
insert into categories (title_category) values ('Fantastic');
insert into categories (title_category) values ('Adventures');
insert into categories (title_category) values ('Fairy tales');
insert into categories (title_category) values ('Publicity');
insert into categories (title_category) values ('Documentary prose');
insert into categories (title_category) values ('Humor');
insert into categories (title_category) values ('Horrors');
insert into categories (title_category) values ('Fanfic');

insert into languages (title) values ('English');
insert into languages (title) values ('German');
insert into languages (title) values ('French');
insert into languages (title) values ('Russian');
insert into languages (title) values ('Italian');

INSERT INTO city (postal_code, title_city) VALUES ('13599', 'Berlin');
INSERT INTO city (postal_code, title_city) VALUES ('01109', 'Hellerau');
INSERT INTO city (postal_code, title_city) VALUES ('01067', 'Dresden');
INSERT INTO city (postal_code, title_city) VALUES ('55743', 'Fischbach');
INSERT INTO city (postal_code, title_city) VALUES ('60308', 'Frankfurt am Main');

INSERT INTO users (agreement, email, first_name, last_name, password, role, state, city_id) VALUES (true, 'test@gmail.com', 'Joanne', 'Rowling', '$2a$10$umFgAvD5TcsCh0CK5uaSAOXbXek1d.7NrEqmsZzuLoA.hCypaxZlS', 'USER', 'CONFIRMED', 1);
INSERT INTO users (agreement, email, first_name, last_name, password, role, state, city_id) VALUES (true, 'test2@gmail.com', 'Boris', 'Johnson', '$2a$10$OkmkhWacyebrWeis2e89bOqWiUh9e7WFHTCdgXNsgt9zgkTiEaoW.', 'USER', 'CONFIRMED', 2);
INSERT INTO users (agreement, email, first_name, last_name, password, role, state, city_id) VALUES (true, 'test3@gmail.com', 'Frank', 'Henry', '$2a$10$umFgAvD5TcsCh0CK5uaSAOXbXek1d.7NrEqmsZzuLoA.hCypaxZlS', 'USER', 'CONFIRMED', 1);
INSERT INTO users (agreement, email, first_name, last_name, password, role, state, city_id) VALUES (true, 'test4@gmail.com', 'Anna', 'Müller', '$2a$10$umFgAvD5TcsCh0CK5uaSAOXbXek1d.7NrEqmsZzuLoA.hCypaxZlS', 'USER', 'CONFIRMED', 3);
INSERT INTO users (agreement, email, first_name, last_name, password, role, state, city_id) VALUES (true, 'test5@gmail.com', 'Frank', 'Bachmann', '$2a$10$umFgAvD5TcsCh0CK5uaSAOXbXek1d.7NrEqmsZzuLoA.hCypaxZlS', 'USER', 'CONFIRMED', 4);
INSERT INTO users (agreement, email, first_name, last_name, password, role, state, city_id) VALUES (true, 'test6@gmail.com', 'Bernar', 'Wolff', '$2a$10$umFgAvD5TcsCh0CK5uaSAOXbXek1d.7NrEqmsZzuLoA.hCypaxZlS', 'USER', 'CONFIRMED', 5);

INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('George R. R. Martin', 'https://bilder.buecher.de/produkte/34/34961/34961218n.jpg', '2023-09-06', 'Daenerys Targaryen, die Königin der Drachen, muss sich entscheiden, welchen ihrer adligen Freier sie heiraten wird. Wer wird der mächtigste Verbündete für die Eroberung von Westeros sein? ', 321, '2023', 'AVAILABLE', 'Ein Tanz mit Drachen', 4, 2, 3);
INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('Murphy Raymond', 'https://img4.labirint.ru/rc/e56c5caf0dccaec429eaa4fa7e6a7452/363x561q80/books33/328132/cover.jpg?1563654441', '2023-09-06', 'Lewis''s immortal prose have left many a lasting memory. For here is a world where a witch decrees eternal winter; where there are more talking animals than people; and where battles are fought by Centaurs, Giants and Fauns', 556, '2019', 'AVAILABLE', 'The Chronicles of Narnia', 2, 1, 1);
INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('Sebastian Fitzek', 'https://bilder.buecher.de/produkte/66/66297/66297806n.jpg', '2020-04-11', 'Ein lebenskluger und hinreißend komischer Roman im Stil von Sebastian Fitzeks', 95, '2020', 'AVAILABLE', 'Elternabend', 6, 2, 5);
INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('Акунин', 'https://booqua.de/images/product_gallery/1684141320_123.jpg.pagespeed.ce.wyRDGMECFL.jpg', '2023-09-06', 'Большое приключение Эраста Петровича Фандорина, рассказанное его бессменным помощником Масахиро Сибатой, началось в первый день двадцатого столетия.', 108, '2015', 'AVAILABLE', 'Яма', 3, 4, 2);
INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('Murphy Raymond', 'https://img3.labirint.ru/rc/75366e5b3a95d61eaf89a8900dd173f5/363x561q80/books71/700689/cover.jpg?1684934705', '2023-09-06', 'This book has clear explanations and practice exercises that have helped millions of people around the world improve their English', 256, '2017', 'AVAILABLE', 'English Grammar in Use. Book with Answers', 16, 1, 1);
INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('T. C. Boyle', 'https://bilder.buecher.de/produkte/66/66249/66249530n.jpg', '2023-09-06', 'Welcome to America. On the east coast, homes are being swallowed by the ocean; on the west coast, California is engulfed with wildfire. But for one family, the impending environmental disaster is the least of their worries. ', 236, '2023', 'AVAILABLE', 'Blue Skies', 10, 1, 2);
INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('Richard Flanagan', 'https://bilder.buecher.de/produkte/62/62528/62528738n.jpg', '2023-09-06', e'An ember storm of a novel, this is Booker Prize-winning novelist Richard Flanagan at his most moving-and astonishing-best.
Anna\'s aged mother is dying - if her three children would just allow it.ow into visions of horror and delight.', 543, '2022', 'AVAILABLE', 'The Living Sea of Waking Dreams', 8, 1, 6);
INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('David Sedaris', 'https://bilder.buecher.de/produkte/60/60590/60590709m.jpg', '2023-09-06', 'There''s no right way to keep a diary, but if there''s an entertaining way, David Sedaris seems to have mastered it.', 155, '2022', 'AVAILABLE', 'A Carnival of Snackery', 4, 1, 5);
INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('J. D. Robb', 'https://bilder.buecher.de/produkte/47/47661/47661977m.jpg', '2023-09-06', 'A devilishly disturbing new case for Lieutenant Eve Dallas from the Sunday Times bestseller J.D. Robb. As Eve investigates this shocking case, she soon discovers a disturbing pattern', 321, '2017', 'AVAILABLE', 'Echoes in Death', 15, 1, 4);

INSERT INTO history (book_id, user_id) VALUES (1, 1);
INSERT INTO history (book_id, user_id) VALUES (7, 1);
INSERT INTO history (book_id, user_id) VALUES (5, 1);

