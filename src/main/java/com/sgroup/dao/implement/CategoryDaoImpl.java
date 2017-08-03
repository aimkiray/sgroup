package com.sgroup.dao.implement;

import com.sgroup.dao.CategoryDao;
import com.sgroup.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends BaseDao implements CategoryDao {
    /**
     * 添加目录
     * @param category 要添加的目录
     * @return 返回添加执行的条目数，0为失败
     */
    @Override
    public int addCategory(Category category) {
        int result = 0;
        String sql = "INSERT INTO category(categoryID) VALUES(?)";
        Object[] objects = {category.getCategoryId()};
        result = super.executeUpdate(sql, objects);
        return result;
    }

    /**
     * 删除目录
     * @param categoryId 目录id
     * @return 返回修改执行的条目数，0为失败
     */
    @Override
    public int delCategory(int categoryId) {
        int result = 0;
        String sql = "DELETE FROM category WHERE categoryID = ?";
        Object[] objects = {categoryId};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    /**
     * 修改目录（暂时无用）
     * @param category 要修改的目录
     * @return 返回修改的条目数，0为失败
     */
    @Override
    public int editCategory(Category category) {
        int result = 0;
        String sql = "UPDATE category SET  WHERE categoryID = ?";
        Object[] objects = {category.getCategoryId()};
        result = super.executeUpdate(sql, objects);
        return result;
    }

    /**
     * 分页查询目录
     *
     * @param start 从哪条开始查询
     * @param limit 一共查询多少条
     * @return 查到的目录列表
     */
    @Override
    public List<Category> queryCategory(int start, int limit) {
        String sql = "SELECT * FROM category LIMIT ?,?";
        Object[] objects = {start, limit};
        ResultSet resultSet = super.executeQuery(sql, objects);
        return getCategoryList(resultSet);
    }

    /**
     * 查询目录总数，用于分页
     *
     * @return 目录总数
     */
    @Override
    public int queryCategoryNum() {
        int result = 0;
        String sql = "SELECT COUNT(categoryID) FROM category";
        Object[] objects = {};
        ResultSet resultSet = super.executeQuery(sql, objects);
        try {
            if(resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return result;
    }

    /**
     * 从结果集中获取目录列表
     * @param resultSet
     * @return
     */
    private List<Category> getCategoryList(ResultSet resultSet) {
        List<Category> categorys = new ArrayList<Category>();
        try {
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("categoryID"));
                categorys.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return categorys;
    }
}
