package com.lph.app.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lph.app.Service.AppUserService;
import com.lph.app.Service.UserProfileService;
import com.lph.app.entity.AppUser;
import com.lph.app.entity.UserProfile;

@Controller
@RequestMapping("/appUser")
@SessionAttributes("roles")
public class AppUserController {

    @Autowired
    AppUserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    /**
     * This method will list all existing users.
     */
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listUsers(HttpServletRequest request,ModelMap model) {

	List<AppUser> appUsers = userService.findAllUsers();
//	List<UserProfile> roles=userProfileService.findAll();
//	request.getSession().setAttribute("roles", roles);
	model.addAttribute("appUsers", appUsers);
	return "app_userslist";
    }

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
	AppUser appUser = new AppUser();
	model.addAttribute("appUser", appUser);
	model.addAttribute("edit", false);
	return "app_registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
    public String saveUser(@Valid AppUser appUser, BindingResult result, ModelMap model) {

	if (result.hasErrors()) {
	    return "app_registration";
	}

	/*
	 * Preferred way to achieve uniqueness of field [sso] should be
	 * implementing custom @Unique annotation and applying it on field [sso]
	 * of Model class [User].
	 * 
	 * Below mentioned peace of code [if block] is to demonstrate that you
	 * can fill custom errors outside the
	 * 
	 * validation framework as well while still using internationalized
	 * messages.
	 * 
	 */
	if (!userService.isUserSSOUnique(appUser.getId(), appUser.getSsoId())) {
	    FieldError ssoError = new FieldError("appUser", "ssoId", messageSource.getMessage("non.unique.ssoId", new
	    String[] { appUser.getSsoId() }, Locale.getDefault()));
	    result.addError(ssoError);
	    return "app_registration";
	}
	userService.saveUser(appUser);
	model.addAttribute("success",
		"User " + appUser.getFirstName() + " " + appUser.getLastName() + " registered successfully");
	// return "success";
	return "registrationsuccess";
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String ssoId, ModelMap model) {
	AppUser appUser = userService.findBySSO(ssoId);
	model.addAttribute("appUser", appUser);
	model.addAttribute("edit", true);
	return "app_registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
    public String updateUser(@Valid AppUser appUser, BindingResult result, ModelMap model, @PathVariable String ssoId) {

	if (result.hasErrors()) {
	    return "app_registration";
	}

	/*
	 * //Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in
	 * UI which is a unique key to a User.
	 * if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
	 * FieldError ssoError =new
	 * FieldError("user","ssoId",messageSource.getMessage(
	 * "non.unique.ssoId", new
	 * 
	 * String[]{user.getSsoId()}, Locale.getDefault()));
	 * result.addError(ssoError); return "registration"; }
	 */

	userService.updateUser(appUser);

	model.addAttribute("success",
		"User " + appUser.getFirstName() + " " + appUser.getLastName() + " updated successfully");
	return "registrationsuccess";
    }

    /**
     * This method will delete an user by it's SSOID value.
     */
    @RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId) {
	userService.deleteUserBySSO(ssoId);
	return "redirect:/appUser/list";
    }

    /**
     * This method will provide UserProfile list to views
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
	return userProfileService.findAll();
    }

}
