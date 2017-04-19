/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("sucursalesModule");
    mod.controller("domiciliosDeSucursalCtrl", ['$scope', '$state', '$stateParams', '$http', 'domiciliosContext', '$log',
        function ($scope, $state, $stateParams, $http, domiciliosContext, $log) {
                 
            if ($stateParams.sucursalId !== null && $stateParams.sucursalId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.sucursalId;
                // obtiene el dato del recurso REST
                $log.warn('Get ' + domiciliosContext + "/" + id + "/sucursal");
                $http.get(domiciliosContext + "/" + id + "/sucursal")
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.domiciliosDeSucursal = response.data;
                   
                        }, responseError);
                // el controlador no recibió un sucursalId 
                 $state.go('domiciliosDeSucursalList');
                 
            };

            
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

