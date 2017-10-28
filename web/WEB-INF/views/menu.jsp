<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Cat's view</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.8.2/chosen.jquery.min.js" type="text/javascript"></script>
    <script src="<c:url value='/static/js/app.js' />" type="text/javascript"></script>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.8.2/chosen.min.css"/>

</head>

<body>

<h1>Admin menu</h1>
<label for="model_name">Select model:</label>
<div id="model_name">
    <select class="chosen-select" name="select_model_name" style="width:200px;">
        <option value="Cat">Cat</option>
        <option value="News">News</option>
        <option value="Tags">Tags</option>
        <option value="News_cat">NewsCategory</option>
    </select>
</div>
</br>

<table class="table">
    <tr>
        <th>Request's type</th>
        <th>URL</th>
        <th>Value</th>
    </tr>
    <tr>
        <td>Add <span class="current_model">Cat</span>'s</td>
        <td><code><strong>PUT </strong>/<span class="current_name">cat</span>/add</code></td>
        <td>
            <form class="form-inline">
                name: <input type="text" id="putName" value="catName">
                description: <input type="text" id="putDescription" value="catDescription">
                <button type="button" onclick="RestPut($('#putName').val(), $('#putDescription').val())">Try</button>
            </form>
        </td>
    </tr>
    <tr>
        <td>GET <span class="current_model">Cat</span> by ID</td>
        <td><code><strong>GET </strong>/<span class="current_name">cat</span>/get/id/{id}</code></td>
        <td>
            Id: <input id="getById" value=""/>
            <button type="button" onclick="RestGet($('#getById').val())">Try</button>
        </td>
    </tr>
    <tr>
        <td>Get all <span class="current_name">cat</span></td>
        <td><code><strong>GET All </strong>/<span class="current_name">cat</span>/all</code></td>
        <td>
            <button type="button" onclick="RestGetAll()">Try</button>
        </td>
    </tr>
    <tr>
        <td>Delete <span class="current_model">Cat</span> by Id</td>
        <td><code><strong>DELETE </strong>/<span class="current_name">cat</span>/delete/{id}</code></td>
        <td>
            Id: <input id="IdForDelete" value=""/>
            <button type="button" onclick="RestDelete($('#IdForDelete').val())">Try</button>
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
