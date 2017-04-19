/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("platosModule", ["ngMessages"]);
    mod.constant("platosContext", "api/platos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/platos/';
            $urlRouterProvider.otherwise("/platos");

            $stateProvider.state('platos', {
                url: '/platos',
                views: {
                    'mainView': {
                        controller: 'platosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'plato.html'
                    }
                }
            }).state('platosList', {
                url: '/platosLista',
                parent: 'platos',
                views: {
                    'platosView': {
                        controller: 'platosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'platos.list.html'
                    },
                    'childsView': {
                        controller: 'platosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'platos.instance.html'
                    }
                }

            }).state('platosListAdmin', {
                url: '/platosListaAdmin',
                parent: 'platos',
                views: {
                    'platosView': {
                        controller: 'platosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'platos.list.admin.html'
                    }
                }

            }).state('platosCreate', {
                url: '/platos/create',
                parent: 'platos',
                views: {
                    'platosView': {
                        controller: 'platosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'platos.create.html'

                    }
                }

            }).state('platosEdit', {
                url: '/platos/:id',
                parent: 'platos',
                param: {
                    id: null
                },
                views: {
                    'platosView': {
                        controller: 'platosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'platos.create.html'
                    }
                }
            }).state('platosListActualizar', {
                url: '/platosListaAdmin',
                parent: 'platos',
                views: {
                    'platosView': {
                        controller: 'platosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'platos.list.admin.html'
                    }

                }
            });
        }]);
})(window.angular);

