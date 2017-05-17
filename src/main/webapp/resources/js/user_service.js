/**
 * 创建AngularJS服务和服务器进行通信
 * 在我们的应用中，将与Spring REST API，例如基于后端服务器的方式进行通信。
 * 在基于AngularJS应用中，与服务器进行通信的首选方式是使用AngularJS内置的 $http 服务。
 * AngularJS$ http服务允许我们使用XHR[浏览器的XMLHttpRequest对象] API与服务器端点通信。
 */

'use strict';

App.factory('UserService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllUsers: function() {
					return $http.get('http://127.0.0.1/HelloSpringMVC/user/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching users');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createUser: function(user){
					return $http.post('http://127.0.0.1/HelloSpringMVC/user/', user)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating user');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateUser: function(user, id){
					return $http.put('http://127.0.0.1/HelloSpringMVC/user/'+id, user)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating user');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteUser: function(id){
					return $http.delete('http://127.0.0.1/HelloSpringMVC/user/'+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting user');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

}]);
