employeeApp.service('employeeService',  ['$http',

function employeeService($http){
	var self = this;
	
	self.getAllEmployee = function(callback) {
		var httpPromise = $http({method = "get", url = '/employee/all'});
		
		httpPromise.then(function(response){
			if(response.status >= 200 && response.status <400) {
				if(callback){
					if(response.data){
						callback(response.data);
						return;
					}
					callback();
				}
			}
		})
	}
}

])