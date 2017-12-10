(function () {
    var goods001 = new Vue({
        el: '#goods001',
        data: {
            id: 0,
            name: '',
            description: '',
            price: 0,
            category: {},
            sellerLogin: '',
            quantityInStock: 0,
            quantityReserved: 0,
        },
        mounted() {
            this.get();
        },
        methods: {
            get: function () {
                axios.get('http://localhost:8080/goods/get/id/1')
                    .then(response => {
                        this.data = response.data;
                    })
                    .catch(e => {
                        this.errors.push(e);
                    });
            }
        }
    })
})();