# Evo-Payments--JAVA-SDK
This  library provides integration access to the EVO Payments Gateway.

## Quick Start

EVO Payments Java SDK is a small library/sample of Java code that you can use to quickly integrate with the EVO Payments system and submit transactions, check their status and more.

## Before you Begin

Before using the EVO Payments Java SDK you should be familiar with the contents of the [API Specification for Merchants](docs/API-Specification.pdf) document as it describes all fields and their meaning within a given payment transaction.

## Setup your Project

EVO Payments Java SDK is delivered as Maven project. The possible profiles:
* "batch" creates a fat JAR (for command line usage)
* "webapp" creates a deployable WAR file (sample servlets)

There are only a very few dependencies (Apache HttpComponents, JUnit, org.json parser).

## Choose an Operation Mode

The SDK lets you choose between two ways of using it:

* __Server-to-Server mode__ - where your Java code performs all necessary preparations and operations on behalf of the user, but without his or her direct involvement (asside from the input params on the sample forms), or
 
* __Browser-to-Server mode__ - where your web page only instructs the client’s browser to connect directly to the Payment Processing servers where everything is settled directly between the two

Choose the one that is most appropriate for your project.

__It is possible to use the SDK from the command line__ (this is also Server-to-Server mode, but the SDK calls are initiated via command line params, not via servlets/forms).

## Configure

The global configuration can be set with the "evopayments-turnkey-sdk-config" system property.
The possible values are: "production" and "test" (default).

```bash
-Devopayments-turnkey-sdk-config=test
``` 

```bash
-Devopayments-turnkey-sdk-config=production
```

The releavant .properties files are in the src\main\resources\

## Form examples

You can find various examples, in the src\main\webapp\forms\ directory (there is an index.html in the webapp folder for quicker/easier access).

## Possible Requests

Every payment operation has its own Call Object. To successfully perform any request one needs to create the object (configure it) and then call its execute() method.

* __GetAvailablePaymentSolutionsCall__ queries the list of the possible payment solutions (ie. credit card) (based on the country/currency)
* __TokenizeCall__ tokenizes the card for future use.
* __AuthCall__ requests authorisation for a payment.
* __CaptureCall__ performs a capture operation on an authorized payment.
* __VoidCall__ cancels a previously authenticated payment.
* __PurchaseCall__ does an authorize and capture operations at once (and cannot be voided). It also supports Recurring Payments(COF) - set cardOnFileType to 'First' for initial transaction, set cardOnFileType to 'Repeat' and cardOnFileInitiator to 'Merchant' for subsequent transactions.
* __RefundCall__ refunds a previous capture operation, partially or in full.
* __StatusCheckCall__ returns the status of an already issued payment transaction, as such it doesn’t actually generate a new transaction.

All classes are descendants of the _ApiCall_ class.

For more information on payment transactions please check the [API Specification for Merchants](docs/API-Specification.pdf) document.

Some of the possible request/call chains (ie. tokenize -> auth -> capture) can be seen in the unit test to.

## Typical Flow

### I. Access the ApplicationConfig object like this:
```java
ApplicationConfig config = ApplicationConfig.getInstanceBasedOnSysProp();
```
### II. Create the a Call object:

```java
final Map<String, String> params = new HashMap<>();
inputParams.put("country", "FR");
inputParams.put("currency", "EUR");

ApiCall call = new GetAvailablePaymentSolutionsCall(config, params, new PrintWriter(System.out, true));
```

The call parameters have to supplied via a Map (the "params" parameter). 
For more information on the possible/needed map parameters please check the [API Specification for Merchants](docs/API-Specification.pdf) document.

The constructor will do a simple "pre" validation on the params Map. it will only check for the required keys (without an HTTP/API call).

### III. Execute the call:
```java
JSONObject result = call.execute();
```
For more information on the possible result values (JSON) please check the [API Specification for Merchants](docs/API-Specification.pdf) document.

### IV. Watch for Exceptions

Occasionally the SDK will not be able to perform your request and it will throw an _SDKException_. This could be due to misconfiguration or unexpected conditions like no connectivity to the API. 

Exceptions are described in more detail in a later section of this document.

```java
try {
	ApiCall call = new GetAvailablePaymentSolutionsCall(config, params, new PrintWriter(System.out, true));
} catch (RequiredParamException e) {
	// notify the user, exit the program, redirect to the error page etc.
}
```

```java
try {
	JSONObject result = call.execute();
} catch (ActionCallException e) {
	// notify the user, exit the program, redirect to the error page etc.
}
```

## Payments Errors

Occasionally your payment processing API will not be able to successfully complete a request and it will return an error. Please check out the [API Specification for Merchants](docs/API-Specification.pdf) document to find out more about errors and what causes them to occur.

## Exceptions

In addition the Java SDK provides custom exceptions:

* _NetworkException_
	The SDK could not connect to the API or there was another network connection-related error. The network call method failed for some reason. You can use getCause() to inspect the underlying Exception.
* _RequiredParamException_
	Thrown when a mandatory parameter has not been set (this is just a simple "pre" validation, without the API server, thrown by the constructor).
* _TokenAcquirationException_
	Failed to aquire the token for the action call.
* _ActionCallException_
	Failure during the main (the action) call.  
* _GeneralException_
	Other, not specified error in the SDK code. You can use getCause() to inspect the underlying Exception.
    
All these classes inherit from SDKException class so that you can easily separate them in a try-catch block.

The various exceptions can be seen in the unit test to. 

## Log

The ApiCall objects for the most part use the optional PrintWriter parameter object for logging purposes (the call actions (only the ActionType) are logged with a java.util.logging.Logger too).

You can use any PrintWriter implementation, for example StringWriter or something like this: 
https://logging.apache.org/log4j/2.x/log4j-iostreams/apidocs/org/apache/logging/log4j/io/LoggerPrintWriter.html

To enable the additional verbose API call log (= raw HTTP requests/response logs) use this system property:

```bash
-Devopayments-turnkey-sdk-http-log=verbose
``` 