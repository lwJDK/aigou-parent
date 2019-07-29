package org.li;

import java.util.ArrayList;

public class PageList<T> {
    private Long total;
    private ArrayList<T> rows = new ArrayList<>();

    public PageList() {
    }

    public PageList(Long total, ArrayList<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public ArrayList<T> getRows() {
        return rows;
    }

    public void setRows(ArrayList<T> rows) {
        this.rows = rows;
    }
}
