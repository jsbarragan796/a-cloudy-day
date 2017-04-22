/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("clientesModule");

    mod.controller("reservasCtrl", ['$scope', '$state', '$stateParams', '$http', 'clientesContext', 'sucursalesContext', function ($scope, $state, $stateParams, $http, clientesContext, sucursalesContext) {

            // inicialmente el listado de reservas está vacio
            $scope.records = {};

            $scope.sucursal = {};

            $scope.mesas = {};


            $http.get(clientesContext + "/" + id + "/reservas").then(function (response) {
                $scope.records = response.data;
            }, responseError);

            // el controlador recibió un reservaId ??
            // revisa los parámetros (ver el :reservasId en la definición de la ruta)
            if ($stateParams.id !== null && $stateParams.id !== undefined) {
                // se tiene un id reserva 
                if ($stateParams.idReserva !== null && $stateParams.idReserva !== undefined) {

                    id = $stateParams.id;
                    sucursalId = $stateParams.sucursalId;
                    // obtiene el dato del recurso REST
                    $http.get(clientesContext + "/" + id + "/reservas/" + $stateParams.idReserva)
                            .then(function (response) {
                                // $http.get es una promesa
                                // cuando llegue el dato, actualice currentRecord
                                $scope.currentRecord = response.data;
                            }, responseError);

                    $http.get(sucursalesContext + "/" + sucursalId).then(function (response) {
                        $scope.sucursal = response.data;
                    });

                    $http.get(sucursalesContext + "/" + sucursalId + '/mesas').then(function (response) {
                        $scope.mesas = response.data;
                    });


                    // trae todas las reservas del cliente 
                } else if ($stateParams.sucursalId !== null && $stateParams.sucursalId !== undefined) {

                    $http.get(sucursalesContext + "/" + $stateParams.sucursalId).then(function (response) {
                        $scope.sucursal = response.data;
                    });

                    $http.get(sucursalesContext + "/" + $stateParams.sucursalId + '/mesas').then(function (response) {
                        $scope.mesas = response.data;
                    });
                    $scope.currentRecord =
                            {
                            };
                } else {
                    id = $stateParams.id;
                    // obtiene el dato del recurso REST
                    $http.get(clientesContext + "/" + id + "/reservas")
                            .then(function (response) {
                                // $http.get es una promesa
                                // cuando llegue el dato, actualice currentRecord
                                $scope.currentRecord = response.data;
                            }, responseError);

                }
                ;
                // el controlador no recibió un id reserva
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord =
                        {
                        };

                $scope.alerts = [];

            }

            $scope.currentRecord = {
                name: '',
                id: undefined,
                numPiso: undefined,
                mesa: {id: undefined},
                numPersonas: undefined
            };

            this.saveRecord = function (idReserva) {

                currentRecord = $scope.currentRecord;
                idCliente = $stateParams.id;
                // si el id es undefined, es un registro nuevo, entonces lo crea
                if (idReserva === undefined) {

                    // ejecuta POST en el recurso REST
                    return $http.post(clientesContext + "/" + idCliente + "/reservas", currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado

                                $state.go('reservasClienteList');
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    currentRecord2 = {
                        name: currentRecord.name,
                        id: currentRecord.id,
                        numPiso: currentRecord.numPiso,
                        mesa: {id: currentRecord.mesa.id},
                        numPersonas: currentRecord.numPersonas,
                        fecha: currentRecord.fecha
                    };

                    // ejecuta PUT en el recurso REST
                    return $http.put(clientesContext + "/" + idCliente + "/reservas/" + currentRecord.id, currentRecord2)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando ter mine bien, cambie de estado
                                $state.go('reservasClienteList');

                            }, responseError);
                }
                ;
            };


            this.deleteRecord = function (id) {

                idCliente = $stateParams.id;
                // ejecuta DELETE en el recurso REST
                $http.delete(clientesContext + "/" + idCliente + "/reservas/" + id)
                        .then(function () {
                            // $http.delete es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('reservasClienteList');
                        }, responseError);
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
        }]);

})(window.angular);