angular.module('sampleApp', []).controller('Sample', function($scope, $http) {
	$http.get('http://localhost:8080/samples/all').success(function(data) {
		$scope.samples = angular.fromJson(data);
		$scope.predicate = '';
	});
});