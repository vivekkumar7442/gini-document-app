About Project:

This is an spring boot project and it involves REST API implementation for document upload and various operations performed on the documents.

Core Framework:

Spring Boot
Java 1.8
JPA (Hibernate Implementation)
MySQL
Swagger
Spring security with Basic Auth
JUnit with Mockito

About the Implementation:

Security Implementation:

REST API related to admin are secured with basic auth. Username and password are configured in the application properties.

As of now, I have restricted the access for internal admin API. We can add the filters in security configuration file if we wanted to extend it to other API's as well.

DB Model:

For this implementation I have created two entity's as "UserDetails" and " Documents" which has "one-to-many" relationship.

Swagger Implementation

I have implemented swagger. It can be accessed with the below URL:

http://localhost:7868/gini-filestorage/api/swagger-ui.html

REST API

I have implemented two controllers one for admin access and the other for normal users listed below:

DocumentController - As of now, I have allowed all the API access without restriction we can also add Basic Authentication to this also.

Actions Implemented

Create the document record.
Retrieve the record by id and user id
Update document description
InternalStakeHolderController - The API can be accessed only with Basic Authentication username and password provided in the application properties.

Actions Implemented

Get all documents for the user
For the given user id, convert all the documents and upload the zip file to a other REST API.
JUnit Implementation

I have implemented Junit with Mockito for integration testing and service layer testing.
