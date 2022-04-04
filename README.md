[![Build Status](https://app.travis-ci.com/Xazeq/job4j_url_shortcut.svg?branch=master)](https://app.travis-ci.com/Xazeq/job4j_url_shortcut)

![](https://img.shields.io/badge/Maven-=_3-red)
![](https://img.shields.io/badge/Java-=_14-orange)
![](https://img.shields.io/badge/Spring-=_5-darkorange)
![](https://img.shields.io/badge/PostgerSQL-=_9-blue)
![](https://img.shields.io/badge/Hibernate-59666C)
![](https://img.shields.io/badge/Checkstyle-lightgrey)

## Описание
Сервис для сокращения ссылок. Позволяет пользователю получать сокращенные ссылки на свой сайт. При использовании сокращенной ссылки сервис будет производить редирект на исходный URL-адрес. Взаимодействие с сервисом происходит через простые http-запросы.

## Технологии
* Java 14
* PostgreSQL
* Hibernate
* Spring Boot 2
* Spring Data JPA
* Spring Security
* JWT

## Использование
Для использования приложения необходимо зарегистрироваться.
`POST /registration`
Регистрация производится по названию сайта, например: openjdk.java.net
![ScreenShot](images/1.JPG)
После регистрации для пользователя генерируются логин и пароль для доступа с систему. Их необходимо сохранить для дальнейшего входа в систему. Флаг "registered" означает, был ли пользователь ранее зарегистрирован в системе.

После входа в систему пользователю присваивается уникальный токен, т.к. приложение использует JWT аутентификацию и авторизацию.
Для получения токена используется запрос `POST /login`
![ScreenShot](images/2.JPG)

Для получения сокращенной ссылки необходимо выполнить запрос (`POST /convert`), передав в нем исходную ссылку, и в Headers добавить токен JWT (Например, Authorization: Bearer e25d31c5-db66-4cf2-85d4-8faa8c544ad6). Результат будет отправлен в теле ответа.
![ScreenShot](images/3.JPG)

Для перенаправления на исходный url адрес используется запрос `GET /redirect/{shortcut}`. Наличие токена не требуется.
![ScreenShot](images/4.JPG)

В сервисе считается количество вызовов каждого адреса. Для получения статистики необходимо выполнить запрос `GET /statistic`.
![ScreenShot](images/5.JPG)
