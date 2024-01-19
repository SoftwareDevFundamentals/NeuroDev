# NeuroDev

NeuroDev is a `Spring` API using `MongoDB` as a database, applying various methodologies and techniques.

> **Important Note:** The server of this project is currently running on a local system. We plan to make it available globally by implementing cloud-based solutions or containers in the near future.

## Project Inspiration

As a team, we aimed to address a specific problem in the educational field—raising awareness about mental health and learning disabilities. Our goal was to create an API to centralize information on these topics in a non-controversial manner, empowering individuals with accessible information. We strive to contribute to the creation of a more inclusive environment for individuals with disabilities, often overlooked as significant issues in our country.

# How It's Built

## Gitflow

We established a continuous integration environment to automate specific tasks and ensure the seamless operation of both code and content functionalities. This includes a specific checkstyle for clean code and enhanced readability, rulesets to prevent disruptions to the `main` branch, and a thorough code review process before merging. Additionally, we follow the practice of deleting every issue branch upon completion.

## Workflow

We utilized the Kanban methodology for API development, with Trello serving as our planning board. Trello played a crucial role in efficiently dividing tasks, keeping us organized, and meeting our deadlines.

## Frameworks and Tools

As per job requirements:
- **Spring:** Used with `Java`.
- **JetBrains:** IDE of choice.
- **MongoDB:** Database hosted on `Atlas`.
- **Postman:** Used for testing.

# How to Use It

## GET
To retrieve all data, make a `GET` request to:
>http://localhost:8080/api/v1/neurodiversities

### GET by ID
To retrieve data for a specific neurodiversity by ID:
>http://localhost:8080/api/v1/neurodiversity/{id}

### GET by name and cases
To retrieve data by name (case-insensitive):
>http://localhost:8080/api/v1/neurodiversity?name={name}

### GET by description keyword
To retrieve data using a keyword in the description:
>http://localhost:8080/api/v1/neurodiversities/description?keyword=disorder

### POST
To add data to the database, make a POST request to:
>http://localhost:8080/api/v1/neurodiversity

Include a JSON body in the following format:

>{
"name": "test627",
"description":"This is a test",
"worldWidePercentage":"This is a test",
"basicExplanationLink":"This is a test",
"testLink":"This is a test"
}

The ID part is avoided as MongoDB adds it automatically, but you can include it if desired.

### PUT/PATCH
To update data, make a PUT/PATCH request to:
>http://localhost:8080/api/v1/neurodiversity/{id}


Include a JSON body for the updates, for example:
>{
"basicExplanationLink": "This is 8291-123"
}

### DELETE
To delete all data associated with an ID, make a DELETE request to:
>http://localhost:8080/api/v1/neurodiversity/{id}


