# Web Quiz Engine API

- To register a new user
```
curl --location 'localhost:8080/api/register' \
--header 'Content-Type: application/json' \
--data-raw '{
  "email": "test@gmail.com",
  "password": "secret"
}'
```

- To get all quizzes (first page)
```
 curl --location 'localhost:8080/api/quizzes/' -u "test@gmail.com:secret" | python -m json.tool
```
```
{
    "content": [
        {
            "id": 1,
            "title": "Geography Quiz",
            "text": "What is the capital of France?",
            "options": [
                "London",
                "Paris",
                "Rome",
                "Berlin"
            ]
        },
        {
            "id": 2,
            "title": "Color Quiz",
            "text": "Which of the following are primary colors?",
            "options": [
                "Red",
                "Green",
                "Blue",
                "Yellow",
                "Cyan",
                "Magenta"
            ]
        },
        {
            "id": 3,
            "title": "Astronomy Quiz",
            "text": "What is the smallest planet in our solar system?",
            "options": [
                "Mercury",
                "Venus",
                "Mars",
                "Jupiter"
            ]
        },
        {
            "id": 4,
            "title": "Literature Quiz",
            "text": "Who wrote the novel \"To Kill a Mockingbird\"?",
            "options": [
                "John Steinbeck",
                "F. Scott Fitzgerald",
                "Harper Lee",
                "Ernest Hemingway"
            ]
        },
        {
            "id": 5,
            "title": "Programming Quiz",
            "text": "Which of the following are common programming languages?",
            "options": [
                "Java",
                "Python",
                "HTML",
                "CSS",
                "JavaScript",
                "C++"
            ]
        },
        {
            "id": 6,
            "title": "Geography Quiz",
            "text": "What is the currency of Japan?",
            "options": [
                "Yen",
                "Dollar",
                "Euro",
                "Pound"
            ]
        },
        {
            "id": 7,
            "title": "Weather Quiz",
            "text": "Which of the following are types of clouds?",
            "options": [
                "Cirrus",
                "Stratus",
                "Cumulus",
                "Nimbus",
                "Fog"
            ]
        },
        {
            "id": 8,
            "title": "Art Quiz",
            "text": "Who painted the famous work \"The Persistence of Memory\"?",
            "options": [
                "Pablo Picasso",
                "Salvador Dali",
                "Vincent van Gogh",
                "Leonardo da Vinci"
            ]
        },
        {
            "id": 9,
            "title": "Multiple Choice Quiz",
            "text": "Which of the following are prime numbers?",
            "options": [
                "2",
                "4",
                "5",
                "6",
                "11"
            ]
        },
        {
            "id": 10,
            "title": "History Quiz",
            "text": "Who was the first president of the United States?",
            "options": [
                "Thomas Jefferson",
                "Abraham Lincoln",
                "George Washington",
                "John Adams"
            ]
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "paged": true,
        "unpaged": false
    },
    "last": false,
    "totalElements": 12,
    "totalPages": 2,
    "size": 10,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 10,
    "number": 0,
    "empty": false
}
```

- To get second page
```
curl --location 'localhost:8080/api/quizzes?page=1' -u "test@gmail.com:secret" | python -m json.tool
```
```
{
    "content": [
        {
            "id": 11,
            "title": "Science Quiz",
            "text": "Which of the following is a type of electromagnetic radiation?",
            "options": [
                "Sound waves",
                "X-rays",
                "Alpha particles",
                "Beta particles"
            ]
        },
        {
            "id": 12,
            "title": "Sports Quiz",
            "text": "Who won the FIFA World Cup in 2018?",
            "options": [
                "Brazil",
                "Argentina",
                "France",
                "Germany"
            ]
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "pageNumber": 1,
        "pageSize": 10,
        "offset": 10,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalElements": 12,
    "totalPages": 2,
    "first": false,
    "numberOfElements": 2,
    "number": 1,
    "size": 10,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "empty": false
}
```

- To get a quiz by id
```
curl --location 'localhost:8080/api/quizzes/1' -u "test@gmail.com:secret"  | python -m json.tool
``` 
```
{
    "id": 1,
    "title": "Geography Quiz",
    "text": "What is the capital of France?",
    "options": [
        "London",
        "Paris",
        "Rome",
        "Berlin"
    ]
}
```

- To solve a quiz 
``` 
curl --location 'localhost:8080/api/quizzes/1/solve' \
--user "test@gmail.com:secret"  \
--header 'Content-Type: application/json' \
--data '{"answer": [1] }' | python -m json.tool
```
```
{
    "success": true,
    "feedback": "Congratulations, you're right!"
}
```

- To add a new quiz
```
curl --location 'localhost:8080/api/quizzes' --user "test@gmail.com:secret"  --header 'Content-Type: application/json' --data '{
    "title": "Test Tile",
    "text": "Test text",
    "options": [ "zero", "one", "tow", "three"],
    "answer": [0, 2]
}' | python -m json.tool
```
```
{
    "id": 16,
    "title": "Test Tile",
    "text": "Test text",
    "options": [
        "zero",
        "one",
        "tow",
        "three"
    ]
}
```

- To delete a quiz
```
curl --location --request DELETE 'localhost:8080/api/quizzes/17' \
--user "test@gmail.com:secret" \
--data '' | python -m json.tool
```
- To get completed quizzes
```
curl --location 'localhost:8080/api/quizzes/completed' \
--user "test@gmail.com:secret" | python -m json.tool
```
```
{
    "content": [
        {
            "completedAt": "2023-03-30T20:22:02.765064",
            "id": 1
        },
        {
            "completedAt": "2023-03-30T20:21:04.169053",
            "id": 1
        },
        {
            "completedAt": "2023-03-30T20:19:52.540163",
            "id": 1
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "pageNumber": 0,
        "pageSize": 10,
        "offset": 0,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalElements": 3,
    "totalPages": 1,
    "first": true,
    "numberOfElements": 3,
    "number": 0,
    "size": 10,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "empty": false
}
```