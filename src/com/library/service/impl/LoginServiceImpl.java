package com.library.service.impl;

import com.library.entity.Reader;
import com.library.repository.AdminRepository;
import com.library.repository.ReaderRepository;
import com.library.repository.impl.AdminRepositoryImpl;
import com.library.repository.impl.ReaderRepositoryImpl;
import com.library.service.LoginService;

public class LoginServiceImpl implements LoginService {
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    private AdminRepository adminRepository = new AdminRepositoryImpl();
    @Override
    public Object login(String username, String password,String type) {
        Object object = null;
        switch (type){
            case "reader":
                object = readerRepository.login(username, password);
                break;
            case "admin":
                object = adminRepository.login(username, password);
                break;
        }
        return object;
    }
}
