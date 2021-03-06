package com.shengdiyage.service.serviceImplement;

import com.shengdiyage.dao.BossDao;
import com.shengdiyage.dao.implement.BossDaoImpl;
import com.shengdiyage.entity.Boss;
import com.shengdiyage.service.BossService;

/**
 * Created by Akari on 2017/6/29.
 */
public class BossServiceImpl implements BossService {

    BossDao bossDao = new BossDaoImpl();

    @Override
    public boolean verifyBoss(Boss boss) {
        return bossDao.verifyBoss(boss);
    }
}
