package com.sgroup.dao.implement;

import com.sgroup.dao.FileDao;
import com.sgroup.entity.FileDemo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akari on 2017/7/16.
 */
public class FileDaoImpl extends BaseDao implements FileDao {
    /**
     * 向数据库中添加文件
     * @param fileDemo 要添加的文件类
     * @return 大于0添加成功
     */
    @Override
    public int addFile(FileDemo fileDemo) {
        int result = 0;
        String sql = "INSERT INTO file_catalog(name, date) VALUES(?, ?)";
        Object[] objects = {fileDemo.getName(), fileDemo.getDate()};
        result = super.executeUpdate(sql, objects);
        return result;
    }

    /**
     * 从数据库中删除文件
     * @param id 要删除的文件id
     * @return 大于0删除成功
     */
    @Override
    public int delFile(int id) {
        int result = 0;
        String sql = "DELETE FROM file_catalog WHERE id = ?";
        Object[] objects = {id};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    /**
     * 查询数据库中的全部产品
     * @return 全部文件类的list
     */
    @Override
    public List<FileDemo> queryFile(int start, int conut) {
        List<FileDemo> fileDemos = new ArrayList<FileDemo>();
        String sql = "SELECT * FROM file_catalog LIMIT ?,?";
        Object[] objects = {start,conut};
        ResultSet rs = super.executeQuery(sql, objects);
        try {
            while (rs.next()) {
                FileDemo fileDemo = new FileDemo();
                fileDemo.setName(rs.getString("name"));
                fileDemo.setDate(rs.getDate("date"));
                fileDemos.add(fileDemo);
            }
            super.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return fileDemos;
    }

    @Override
    public int queryFileNum() {
        int result = 0;
        String sql = "SELECT COUNT(id) FROM file_catalog";
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
}
