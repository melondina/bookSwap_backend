# BookShare project #
*those who do not read - live one life, those who read - thousands*

## 1. User view ##

### 1.a. Description ###

Many people love books - reading them, buying them, and using them. Our team also love books! According to statistics,
approximately 2.2 billion books are sold worldwide every year. However, this passion for books can come with its own set
of challenges:

1. You have a large collection of books at home that are in good condition. But a lot of space is taken up by books, and
   no one reads them anymore, they just get covered with dust and are useless. It would be great if they were read by
   someone who could appreciate the books at their true worth!
2. You want to continue reading, discover new things and new books, but if you read one or two books a month, then in a
   year that’s 24 new books on the shelf and that costs money!
3. Libraries can be difficult - limited or paid access, short time to read a book, relatively small selection of books,
   or no books in the language you need. And it happens that the library is very far away or it is not in the city at
   all.
4. We all understand that the consumer economy is detrimental to our planet. If you want to save the forest and reuse
   already produced things as much as possible, then the BookShare project will also help you with this.

In all of that cases, Book Share could be the solution. The concept is simple: you register on the website and
generously share
some of your books with a ***"Book Share Library"***. These books become available to all participants, who can also see
your
location down to the postal code. Anyone can request one of your books, and the shipping costs are covered by the
reader. Your responsibility, as a club member, is to take care of the books you have and send the requested book from
the nearest post office to the recipient's address (if provided) or to the post office specified by the recipient. And
you have access to a huge number of books from other members and your like-minded people!

The website displays all available books for unregistered users, but only registered users who have provided their
details can book a book. You can search for books based on filters such as country, language, genre, and authors. There
are sections for "My Library," where the books you've put up for lending, the ones you want to borrow, or the ones
someone wants to borrow from you are displayed. Additionally, each book has a specific description provided by the user,
including the number of pages, author, title, description, cover photo, and more.

Join Book Share today and be a part of this innovative book-sharing community that promotes sustainable reading and
access to a wide variety of books for everyone.

### 1.b. Interface ###

![First_page](https://drive.google.com/uc?export=view&id=1SOLNWrTHm6QBcY0GH3m5WieSJmKFFjyy)
![Book_page](https://drive.google.com/uc?export=view&id=1YG7zMQvU4xS1vNX1b7QQKDY9wdzh-v8e)
![My_library](https://drive.google.com/uc?export=view&id=1yjLbQ_D7_VHD4il2ODhdBjYgBcf3Xtrv)

## 2. Point of entry ##

This project is still in search of sponsors and is not posted on open paid platforms. To install the project locally,
please:

* download the [frontend](https://github.com/melondina/bookSwap_frontend)
  and [backend](https://github.com/melondina/bookSwap_backend) from the repositories.
* install the PostgreSQL database and create the `book_db` database there.
* please, check that the application.properties file from backend contains the correct address, password and username
  for
  your local database.
* using the file data.sql from the \src\main\resources\sql folder, you can autofill the database with starting values.
* please, start the backend (BookSwapBackendApplication file) and frontend (npm start) code.

## 3. Program architecture backend ##

### 3.a. Technology stack ###

Model View Controller (MVC) was used as the main architectural pattern

+ Java 17
+ Spring Framework (Boot, Web, JPA, Security, Validation, Test)
+ Swagger
+ Lombok
+ PostgreSQL
+ Maven
+ JUnit5, H2

### 3.b. Documentation ###

The annotations @Operation, @ApiResponses, @Schema, etc. are used to describe requests in the controller.
The API with a description of all possible requests and responses, as well as a description of the DTO entities can be
found in the swagger http://localhost:8080/swagger-ui/index.html after the project is launched.

### 3.c. Database structure ###

![DB](https://drive.google.com/uc?export=view&id=1jZdlgGFPyX92_rdtt3sUBf8nJ8UkLFiU)

## 4. Tests ##

The backend of the application is 100% covered with unit tests. For testing, the JUnit5 library and H2 local temporary
database were used. 37 unit tests were written, including negative and positive ones for 21 available endpoints.