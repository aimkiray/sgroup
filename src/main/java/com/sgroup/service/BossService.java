package com.sgroup.service;

import com.sgroup.entity.Boss;

/**
 * Created by Akari on 2017/6/29.
 */
public interface BossService {
    /**
     * 管理员登陆验证
     * @return
     */
    boolean verifyBoss(Boss boss);
}
