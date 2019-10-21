package com.longxi.data.obj;

/**
 * @author longxi.cwl
 * @date 2019/10/18
 */
public class RelationBase extends BaseDO {

    private int fromId;

    private int toId;

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }
}
