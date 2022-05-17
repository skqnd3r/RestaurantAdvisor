<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
    <head>

    <body>
        {{-- @foreach ($errors as $error)
            
        @endforeach --}}
        <h1>RESTAURANT</h1>
        <form method="POST" action="/restaurants">
            @csrf
            <label>name</label>
            <input type="text" name="name"><br>
            <label>description</label>
            <input type="text" name="description"><br>
            <label>grade</label>
            <input type="text" name="grade"><br>
            <label>localization</label>
            <input type="text" name="localization"><br>
            <label>phone_number</label>
            <input type="text" name="phone_number"><br>
            <label>website</label>
            <input type="text" name="website"><br>
            <label>hours</label>
            <input type="text" name="hours"><br>
            <button type="submit">SUBMIT</button>
        </form>
    </body>
</html>