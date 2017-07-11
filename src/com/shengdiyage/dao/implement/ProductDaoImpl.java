package com.shengdiyage.dao.implement;

import com.shengdiyage.dao.ProductDao;
import com.shengdiyage.entity.Product;
import com.shengdiyage.entity.ProductType;
import com.shengdiyage.service.ProductTypeService;
import com.shengdiyage.service.serrviceImplement.ProductTypeServiceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akari on 2017/6/27.
 */
public class ProductDaoImpl extends BaseDao implements ProductDao {
    @Override
    public int addProduct(Product product) {
        int result = 0;
        String sql = "INSERT INTO product(pname, pprice, pnumber, ptype, producttime, id) VALUES (?,?,?,?,?,?)";
        Object[] objects = {product.getProductName(),product.getProductPrice(),product.getNumber(),product.getProductType().getTypeId(),product.getProductTime(),product.getId()};
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
        String sql = "UPDATE product SET pname = ?, pprice = ?, pnumber = ?,ptype = ?,producttime = ?,id = ? WHERE pid = ?";
        Object[] objects = {product.getProductName(),product.getProductPrice(),product.getNumber(),product.getProductType().getTypeId(),product.getProductTime(),product.getId(),product.getProductId()};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    @Override
    public Product queryProduct(int pid) {
        Product product = null;
        String sql = "SELECT * FROM product WHERE pid = ?";
        Object[] objects = {pid};
        ResultSet rs = super.executeQuery(sql,objects);
        try {
            if(rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("pid"));
                product.setProductName(rs.getString("pname"));
                product.setProductPrice(rs.getInt("pprice"));
                product.setNumber(rs.getInt("pnumber"));
                product.setProductTime(rs.getTime("producttime"));
                product.setId(rs.getInt("id"));
                ProductTypeService productTypeService = new ProductTypeServiceImpl();
                ProductType productType = productTypeService.queryTypeByTypeId(rs.getInt("ptype"));
                product.setProductType(productType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.closeAll();
        return product;
    }

    /**
     * 批量删除产品
     * @param pids 要删除的产品id数组
     * @return
     */
    @Override
    public int delMulProduct(Integer[] pids) {
        int result = 0;
        String sql = "DELETE FROM product WHERE 1=1";
        if (pids != null && pids.length > 0) {
            sql += " AND pid IN (";
            for (int i = 0; i < pids.length; i++){
                if (i == 0) {
                    sql += "?";
                } else {
                    sql += ",?";
                }
            }
            sql += ")";
        }
        System.out.println(pids);
        for(Integer pid:pids){
            System.out.println(pid);
        }
        result = super.executeUpdate(sql,pids);
        return result;
    }

    /**
     * 查询产品总数
     * @return 产品总数
     */
    @Override
    public int queryProductNum() {
        int result = 0;
        String sql = "SELECT COUNT(*) FROM product";
        Object[] objects = {};
        ResultSet rs = super.executeQuery(sql, objects);
        try {
            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Product> queryProduct() {
        List<Product> products = new ArrayList<Product>();
//        以id增序查询全部产品
        String sql = "SELECT * FROM product ORDER BY pid ASC";
        Object[] objects = {};
        ResultSet rs = super.executeQuery(sql, objects);
        try {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("pid"));
                product.setProductName(rs.getString("pname"));
                product.setProductPrice(rs.getInt("pprice"));
                product.setNumber(rs.getInt("pnumber"));
                product.setProductTime(rs.getTime("producttime"));
                product.setId(rs.getInt("id"));
                ProductTypeService productTypeService = new ProductTypeServiceImpl();
                ProductType productType = productTypeService.queryTypeByTypeId(rs.getInt("ptype"));
                product.setProductType(productType);
//                循环添加产品到List
                products.add(product);
            }
//            关闭所有数据库连接
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
                Product product = new Product();
                product.setProductId(rs.getInt("pid"));
                product.setProductName(rs.getString("pname"));
                product.setProductPrice(rs.getInt("pprice"));
                product.setNumber(rs.getInt("pnumber"));
                product.setProductTime(rs.getTime("producttime"));
                product.setId(rs.getInt("id"));
                ProductTypeService productTypeService = new ProductTypeServiceImpl();
                ProductType productType = productTypeService.queryTypeByTypeId(rs.getInt("ptype"));
                product.setProductType(productType);
                products.add(product);
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
        Product product = null;
        String sql = "SELECT * FROM product WHERE pname = ?";
        Object[] objects = {pname};
        ResultSet rs = super.executeQuery(sql, objects);
        try {
            if(rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("pid"));
                product.setProductName(rs.getString("pname"));
                product.setProductPrice(rs.getInt("pprice"));
                product.setNumber(rs.getInt("pnumber"));
                product.setProductTime(rs.getTime("producttime"));
                product.setId(rs.getInt("id"));
                ProductTypeService productTypeService = new ProductTypeServiceImpl();
                ProductType productType = productTypeService.queryTypeByTypeId(rs.getInt("ptype"));
                product.setProductType(productType);
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
