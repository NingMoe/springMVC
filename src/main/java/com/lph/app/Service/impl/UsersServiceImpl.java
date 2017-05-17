package com.lph.app.Service.impl;

import java.util.List;

import com.lph.app.Service.UsersService;
import com.lph.app.entity.User;
import com.lph.app.entity.Users;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UsersService")
@Transactional
public class UsersServiceImpl implements UsersService {

    private static final AtomicLong counter = new AtomicLong();

    private static List<Users> Userss;

    static {
	Userss = populateDummyUserss();
    }

    public List<Users> findAllUserss() {
	return Userss;
    }

    public Users findById(long id) {
	for (Users Users : Userss) {
	    if (Users.getId() == id) {
		return Users;
	    }
	}
	return null;
    }

    public Users findByName(String name) {
	for (Users Users : Userss) {
	    if (Users.getUsername().equalsIgnoreCase(name)) {
		return Users;
	    }
	}
	return null;
    }

    public void saveUsers(Users Users) {
	Users.setId(counter.incrementAndGet());
	Userss.add(Users);
    }

    public void updateUsers(Users Users) {
	int index = Userss.indexOf(Users);
	Userss.set(index, Users);
    }

    public void deleteUsersById(long id) {

	for (Iterator<Users> iterator = Userss.iterator(); iterator.hasNext();) {
	    Users Users = iterator.next();
	    if (Users.getId() == id) {
		iterator.remove();
	    }
	}
    }

    public boolean isUsersExist(Users Users) {
	return findByName(Users.getUsername()) != null;
    }

    public void deleteAllUserss() {
	Userss.clear();
    }

    private static List<Users> populateDummyUserss() {
	List<Users> Userss = new ArrayList<Users>();
	Userss.add(new Users(counter.incrementAndGet(), "Sam", "NY", "sam@abc.com"));
	Userss.add(new Users(counter.incrementAndGet(), "Tomy", "ALBAMA", "tomy@abc.com"));
	Userss.add(new Users(counter.incrementAndGet(), "Kelly", "NEBRASKA", "kelly@abc.com"));
	return Userss;
    }

}
