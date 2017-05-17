package com.lph.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lph.app.Service.UsersService;
import com.lph.app.entity.Users;

@RestController
/**
 * Spring MVC4整合AngularJS。
 * 我们将创建一个使用后端和AngularJS作为前端的纯JSP封装Spring REST API一个CRUD应用程序， 
 * 使用 $http 服务与服务器异步通信。我们还将进行使用UI AngularJS表单验证各种验证。
 * 
 * @author Administrator
 *
 */
public class RealRestHelloController {

    @Autowired
    UsersService UsersService;  //Service which will do all data retrieval/manipulation work
 

    
    
    //-------------------Retrieve All Userss--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<Users>> listAllUserss() {
        List<Users> Userss = UsersService.findAllUserss();
        if(Userss.isEmpty()){
            return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Users>>(Userss, HttpStatus.OK);
    }
 
 
    
    //-------------------Retrieve Single Users--------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> getUsers(@PathVariable("id") long id) {
        System.out.println("Fetching Users with id " + id);
        Users Users = UsersService.findById(id);
        if (Users == null) {
            System.out.println("Users with id " + id + " not found");
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Users>(Users, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a Users--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUsers(@RequestBody Users Users,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Users " + Users.getUsername());
 
        if (UsersService.isUsersExist(Users)) {
            System.out.println("A Users with name " + Users.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        UsersService.saveUsers(Users);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/Users/{id}").buildAndExpand(Users.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    
     
    //------------------- Update a Users --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Users> updateUsers(@PathVariable("id") long id, @RequestBody Users Users) {
        System.out.println("Updating Users " + id);
         
        Users currentUsers = UsersService.findById(id);
         
        if (currentUsers==null) {
            System.out.println("Users with id " + id + " not found");
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
 
        currentUsers.setUsername(Users.getUsername());
        currentUsers.setAddress(Users.getAddress());
        currentUsers.setEmail(Users.getEmail());
         
        UsersService.updateUsers(currentUsers);
        return new ResponseEntity<Users>(currentUsers, HttpStatus.OK);
    }
 
    
    
    //------------------- Delete a Users --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Users> deleteUsers(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Users with id " + id);
 
        Users Users = UsersService.findById(id);
        if (Users == null) {
            System.out.println("Unable to delete. Users with id " + id + " not found");
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
 
        UsersService.deleteUsersById(id);
        return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
    }
 
     
    
    //------------------- Delete All Userss --------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<Users> deleteAllUserss() {
        System.out.println("Deleting All Userss");
 
        UsersService.deleteAllUserss();
        return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
    }

}
