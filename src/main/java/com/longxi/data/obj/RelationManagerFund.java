package com.longxi.data.obj;

import com.longxi.data.annotation.Property;
import com.longxi.data.annotation.Relation;

/**
 * @author longxi.cwl
 * @date 2019/10/18
 */
@Relation(type = "manage")
public class RelationManagerFund extends RelationBase {

    /**
     * 历史回报
     */
    @Property
    private Double redound;
    /**
     * 管理状态
     */
    @Property
    private Integer status;

    public Double getRedound() {
        return redound;
    }

    public void setRedound(Double redound) {
        this.redound = redound;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
