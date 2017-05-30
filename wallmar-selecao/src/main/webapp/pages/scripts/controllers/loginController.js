
angular.module('wallmarselecao').controller('LoginController', function($scope, $routeParams, $location, $window, flash, LoginResource ) {
	
	$scope.$location = $location;
	$scope.user = $scope.user || {};
	
	$scope.login = function() {
		
		var successCallback = function(data, responseHeaders) {
			var response = {};
			response.header = responseHeaders();
            if (response.header['authorization']) {
            	var token = response.header['authorization'].substring("Bearer ".length);
            	$window.sessionStorage.token = token;
            	$location.path('/');
			} else {
				flash.setMessage({'type': 'error', 'text': 'Falha ao gerar o Token'}, true);
				$location.path('/Login');
			}
        };
        var errorCallback = function(response) {
        	$window.sessionStorage.token = null;
        	if (response && response.status && response.status === 401) {
        		flash.setMessage({'type': 'error', 'text': 401 + ' Falha ao autentica usuário.'}, true);
			} else if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Ocorreu uma falha no sistema.'}, true);
            }
            $location.path('/Login');
        };
        
        LoginResource.login($scope.user, successCallback, errorCallback);
	};
});