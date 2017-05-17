package com.lph.app.dao;

import java.util.List;

import com.lph.app.entity.AppUser;

public interface AppUserDao {
    AppUser findById(int id);

    AppUser findBySSO(String sso);

    void save(AppUser user);

    void deleteBySSO(String sso);

    List<AppUser> findAllUsers();
}
