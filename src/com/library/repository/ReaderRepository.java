package com.library.repository;

import com.library.entity.Reader;

public interface ReaderRepository {
    public Reader login(String username,String password);
}
