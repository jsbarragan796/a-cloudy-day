(function (ng) {
    var mod = angular.module('mesasModule', []);
    
    mod.constant("mesasContext", "/mesas");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/mesas/';
            $urlRouterProvider.otherwise("/mesasList");

            $stateProvider.state('mesas', {
                url: '/mesas',
                parent: 'sucursalEdit',
                views: {
                    'sucursalInstanceView': {
                        controller: 'mesasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'mesas.list.html'
                    }
                }
            }).state('mesasList', {
                url: '/mesas',
                parent: 'sucursalEdit',
                views: {
                    'sucursalInstanceView': {
                        controller: 'mesasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'mesas.list.html'
                    }
                }

            }).state('mesaCreate', {
                url: '/mesas/create',
                parent: 'sucursalEdit',
                views: {
                    'sucursalInstanceView': {
                        controller: 'mesasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'mesas.create.html'
                    }
                }
            }).state('mesaEdit', {
                url: '/mesas/{mesaId:int}/edit',
                param:{ 'mesaId': null},
                parent: 'sucursalEdit',
                views: {
                    'sucursalInstanceView': {
                        controller: 'mesasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'mesas.create.html'
                    }
                }
            });

        }]);
})(window.angular);