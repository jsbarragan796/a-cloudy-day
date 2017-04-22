//(function (ng) {
//
//    var mod = ng.module("mainApp", 
//    [
//        
//        // External dependencies
//        'ui.router',
//        'ui.bootstrap',
//        'ui.select',
//        'ngSanitize',
//        "ngMessages"
//        ,
//        "mdpModule",
//        "sucursalesModule",
//        'mesasModule',
//        'platosEspModule',
//
//        "platosModule",
//        "inicioModule",
//        "clientesModule",
//        "domiciliosModule",
//        'datePickerModule'
//    ]);
//    
//    mod.directive('datePicker', [function () {
//        return {
//            scope: {
//                model: '='
//            },
//            restrict: 'E',
//            templateUrl: 'src/utils/datepicker.tpl.html',
//            controller: 'datePickerCtrl'
//        };
//    }]);
//
//    mod.config(['$logProvider', function ($logProvider) 
//        {
//            $logProvider.debugEnabled(true);
//        }]);
//
//    mod.config(['$urlRouterProvider', function ($urlRouterProvider) {
//            $urlRouterProvider.otherwise('inicio');
//        }]);
//    
//
//  
//})(window.angular);