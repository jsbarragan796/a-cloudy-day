(function (ng) {
    var mod = ng.module("mesasModule");

    mod.controller("mesasCtrl", ['$scope', '$state', '$stateParams', '$http', 'sucursalesContext',
        function ($scope, $state, $stateParams, $http, sucursalesContext) {

            // inicialmente el listado de mesas está vacio
            $scope.mesasContext = '/mesas';
            $scope.records = {};
            // carga las mesas
            $http.get(sucursalesContext + "/" + $stateParams.sucursalId + $scope.mesasContext).then(function (response) {
                $scope.records = response.data;
            }, responseError);

            // el controlador recibió un mesaId ??
            // revisa los parámetros (ver el :mesaId en la definición de la ruta)
            if ($stateParams.mesaId !== null && $stateParams.mesaId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.mesaId;
                // obtiene el dato del recurso REST
                $http.get(sucursalesContext + "/" + $stateParams.sucursalId + $scope.mesasContext + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;
                        }, responseError);

                // el controlador no recibió un reviewId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord =
                        {
                            id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                            capacidad: 0,
                            numPiso: 0
                        };

                $scope.alerts = [];
            }

            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;

                // si el id es null, es un registro nuevo, entonces lo crea
                if (id === null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(sucursalesContext + "/" + $stateParams.sucursalId + $scope.mesasContext, currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('mesasList');
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(sucursalesContext + "/" + $stateParams.sucursalId + $scope.mesasContext + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('mesasList');
                            }, responseError);
                }
                ;
            };

            this.deleteRecord = function (id) {

                // si el id es distinto de undefined, se puede eliminar
                if (id !== undefined) {

                    // ejecuta DELETE en el recurso REST
                    $http.delete(sucursalesContext + "/" + $stateParams.sucursalId + $scope.mesasContext + "/" + id)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('mesasList');

                            }, responseError);
                }
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
        }]);

})(window.angular);