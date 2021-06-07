## Install

You can download Presto CLI from [here](https://trino.io/download.html)

## Run
Once download that cli, you can run follwoing command

```
./trino.jar --server {{ http_url }} --access-token {{ token }} --user {{ username }}
```

After successful login your promot will change like below

```
trino>
```

Now you can run your SQL queries. For get list of catalogs you can use below query

```
SHOW CATALOGS;
```

List the available system schemas:
```
SHOW SCHEMAS FROM system;
```
List the tables in one of the schemas:
```
SHOW TABLES FROM system.runtime;
```
Query one of the tables:
```
SELECT * FROM system.runtime.nodes;
```