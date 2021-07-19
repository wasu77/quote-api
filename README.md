# quote-api
Small API giving access to famous person quotes and manipulating them

1.How to run app
   
From command line: 

mvn spring-boot:run

2. Endpoints description

### Get all quotes from database
GET 
    api-url/quotes
    Content-Type: application/json

### Get quote with given quoteId
GET 
    api-url/quotes/{{quoteId}}
    Content-Type: application/json

### Add new quote
POST 
    api-url/quotes/
    Content-Type: application/json

    Body: 

    {
    "text": "Wiem, że nic nie wiem",
    "firstName": "Mądry",
    "lastName": "Człowiek"
    }

### Modify existing quote
PATCH 
    api-url/quotes/
    Content-Type: application/json

    {
    "text": "Znowu w życiu mi nie wyszło",
    "id": 1
    }

### Remove quote
DELETE 
    {{api-url}}/quotes/{{quoteId}}
    Content-Type: application/json


