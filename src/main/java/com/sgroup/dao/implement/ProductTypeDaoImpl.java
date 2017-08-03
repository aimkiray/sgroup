package com.sgroup.dao.implement;

import com.sgroup.dao.ProductTypeDao;
import com.sgroup.entity.Product;
import com.sgroup.entity.ProductType;
import com.sgroup.service.ProductTypeService;
import com.sgroup.service.serviceImplement.ProductTypeServiceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akari on 2017/6/28.
 */
public class ProductTypeDaoImpl extends BaseDao implements ProductTypeDao {

    @Override
    public int addProductTypeByType(ProductType productType) {
        int result = 0;
        String sql = "INSERT INTO product_type(typename) VALUES (?)";
        Object[] objects = {productType.getTypeName()};
        result = super.executeUpdate(sql, objects);
        return result;
    }

    @Override
    public int deleteProductTypeByTypeId(int typeId) {
        int result = 0;
        String sql = "DELETE FROM product_type WHERE typeid = ?";
        Object[] objects = {typeId};
        result = super.executeUpdate(sql, objects);
        return result;
    }

    @Override
    public int updateProductTypeByTypeId(ProductType productType) {
        int result = 0;
        String sql = "UPDATE product_type set typename = ? WHERE typeid = ?";
        Object[] objects = {productType.getTypeName(), productType.getTypeId()};
        result = super.executeUpdate(sql, objects);
        return result;
    }

    @Override
    public List<Product> queryProductsByTypeId(int typeId) {
        List<Product> products = new ArrayList<Product>();
        String sql = "SELECT * FROM product_type INNER JOIN product ON product_type.typeid = product.ptype WHERE ptype = ?";
        Object[] objects = {typeId};
        ResultSet rs = super.executeQuery(sql, objects);
        try {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("pid"));
                product.setProductName(rs.getString("pname"));
                product.setProductPrice(rs.getInt("pprice"));
                product.setNumber(rs.getInt("pnumber"));
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
    public ProductType queryProductTypeByTypeId(int typeId) {
        ProductType productType = new ProductType();
        String sql = "SELECT * FROM product_type WHERE typeid = ?";
        Object[] objects = {typeId};
        ResultSet rs = super.executeQuery(sql, objects);
        try {
            if (rs.next()) {
                productType.setTypeId(rs.getInt("typeid"));
                productType.setTypeName(rs.getString("typename"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return productType;
    }

    @Override
    public List<ProductType> queryAllProductType() {
        List<ProductType> productTypes = new ArrayList<>();
        String sql = "SELECT * FROM product_type";
        Object[] objects = {};
        ResultSet rs = super.executeQuery(sql, objects);
        try {
            while (rs.next()) {
                productTypes.add(new ProductType(rs.getInt("typeid"), rs.getString("typename")));
            }
            super.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productTypes;
    }

//    @Override
//    public boolean queryProductTypeByTypeName(String typename) {
//        String sql = "SELECT typename FROM product_type WHERE typename = ?";
//        Object[] objects = {typename};
//        try {
//            if("".equals(super.executeQuery(sql,objects).getString("typename"))) {
//                return true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    @Override
    public ProductType queryProductTypeByTypeName(String typeName) {
        ProductType productType = null;
        String sql = "SELECT * FROM product_type WHERE typename = ?";
        Object[] objects = {typeName};
        ResultSet rs = super.executeQuery(sql, objects);
        try {
            if (rs.next()) {
                productType = new ProductType();
                productType.setTypeId(rs.getInt("typeid"));
                productType.setTypeName(rs.getString("typename"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return productType;
    }

//    @Override
//    public List<Product> queryProductByTypeId(int start, int conut) {
//        return null;
//    }
}
