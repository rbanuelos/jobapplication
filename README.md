# Job Offer & Application app <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40" /><img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original-wordmark.svg" alt="mysql" width="40" height="40" /><img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/docker/docker-original-wordmark.svg" alt="docker" width="40" height="40" /><img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/redis/redis-original-wordmark.svg" alt="redis" width="40" height="40" />

Spring boot ReST API for managing job offers and job applications. A job offer is an object that represent a job opening/opportunity to which users can apply to. New Job offers can be added to the app using the API. Also, a job offer can have 0 to N applications and more applications can be added using the API. 

This implementation is build using Spring boot framework and MySQL as the main data store and Redis as a cache for specific service methods. Entries in cache has a 5 minutes TTL and cache entries might be removed if a new job offer or application that could affect the existing cache entry has been created.

## Requisites
* JDK version: 11 (I'm using Amazon Corretto 11.0.15)
* Gradle version: 7.4.1
* Spring Boot version: 2.7.0
* Docker Compose version: 1.29.2

## How to use

### Startup MySQL & Redis containers
```bash
docker-compose up
```

### Run
```bash
./gradlew bootRun
```

### Tests
```bash
./gradlew test
```

## API
| URL | Method | Query Params | Path Params | Body  | Response  |
| :---: | :--: | :--: | :--: | :--: | :--: |
| /jobs | `GET` | tags={comma separated tags} | None | None | `[    {        "title": "Job Offer 1",        "description": "Job Offer 1 description",        "tags": "java, react, docker"    },    {        "title": "Job Offer 3",        "description": "Job Offer 3 description",        "tags": "spring, java, kubernetes"    }]` |
| /jobs/{id} | `GET` | None | id={job offer id} | None | `{        "title": "Job Offer 1",        "description": "Job Offer 1 description",        "tags": "java, react, docker"    }` |
| /jobs | `POST` | None | None | `{ "title" : "Job Offer 1","description" : "Job Offer 1 description","tags" : "tag1, tag2, tag3"}` | `id` |
| /jobs/{id}/applications | `GET` | None | id={job offer id} | None | `[    {        "fullName": "John Doe",        "address": "John Doe address",        "phone": "John Doe phone"    },    {        "fullName": "Jack E.",        "address": "Jack E. address",        "phone": "Jack E. phone"    }]` |
| /jobs/{id}/apply | `POST` | None | None | `{    "fullName" : "M.J.",    "address" : "M.J. address",    "phone" : "M.J. phone"}` | `boolean` |
