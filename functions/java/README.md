## How to deploy a Java function?

The following guide will help you setup and configure your Pandio function via the form on the left.

### Function Name

We generated a random function name but feel free to change it. The name must be unique within each Pulsar namespace and we recommend it be descriptive, eg `check-fraud` or `enrich-stream`. We only allow lower case characters `a-z`, numbers `0-9` and `-`.

### Runtime

Currently, we support Python 3 and Java 8 runtimes.

### Select Function / Package file

Java function must be packaged and uploaded as a JAR file. To get started please refer to:

* [Example how to build a JAR package using maven](https://pulsar.apache.org/docs/en/functions-package/#java) Steps 1-3
* [Java Examples library from Pulsar](https://github.com/apache/pulsar/tree/master/pulsar-functions/java-examples) (You can check it out and build using [maven](https://maven.apache.org/))

### ClassName

FQCN (Fully Qualified Class Name) of a function. Example: `org.apache.pulsar.functions.api.examples.ExclamationFunction`

### Processing Guarantees

A [messaging semantics](https://pulsar.apache.org/docs/en/functions-overview/#processing-guarantees) that can be applied to a function.

### Auto Acknowledgement

A flag to enable or disable auto acknowledgement of consumed messages.

### Search & Select Output Topic

Name of an output topic where the function will produce results.

### Parallelism & Resources

* Number of function instances to spin up.
* Amount of computational resources to allocate to the function instances.

#### NOTE: Resource Quotas

* The Business Plan can have at most **10** function instances that share 3 CPU units, 3 GB of RAM, and 10 GB of storage
* The Starter Plan can have at most **1** function instance with 1 CPU unit, 1 GB of RAM, and 10 GB of storage

### Add Inputs Configuration

A list of Pulsar topics a function will consume from. The function will apply processing logic to every message it receives.

A user can specify a few advanced properties for the Input topics: **Schema type** (eg AVRO, JSON), **Schema Properties**, [**Serialization/Deserialization class name**](https://pulsar.apache.org/docs/en/functions-develop/#serde) and a flag which indicates whether a topic name is a regex pattern

### User Configuration

Optional user-defined configuration properties passed to functions as arbitrary key-value pairs. The key-value pair map is part of the function context. An example that reads user-defined configuration property in [Java](https://github.com/apache/pulsar/blob/master/pulsar-functions/java-examples/src/main/java/org/apache/pulsar/functions/api/examples/UserConfigFunction.java#L33).

## Examples

* [Native Java Function](https://github.com/apache/pulsar/blob/master/pulsar-functions/java-examples/src/main/java/org/apache/pulsar/functions/api/examples/ExclamationFunction.java)
* [Pulsar SDK Java Function](https://github.com/apache/pulsar/blob/master/pulsar-functions/java-examples/src/main/java/org/apache/pulsar/functions/api/examples/UserPublishFunction.java)
* [SerDe (Serialization/Deserialization) class example](https://github.com/apache/pulsar/blob/master/pulsar-functions/java-examples/src/main/java/org/apache/pulsar/functions/api/examples/serde/CustomObjectSerde.java)
