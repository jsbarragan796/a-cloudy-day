(function () {
    var mod = angular.module('inicioModule', []);
    
    mod.constant("inicioContext", "api/inicio");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/inicio/';
            $urlRouterProvider.otherwise("/inicio");
            
  
                $stateProvider.state('inicioAb', {
                url: '/inicioAb', 
                abstract: true,
                views: {
                    'mainView': {
                        controller: 'inicioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'inicio.html'
                    }
                }
            
        }).state('inicio', {
                url: '/inicio',
                views: {
                    'foto': {
                        controller: 'inicioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'foto.html'
                    },
                    'mainView': {
                        controller: 'inicioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'inicio.html'
                    }
                }
            });

        }]);
})(window.angular);