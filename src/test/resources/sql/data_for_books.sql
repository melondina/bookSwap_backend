insert into categories (title)
values ("Esse");

insert into countries (title)
values ("Germany");

insert into users (agreement, email, first_name, last_name, postal_code, role, state, country_id)
VALUES (true, 'test@gmail.com', 'Ivan', 'Ivanov', 11111, 'USER', 'CONFIRMED', 1);

insert into books (title, author, description, category, language, pages, publisherDate, , cover, owner)
values ("Braiding Sweetgrass", "Robin Wall Kimmerer",
        "Drawing on her life as an indigenous scientist, and as a woman, Kimmerer shows how other living beings...",
        1, "English", 408, "2015-04-11", "f:/book_db/1.jpg", 1);