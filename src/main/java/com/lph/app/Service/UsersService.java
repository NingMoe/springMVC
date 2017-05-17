package com.lph.app.Service;

import java.util.List;

import com.lph.app.entity.Users;

public interface UsersService {

    Users findById(long id);

    Users findByName(String name);

    void saveUsers(Users Users);

    void updateUsers(Users Users);

    void deleteUsersById(long id);

    List<Users> findAllUserss();

    void deleteAllUserss();

    public boolean isUsersExist(Users Users);

}
