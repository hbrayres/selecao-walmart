angular.module('wallmarselecao').factory('LoginResource', 
		function($resource) {
			return $resource('../api/login/',{}, {
				'login' : {
					method : 'POST'
				}
			});
		});