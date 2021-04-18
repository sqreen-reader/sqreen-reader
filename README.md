# ![sQreen Reader Logo](sqreen-reader.png) sQReen Reader [![Build sQReen Reader](https://github.com/sqreen-reader/sqreen-reader/actions/workflows/build.yml/badge.svg)](https://github.com/sqreen-reader/sqreen-reader/actions/workflows/build.yml) 
sQreen Reader watches your screen for QR codes and automatically reads them and provides links for you to be able to easily open hyperlinks embedded in QR codes.

# Build
sQReen reader requires java to run. To build sQReen reader run the following command:

```shell
./mvnw clean package
```

# Tests

To run all unit and integration tests run the following command:

```shell
./mvnw clean verify
```

> Note: Integration tests require a Desktop environment. If you want to run in headless mode
> run:

```shell
./mvnw clean verify -Djava.awt.headless=true
```

# Run

to start sQreen Reader run the following command:

```shell
./mvnw spring-boot:run -pl sqreen-reader-ui
```

# Scanning

All Scans are ran as part of the build pipeline

## Sonar
To run Sonar scans locally first set the following env variable.

`SONAR_TOKEN`

Then run the following command:

```shell
./mvnw -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar
```
## Snyk
to run Snyk scans locally run the following command:

```shell
snyk test
```

## CodeQL
TODO: Run scan locally



