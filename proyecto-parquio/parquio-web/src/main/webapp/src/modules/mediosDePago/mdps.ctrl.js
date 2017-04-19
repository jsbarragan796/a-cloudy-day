(function (ng) {
    var mod = ng.module("mdpModule");

    mod.controller("mdpsCtrl", ['$scope', '$state', '$stateParams', '$http', 'clientesContext',
        function ($scope, $state, $stateParams, $http, clientesContext) {

            // inicialmente el listado de mdps está vacio
            $scope.mdpsContext = '/mdps';
            $scope.records = {};
            $scope.idCliente = $stateParams.idCliente;
            // carga los mdps
            if($stateParams.idCliente !== null && $stateParams.idCliente !== undefined){
            $http.get(clientesContext + "/" + $stateParams.idCliente + $scope.mdpsContext).then(function (response) {
                $scope.records = response.data;
            }, responseError);
            }
            // el controlador recibió un mdpID??
            // revisa los parámetros (ver el :mdpID en la definición de la ruta)
            if ($stateParams.idmdp !== null && $stateParams.idmdp !== undefined) {

                // toma el id del parámetro
                id = $stateParams.idmdp;
                // obtiene el dato del recurso REST
                $http.get(clientesContext + "/" + $stateParams.idCliente + $scope.mdpsContext + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord2 = response.data;
                        }, responseError);

                // el controlador no recibió un reviewId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord2 = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    tipo: "",
                   num: 0
                };

                $scope.alerts = [];
            }

            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord2;

                // si el id es null, es un registro nuevo, entonces lo crea
                if (id === null||id === undefined) {

                    // ejecuta POST en el recurso REST
                    return $http.post(clientesContext + "/" + $stateParams.idCliente + $scope.mdpsContext, currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('mdpsList({idCliente:dCliente)');
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(clientesContext + "/" + $stateParams.idCliente + $scope.mdpsContext + "/" + currentRecord.idmdp, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('mdpsList({idCliente:dCliente}');
                            }, responseError);
                }
                ;
            };

            this.deleteRecord = function (id) {

                // si el id es distinto de undefined, se puede eliminar
                if (id !== undefined) {

                    // ejecuta DELETE en el recurso REST
                    $http.delete(clientesContext + "/"+$stateParams.id +$scope.mdpsContext+"/" + id)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('mdpsList({idCliente:idCliente})');

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