package com.longxi.data.obj;

import com.longxi.data.annotation.Property;
import com.longxi.data.annotation.Relation;

/**
 * @author longxi.cwl
 * @date 2019/10/18
 */
@Relation(type = "invest")
public class RelationFundIndustry extends RelationBase {

    /**
     * 占净值比例
     */
    @Property
    private Double marketRatio;
    /**
     * 持仓市值
     */
    @Property
    private Double marketValue;

    public Double getMarketRatio() {
        return marketRatio;
    }

    public void setMarketRatio(Double marketRatio) {
        this.marketRatio = marketRatio;
    }

    public Double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }
}
