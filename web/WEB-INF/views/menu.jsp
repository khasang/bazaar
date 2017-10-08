<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cat's view</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    </link>
</head>

<script type="text/javascript">
    var service = '/cat';
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
</script>
<body>
<table class="table">
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>DESCRIPTION</th>
    </tr>
    <tr>
        <td>GET Cat by ID</td>
        <td><code><strong>GET</strong>/cat/get/id/{id}</code></td>
        <td>
            Id: <input id="getCatById" value=""/>
            <button type="button" onclick="RestGet($('#getCatById').val())">Try</button>
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
