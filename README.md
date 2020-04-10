## Fork of  Spring Cloud Stream ETL Demo - https://github.com/Paul-Warren/rdbms-to-geode

#### With modifications to use:
   1. Spring Cloud Stream Function Support.
   2. docker rabbitmq image
   3. docker geode image and local mysql database
   4. Spring boot starter geode - with auto configured clientCache, ClientRegionfactory, pdxSerializer and @Region annotation
   5. Updated spring-boot 2.2.5.RELEASE

## Setting up your environment
This demo uses Apache Geode docker image, RabbitMQ docker Image

1. Run MySQL locally; make sure user & password to access are added to application.properties
     ```
     create database rdbms_to_geode
     ```
2. Run Geode, RabbitMQ in docker
    ```
    docker-compose up
    ```
3. Build & Run jdbc-event-source-app`, `jdbc-event-processor-app`, and `geode-sink-app`
    ```
    java -jar source/build/libs/source.jar
    java -jar processor/build/libs/processor-0.0.1-SNAPSHOT.jar
    java -jar build/libs/sink-0.0.1-SNAPSHOT.jar
    ```
### Starting Apache Geode
1. [Open `gfsh` terminal](#apache-geode-commands)

2. Create regions
    ```
    create region --name=customer --type=PARTITION --skip-if-exists
    create region --name=customerOrder --type=PARTITION --skip-if-exists
    create region --name=item --type=PARTITION --skip-if-exists
    ```

## Running the demo
### Insert initial data
1. Run [schema.sql](demo-steps/mysql/1_create_schema.sql) to create tables
1. Run [data.sql](demo-steps/mysql/2_initial-data.sql) to insert `CUSTOMER`, `ITEM`, `CUSTOMER_ORDERS`, and `ORDER_ITEM` rows
1. Start a `gfsh` terminal and connect to the locator
    ```
    connect
    ```
1. Query the regions to see that the data was extracted from the MySQL database, transformed to the proper types, and 
loaded into Geode
    ```
    query --query="select * from /customer"
    query --query="select * from /customerOrder"
    query --query="select * from /item"
    ```

### Trigger a data change
1. Run [trigger.sql](demo-steps/mysql/4_create_triggers.sql) to create the triggers that will insert `DB_EVENT` rows 
when data in other tables is inserted, updated, or deleted
1. Run [delta-data.sql](demo-steps/mysql/5_update_data.sql) to do the following:
    1. `DELETE` an `ORDER_ITEM` from `order1`
    1. `DELETE` a `CUSTOMER_ORDER` from `customer1` (will `DELETE` all `ORDER_ITEM` rows for `order2` first)
    1. `INSERT` a `ITEM` (will add a new item (Stapler) that can be included in an order)
    1. `INSERT` a `CUSTOMER_ORDER` and `ORDER_ITEM (will create an order for the new item created above)
1. Query the regions again to see that the data that was deleted from the MySQL database was also deleted from Geode
    ```
    query --query="select * from /customer"
    query --query="select * from /customerOrder"
    query --query="select * from /item"
    ```
---
---

## Additional Information
### Apache Geode Commands
In order to run `gfsh` commands you need to open a `gfsh` terminal. It's recommended that you create a working 
directory that you open `gfsh` from because starting locators and servers creates directories and files.
```
mkdir tmp && cd tmp
gfsh
```

Once in the `gfsh` terminal:
- `connect` connects the gfsh session to the cluster
- `list members` displays all members (locators, servers) that are part of the cluster
- `list regions` displays all regions that have been created
- `query --query="<oql here>"` executes a query ([OQL help](http://geode.apache.org/docs/guide/13/developing/querying_basics/query_basics.html))


#TODO
Add graceful shutdown of geode docker containers - Geode Docker containers do not shut down gracefully; To restart , previous containers  have to be removed
```
docker rm geode_server_1
docker rm geode_locator_1
```

Add message aggregation - Aggregation from previous project has  been removed - I could not use an IntegrationFlow inside the Consumer Function
