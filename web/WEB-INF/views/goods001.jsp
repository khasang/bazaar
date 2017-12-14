<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Bazaar - Books - Recipe book</title>
    <link href="<c:url value='/static/css/goods.css'/>" rel="stylesheet"></link>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.8.2/chosen.min.css"/>
</head>

<body>
    <div id="goods001">
        <h1>{{ goods.name }}</h1>
        <img src="http://t2.gstatic.com/images?q=tbn:ANd9GcSBHuR9vXsFerPZKoUGyL2ryuUhjiUfv_0Vr7ouA3Z4v3f5gSgt"/>

        <div class="panel panel-default">
            <div class="panel-heading">
                <strong>Description</strong>
            </div>
            <div class="panel-body" id="description">{{ goods.description }}</div>
        </div>
    </div>

    <script
            src="//cdnjs.cloudflare.com/ajax/libs/vue/2.1.6/vue.js">
    </script>

    <script
            src="//cdnjs.cloudflare.com/ajax/libs/axios/0.17.1/axios.min.js">
    </script>

    <script>
        var vm = new Vue({
            el: "#goods001",
            data() {
                return {
                    goods: {
                        id: 0,
                        name: '',
                        description: '',
                        price: 0,
                        category: {},
                        sellerLogin: '',
                        quantityInStock: 0,
                        quantityReserved: 0
                    }
                }
            },
            mounted() {
                this.get();
            },
            methods: {
                get: function () {
                    axios.get('http://localhost:8080/goods/get/id/1')
                        .then(response => {
                            this.goods = response.data;
                        })
                        .catch(e => {
                            this.errors.push(e);
                        });
                }
            }
        });
    </script>

</body>

</html>
