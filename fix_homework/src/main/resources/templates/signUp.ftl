<#ftl encoding='UTF-8'>
<html>
<head>
    <link href="/css/styles.css" rel="stylesheet">
    <link href="/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please register!
    </div>
    <form method="post" action="/signUp">
        <label for="login">Name
            <input class="input-field" type="text" id="name" name="name">
        </label>
        <label for="login">Login
            <input class="input-field" type="text" id="login" name="login">
        </label>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <label for="first-name">Date of Birth
            <input class="input-field"  id="birth-date" name="birthDate">
        </label>
        <label for="remember-me">
            <input type="checkbox" id="remember-me" name="remember-me">Remember me</label>
        <input type="submit" value="Добавить">
    </form>
</div>
</body>
</html>