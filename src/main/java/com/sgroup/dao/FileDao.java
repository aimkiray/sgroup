package com.sgroup.dao;

import com.sgroup.entity.FileDemo;

import java.util.List;

/**
 * Created by Akari on 2017/7/16.
 */
public interface FileDao {
    int addFile(FileDemo fileDemo);
    int delFile(int id);
    List<FileDemo> queryFile(int start, int count);
    int queryFileNum();
}
