/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("clientesModule", ["ngMessages"]);
    mod.constant("clientesContext", "api/clientes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/clientes/';
            $urlRouterProvider.otherwise("/clientes");
            $stateProvider.state('clientes', {
                url: '/clientes',
                views: {
                    'mainView': {
                        controller: 'clientesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.html'
                    }
                }
            }).state('clientesLogin', {
                url: '/list',
                    parent:'clientes',
                views: {
                    'clientesView': {
                        controller: 'clientesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.login.html'
                    }
                }
            }).state('clientesList', {
                url: '/list',
                    parent:'clientes',
                views: {
                    'clientesView': {
                        controller: 'clientesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.list.html'
                    }
                }
            }).state('clientesCreate', {
                url: '/clientes/create',
                parent:'clientes',
                views: {
                    'clientesView': {
                        controller: 'clientesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.create.html'
                    }
                }
            }).state('clientesEdit', {
                url: '/clientes/:id',
                parent:'clientes',
                param: {
                    id: null
                },
                views: {
                    'clientesView': {
                        controller: 'clientesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.create.html'
                    },
                    'childsView': {
                        controller: 'clientesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.instance.html'
                    }
                }
            }).state('clientesListActualizar', {
                url: '/clientes',
                views: {
                    'clientesView': {
                        controller: 'clientesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.list.html'
                    }
                }
            }).state('reservasClienteList', {
                url: '/reservas',             
                parent: 'clientesEdit',
                views: {
                    'clientesInstanceView': {
                        controller: 'reservasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath +'clientesreservas/' +'reservas.list.html'
                    }
                }
            }).state('createReservaCliente', {
                param: {
                    sucursalId: null,
                    id:null   
                }, 
                url: '/cliente/:id/reservacion/:sucursalId',
                parent: 'clientesEdit',
                          
                views: {
                    'clientesInstanceView': {
                        controller: 'reservasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath  +'clientesreservas/' +'reservas.create.html'
                    }
                }
            }).state('editReservaCliente', {
                parent: 'clientesEdit',
                param: {
                    idReserva: null,
                    sucursalId: null,
                    id:null   
                },
                url: '/reserva/:idReserva/:sucursalId/edit',
                
                views: {
                    'clientesInstanceView': {
                        controller: 'reservasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath +'clientesreservas/'+'reservas.create.html'
                    }
                }
            });
        }]);
})(window.angular);