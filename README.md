# bowling-cdi
10 bowling game implementation.

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
    src\test\resources\pinfalls\scores.txt
    src\test\resources\pinfalls\zeroes.txt
    src\test\resources\pinfalls\perfect.txt
```
    
Provided bad example files (for exceptions catch):
```
    src\test\resources\pinfalls\bad-scores-value.txt
    src\test\resources\pinfalls\bad-scores-amount.txt
```
