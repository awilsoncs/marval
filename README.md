# Marval : Marvelous Validation

Marval provides an extensible, fluent, declarative API for defining data
validation rules in boolean and predicate logic. This lets you skip
writing tedious if-then logic to check for validity. 

Marval provides simple callback endpoints that let the client decide 
how to handle both invalid and valid data.

## Installing Marval

Marval is built with Maven.

```bash
$ git clone https://gitlab.com/awilson/marval.git
$ mvn clean install
```

## Using Marval

### Custom Endpoints

Customize the endpoint of the data validations by providing a callback function. In Java:

```java

class MyValidator implements Validator {
    
    public void validate(Object obj, BindingResult result) {
        //Logging validation
        the(obj).isAlphaNumeric().orDo(() -> log.info("I'm not alphanumeric"));
        the(obj).isAmong(Arrays.asList("A", "B", "C")).thenDo(() -> log.info("I am A, B, or C!"));
    }
}
```

Marval doesn't depend on the JVM, so it compiles to JS as well.

### Quantified Expressions

Marval supports first-order logic via universal, existential, and uniqueness quantifiers.

### Custom Selectors

You can add your own custom selectors for Marval. 

Selectors, by contract, accept an object argument and return a Selection.

In Java:

```java
//CustomSelectors.java
class CustomSelectors {
    //Select the largest digit of an Integer
    public static Selection largestDigitOf(Integer integer) {
        //Not required, but recommended.
        if(integer == null) { return new NullSelection(); }
        
        List<String> largest = Arrays.asList(integer.toString().split());
        return new SimpleSelection(largest.sort().get(0));
    }
}
```
```java
//CustomSelectorValidator.java
class CustomSelectorValidator implements Validator {
    public void validate(Object obj, BindingResult result) {
        largestDigitOf(obj).isGreaterThan(5).orDo(this::logResult);
    }
}
```

### Custom Rules