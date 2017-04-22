(function (ng) {
    var mod = ng.module("domiciliosModule");

    mod.controller("domiciliosCtrl", ['$scope', '$state', '$stateParams', '$http', 'domiciliosContext', 'sucursalesContext', 'clientesContext',
        function ($scope, $state, $stateParams, $http, context, sucursalesContext, clientesContext) {

            // inicialmente el listado de domicilios está vacio
            $scope.records = {};
            // carga los domicilios si hay un cliente carga los domicilios de solo ese cliente 
            if ($stateParams.idCliente !== null && $stateParams.idCliente !== undefined) {

                // toma el id cliente del parámetro
                idCliente = $stateParams.idCliente;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + "cliente/" + idCliente)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice records
                            $scope.records = response.data;
                        }, responseError);

                // el controlador no recibió un id cliente ni 
            } else {
                $http.get(context).then(function (response) {
                    $scope.records = response.data;
                }, responseError);
            }
            // el controlador recibió un domicilioId ??
            // revisa los parámetros (ver el :domicilioId en la definición de la ruta)
            if ($stateParams.id !== null && $stateParams.id !== undefined && ($stateParams.idCliente === undefined || $stateParams.idCliente === null)) {

                // toma el id del parámetro
                domicilioId = $stateParams.id;

                // obtiene el dato del recurso REST
                $http.get(context + "/" + domicilioId)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;
                            $scope.cliente = $scope.currentRecord.cliente;
                        }, responseError);

                // el controlador no recibió un id domicilio
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord =
                        {
                            idDomicilio: undefined /*Tipo Long. El valor se asigna en el backend*/,
                            sucursal: {
                                id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                                name: '',
                                ubicacion: '',
                                direccion: ''
                            } /*Tipo sucursal*/,
                            cliente: {
                                id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                                username: '',
                                passwords: '',
                                medioDePago: '',
                                puntos: undefined
                            }, /*Tipo cliente*/
                            distanciaAprovada: '' /*Tipo String.*/,
                            lugarEntrega: '' /*Tipo String*/,
                            platosDomicilio: [] /*Tipo arreglo*/,
                            descuento: '' /*Tipo String*/,
                            entregado: '' /*Tipo String*/

                        };

                $scope.alerts = [];
            }

            $http.get(sucursalesContext).then(function (response) {
                $scope.sucursales = response.data;
            });
            
            if ($stateParams.idCliente !== null && $stateParams.idCliente !== undefined)
            {
                $http.get(clientesContext + "/" + $stateParams.idCliente).then(function (response) {
                    $scope.cliente = response.data;
             });}


            this.saveRecord = function (id) {

                cliente = $scope.cliente;
                currentRecord = $scope.currentRecord;

                // si el id es undefined, es un registro nuevo, entonces lo crea
                if (id === undefined) {
                    
                    currentRecord.descuento = 'false';
                    currentRecord.distanciaAprovada = 'true';
                    currentRecord.entregado = 'false';
                    currentRecord.platosDomicilio = null;
                    currentRecord.cliente = cliente;
                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('domiciliosList({idCliente:currentRecord.cliente.id})');
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.idDomicilio, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('domicilioList({idCliente:currentRecord.cliente.id})');

                            }, responseError);
                }
                ;
            };


            this.deleteRecord = function (id) {

                // si el id es distinto de undefined, se puede eliminar
                if (id !== undefined) {

                    // ejecuta DELETE en el recurso REST
                    $http.delete(context + "/" + id)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('domiciliosList');

                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                }
            };

            this.deleteRecordPlato = function (id, idP) {

                // si el id es distinto de undefined, se puede eliminar
                if (id !== undefined && idP !== undefined) {

                    // ejecuta DELETE en el recurso REST
                    $http.delete(context + "/plato/" + id + "/" + idP)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('domiciliosList');

                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
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
        }]);

})(window.angular);