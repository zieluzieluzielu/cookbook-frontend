# CookBook - Frontend application
![CookBook logo](https://i.imgur.com/Cd5H84o.png)




## PROJECT DESCRIPTION

CookBook application was created to help in gathering recipes in one place (app), with matching specific wine (type and product with price) to the dish (based on products) and is counting nutrition values (kcal, carbohydrates, protein and fat) of each ingredient.

Project is connected to two external API's: Spoonacular and Edamam.

**MAIN FEATURES:**

- gathering the recipes by products, recipe category, description, preparation time etc.
- filtering the recipes by i.a. product, recipe category, preparation time
- wine selector api -> founding the best fitting wine to recipe based on main ingredients
- nutrition values api -> summing all nutrition values (kcal, carbo, protein, fat) by taking quantity of each ingredient of recipe.
- adding recipes, products and ingredients

## HOW TO RUN

Front-end address: http://localhost:8081/
 
To have the full functionality - please import the dump of the database (MYSQL) - stored internally, to be downloaded here: https://github.com/zieluzieluzielu/cookbook-backend/blob/master/src/main/resources/db%20dumps/Dump20200622.sql

To run this project in your IDE, you need to have Lombok plugin and enabled annotation processing.
This project has a front-end instance:
Front-end address: http://localhost:8081/

database details (mysql)
url: jdbc:mysql://localhost:3306/cookbook?serverTimezone=Europe/Warsaw&useSSL=false&allowPublicKeyRetrieval=true

datbase username / password: mzielinski

## BACK-END

This project has a back-end instance.

https://github.com/zieluzieluzielu/cookbook-backend
