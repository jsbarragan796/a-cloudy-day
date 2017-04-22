(function (ng) {
    var mod = ng.module("sucursalesModule", ["ngMessages"]);

    mod.constant("sucursalesContext", "api/sucursales");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/sucursales/';
            $urlRouterProvider.otherwise("/sucursalesList");

            $stateProvider.state('sucursales', {
                url: '/sucursales',
                abstract: true,
                views: {
                    'mainView': {
                        controller: 'sucursalesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sucursales.html'
                    }
                }
            }).state('sucursalesList', {
                url: '/sucursales',
                parent: 'sucursales',
                views: {
                    'sucursalView': {
                        controller: 'sucursalesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sucursales.list.html'
                    },
                    'childsView': {
                        templateUrl: basePath + 'sucursales.instance.main.html'
                    }
                }
            }).state('sucursalesListAdmin', {
                url: '/sucursalesAdmin',
                parent: 'sucursales',
                views: {
                    'sucursalView': {
                        controller: 'sucursalesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sucursales.list.admin.html'
                    }
                }
            }).state('sucursalesListAdminAC', {
                url: '/sucursalesAdmin',
                parent: 'sucursales',
                views: {
                    'sucursalView': {
                        controller: 'sucursalesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sucursales.list.admin.html'
                    }
                }
            }).state('sucursalCreate', {
                url: '/create',
                parent: 'sucursales',
                views: {
                    'sucursalView': {
                        controller: 'sucursalesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sucursales.create.admin.html'
                    }
                }
            }).state('sucursalMas', {
                param: {'sucursalId': null},
                url: '/{sucursalId:int}/mas',
                parent: 'sucursales',
                views: {
                    'sucursalView': {
                        controller: 'sucursalesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sucursales.detail.html'
                    },
                    'childsView': {
                        templateUrl: basePath + 'sucursales.instance.html'
                    }
                }
            }).state('sucursalEdit', {
                param: {'sucursalId': null},
                url: '/{sucursalId:int}/edit',
                parent: 'sucursales',
                views: {
                    'sucursalView': {
                        controller: 'sucursalesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sucursales.create.admin.html'
                    },
                    'childsView': {
                        templateUrl: basePath + 'sucursales.instance.admin.html'
                    }
                }
            }).state('reservasSucursalesList', {
                parent: 'sucursalEdit',
                views: {
                    'sucursalInstanceView': {
                        controller: 'reservasSucursalCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sucursalreservas/' + 'reservasSucursal.list.html'
                    }
                }
            }).state('domiciliosDeSucursalList', {
                param: {'sucursalId': null},
                url: '/{sucursalId}/domicilios',
                parent: 'sucursalEdit',
                views: {
                    'sucursalInstanceView': {
                        controller: 'domiciliosDeSucursalCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'domiciliosDeSucursal/' + 'domiciliosDeSucursal.list.html'
                    }
                }
            }).state('sucursalesListReservacion', {
                parent: 'clientesEdit',
                views: {
                    'clientesInstanceView': {
                        controller: 'sucursalesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sucursales.list.reservacion.html'
                    }
                }
            });
        }]);
})(window.angular);