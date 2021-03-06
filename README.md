# CurrencyAPI

An easy way to access your players balance. Simple import this API into your projects where balance has a role and your're ready to go!

You have to write following to get started:

```java
    @Override
    public void onEnable() {
        //For Spigot Plugins
        CurrencyAPI currencyAPI = SpigotCurrency.getInstance().getCurrencyAPI();
        //For BungeeCord Plugins
        CurrencyAPI currencyAPI = BungeeCurrency.getInstance().getCurrencyAPI();
    
    }
```
    
It is as simple as it can go. Now you just use the methods provided by the API

For example like this:

```java
        UUID uuid = null; //For presentation purpose null
        double balance = currencyAPI.getBalance(uuid);

        System.out.println(balance);
```
This would output the balance stored in the database in combination with the uuid

Or to add some balance:

```java
        UUID uuid = null; //For presentation purpose null
        currencyAPI.depositBalance(uuid, 175.0);
```

Maven Repository:
        
```xml
    <repositories>
        <repository>
            <id>currencyapi-repo</id>
            <url>http://repo.zm4xi.de</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>de.zm4xi.currencyapi</groupId>
            <artifactId>CurrencyAPI</artifactId>
            <version>1.2</version>
        </dependency>
    </dependencies>
```
