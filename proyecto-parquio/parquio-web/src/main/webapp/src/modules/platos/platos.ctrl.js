/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("platosModule");

    mod.controller("platosCtrl", ['$scope', '$state', '$stateParams', '$http', 'platosContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de platos está vacio
            $scope.records = {};
            // carga los platos
            $http.get(context).then(function (response) {
                $scope.records = response.data;
            }, responseError);

            // el controlador recibió un platoId ??
            // revisa los parámetros (ver el :platoId en la definición de la ruta)
            if ($stateParams.id !== null && $stateParams.id !== undefined) {

                // toma el id del parámetro
                id = $stateParams.id;

                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;
                        }, responseError);

                // el controlador no recibió un id plato
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord =
                        {
                            id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                            nombre: '' /*Tipo String*/,
                            precio: undefined /*Tipo Long.*/,
                            descripcion: '' /*Tipo String*/
//                    exclusivo:'' /*Tipo String*/,
                        };

                $scope.alerts = [];
            }


            this.saveRecord = function (id) {

                currentRecord = $scope.currentRecord;

                // si el id es undefined, es un registro nuevo, entonces lo crea
                if (id === undefined) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('platosListAdmin');
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('platosListAdmin');

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
                                $state.go('platosListActualizar');

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

