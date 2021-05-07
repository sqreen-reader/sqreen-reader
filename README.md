# ![sQreen Reader Logo](sqreen-reader.png) sQReen Reader [![Build sQReen Reader](https://github.com/sqreen-reader/sqreen-reader/actions/workflows/build.yml/badge.svg)](https://github.com/sqreen-reader/sqreen-reader/actions/workflows/build.yml) 
sQReen Reader is a desktop tool that watches your screen for QR codes and gives you a way to open
the hyperlinks embedded in the QR code.

# Build
sQReen reader requires nodeJS to run. To build sQReen reader run the following command:

```shell
npm install
```

# Tests

To run all unit tests run the following command:

```shell
npm test
```

# package

To build the installable image run the following command.

```shell
npm run pack
```


# Run

to start sQreen Reader run the following command:

```shell
npm run start
```

# Scanning

All Scans are ran as part of the build pipeline

## Sonar

The sonar client can be used to run locally.

## Snyk
to run Snyk scans locally run the following command:

```shell
snyk test
```

## CodeQL
TODO: Run scan locally



