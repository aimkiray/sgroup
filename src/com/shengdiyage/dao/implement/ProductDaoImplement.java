package com.shengdiyage.dao.implement;

import com.shengdiyage.dao.ProductDao;
import com.shengdiyage.model.Product;
import com.shengdiyage.model.ProductType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akari on 2017/6/27.
 */
public class ProductDaoImplement extends BaseDao implements ProductDao {
    @Override
    public int addProduct(Product product) {
        int result = 0;
        String sql = "INSERT INTO product(pname, pprice, pnumber, ptype) VALUES (?,?,?,?)";
        Object[] objects = {product.getPname(),product.getPprice(),product.getNumber(),product.getPtype()};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    @Override
    public int deleteProduct(int pid) {
        int result = 0;
        String sql = "DELETE FROM product WHERE pid = ?";
        Object[] objects = {pid};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    @Override
    public int updateProduct(Product product) {
        int result = 0;
        String sql = "UPDATE product set pname = ?, pprice = ?, pnumber = ?,ptype = ? WHERE pid = ?";
        Object[] objects = {product.getPname(),product.getPprice(),product.getNumber(),product.getPtype(),product.getPid()};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    @Override
    public Product getProduct(int pid) {
        Product product = null;
        String sql = "SELECT * FROM product WHERE pid = ?";
        Object[] objects = {pid};
        ResultSet rs = super.executeQuery(sql,objects);
        try {
            if(rs.next()) {
                product = new Product();
                product.setPname(rs.getString("pname"));
                product.setPprice(rs.getInt("pprice"));
                product.setNumber(rs.getInt("pnumber"));
                product.setPtype(rs.getInt("ptype"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.closeAll();
        return product;
    }

    public List<Product> queryProduct() {
        List<Product> products = new ArrayList<Product>();
        String sql = "SELECT * FROM product ORDER BY pid ASC";
        Object[] objects = {};
        ResultSet rs = super.executeQuery(sql, objects);
        try {
            while (rs.next()) {
                products.add(new Product(rs.getInt("pid"), rs.getString("pname"), rs.getInt("pprice"), rs.getInt("pnumber"), rs.getInt("ptype")));
            }
            super.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> queryProduct(int start, int conut) {
        List<Product> products = new ArrayList<Product>();
        String sql = "SELECT * FROM product ORDER BY pid ASC LIMIT ?,?";
        Object[] objects = {start,conut};
        ResultSet rs = super.executeQuery(sql, objects);
        try {
            while (rs.next()) {
                products.add(new Product(rs.getInt("pid"), rs.getString("pname"), rs.getInt("pprice"), rs.getInt("pnumber"), rs.getInt("ptype")));
            }
            super.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    return products;
    }

    @Override
    public Product queryProductByName(String pname) {
        Product product = new Product();
        String sql = "SELECT * FROM product WHERE pname = ?";
        Object[] objects = {pname};
        ResultSet rs = super.executeQuery(sql, objects);
        try {
            if(rs.next()) {
                product.setPid(rs.getInt("pid"));
                product.setPname(rs.getString("pname"));
                product.setPprice(rs.getInt("pprice"));
                product.setNumber(rs.getInt("pnumber"));
                product.setPtype(rs.getInt("ptype"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return product;
    }
}
