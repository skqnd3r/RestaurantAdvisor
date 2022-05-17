<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
    <head>

    <body>
        @if (Auth::check()) <a href="{{url('/disconnect')}}">salut</a> @endif
        <h1>REGISTER</h1>
        @if ($errors->any()) 
            @foreach ($errors->all() as $error) <p>{{$error}}</p> @endforeach
        @endif
        <form method="POST" action="/register">
            @csrf
            <label>login</label>
            <input type="text" name="login"><br>
            <label>password</label>
            <input type="text" name="password"><br>
            <label>password</label>
            <input type="text" name="password_confirmation"><br>
            <label>email</label>
            <input type="text" name="email"><br>
            <label>name</label>
            <input type="text" name="name"><br>
            <label>firstname</label>
            <input type="text" name="firstname"><br>
            <label>age</label>
            <input type="text" name="age"><br>
            <button type="submit">SUBMIT</button>
        </form>
    </body>
</html>