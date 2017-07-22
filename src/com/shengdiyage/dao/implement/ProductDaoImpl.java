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
        String sql = "INSERT INTO product(pname, pprice, pnumber, ptype, producttime, id, filename) VALUES (?,?,?,?,?,?,?)";
        Object[] objects = {product.getProductName(),product.getProductPrice(),product.getNumber(),product.getProductType().getTypeId(),product.getProductTime(),product.getId(),product.getFileName()};
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
        String sql = "UPDATE product SET pname = ?, pprice = ?, pnumber = ?,ptype = ?,producttime = ?,id = ?,filename = ? WHERE pid = ?";
        Object[] objects = {product.getProductName(),product.getProductPrice(),product.getNumber(),product.getProductType().getTypeId(),product.getProductTime(),product.getId(),product.getFileName(),product.getProductId()};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    @Override
    public Product queryProduct(int pid) {
        String sql = "SELECT * FROM product WHERE pid = ?";
        Object[] objects = {pid};
        ResultSet rs = super.executeQuery(sql,objects);
        return super.getOneProduct(rs);
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

    /**
     * 查询搜素目标产品的总数
     * @return 产品总数
     */
    @Override
    public int queryProductNum(Product product) {
        int result = 0;
        String sql = "SELECT COUNT(*) FROM product WHERE 1=1 ";
        List values = new ArrayList();
        if (product != null) {
            if (product.getProductId() > 0) {
                sql += "AND pid = ? ";
                values.add(product.getProductId());
            }
            if (product.getProductType() != null && product.getProductType().getTypeId() > 0) {
                sql += "AND ptype = ? ";
                values.add(product.getProductType().getTypeId());
            }
            if (product.getProductName() != null && !"".equals(product.getProductName())) {
                sql += "AND pname like ? ";
                values.add("%"+product.getProductName()+"%");
            }
        }
        ResultSet rs = super.executeQuery(sql, values.toArray());
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
//        以id增序查询全部产品
        String sql = "SELECT * FROM product ORDER BY pid ASC";
        Object[] objects = {};
        ResultSet rs = super.executeQuery(sql, objects);
        return super.getProducts(rs);
    }

    public List<Product> queryProduct(int start, int conut) {
        String sql = "SELECT * FROM product ORDER BY pid ASC LIMIT ?,?";
        Object[] objects = {start,conut};
        ResultSet rs = super.executeQuery(sql, objects);
        return super.getProducts(rs);
    }

    /**
     * 根据产品信息分页搜索符合条件的产品
     * @param product 要查询的产品
     * @param start 从第几条开始查询
     * @param conut 每次查询的数量
     * @return 查询到的产品集合
     */
    @Override
    public List<Product> queryProduct(Product product, int start, int conut) {
        List values = new ArrayList();
        String sql = "SELECT * FROM product WHERE 1=1 ";
        if (product != null) {
            if (product.getProductId() > 0) {
                sql += "AND pid = ? ";
                values.add(product.getProductId());
            }
            if (product.getProductType() != null && product.getProductType().getTypeId() != 0) {
                sql += "AND ptype = ? ";
                values.add(product.getProductType().getTypeId());
            }
            if (product.getProductName() != null && !"".equals(product.getProductName())) {
                sql += "AND pname like ? ";
//                模糊搜索
                values.add("%"+product.getProductName()+"%");
            }
        }
        sql += "ORDER BY pid ASC LIMIT ?,?";
        values.add(start);
        values.add(conut);
        ResultSet rs = super.executeQuery(sql, values.toArray());
        return super.getProducts(rs);
    }

    /**
     * 通过产品名称查找产品
     * @param productName 产品名称
     * @return 找到的产品，没找到返回null
     */
    @Override
    public Product queryProductByName(String productName) {
        String sql = "SELECT * FROM product WHERE pname = ?";
        Object[] objects = {productName};
        ResultSet rs = super.executeQuery(sql, objects);
        return super.getOneProduct(rs);
    }
}
