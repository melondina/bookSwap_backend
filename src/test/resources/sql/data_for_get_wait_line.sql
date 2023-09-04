insert into categories (title)
values ('Esse');

insert into languages (title)
values ('English');

insert into users (agreement, email, first_name, last_name, role, state)
VALUES (true, 'test@gmail.com', 'Ivan', 'Ivanov',  'USER', 'CONFIRMED');

insert into users (agreement, email, first_name, last_name, role, state)
VALUES (true, 'test2@gmail.com', 'Ivan', 'Ivanov',  'USER', 'CONFIRMED');

insert into books (title, author, description, category_id, language_id, pages, publisher_date, cover, user_id, date_create, state)
values ('Braiding Sweetgrass', 'Robin Wall Kimmerer', 'Drawing on her life as an indigenous scientist, and as a woman, Kimmerer shows how other living beings...',
        1, 1, 408, '2015-04-11', 'f:/book_db/1.jpg', 1, '2023-08-30', 'AVAILABLE');

insert into wait_line (book_id, user_id)
VALUES (1, 2);