# Bank Kata in Scala

## Description 

Completing the kata using the best of an OO and functional approaches

## Usage 

Clone this reposistory 
To see example usage of program run this command to run the tests: 
```
sbt test
```

## Feedback/Improvements 

Inside Account Tests are modeled more like integration tests, For example: 

The testing of the deposit method checks the behaviour of the balance method not the method calls it makes itself 
Focus on making tests completely relative to a single method.

Impliment Mocking of Statement class inside Account. 

