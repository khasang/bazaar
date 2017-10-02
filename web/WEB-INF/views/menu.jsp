<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cat's view</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>

<script type="text/javascript">
    var service = '/cat';
    var RestPut = function (name, description) {
        var JSONObject = {
            'name': name,
            'description': description
        };

        $.ajax({
            type: 'PUT',
            url: service + '/add',
            contentType: 'application/json;utf-8',
            data: JSON.stringify(JSONObject),
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };

    var RestGet = function (id) {
        $.ajax({
            type: 'GET',
            url: service + '/get/id/' + id,
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };

    var RestGetAll = function () {
        $.ajax({
            type: 'GET',
            url: service + '/all',
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };

    var RestDelete = function (id) {
        $.ajax({
            type: 'DELETE',
            url: service + '/delete?id=' + id,
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    }
</script>

<body>
<table class="table">
    <tr>
        <th>Request's type</th>
        <th>URL</th>
        <th>Value</th>
    </tr>
    <tr>
        <td>Add cat's</td>
        <td><code><strong>PUT </strong>/cat/add</code></td>
        <td>
            <form class="form-inline">
                name: <input type="text" id="putName" value="catName">
                description: <input type="text" id="putDescription" value="catDescription">
                <button type="button" onclick="RestPut($('#putName').val(), $('#putDescription').val())">Try</button>
            </form>
        </td>
    </tr>
    <tr>
        <td>GET Cat by ID</td>
        <td><code><strong>GET </strong>/cat/get/id/{id}</code></td>
        <td>
            Id: <input id="getCatById" value=""/>
            <button type="button" onclick="RestGet($('#getCatById').val())">Try</button>
        </td>
    </tr>
    <tr>
        <td>Get all cats</td>
        <td><code><strong>GET All </strong>/cat/all</code></td>
        <td>
            <button type="button" onclick="RestGetAll()">Try</button>
        </td>
    </tr>
    <tr>
        <td>Delete Cat by Id</td>
        <td><code><strong>DELETE </strong>/cat/delete/{id}</code></td>
        <td>
            Id: <input id="CatIdForDelete" value=""/>
            <button type="button" onclick="RestDelete($('#CatIdForDelete').val())">Try</button>
        </td>
    </tr>
</table>

<div class="panel panel-default">
    <div class="panel-heading">
        <strong>RESPONSE</strong>
    </div>
    <div class="panel-body" id="response"></div>
</div>
</body>
</html>
