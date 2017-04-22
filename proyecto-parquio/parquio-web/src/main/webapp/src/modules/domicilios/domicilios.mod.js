(function (ng) {
    var mod = ng.module("domiciliosModule", ["ngMessages"]);
    
    mod.constant("domiciliosContext", "api/domicilios");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/domicilios/';
            $urlRouterProvider.otherwise("/domiciliosList");

            $stateProvider.state('domicilios', {
                url: '/domicilios',
                abstract: true,
                views: {
                    'mainView': {
                        controller: 'domiciliosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'domicilios.html'
                    }
                }
            }).state('domiciliosList', {
                url: '/list/cliente/:idCliente',
                param: {idCliente:null},
                parent: 'clientesEdit',
                views: {
                      
                    'clientesInstanceView': {
                        controller: 'domiciliosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'domicilios.list.html'
                        
                    }
                }

            }).state('domiciliosCreate', {
                param: {idCliente:null},
                url: '/create/cliente/:idCliente',
                parent: 'domicilios',
                views: {
                    'domicilioView': {
                        controller: 'domiciliosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'domicilios.create.html'
                        
                    },
                    'childView': {
                        templateUrl: basePath + 'domicilios.instance.html'
                    }
                }

            }).state('domicilioEdit', {
                param: {id: null},
                url: '/edit/:id',
                parent: 'domicilios',
                views: {
                    'domicilioView': {
                        controller: 'domiciliosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'domicilios.create.html'
                    },
                    'childView': {
                        templateUrl: basePath + 'domicilios.instance.html'
                    }
                }
            }).state('domicilioPlatosList', {
                url: '/platos/list/:idDomicilio',             
                parent: 'domicilioEdit',
                views: {                 
                    'domicilioInstanceView': {
                        controller: 'domicilioPlatosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'domicilioPlatos/' + 'domicilioPlatos.list.html'
                    }
                }
            }).state('agregarPlatosList', {
                url: '/agregarplatos/list/:idDomicilio',             
                parent: 'domicilioEdit',
                views: {                 
                    'domicilioInstanceView': {
                        controller: 'agregarPlatosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'agregarPlatos/' + 'agregarPlatos.list.html'
                    }
                }
            }).state('domicilioPlatosAgregar', {
                url: '/{sucursalId:int}/domicilioplatosagregar/list',  
                param: {sucursalId: null},
                parent: 'domicilioEdit',
                views: {                 
                    'domicilioInstanceView': {
                        controller: 'agregarPlatosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'agregarPlatos/' + 'domicilioPlatosAgregar.list.html'
                    }
                }
            });
        }]);
})(window.angular);