

# suggestion_system

In this project H2 Database is used to store posts and users choice.
you will need to jar files
download and import it in your project

1.h2-latest-version.jar

2.rs2xml.jar

create a database with name article and username article.
then use below query to create tables.

  CREATE TABLE TEST(ID NOT NULL AUTO_INCREMENT , NAME VARCHAR(255) , TAGS VARCHAR(255) , VIEWS INT(4));
  
  CREATE TABLE USERS(ID NOT NULL AUTO_INCREMENT , NAME VARCHAR(255) , LANGUAGE(255) , VIEWS INT(4));
  
Afte that insert dat manually and see the suggestion system working like a charm.



