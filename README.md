# The small cryptocurrency parsing application using the MongoDB

### What it does

This is small, but useful app takes the last price of a cryptocurrency pair from the [cex.io](URL) site, adds the timestamp and writes all information to MongoDB (feel free to use the local or remote MongoDB). The database connection properties is in the **/resources/application.properties** file
The document in database is look like

{"id":"6408664e86e5e946fd61b12a","name":"BTC/USD","price":22139.9,"timeStamp":"2023-03-08T12:41:18.558"}

### How to use it

Use the Intellij IDEA to run the application. Start point is in the **MongoAppApplication** class.
You can select the list of the cryptocurrency pairs. The **com.mate.mongoapp.model** package contains the **Pair** enum with cryptocurrency pairs names in it. Feel free to delete or add any pair to this list.
The parsing period is 10 seconds. If you want to change it, set new value in milliseconds to TIME_RANGE constant in **MongoAppApplication** class.

Then, use your browser to find needed information in your database. After starts the application, the Apache Tomcat available URL is 
**http://localhost:8080/cryptocurrencies/**

Some endpoints uses for:

  * **/cryptocurrencies/minprice?name=[currency_name]** - should return a record with the lowest price of selected cryptocurrency. **name** like btc, xrp in the lowercase
 * **/cryptocurrencies/maxprice?name=[currency_name]** - should return a record with the highest price of selected cryptocurrency. **name** like btc, xrp in the lowercase
 * **/cryptocurrencies?name=[currency_name]&page=[page_number]&size=[page_size]** - should return a selected page with a selected number of elements and default sorting should be by price from lowest to highest. For example, if page=0&size=10, the app will return the first 10 elements from the database, sorted by price from lowest to highest.