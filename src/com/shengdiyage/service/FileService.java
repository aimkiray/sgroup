package com.shengdiyage.service;

import com.shengdiyage.entity.FileDemo;

import java.util.List;

/**
 * Created by nekuata on 2017/7/16.
 */
public interface FileService {
    int addFile(FileDemo fileDemo);
    int delFile(int id);
    List<FileDemo> queryFile(int curPage, int pageSize);
    int queryFileNum();
}
