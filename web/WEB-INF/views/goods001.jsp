<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Bazaar - Books - Recipe book</title>
    <%--<script src="https://unpkg.com/vue"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>--%>
    <script src="<c:url value='/static/js/vue.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/static/js/axios.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/static/js/goods001.js' />" type="text/javascript"></script>
    <link href="<c:url value='/static/css/goods.css'/>" rel="stylesheet"></link>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.8.2/chosen.min.css"/>
</head>

<body>
    <div id="goods001">
        <h1>{{ data.name }}</h1>
        <img src="http://t2.gstatic.com/images?q=tbn:ANd9GcSBHuR9vXsFerPZKoUGyL2ryuUhjiUfv_0Vr7ouA3Z4v3f5gSgt"/>

        <div class="panel panel-default">
            <div class="panel-heading">
                <strong>Description</strong>
            </div>
            <div class="panel-body" id="description">{{ data.description }}</div>
        </div>
    </div>
</body>

</html>
