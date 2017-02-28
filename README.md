# Luola Java Bot

Sample Java bot for Dungeon Crawl game [Luola](https://github.com/aoh/luola)

## Prerequisites

* Java JDK 8
* Maven

## Installing

Install dependencies with Maven

```
mvn install
```

Start the bot

```
mvn exec:java
```

Start multiple bots

```
mvn exec:java -Dexec.args="5"
```


## Build jar with dependencies

```
mvn package
```

```
java -jar target/javabot-1.0.0-jar-with-dependencies.jar 5
```
