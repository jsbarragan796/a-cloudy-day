(function (ng) {
    var mod = ng.module("domiciliosModule");
    mod.controller("agregarPlatosCtrl", ['$scope', '$state', '$stateParams', '$http', 'domiciliosContext', 'platosContext', '$log',
        function ($scope, $state, $stateParams, $http, domiciliosContext, platosContext, $log) {

            idDomicilio = $stateParams.id;
            
            // obtiene el dato del recurso REST
            $log.warn('Get ' + platosContext);
            $http.get(platosContext)
                .then(function (response) {
                // $http.get es una promesa
                // cuando llegue el dato, actualice currentRecord
                $scope.agregarPlatos = response.data;
                }, responseError);            
            $state.go('agregarPlatosList');

            this.agregarRecordPlato = function (plato) {      

                // si el id es distinto de undefined, se puede eliminar
                if (plato !== undefined && plato !== null) {
                    
                        $http.post(domiciliosContext+"/"+idDomicilio+"/agregarPlato", plato)
                        .then(function () {
                            // $http.delete es una promesa
                            // cuando termine bien, cambie de estado
                           $state.go('agregarPlatosList');

                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } 
            };
           

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