var app = angular.module('employeeApp', []);
app.controller('employeeController', ['$scope', '$http',

function employeeController($scope, $http) {
	
	$scope.edit = false ;
	$scope.add = true ;
	
	$scope.employeeList = {
		employees: [] 
	};
	$scope.employee = {
        id: "",
        name: "",
        email: "",
        address: "",
        telephone: ""
    };

    $scope.getAllEmployee = function() {
		
		$http({
            method: 'get',
            url: '/spring-boot/employee/all'
        }).then(
            function(res) { // success
                $scope.employeeList = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
	};
	
    $scope.editEmployee = function(employee) {
	    //$scope.employee = employee;
        $scope.employee = {
                   id: employee.id,
                   name: employee.name,
                   email: employee.email,
                   address: employee.address,
                   telephone: employee.telephone
                };
       $scope.edit = true;
       $scope.add = false;
    };

    $scope.addEmployee = function(employee){
	     $scope.edit = false;
         $scope.add = true;
         //Not clearing the submit form, 
         //if user wants to copy from exsiting employee and just keep editing.Not good approach.
         //but if user already add everything but somehow it didnt get add , for existing id
         $scope.employee = {
                   id: 'new id',
                   name: employee.name,
                   email: employee.email,
                   address: employee.address,
                   telephone: employee.telephone
                };
         //clearing the submit form,if present anything, say if edit clicked before 
         //_clearData();
         //similar to editEmployee assigning employee to $scope.employee 
         //$scope.employee = employee; 
    };

    
    $scope.submitEmployee = function(employee) {
	    
        var url = '/spring-boot/employee/';
		if($scope.edit && !$scope.add){
			url = url+ 'update';
		}
		if(!$scope.edit && $scope.add){
			url = url+ 'add';
		}
        $scope.employee = employee; 
        $http({
            method: 'post',
            url: url,
            data: angular.toJson($scope.employee),
            headers: {'Content-Type': 'application/json'}
         }).then(_success, _error);
     };

    $scope.deleteEmployee = function(employee) {
	     
        $http({
            method: 'post',
            url: '/spring-boot/employee/delete',
            data: angular.toJson(employee),
            headers: {'Content-Type': 'application/json'}
         }).then(_success, _error);
	}

    function _success(res) {
	     var responseStatus = res.data; 
	     //alert("Success: " + res.header +": "+ res.config +": "+ res.status + ": " + res.data);
         alert(responseStatus.status + ", " + responseStatus.message);
         $scope.edit = false ;
	     $scope.add = true ;
         if(responseStatus.status == 'Success'){
	         _clearData();
         }
         else if(responseStatus.status == 'Can not add...'){
	         $scope.employee.id = 'Enter new Id';
         }
     }
         
    
    function _error(res) {
        //var header = res.header;
        //var config = res.config;
        alert("Error: " + res.status + ":" + res.data);
        $scope.edit = false ;
	    $scope.add = true ;
    }
	function _clearData() {
        $scope.employee = {
        id: "",
        name: "",
        email: "",
        address: "",
        telephone: ""
        };
    };
 
    
}

]);