<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
    <head>

    <body>
        <h1>RESTAURANT {{$restaurant->id}} MENU</h1>
        <form method="POST" action="/restaurant/{{$restaurant->id}}/menu">
            @csrf
            <label>{{$menu->id}}</label>
            <input type="text" name="name"><br>
            <label>{{$menu->description}}</label>
            <input type="text" name="description"><br>
            <label></label>
            <input type="text" name="price"><br>
            <button type="submit">SUBMIT</button>
        </form>
    </body>
</html>