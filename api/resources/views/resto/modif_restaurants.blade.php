<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
    <head>

    <body>
        <h1>MODIF {{$restaurant->id}}</h1>
        <form action="/restaurant/{{$restaurant->id}}" method="post">
            @csrf
            @method('put')
            <label>{{$restaurant->name}}</label>
            <input type="text" name="name"><br>
            <label>{{$restaurant->description}}</label>
            <input type="text" name="description"><br>
            <label>{{$restaurant->grade}}</label>
            <input type="text" name="grade"><br>
            <label>{{$restaurant->localization}}</label>
            <input type="text" name="localization"><br>
            <label>{{$restaurant->phone_number}}</label>
            <input type="text" name="phone_number"><br>
            <label>{{$restaurant->website}}</label>
            <input type="text" name="website"><br>
            <label>{{$restaurant->hours}}</label>
            <input type="text" name="hours"><br>
            <button type="submit">SUBMIT</button>
        </form>
        <form action = "/restaurant/{{$restaurant->id}}/menu/{{$menu->id}}" method = "POST">
            @csrf
            @method('delete')
            <button type="submit">SUPPR</button>
        <form>
    </body>
</html>