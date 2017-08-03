package com.sgroup.service.serviceImplement;

import com.sgroup.dao.BossDao;
import com.sgroup.dao.implement.BossDaoImpl;
import com.sgroup.entity.Boss;
import com.sgroup.service.BossService;

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
