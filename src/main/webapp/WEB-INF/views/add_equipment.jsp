<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add equipment</title>
</head>
<body>
<form action="" method="post" enctype="multipart/form-data">
    <table style="text-align: left">
        <tr>
            <th>Наименование</th>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <th>Цена</th>
            <td><input type="text" name="price"></td>
        </tr>
        <tr>
            <th>Тип</th>
            <td>
                <select name="type">
                    <option value="helm">Шлем</option>
                    <option value="main_armor">Основные доспехи</option>
                    <option value="boots">Ботинки</option>
                    <option value="jewellery">Подвеска</option>
                    <option value="ring">Кольцо</option>
                    <option value="weapon">Оружие</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>Аватар</th>
            <td><input type="file" name="avatar"></td>
        </tr>
        <tr>
            <th>Защита</th>
            <td><input type="text" name="defence"></td>
        </tr>
        <tr>
            <th>Атака</th>
            <td><input type="text" name="attack"></td>
        </tr>
        <tr>
            <input type="submit" value="Подтвердить">
        </tr>
    </table>
</form>
</body>
</html>
