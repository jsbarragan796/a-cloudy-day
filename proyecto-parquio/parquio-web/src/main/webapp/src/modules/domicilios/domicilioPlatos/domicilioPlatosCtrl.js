(function (ng) {
    var mod = ng.module("domiciliosModule");
    mod.controller("domicilioPlatosCtrl", ['$scope', '$state', '$stateParams', '$http', 'domiciliosContext', '$log',
        function ($scope, $state, $stateParams, $http, domiciliosContext, $log) {

            // carga los authores del domicilio $stateParams.domicilioId
            if ($stateParams.domicilioId !== null && $stateParams.domicilioId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.id;
                // obtiene el dato del recurso REST
                $log.warn('Get ' + domiciliosContext + "/" + id + "/platos");
                $http.get(domiciliosContext + "/" + id + "/platos")
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.domicilioPlatos = response.data;
                            $scope.selectedPlatos = Array.from($scope.domicilioPlatos);
                        }, responseError);
                // el controlador no recibió un domicilioId
            } else {
                showError(" El domicilio no existe");
                $scope.domicilioPlatos = {};
            }
            

            this.deleteRecordPlato = function (idP) {        
                
                // si el id es distinto de undefined, se puede eliminar
                if (idP !== undefined && idP !== null) {
                    
                    // ejecuta DELETE en el recurso REST
                        $http.delete(domiciliosContext+"/"+id+"/"+idP+"/plato")
                        .then(function () {
                            // $http.delete es una promesa
                            // cuando termine bien, cambie de estado
                           $state.go('domicilioPlatosList');

                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } 
            };

            $state.go('domicilioPlatosList');

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

           
            // -----------------------------------------------------------------
            // Funciones para manejra los mensajes en la aplicación


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