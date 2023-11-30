# SpringBootAssignment

<br/>
> A Spring Boot Application backed by MongoDB with the following schemaa nd API endpoints:
<br/>
##Model

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
a. id b. name c. Address ->(house no, city,state) d. BookList (containing bookid's)

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
a. house no. b. city c. state
```java
public class Address { //No Entity beacuse this is just author being destructured
    private int houseNumber;
    private String city;
    private String state;
    // Getters, Setters and Constructors
}
```

Now for the API Endpoints we created Service, Controllers and Repositories

##API endpoints:

1) Create a GET api to get all the books from db (Done)
2) Create a GET api to get all the books from db whose genre matches the query param input (Done)
3) Create a GET api to get all the books from db whose genre matches the query param input and copiesAvailable is more than input provided in the path param(Done).
4) Create a POST api to save book detail in db (including validations of not blank, not null etc)(Done)
5) Create a POST api to save author detail in db (including validations of not blank, not null etc)(Done)
6) Create a GET api to fetch all books whose author is in one of the input provided in the query params(Done)
7) Create a GET api to fetch all authors whose name matches the regular expression provided in the input query params.(Done)
