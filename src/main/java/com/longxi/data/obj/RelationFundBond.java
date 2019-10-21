package com.longxi.data.obj;

import com.longxi.data.annotation.Property;
import com.longxi.data.annotation.Relation;

/**
 * @author longxi.cwl
 * @date 2019/10/18
 */
@Relation(type = "hold")
public class RelationFundBond extends RelationBase {

    @Property
    private Double assetsRate;

    @Property
    private Double marketValue;

    public Double getAssetsRate() {
        return assetsRate;
    }

    public void setAssetsRate(Double assetsRate) {
        this.assetsRate = assetsRate;
    }

    public Double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }
}
