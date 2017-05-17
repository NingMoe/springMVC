package com.lph.app.Service;

import java.util.List;

import com.lph.app.entity.AppUser;

public interface AppUserService {
    AppUser findById(int id);

    AppUser findBySSO(String sso);

    void saveUser(AppUser user);

    void updateUser(AppUser user);

    void deleteUserBySSO(String sso);

    List<AppUser> findAllUsers();

    boolean isUserSSOUnique(Integer id, String sso);

}
