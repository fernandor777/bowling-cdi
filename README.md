# bowling-cdi

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
