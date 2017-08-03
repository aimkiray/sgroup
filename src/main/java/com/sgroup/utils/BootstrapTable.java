package com.sgroup.utils;

import com.sgroup.entity.Product;

import java.util.List;

/**
 * Created by nekuata on 2017/7/20.
 */
public class BootstrapTable {
    private int total;
    private List<Product> rows;

    public BootstrapTable() {
    }

    public BootstrapTable(int total, List<Product> rows) {
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Product> getRows() {
        return rows;
    }

    public void setRows(List<Product> rows) {
        this.rows = rows;
    }
}
