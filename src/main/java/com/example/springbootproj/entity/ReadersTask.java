package com.example.springbootproj.entity;

import java.util.List;

public class ReadersTask {

    private Reader reader;
    boolean flag;

    public ReadersTask(Reader reader, boolean flag) {
        this.reader = reader;
        this.flag = flag;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        if (this.flag == false) {
            this.flag = true;
        }
        else flag = false;
    }
}

