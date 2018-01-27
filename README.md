# bowling-cdi
10 bowling game implementation.

## Dependencies and environment
This application is based on Java EE dependency injection standard CDI.

There are the main packages in the layout:
* org.fmino.bowlingscore.model:  serializable data models
* org.fmino.bowlingscore.api: provides the API interfaces and Exceptions for new swappable implementations (api jar + impl jar swap)
* org.fmino.bowlingscore.impl: Our current implementation

### CDI comppatibility:
Current implementation is CDI >= 1.2 compatible, with certified implementations: Apache OpenWebbeans or Weld. Also can be used in JavaEE 7 servers as Apache TomEE, Glassfish, Jboss Wildfly, Payara server, IBM Websphere, Oracle Weblogic and more.

## CDI usage
Field Injection:
```java
    @Inject
    private BowlingScoreProcessor processor;
```

Filesystem path resource process:
```java
    processor.processScore("/home/bowling/score-180127.txt");
```

## Get the sources from repo:
```
git clone https://github.com/fernandor777/bowling-cdi.git
```

## Executing tests:
```
mvn test
```

## Compile:
```
mvn package
mvn install
```

## Run
```
# Unix/Linux:
java -cp target/dist/bin/*:target/dist/lib/*:. org.fmino.bowlingscore.BowlingScoreAppMain src/test/resources/pinfalls/scores.txt
# Win:
java -cp "target/dist/bin/*;target/dist/lib/*;." org.fmino.bowlingscore.BowlingScoreAppMain src\test\resources\pinfalls\scores.txt
```

Provided good example files:
```
    src/test/resources/pinfalls/scores.txt
    src/test/resources/pinfalls/zeroes.txt
    src/test/resources/pinfalls/perfect.txt
```
    
Provided bad example files (for exceptions catch):
```
    src/test/resources/pinfalls/bad-scores-value.txt
    src/test/resources/pinfalls/bad-scores-amount.txt
```
