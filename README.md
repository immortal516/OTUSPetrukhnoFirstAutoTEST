# OTUS APetrukhno HW: Первый автотест

## Requirements
- Java 21+

## Run tests (Maven Wrapper - default browser(chrome) and url)
```bash
./mvnw test -Dlogin=myLogin -Dpassword=myPassword
```

## Run tests (Maven Wrapper - custom url)
```bash
./mvnw test -DbaseUrl=https://otus.home.kartushin.su -Dlogin=myLogin -Dpassword=myPassword
```

## Run tests (Maven Wrapper - with browser)
```bash
./mvnw test -Dbrowser=CHROME -Dlogin=myLogin -Dpassword=myPassword
./mvnw test -Dbrowser=firefox -Dlogin=myLogin -Dpassword=myPassword
```