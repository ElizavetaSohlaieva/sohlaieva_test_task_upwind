# Test assignment: UI and API tests

UI tests for [SauceDemo](https://www.saucedemo.com) and API tests for
[Restful Booker](https://restful-booker.herokuapp.com).

## Versions

- Java 25
- Maven, Surefire plugin 3.5.4
- JUnit 5.14.4
- Selenide 7.19.1 — UI tests, run in Chrome
- REST Assured 6.3.0 — API tests
- Jackson 2.21.3 — JSON mapping

## How to run locally

You need JDK 25, Maven and Google Chrome. Chrome driver is downloaded automatically.

Run all tests:

```bash
mvn test
```
Run only API or only UI tests:

```bash
mvn test -Dtest=ApiBookingTest
mvn test -Dtest='Ui*Test'
```
Tests run in parallel in two threads, so you may see two browser windows at the same time.
Results are saved to `target/surefire-reports`.

## How to run in Docker

You only need Docker. Java 25, Maven and Google Chrome are installed inside the image.

Build the image and run all tests:

```bash
docker build -t qa-automation-tests .
docker run --rm qa-automation-tests
```

Run only API or only UI tests:

```bash
docker run --rm qa-automation-tests test -Dtest=ApiBookingTest
docker run --rm qa-automation-tests test -Dtest='Ui*Test'
```

To keep the test reports after the container is removed, mount the `target` folder:

```bash
docker run --rm -v "$PWD/target:/tests/target" qa-automation-tests
```

The first build takes a few minutes and the image is about 2 GB. Inside the container
UI tests run in Chrome without a window (headless mode).

## Note about the Booking API

If a required field is missing in `POST /booking` (`firstname`, `lastname`, `totalprice`,
`depositpaid` or `bookingdates`), the API returns **500 Internal Server Error** instead of
**400 Bad Request**. A missing field is a client error, so 400 would be the correct answer;
500 means the server does not validate the request and fails on it.

The negative test checks the actual status (500), so it passes now. If the API is fixed,
the expected status in the test should be changed to 400.