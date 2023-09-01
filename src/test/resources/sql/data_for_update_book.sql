insert into categories (title)
values ('Esse');

insert into users (agreement, email, first_name, last_name, role, state)
VALUES (true, 'test@gmail.com', 'Ivan', 'Ivanov',  'USER', 'CONFIRMED');

insert into books (title, author, description, category_id, language, pages, publisher_date, cover, user_id, date_create, state)
values ('Braiding Sweetgrass', 'Robin Wall Kimmerer', 'Drawing on her life as an indigenous scientist, and as a woman, Kimmerer shows how other living beings...',
        1, 'English', 408, '2015-04-11', 'f:/book_db/1.jpg', 1, '2023-08-30', 'AVAILABLE')