package com.library.repository;

import com.library.entity.Admin;

public interface AdminRepository {
    public Admin login(String username,String password);
}
