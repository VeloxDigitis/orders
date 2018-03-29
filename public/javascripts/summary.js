angular.module('orders', [])
    .controller('summary-controller', function($scope, $http) {

        $scope.search = function () {
            $http.get('/orders').
            then(function(response) {
                $scope.orders = response.data;
            });
        };

        $scope.expand = function(order) {
            order.showDetails = !order.showDetails;
            $http.get('/orders/' + order.id).
            then(function(response) {
                order.items = response.data;
            });
        };

        $scope.search();

    });