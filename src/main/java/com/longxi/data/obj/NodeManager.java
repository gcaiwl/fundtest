package com.longxi.data.obj;

import com.longxi.data.annotation.Node;
import com.longxi.data.annotation.Property;

/**
 * @author longxi.cwl
 * @date 2019/10/17
 */
@Node(label = "manager")
public class NodeManager extends NodeBase {

    @Property
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
