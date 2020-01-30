package com.longxi.data.obj;

import com.longxi.data.annotation.Node;
import com.longxi.data.annotation.Property;

/**
 * @author longxi.cwl
 * @date 2019/10/17
 */
@Node(label = "shares")
public class NodeShares extends NodeBase {

    /**
     * 股票code
     */
    @Property
    private String sharesCode;
    /**
     * 股票名称
     */
    @Property
    private String sharesName;

    public String getSharesCode() {
        return sharesCode;
    }

    public void setSharesCode(String sharesCode) {
        this.sharesCode = sharesCode;
    }

    public String getSharesName() {
        return sharesName;
    }

    public void setSharesName(String sharesName) {
        this.sharesName = sharesName;
    }
}
