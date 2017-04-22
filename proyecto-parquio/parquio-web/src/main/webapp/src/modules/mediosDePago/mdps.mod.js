(function (ng) {
    var mod = angular.module('mdpModule', []);
    
    mod.constant("mdpsContext", "/mdps");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/mediosDePago/';
            $urlRouterProvider.otherwise("/mdpsList");

            $stateProvider.state('mdps', {
                url: '/mediosDePago',
                parent: 'clientesEdit',
                views: {
                    'clientesInstanceView': {
                        controller: 'mdpsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'mdps.list.html'
                    }
                }
            }).state('mdpsList', {
                url: '/mediosDePago/:idCliente',
                parent: 'clientesEdit',
                param:{ 'idCliente': null},
                views: {
                    'clientesInstanceView': {
                        controller: 'mdpsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'mdps.list.html'
                    }
                }

            }).state('mdpsCreate', {
                url: '/mediosDePago/create/:idCliente',
                parent: 'clientesEdit',
                param:{ 'idCliente': null},
                views: {
                    'clientesInstanceView': {
                        controller: 'mdpsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'mdps.create.html'
                    }
                }
            }).state('mdpEdit', {
                url: '/mediosDePago/:idmdp/:idCliente/edit',
                param:{ 'idmdp': null,
                        'idCliente': null},
                parent: 'clientesEdit',
                views: {
                    'clientesInstanceView': {
                        controller: 'mdpsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'mdps.create.html'
                    }
                }
            });

        }]);
})(window.angular);