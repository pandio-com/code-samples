## How to deploy a Python function?

The following guide will help you setup and configure your Pandio function via the form on the left.

### Function Name

We generated a random function name but feel free to change it. The name must be unique within each Pulsar namespace and we recommend it be descriptive, eg `check-fraud` or `enrich-stream`. We only allow lower case characters `a-z`, numbers `0-9` and `-`.

### Runtime

Currently, we support Python 3 and Java 8 runtimes.

### Function / Package file

You can upload either a [single Python file](simple.py) or package a function with the [Zip file](https://pulsar.apache.org/docs/en/functions-package/#zip-file).

### Class Name

The Class Name of the function. For Python functions with no classes, enter the file name without extension, eg `my-function`.

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

Optional user-defined configuration properties passed to functions as arbitrary key-value pairs. Key-value pair map is part of function context. An example which reads user-defined configuration property in [Python](user-prop.py).

### Paralelism & Resources

* Number of function instances to spin up.
* Amount of computational resources to allocate to the function instances.

### Resource Quotas

* The Business Plan can have at most **10** function instances which share 3 CPU units, 3 GB of RAM and 10 GB of storage
* The Starter Plan can have at most **1** function instance with 1 CPU unit, 1 GB of RAM and 10 GB of storage

## Examples

* [Native Python function](simple.py)
* [Pulsar SDK Python function](sdk.py)
* [Example with SerDe (Serialization/Deserialization)](serde.py)
