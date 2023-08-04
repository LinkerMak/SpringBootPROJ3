package com.example.springbootproj.utils;

import com.example.springbootproj.entity.Reader;
import com.example.springbootproj.entity.security.User;

public class userFormReader {

    Reader reader;

    User user;

    public userFormReader(Reader reader, User user) {
        this.reader = reader;
        this.user = user;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
