package org.zam.models;

import java.util.ArrayList;
import java.util.List;

public class Request {
    private String handle;
    private int count;
    private int total;
    public Request(String handle, int count) {
        this.handle = handle;
        this.count = count;
    }
    public Request() {}

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
