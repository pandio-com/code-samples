## How to deploy a Java function?

The following guide will help you setup and configure your Pandio function via the form on the left.

### Function Name

We generated a random function name. Feel free to change it. The name must be unique within each Pulsar namespace.

### Runtime

Currently, we support Python 3 and Java 8 runtimes.

### Function / Package file

Java function must be packaged and uploaded as a JAR file. To get started please refer to:

* [Example how to build a JAR package using maven](https://pulsar.apache.org/docs/en/functions-package/#java) Steps 1-3
* [Java Examples library from Pulsar](https://github.com/apache/pulsar/tree/master/pulsar-functions/java-examples) (You can check it out and build using [maven](https://maven.apache.org/))

### Class Name

FQCN (Fully Qualified Class Name) of a function. Example: `org.apache.pulsar.functions.api.examples.ExclamationFunction`

### Processing Guarantees

A [messaging semantics](https://pulsar.apache.org/docs/en/functions-overview/#processing-guarantees) that can be applied to a function.

### Auto Acknowledgement

A flag to enable or disable auto acknowledgement of consumed messages.

### Input topics

A list of Pulsar topics a function will consume from. The function will apply processing logic to every message it receives.

Advanced input topic properties:

* [Schema Type](https://pulsar.apache.org/docs/en/schema-understand/#schema-type)
* [Schema Properties](https://pulsar.apache.org/docs/en/schema-understand/#schema-type)
* [SerDe Class Name](https://pulsar.apache.org/docs/en/functions-develop/#serde)
* Regex Pattern - flag that indicates whether topic name is regex pattern

### Output topic

Name of an output topic where the function will produce results.

Advanced output topic properties:

* [Schema Type](https://pulsar.apache.org/docs/en/schema-understand/#schema-type)
* [SerDe Class Name](https://pulsar.apache.org/docs/en/functions-develop/#serde)

### User properties

Optional user-defined configuration properties passed to functions as arbitrary key-value pairs. Key-value pair map is part of function context. An example which reads user-defined configuration property in [Java](https://github.com/apache/pulsar/blob/master/pulsar-functions/java-examples/src/main/java/org/apache/pulsar/functions/api/examples/UserConfigFunction.java#L33).

### Paralelism & Resources

* Number of function instances to spin up.
* Amount of computational resources to allocate to the function instances.

### Resource Quotas

* Business Plan account users can create at most **10** function instances which share 3 CPU units, 3 GB of RAM and 10 GB of storage
* Starter Plan account users can create at most **1** function instance with 1 CPU unit, 1 GB of RAM and 10 GB of storage

## Examples

* [Native Java Function](https://github.com/apache/pulsar/blob/master/pulsar-functions/java-examples/src/main/java/org/apache/pulsar/functions/api/examples/ExclamationFunction.java)
* [Pulsar SDK Java Function](https://github.com/apache/pulsar/blob/master/pulsar-functions/java-examples/src/main/java/org/apache/pulsar/functions/api/examples/UserPublishFunction.java)
* [SerDe (Serialization/Deserialization) class example](https://github.com/apache/pulsar/blob/master/pulsar-functions/java-examples/src/main/java/org/apache/pulsar/functions/api/examples/serde/CustomObjectSerde.java)
