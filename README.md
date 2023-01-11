# JPA Relationship Lab 2

## Setup

[Fork and clone](https://github.com/learn-co-curriculum/java-mod-5-jpa-relationship-lab-2)
the lab and open the project in IntelliJ.

The lab repository has the required dependencies defined in `pom.xml`
and the database configuration is defined in  `persistence.xml`.

## Entity Relationship Model

You are given the following entity relationship model:

![Game review one to many relationship diagram](https://curriculum-content.s3.amazonaws.com/6036/java-mod-5-jpa/jpa_lab2_erd.png)

There is a one-to-many relationship between `Game` and `Review`.
A game has many reviews and each review is for a single game.

The `Review` class is on the "many" side of the relationship, thus it is the owner.

Open the `Game` class.  Notice it has a field for a list of reviews.

```java
private List<Review> reviews = new ArrayList<>();
```

Open the `Review` class.  It has a field for the game.

```java
private Game game;
```

The `Game` and `Review` classes are POJOs (plain old Java objects).
Neither class contains JPA annotations (@Entity, @Id, etc).

## Implement one-to-many/many-to-one relationship using JPA

1. You will use the database named `jpalab_db` that you created from the previous JPA lab.  
   Check `persistence.xml` to make sure the `hibernate.hbm2ddl.auto` property is set to `create`.
2. Edit the `Review` class:
   - Add the `@Entity` annotation to the class.
   - Add the `@Id` and `@GeneratedValue` annotations to the `id` field to make it an auto-incremented primary key.
   - Add the  `@ManyToOne` annotation to the `game` field.  
3. Edit the `Game` class:
   - Add the `@Entity` annotation to the class.
   - Add the `@Id` and `@GeneratedValue` annotations to the `id` field to make it an auto-incremented primary key.
   - Add the  `@OneToMany` annotation to the `reviews` field.  Set the `mappedBy` property to `"game"` and the `cascade` property to  `CascadeType.REMOVE`.
4. Run the `JpaCreate.main` method.  The code should create two tables `GAME` and `REVIEW`.
5. Use the **pgAdmin** query tool to query the tables.

`SELECT * FROM GAME;`

| ID  | GENRE  | TITLE  |
|-----|--------|--------|



`SELECT * FROM REVIEW;`

| ID  | COMMENT | SCORE | GAME_ID  |
|-----|---------|-------|----------|



Notice the one-to-many association is stored in the `REVIEW` table
as the column `GAME_ID`.  

Both tables are empty because `JpaCreate` created instances of `Game` and
`Review`, but the instances were not persisted into the database.

6. Edit `JpaCreate` and persist the two games and three reviews in the database.  
   Add the code after the transaction start `transaction.begin();` and before the
   transaction commit `transaction.commit();`

7. Run the `JpaCreate.main` method.  The code should create two tables `GAME` and `REVIEW`
   and add the two games and three reviews to the database.
8. Use the **pgAdmin** query tool to query the tables.

`SELECT * FROM GAME;`

| ID  | GENRE    | TITLE             |
|-----|----------|-------------------|
| 1   | Survival | Cave Escape       |
| 2   | Puzzle   | Holiday Crossword |


`SELECT * FROM REVIEW;`

| ID  | COMMENT             | SCORE | GAME_ID  |
|-----|---------------------|-------|----------|
| 3   | Too easy            | 3     | null     | 
| 4   | Fun and challenging | 7     | null     | 
| 5   | Great puzzle!       | 10    | null     | 

The `GAME_ID` column in the `REVIEW` table is null because `JpaCreate` does
not assign associations between a review and a game.

9. Edit `JpaCreate` to call the `setGame` method for each review.
   Review #3 and #4 are for game #1, while review #5 is for game #2.
10. Run the `JpaCreate.main` method to recreate the tables with the associations. 
11. Use **pgAdmin** to query the tables:

`SELECT * FROM GAME;`

| ID  | GENRE    | TITLE             |
|-----|----------|-------------------|
| 1   | Survival | Cave Escape       |
| 2   | Puzzle   | Holiday Crossword |


`SELECT * FROM REVIEW;`

| ID  | COMMENT             | SCORE | GAME_ID |
|-----|---------------------|-------|---------|
| 3   | Too easy            | 3     | 1       | 
| 4   | Fun and challenging | 7     | 1       | 
| 5   | Great puzzle!       | 10    | 2       | 
