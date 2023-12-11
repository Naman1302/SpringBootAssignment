# SpringBootAssignment

> A Spring Boot Application backed by MongoDB with the following schemaa nd API endpoints:
<br/>

#### Clone Project

```shell
git clone https://github.com/Naman1302/SpringBootAssignment.git
```

This Command will copy a full project to your local environment

## Model

### 1.Book
> a. id  b. copiesAvailable c. authorId d. genre
```java
@Document(collection = "book")
public class Book {
    @Id
    private ObjectId id;

    private int copiesAvailable;
    private String genre;
    private ObjectId authorId;
    //Getters, Setters and Constructors
}
```

### 2. Author
> a. id b. name c. Address ->(house no, city,state) d. BookList (containing bookid's)

```java
@Document(collection = "author")
public class Author {
    @Id
    public ObjectId id;
    private String name;
    private Address address;
    private List<ObjectId> bookList;
    // Getters, Setters and Constructors
}
```

But, now we need address as another object so that we can have more clean and readable code

### 3. Address
> a. house no. b. city c. state
```java
public class Address { //No Entity beacuse this is just author being destructured
    private int houseNumber;
    private String city;
    private String state;
    // Getters, Setters and Constructors
}
```

Now for the API Endpoints we created Service, Controllers and Repositories

## API endpoints:

Base Url: `http://localhost:8080` -> For local environment

#### For Books:

| Description                                        | URL (BaseUrl/)                             | Request Body/Params               |
|----------------------------------------------------| ------------------------------------------ | --------------------------------- |
| Show all Books                                     | /books (GET)                               |              -                    |
| Post new Book                                      | /books (POST)                              | A Book & Author name (String)     |
| Search Books by genre                              | /books/byGenre (GET)                       | Book Genre (String)               |
| Search books by genre and copies count greater than | /books/byGenreAndCopiesCount               | Book Genre (String) & Copies(Int) |
| Search by Authors's names                          | /byAuthorsNames                            | Author's Names seperated by ":"   |

Things I had implemented:
1) A GET api to get all the books from db
2) A GET api to get all the books from db whose genre matches the query param input
3) A GET api to get all the books from db whose genre matches the query param input and copiesAvailable is more than input provided in the path param.
4) A GET api to fetch all books whose author is in one of the input provided in the query params
5) A POST api to save book detail in db (including validations of not blank, not null etc) -> By taking names of author we will fetch the authorId

#### For Authors:

| Description                  | URL (BaseUrl/)                             | Request Body/Params               |
|------------------------------| ------------------------------------------ | --------------------------------- |
| Show all Authors             | /authors (GET)                             |              -                    |
| Post new Authors             | authors (POST)                             | A Author                          |         
| Search Authors by Names Like | /books/byNamesLike (GET)                   | A Author Name Pattern (String)    |

Things I had implemented:
1) A GET api to get all the authors from db
2) A GET api to fetch all authors whose name matches the regular expression provided in the input query params by using Regex queries from MongoDbRepository
3) A POST api to save author detail in db (including validations of not blank, not null etc)

## Unit Tests:

> Written tests using Mockito and JUnit 
