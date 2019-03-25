Data Analysis and Normalization details in backenddoc file

Project Structure and Implementation;

Design Pattern: DAO Factory Model

Project implemented using Data Access Object. 

DAO is an object that provides an abstract interface to database. By mapping applications calls to the persistence layer, the DAO provides details without exposing details of the database.



Motivation of DAO pattern: Changing persistence mechanism, service layer doesn’t even know where the data comes from, all changes (example change of database mysql or mongo) are done in the DAO layer only.



Project Structure:

•	Consumer: Contains client that would connect with services. It is starting point for application. Two clients are available:
o	QueryApp:  consumer for querying database provides services query by id and query by tags
o	WriterApp:  consumer will read the input file configured in conf.yml and write data into database.


•	Dao: Contains DAO and DAO Implementations for Author, Content, Tag and Thumbnail. Also contains DAO factory to return objects of respective DAO


•	Exception: contains code for exception handling


•	Mappings: Contains configuration files to connect to database, setup up log files, commit size and yml files.


•	Model: Contains models for mapping databases tables: Author, Content, ContentType, Tag and Thumbnails. Each model implements serializable


•	Service: Contains code for services provided by app


•	Util: Contains two
o	File utility: contains functions for reading csv file and other file related functions
o	SQL utility: contains SQL queries for insert, updates, select etc.



How to build and run project:

“mvn install” will create backendApi-1.0-SNAPSHOT.jar which have execulatble code.

•	To Run Java consumer to create data set:
Java -cp backendApi-1.0-SNAPSHOT.jar com.codefoo.consumer.WriterApp

•	To Run Query consumer:
Java -cp backendApi-1.0-SNAPSHOT.jar com.codefoo.consumer. QueryApp

Note: Please change conf.yml based on your environment settings.


Future work:	
•	Services to update content, update tag, select by author etc can be added.

