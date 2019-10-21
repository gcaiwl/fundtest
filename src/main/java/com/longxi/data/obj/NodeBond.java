package com.longxi.data.obj;

import com.longxi.data.annotation.Node;
import com.longxi.data.annotation.Property;

/**
 * @author longxi.cwl
 * @date 2019/10/17
 */
@Node(label = "bond")
public class NodeBond extends NodeBase {

    @Property
    private String bondCode;

    @Property
    private String bondName;

    public String getBondCode() {
        return bondCode;
    }

    public void setBondCode(String bondCode) {
        this.bondCode = bondCode;
    }

    public String getBondName() {
        return bondName;
    }

    public void setBondName(String bondName) {
        this.bondName = bondName;
    }
}
