Pour lancer les migrations vous devrez effectuer les commandes suivantes :
```
$ composer update
$ composer install
```
Creér la base de données restaurant_advisor :
```
$ CREATE DATABASE restaurant_advisor;
```
Ensuite changer le fichier `.env.example` en `.env` .
```
$ php artisan key:generate
$ php artisan migration:refresh --seed
$ php artisan storage:link
$ php artisan serve
```
Puis lancez l'application.
Vous aurez dans l'application un utilisateur mis à votre disposition :
```
login: Admin
password: password
```
