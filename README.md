# Web Anime Site

**(RU) Данный pet-project разрабатывается на языке Java с использованием фреймворка Spring Boot и его компонентов.
Документация будет дополняться по мере разработки. Используемые технологии:**

* **_Spring Boot_**
* **_PostgreSQL_**
* **_Spring Security_**
* **_Lombok_**
* **_REST_**
* **_Modelmapper_**

___
**(ENG) This pet project is being developed in Java using the Spring Boot framework and its components. Documentation
will be updated as development progresses. Technologies used:**

* **_Spring Boot_**
* **_PostgreSQL_**
* **_Spring Security_**
* **_Lombok_**
* **_REST_**
* **_Modelmapper_**

## Documentation / Документация

___

### Содержание / Table of Contents:

* ***User Controller API Documentation***
*

___

## User Controller API Documentation

### **Получение списка всех пользователей**

**Endpoint:** GET /users

**Описание:**
Этот эндпоинт возвращает список всех пользователей в системе.

***Запрос:***

```http request
GET /users
```

***Ответ:***

```json
[
  {
    "name": "Bob",
    "password": "1234",
    "email": "BobTest@gmail.com"
  },
  
  ...
  
]
```

### Получение информации о конкретном пользователе по ID

**Endpoint:** GET /users/{id}

**Описание:**
Этот эндпоинт возвращает информацию о конкретном пользователе по заданному идентификатору.

***Запрос:***

```http request
GET /users/3
```

***Ответ:***

```json
{
  "name": "Rob",
  "password": "1234",
  "email": "RobTest@gmail.com"
}
```

### Создание нового пользователя

**Endpoint:** POST /users/new

**Описание:**
Этот эндпоинт создает нового пользователя с использованием предоставленных данных.

***Запрос:***

```http request
POST /users/new
Content-Type: application/json

{
    "name" : "Polly",
    "password": "1234",
    "email": "PolyTest@gmail.com"
}
```

***Ответ:***

```http request
200 OK
```

### Обновление информации о пользователе

**Endpoint:** POST /users/{id}/update

**Описание:**
Этот эндпоинт обновляет информацию о существующем пользователе по заданному идентификатору.

***Запрос:***
```http request
PATCH /users/123/update
Content-Type: application/json

{
    "name": "Polly",
    "password": "1234",
    "email": "PolyTest@gmail.com"
}


```
***Ответ:***
````http request
200 OK
````

### Удаление пользователя

**Endpoint:** DELETE /users/{id}

**Описание:**
Этот эндпоинт удаляет пользователя по заданному идентификатору.

***Запрос:***
```http request
DELETE /users/{id}
```
***Ответ:***
```http request
200 OK
```
## Примечания
* Все запросы и ответы осуществляются в формате JSON.
* Для создания и обновления пользователя используется объект UserDTO.
