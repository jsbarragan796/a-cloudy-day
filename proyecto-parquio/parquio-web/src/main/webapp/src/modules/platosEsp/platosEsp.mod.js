(function (ng) {
    var mod = angular.module('platosEspModule', []);
    
    mod.constant("platosEspContext", "/platosEsp");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/platosEsp/';
            $urlRouterProvider.otherwise("/platosEspList");

            $stateProvider.state('platosEsp', {
                url: '/platosEsp',
                parent: 'sucursalEdit',
                views: {
                    'sucursalInstanceView': {
                        controller: 'platosEspCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'platosEsp.list.html'
                    }
                }
            }).state('platosEspList', {
                url: '/platosEsp',
                parent: 'sucursalMas',
                views: {
                    'sucursalInstanceView': {
                        controller: 'platosEspCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'platosEsp.list.html'
                    }
                }

            }).state('platosEspListAdmin', {
                url: '/platosEspAdmin',
                parent: 'sucursalEdit',
                views: {
                    'sucursalInstanceView': {
                        controller: 'platosEspCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'platosEsp.list.html'
                    }
                }

            }).state('platoEspCreate', {
                url: '/platosEsp/create',
                parent: 'sucursalEdit',
                views: {
                    'sucursalInstanceView': {
                        controller: 'platosEspCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'platosEsp.create.html'
                    }
                }
            }).state('platoEspEdit', {
                url: '/platosEsp/{platoEspId:int}/edit',
                param:{ 'platoEspId': null},
                parent: 'sucursalEdit',
                views: {
                    'sucursalInstanceView': {
                        controller: 'platosEspCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'platosEsp.create.html'
                    }
                }
            });

        }]);
})(window.angular);