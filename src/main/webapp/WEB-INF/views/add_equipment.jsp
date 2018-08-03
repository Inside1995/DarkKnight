<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add equipment</title>
</head>
<body>
<form action="/admin/add_equipment/upload?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
    <table style="text-align: left">
        <tr>
            <th>Name</th>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <th>Price</th>
            <td><input type="text" name="price"></td>
        </tr>
        <tr>
            <th>Type</th>
            <td>
                <select name="type">
                    <option value="helm">Helm</option>
                    <option value="main_armor">Main Armor</option>
                    <option value="boots">Boots</option>
                    <option value="jewellery">Jewellery</option>
                    <option value="ring">Ring</option>
                    <option value="weapon">Weapon</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>Photo</th>
            <td><input type="file" name="avatar"></td>
        </tr>
        <tr>
            <th>Defence</th>
            <td><input type="text" name="defence"></td>
        </tr>
        <tr>
            <th>Attack</th>
            <td><input type="text" name="attack"></td>
        </tr>
        <tr>
            <input type="submit" value="Submit">
        </tr>
    </table>
</form>
</body>
</html>
