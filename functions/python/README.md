## How to deploy a Python function?

The following guide will help you set up and configure your Pandio function via the form on the left.

### Function Name

We generated a random function name but feel free to change it. The name must be unique within each Pulsar namespace and we recommend it be descriptive, eg `check-fraud` or `enrich-stream`. We only allow lower case characters `a-z`, numbers `0-9` and `-`.

### Runtime

Currently, we support Python 3 and Java 8 runtimes.

### Select Function / Package file

You can upload either a [single Python file](https://github.com/pandio-com/code-samples/blob/develop/functions/python/simple.py) or package a function with the [Zip file](https://pulsar.apache.org/docs/en/functions-package/#zip-file).

### ClassName

The Class Name of the function. For Python functions with no classes, enter the file name without extension, eg `my-function`.

### Processing Guarantees

A [messaging semantics](https://pulsar.apache.org/docs/en/functions-overview/#processing-guarantees) that can be applied to a function.

### Auto Acknowledgement

A flag to enable or disable auto acknowledgment of consumed messages.

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

Optional user-defined configuration properties passed to functions as arbitrary key-value pairs. The key-value pair map is part of the function context. An example that reads user-defined configuration property in [Python](https://github.com/pandio-com/code-samples/blob/develop/functions/python/user-prop.py).

## Examples

* [Native Python function](https://github.com/pandio-com/code-samples/blob/develop/functions/python/simple.py)
* [Pulsar SDK Python function](https://github.com/pandio-com/code-samples/blob/develop/functions/python/sdk.py)
* [Example with SerDe (Serialization/Deserialization)](https://github.com/pandio-com/code-samples/blob/develop/functions/python/serde.py)
