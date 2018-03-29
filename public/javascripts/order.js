var app = angular.module('orders', []);

app
    .controller('order-controller', function($scope, $http) {

        $scope.items = [];

        $scope.addItem = function () {
            $scope.items.push({color: $scope.color, size: $scope.size});
        };

        $scope.removeItem = function (x) {
            $scope.items.splice(x, 1);
        };

        $scope.send = function () {
            $http.post('/orders', {name: $scope.name, age: parseInt($scope.age), items: $scope.items});
            $scope.items = [];
            $scope.color = null;
            $scope.size = null;
        }
    }
);