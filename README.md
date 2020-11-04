# auth-service

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This project is educational, this is a Rest API based on [Mangos Authentication Database](https://github.com/mangoszero/database), it provides access to the database through web calls:
- Account
- Ban
- Db Version
- Realm
- Realm status
- Warden logs
- Database Maintenance operations

# OpenAPI

This project uses OpenAPI for documentation, you can consult the YML file describing the [API](src/main/resources/openapi.yml), this file can be imported in any OpenAPI compatible tool (such as Postman).

# How-to deploy ?

First of all, you need to have setup the authentication database and have it available. Refer to the [official website](https://getmangos.eu/) to find out how to do it. Then, you'll need a web server with the following J2EE features available:
- jaxrs-2.1
- jsonp-1.1
- cdi-2.0
- jpa-2.2
- mpConfig-1.4
- mpOpenAPI-1.1

This software is tested against [openliberty 20.0.0.11](https://openliberty.io/).

# Configuring your webserver

Start by making available, in your lib directory, your database JDBC driver. Once done, here's an example for an openliberty server.xml configuration which will setup this application on `/auth-service` on port 9081. It's also assuming that you're using MariaDB and that it's provided in the shared resources folder of the webserver.

```xml
<server description="ZeroAuthDB">
    <featureManager>
        <feature>jaxrs-2.1</feature>
        <feature>jsonp-1.1</feature>
        <feature>cdi-2.0</feature>
        <feature>jpa-2.2</feature>
        <feature>mpConfig-1.4</feature>
        <feature>mpOpenAPI-1.1</feature>
    </featureManager>

    <variable name="default.http.port" defaultValue="9081"/>
    <variable name="version.mariadb" defaultValue="2.7.0"/>
    <variable name="mariadb.lib" defaultValue="${shared.resource.dir}/mariadb-java-client-${version.mariadb}.jar"/>

    <httpEndpoint httpPort="${default.http.port}" id="defaultHttpEndpoint" hosts="*" />

    <jpa defaultJtaDataSourceJndiName="jdbc/AuthDBDS" entityManagerPoolCapacity="5"/>
    <library id="MARIADBLIB" name="Library for Maria DB">
        <file name="${mariadb.lib}"/>
    </library>
    <dataSource id="AuthDBDS" jndiName="jdbc/AuthDBDS">
    	<jdbcDriver javax.sql.DataSource="org.mariadb.jdbc.Driver" libraryRef="MARIADBLIB"/>
    	<properties URL="jdbc:mariadb://<db_url>:<db_port>/<db_name>" databaseName="<db_name>" password="<db_password>" portNumber="<db_port>" serverName="<db_host>" user="<db_user>"/>
    </dataSource>
    <webAppSecurity singleSignonEnabled="false"/>
    <basicRegistry id="basic" realm="dummyRealm">
    	<user name="admin" password="<basic_password>"/>
    </basicRegistry>
</server>
```

Voilà ! You're all set, now you can happily use this API to interact with the database.