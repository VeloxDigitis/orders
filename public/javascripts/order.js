angular.module('orders', [])
    .controller('order-controller', function($scope, $http) {

        $scope.items = [];

        $scope.addItem = function () {
            $scope.items.push({color: $scope.color, size: $scope.size});
        };

        $scope.removeItem = function (x) {
            $scope.items.splice(x, 1);
        };

        $scope.send = function () {
            $http.post('/orders', {date: new Date(), name: $scope.name, age: parseInt($scope.age)});
            $scope.items = [];
            $scope.color = null;
            $scope.size = null;
        }
    }
);