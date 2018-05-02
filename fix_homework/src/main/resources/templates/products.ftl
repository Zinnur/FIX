
<#ftl encoding='UTF-8'>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/css/styles.css" type="text/css"/>
    <title>Title</title>
</head>
<body>
<#if user ??>
    <div class="form-style-2-heading">Добро пожаловать, ${user.name}!</div>
</#if>

<div class="form-style-2">
    <div class="form-style-2-heading">Товары</div>
    <table border=1>
        <thead>
        <tr>
            <th>ID товара</th>
            <th>Название</th>
            <th>Производитель</th>
            <th>Количество</th>
            <th>Стоимость</th>

        </tr>
        </thead>
        <tbody>
        <#list productsFromServer as product>
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.manufacturer.manufacturerName}</td>
            <td>${product.count}</td>
            <td>${product.cost}</td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>
</br></br>

<div class="form-style-2">
    <div class="form-style-2-heading">Добавить товар</div>
    <form method="POST" action="/products" accept-charset="UTF-8">

        <table>
            <tr><td>Название товара</td><td><input type= "text" name="name"  maxlength="50" required id="name"/>
            </td></tr>

            <tr><td>Производитель</td><td><input type= "text" name="manufacturer"  maxlength="50" required id="manufacturer"/>
            </td></tr>

            <tr><td>Количество</td><td><input type= "text" pattern="\d*" name="count" maxlength="4" required id="count"/>
            </td></tr>

            <tr><td>Стоимость, руб</td><td><input type= "text" pattern="\d*" name="cost" maxlength="7" required id="cost"/>
            </td></tr>

        </table>


        </br></br>
        <input type= 'submit' value= 'Добавить'/>


    </form>
</div>

<div class="form-style-2">
    <div class="form-style-2-heading">Редактировать данные о товаре</div>
    <form method="POST" action="/products?upd" accept-charset="UTF-8">

        <table>

            <tr><td>Название товара</td><td><input type= "text" name="name"  maxlength="50"
                                                  required id="name"/>
            </td></tr>

            <tr><td>Стоимость, руб</td><td><input type= "text" pattern="\d*" name="cost" maxlength="7"
                                                  required id="cost"/>
            </td></tr>

        </table>


        </br></br>
        <input type= 'submit' value= 'Редактировать'/>
    </form>
</div>

<div class="form-style-2">
    <div class="form-style-2-heading">Удалить товар</div>
    <form method="POST" action="/products?del" accept-charset="UTF-8">

        <table>
            <tr><td>ID</td><td><input type= "text" pattern="\d*" name="id" maxlength="4"
                                      required id="id"/>
            </td></tr>
        </table>


        </br></br>
        <input type= 'submit' value= 'Удалить'/>
    </form>
</div>

</body>
</html>