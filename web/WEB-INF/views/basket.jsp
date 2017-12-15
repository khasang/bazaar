<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Your Basket</title>
    <link href="<c:url value='/static/css/goods.css' />" rel="stylesheet"></link>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.8.2/chosen.min.css"/>
</head>
<body>

<div id="basket">
    <h1>Basket</h1>
    <div class="panel panel-default">
        <div class="panel-heading">
            <strong>Content</strong>
        </div>
        <div class="panel-body" id="description">{{ basket.goodsid }}</div>
    </div>
</div>

<script
        src="//cdnjs.cloudflare.com/ajax/libs/vue/2.1.6/vue.js">
</script>

<script
        src="//cdnjs.cloudflare.com/ajax/libs/axios/0.17.1/axios.min.js">
</script>

<script>
    var basket = new Vue({
        el: "#basket",
        data() {
            return {
                basket: {
                    id: 0,
                    goodsid: 0,
                    orderid: 0,
                    userid: 0,
                    ordernotissued: 0,
                    orderissues: 0
                }
            }
        },
        mounted() {
            this.get();
        },
        methods: {
            get: function () {
                axios.get('http://localhost:8080/basket/get/id/1')
                    .then(response => {
                        this.basket = response.data;
                    })
                    .catch(e => {
                        this.errors.push(e);
                    });
            }
        }
    })
</script>

</body>
</html>