(function (ng) {
    var mod = ng.module("sucursalesModule");
    mod.controller("reservasSucursalCtrl", ['$scope', '$state', '$stateParams', '$http', 'sucursalesContext', '$log',
        function ($scope, $state, $stateParams, $http, sucursalesContext, $log) {



            $scope.disabled = undefined;
            $scope.searchEnabled = undefined;


            this.enable = function () {
                $scope.disabled = false;
            };

            this.disable = function () {
                $scope.disabled = true;
            };

            this.enableSearch = function () {
                $scope.searchEnabled = true;
            };

            this.disableSearch = function () {
                $scope.searchEnabled = false;
            };

            this.saveRecord = function (rescordSearch) {



                if ($stateParams.sucursalId !== null && $stateParams.sucursalId !== undefined && rescordSearch !== undefined) {
                    // obtiene el dato del recurso REST
                    $log.warn("api/clientes/1/reservas" + "/" + $stateParams.sucursalId + "-" + rescordSearch.getTime() + rescordSearch + "/sucursal");
                    $http.get("api/clientes/1/reservas" + "/" + $stateParams.sucursalId + "-" + rescordSearch.getTime() + "/sucursal ")
                            .then(function (response) {

                                $scope.reservaSucursal = response.data;
                                $scope.selectedReservas = $scope.reservaSucursal;
                            }, responseError);

                    $state.go('reservasSucursalesList');
                } else {
                    $scope.selectedReservas = {};
                }
            };

            // -----------------------------------------------------------------
            // Funciones para manejar los mensajes en la aplicación
            //Alertas
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };
            // Función showMessage: Recibe el mensaje en String y su tipo con el fin de almacenarlo en el array $scope.alerts.
            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            this.showError = function (msg) {
                showMessage(msg, "danger");
            };
            this.showSuccess = function (msg) {
                showMessage(msg, "success");
            };
            var self = this;
            function responseError(response) {

                self.showError(response.data);
            }
        }
    ]);
})(window.angular);