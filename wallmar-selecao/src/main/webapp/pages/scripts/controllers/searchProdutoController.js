

angular.module('wallmarselecao').controller('SearchProdutoController', function($scope, $http, $filter, $location, $window, ProdutoResource ) {

    $scope.search={};
    $scope.currentPage = 0;
    $scope.pageSize= 10;
    $scope.searchResults = [];
    $scope.filteredResults = [];
    $scope.pageRange = [];
    $scope.numberOfPages = function() {
        var result = Math.ceil($scope.filteredResults.length/$scope.pageSize);
        var max = (result == 0) ? 1 : result;
        $scope.pageRange = [];
        for(var ctr=0;ctr<max;ctr++) {
            $scope.pageRange.push(ctr);
        }
        return max;
    };

    $scope.performSearch = function() {
        var errorCallback = function(response) {
        	// verificar se possui permissao a funcionalidade
        	if (response && response.status && response.status === 401) {
        		// remove o token da sessao
        		$window.sessionStorage.token = null;
        		
        		// redireciona para a tela de login, caso nao autorizado
        		$location.path('/Login');
        		
			} else if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Ocorreu alguma falha no sistema.'}, true);
            }
        };
    	
        if ($window.sessionStorage.token) {
        	// atribui o Token no cabecalho Autorization
        	$http.defaults.headers.common.Authorization = "Bearer " + $window.sessionStorage.token;
		}
        
        $scope.searchResults = ProdutoResource.queryAll(function(){
            $scope.filteredResults = $filter('searchFilter')($scope.searchResults, $scope);
            $scope.currentPage = 0;
        }, errorCallback);
    };
    
    $scope.previous = function() {
       if($scope.currentPage > 0) {
           $scope.currentPage--;
       }
    };
    
    $scope.next = function() {
       if($scope.currentPage < ($scope.numberOfPages() - 1) ) {
           $scope.currentPage++;
       }
    };
    
    $scope.setPage = function(n) {
       $scope.currentPage = n;
    };

    $scope.performSearch();
});