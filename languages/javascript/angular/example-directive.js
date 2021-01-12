angular.module("app")
	.directive('test', function() {
		return {
			scope: {},
			controller: function($scope, $element) {
				console.log("Controller");
			},
			template:
				'<div>' +
				'<h1>Test Header</h1>' +
				'</div>',
			replace: true
		}
	});
