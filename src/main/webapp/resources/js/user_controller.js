/**
 * 控制器是一个AngularJS应用的最有用的部分。这些是执行JavaScript函数/对象大多数用户界面相关的工作。
 * 它们可以被看作是驱动程序模型和视图的变化。
 * 它们是模型(在我们的应用程序中的数据)，和视图(无论用户看到在屏幕上以及与其交互)之间的网关
 */
'use strict';

App.controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
          var self = this;
          self.user={id:null,username:'',address:'',email:''};
          self.users=[];
              
          self.fetchAllUsers = function(){
              UserService.fetchAllUsers()
                  .then(
      					       function(d) {
      						        self.users = d;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Currencies');
            					}
      			       );
          };
           
          self.createUser = function(user){
              UserService.createUser(user)
		              .then(
                      self.fetchAllUsers, 
				              function(errResponse){
					               console.error('Error while creating User.');
				              }	
                  );
          };

         self.updateUser = function(user, id){
              UserService.updateUser(user, id)
		              .then(
				              self.fetchAllUsers, 
				              function(errResponse){
					               console.error('Error while updating User.');
				              }	
                  );
          };

         self.deleteUser = function(id){
              UserService.deleteUser(id)
		              .then(
				              self.fetchAllUsers, 
				              function(errResponse){
					               console.error('Error while deleting User.');
				              }	
                  );
          };

          self.fetchAllUsers();

          self.submit = function() {
              if(self.user.id==null){
                  console.log('Saving New User', self.user);    
                  self.createUser(self.user);
              }else{
                  self.updateUser(self.user, self.user.id);
                  console.log('User updated with id ', self.user.id);
              }
              self.reset();
          };
              
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.users.length; i++){
                  if(self.users[i].id == id) {
                     self.user = angular.copy(self.users[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(id){
              console.log('id to be deleted', id);
              for(var i = 0; i < self.users.length; i++){
                  if(self.users[i].id == id) {
                     self.reset();
                     break;
                  }
              }
              self.deleteUser(id);
          };

          
          self.reset = function(){
              self.user={id:null,username:'',address:'',email:''};
              $scope.myForm.$setPristine(); //reset Form
          };

      }]);
