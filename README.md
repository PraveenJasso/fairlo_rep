## Fairlo Demo

**1. Requirements

Java 8
Spring boot dependencies
PostgresSql

**2. Configure PostgreSQL**

First, create a database named `fairlo` with credentials fairlo/fairlo. Then, run `pg_schema.sql` and 'pg_data.sql'.

**3. Run the app**

Type the following command from the root directory of the project to run it -

```bash / cmd
mvn clean instal

**4. To run the application

Start the boot application by this class FairloApplication.java

Alternatively, you can package the application in the form of a JAR file and then run it like so -

```bash
java -jar target/fairlo-0.0.1-SNAPSHOT.jar
```

**5. To Test the API

Read All Artist :

localhost:8080/artist

[
    {
        "id": 0,
        "name": "Praveen",
        "shortName": "Praveen",
        "gender": "Male",
        "area": "Hallonbergsvagen,Sweden,17453"
    },
    {
        "id": 1,
        "name": "Praveen Jas",
        "shortName": "Praveen Jas",
        "gender": "Male",
        "area": "Hallonbergsvagen,Sweden,17453"
    }
]


Read Artist by id :

localhost:8080/artist/0

{
    "id": 0,
    "name": "Praveen",
    "shortName": "Praveen",
    "gender": "Male",
    "area": "Hallonbergsvagen,Sweden,17453"
}


Read All recordings:
===================

localhost:8080/recording

[
    {
        "id": 0,
        "title": "RecordngTitle",
        "artist": {
            "id": 0,
            "name": "Praveen",
            "shortName": "Praveen",
            "gender": "Male",
            "area": "Hallonbergsvagen,Sweden,17453"
        },
        "duration": "40:35",
        "track": {
            "id": 0,
            "title": "TrackTitle",
            "position": "3",
            "duration": "40:50"
        }
    },
    {
        "id": 1,
        "title": "RecordngTitle1",
        "artist": {
            "id": 1,
            "name": "Praveen Jas",
            "shortName": "Praveen Jas",
            "gender": "Male",
            "area": "Hallonbergsvagen,Sweden,17453"
        },
        "duration": "40:35",
        "track": {
            "id": 1,
            "title": "TrackTitle1",
            "position": "3",
            "duration": "40:50"
        }
    }
]
