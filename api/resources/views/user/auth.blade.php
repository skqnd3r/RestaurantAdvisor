<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
    <head>

    <body>
        <h1>AUTH</h1>
        <form method="POST" action="/api/auth">
            @csrf
            <label>login</label>
            <input type="text" name="login"><br>
            <label>password</label>
            <input type="text" name="password"><br>
            <button type="submit">SUBMIT</button>
        </form>
    </body>
</html>