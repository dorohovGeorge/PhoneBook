#Задание для компании Dins на вакансию Intern Java Developer 

##Инстркуция по запуску программы

###Запуск программы
``````
mvn spring-boot:run
``````
###Запуск тестов
``````
mvn test
``````

###Список разработанных запросов
Все Rest запросы

![img_13.png](img_13.png)


###Примеры REST запросов




``````
Get Request
http://localhost:8080/users
``````
![img.png](img.png)


``````
Post Request
http://localhost:8080/users
``````
![img_1.png](img_1.png)

``````
Get Request
http://localhost:8080/users?id=1
``````

![img_2.png](img_2.png)

``````
Post Request
http://localhost:8080/users/1
``````
![img_3.png](img_3.png)

``````
Delete Request
http://localhost:8080/users/2
``````

![img_4.png](img_4.png)

``````
Get Request
http://localhost:8080/users?name="T"
``````

![img_5.png](img_5.png)

``````
Get Request
http://localhost:8080/entries/1234
``````

![img_7.png](img_7.png)

``````
Get Request
http://localhost:8080/users/entries/1
``````

![img_8.png](img_8.png)

``````
Post Request
http://localhost:8080/entries/users/1
``````

![img_9.png](img_9.png)

``````
Get Request
http://localhost:8080/users/1/entries/2
``````

![img_10.png](img_10.png)

``````
Delete Request
http://localhost:8080/users/1/entries/2
``````

![img_11.png](img_11.png)

``````
Put Request
http://localhost:8080/users/1/entries/1
``````

![img_12.png](img_12.png)