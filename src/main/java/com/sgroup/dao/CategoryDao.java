package com.sgroup.dao;

import com.sgroup.entity.Category;

import java.util.List;

/**
 * Created by Akari on 2017/6/27.
 */
public interface CategoryDao {
    /**
     * 添加目录
     * @param category 要添加的目录
     * @return 返回添加执行的条目数，0为失败
     */
    int addCategory(Category category);

    /**
     * 删除目录
     * @param categoryId 目录id
     * @return 返回修改的条目数，0为失败
     */
    int delCategory(int categoryId);

    /**
     * 修改目录
     * @param category 要修改的目录
     * @return 返回修改执行的条目数，0为失败
     */
    int editCategory(Category category);

    /**
     * 分页查询目录
     * @param start 从哪条开始查询
     * @param limit 一共查询多少条
     * @return 查到的目录列表
     */
    List<Category> queryCategory(int start, int limit);

    /**
     * 查询目录总数，用于分页
     * @return 目录总数
     */
    int queryCategoryNum();
}
