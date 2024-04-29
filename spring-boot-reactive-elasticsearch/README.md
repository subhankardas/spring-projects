## Spring Boot + Elasticsearch
This is a POC project to implement criteria-based grouping of data and auto-suggestion/searching on multi-field using Elasticsearch. This functionality can cater to business requirements such as machines needing to be grouped based on matching criteria i.e. OS type is Windows and has Notepad++ installed. The search/auto-suggest functionality will provide one common search across all machine fields such as data containing matching values in all fields such as name, OS name, installed softwares, etc.

### Getting started
1. To start required containers i.e. Elasticsearch in multi-node configuration.
```bash
docker-compose -f .\elastic-docker-compose.yml up
```

#### CURL Commands
1. To add individual endpoint data
```curl
curl --location 'http://localhost:8080/endpoints/3a6ca0d3-fe13-4f28-8e20-21f2f50ef5b0' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Tony'\''s PC",
    "ipAddress": "12.45.56.12",
    "os": {
        "name": "Ubuntu",
        "version": "2.3.7",
        "type": "linux"
    },
    "installedSoftwares": [
        {
            "name": "Mono",
            "version": "1.2.3",
            "installDate": "2024-04-24T21:22:39"
        }
    ],
    "active": true
}'
```
2. To fetch the endpoint's data
```curl
curl --location 'http://localhost:8080/endpoints/dd6d5d15-6b72-408a-871c-032ea79c8e2f'
```
3. Group endpoints by criteria (supported operators are: *AND*, *OR*, *NOT*)
```curl
curl --location --request GET 'http://localhost:8080/endpoints-group' \
--header 'Content-Type: application/json' \
--data '{
    "criteria": [
        {
            "field": "os_type",
            "value": "windows",
            "operator": "and"
        },
        {
            "field": "software_name",
            "value": "Chrome",
            "operator": "not"
        }
    ]
}'
```
4. Search endpoints on multiple fields (autocomplete search/suggestion feature)
```curl
http://localhost:8080/endpoints-search?q=Chrome
```