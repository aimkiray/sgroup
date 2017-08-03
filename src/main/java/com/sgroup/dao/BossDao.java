package com.sgroup.dao;
import com.sgroup.entity.Boss;

import java.util.List;

/**
 * Created by Akari on 2017/6/29.
 */
public interface BossDao {
    /**
     * 增加BOSS
     * @param boss
     * @return
     */
    int addBoss(Boss boss);

    /**
     * 修改密码
     * @param boss
     * @return
     */
    int UpdateBoss(Boss boss);

    /**
     * 管理员列表
     * @return
     */
    List<Boss> queryBoss();

    /**
     * 管理员登陆验证
     * @return
     */
    boolean verifyBoss(Boss boss);
}
