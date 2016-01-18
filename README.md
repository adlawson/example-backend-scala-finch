
# Example Shunter Application Back End (Scala, Finch)

A Scala and [Finch](https://github.com/finagle/finch) based back end for the [Shunter example application](https://github.com/shunterjs/example).


## Requirements

 - [SBT](http://www.scala-sbt.org/) is required to build this application from source.
 - [Java](https://www.java.com/) 8 is required to run this application.


## Running from source

Start the application on port `5000`:

```shell
sbt run
```

Start the application on a different port:

```shell
PORT=1234 sbt run
```

## Running from build

Build the application JAR and startup script:

```shell
sbt compile stage
```

Start the application on port `5000`:

```shell
./target/universal/stage/bin/shunter-example-backend
```

Start the application on a different port:

```shell
PORT=1234 ./target/universal/stage/bin/shunter-example-backend
```


## License

Copyright 2016, licensed under the [Lesser General Public License (LGPL-3.0)](http://www.gnu.org/licenses/lgpl-3.0.txt).
