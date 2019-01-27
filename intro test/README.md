# README #
Task description:
http://enos.itcollege.ee/~jpoial/algorithms/aa_lab1.html

ülesande teksti leiate http://enos.itcollege.ee/~jpoial/algoritmid/aa_lab1.html

## Command line examples. Näidete kasutamine käsurealt ##
#### Compilation. Kompileerimine: ####

```
#!bash

javac -cp src src/Answer.java
```

#### Execution. Käivitamine: ####

```
#!bash

java -cp src Answer
```


### Usage of tests. Testide kasutamine ###
#### Compilation of a test. Testi kompileerimine: ####

```
#!bash

javac -encoding utf8 -cp 'src:test:test/junit-4.12.jar:test/hamcrest-core-1.3.jar' test/AnswerTest.java

```
In Windows replace colons by semicolons. Sama Windows aknas (koolonite asemel peavad olema semikoolonid):

```
#!bash

javac -encoding utf8 -cp 'src;test;test/junit-4.12.jar;test/hamcrest-core-1.3.jar' test/AnswerTest.java


```

#### Running a test. Testi käivitamine: ####

```
#!bash

java -cp 'src:test:test/junit-4.12.jar:test/hamcrest-core-1.3.jar' org.junit.runner.JUnitCore AnswerTest
```

The same for Windows. Sama Windows aknas (koolonite asemel semikoolonid):

```
#!bash

java -cp 'src;test;test/junit-4.12.jar;test/hamcrest-core-1.3.jar' org.junit.runner.JUnitCore AnswerTest
```
