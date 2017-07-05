package com.shengdiyage.service;

import com.shengdiyage.model.Boss;

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
