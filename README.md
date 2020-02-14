com.example.cinema
=======================

To run the application you should have a postgres server running on port 5432. Database name: `cinema`. Database variables are stored in `'application.properties'`.
Scripts to create tables and insert initial data are located in folder `'sql-scripts'`.

The jar file is located in folder `'target'`. 
Open Command line and change the current directory to the target folder via command `cd path/to/folder` and run `java -jar cinema.jar` to start server.

Server will run at port 8080. To search for films you can use postman/curl/etc by sending a GET request to endpoint `api/director/search` with parameters or without. Parameters are: `id` (unique director ID) and `minReleaseDate` (date after which films were released).

Example:
```
localhost:8080/api/director/search?id=3&minReleaseDate=2002-01-01
localhost:8080/api/director/search?minReleaseDate=2005-05-01
```
