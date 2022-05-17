<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
    <head>

    <body>
        <h1>RESTAURANT {{$restaurant->id}} MENU {{$menu->id}}</h1>
        <form method="POST" action="/restaurant/{{$restaurant->id}}/menu/{{$restaurant->id}}">
            @csrf
            @method('PUT')
            <label>{{$menu->name}}</label>
            <input type="text" name="name"><br>
            <label>{{$menu->description}}</label>
            <input type="text" name="description"><br>
            <label>{{$menu->price}}</label>
            <input type="text" name="price"><br>
            <button type="submit">SUBMIT</button>
        </form>
        <form action = "/restaurant/{{$restaurant->id}}/menu/{{$menu->id}}" method = "POST">
            @csrf
            @method('delete')
            <button type="submit">SUPPR</button>
        <form>
    </body>
</html>