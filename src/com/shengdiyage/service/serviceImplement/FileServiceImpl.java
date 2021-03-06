package com.shengdiyage.service.serviceImplement;

import com.shengdiyage.dao.FileDao;
import com.shengdiyage.dao.implement.FileDaoImpl;
import com.shengdiyage.entity.FileDemo;
import com.shengdiyage.service.FileService;

import java.util.List;

/**
 * Created by nekuata on 2017/7/16.
 */
public class FileServiceImpl implements FileService {
    FileDao fileDao = new FileDaoImpl();
    @Override
    public int addFile(FileDemo fileDemo) {
        // 检查是否存在（待填坑）

        return fileDao.addFile(fileDemo);
    }

    @Override
    public int delFile(int id) {
        return fileDao.delFile(id);
    }

    @Override
    public List<FileDemo> queryFile(int curPage, int pageSize) {
        return fileDao.queryFile((curPage-1)*pageSize, pageSize);
    }

    @Override
    public int queryFileNum() {
        return fileDao.queryFileNum();
    }
}
