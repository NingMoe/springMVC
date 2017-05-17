package com.lph.app.dao;

import java.util.List;

import com.lph.app.entity.UserProfile;

public interface UserProfileDao {
    
    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}
