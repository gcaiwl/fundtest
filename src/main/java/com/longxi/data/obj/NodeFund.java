package com.longxi.data.obj;

import java.time.LocalDateTime;

import com.longxi.data.annotation.Node;
import com.longxi.data.annotation.Property;

/**
 * @author longxi.cwl
 * @date 2019/10/17
 */
@Node(label = "fund")
public class NodeFund extends NodeBase {

    @Property
    private String code;

    @Property
    private String name;

    @Property
    private String type;

    @Property
    private LocalDateTime establishTime;

    @Property
    private Double scale;

    @Property
    private Double share;

    @Property
    private Integer status;

    @Property
    private Double holdInsideRatio;

    @Property
    private Double holdMechanismRatio;

    @Property
    private Double holdPersonalRatio;

    @Property
    private Double configSharesRatio;

    @Property
    private Double configCashRatio;

    @Property
    private Double configBondRatio;

    @Property
    private Double configAssets;

    @Property
    private Double configVoucherRatio;

    @Property
    private Double turnRate;

    @Property
    private String style;

    @Property
    private Double scalePurchase;

    @Property
    private Double scaleRedeem;

    @Property
    private Double scaleShare;

    @Property
    private Double scaleAssets;

    @Property
    private Double scaleAssetsRate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(LocalDateTime establishTime) {
        this.establishTime = establishTime;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public Double getShare() {
        return share;
    }

    public void setShare(Double share) {
        this.share = share;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getHoldInsideRatio() {
        return holdInsideRatio;
    }

    public void setHoldInsideRatio(Double holdInsideRatio) {
        this.holdInsideRatio = holdInsideRatio;
    }

    public Double getHoldMechanismRatio() {
        return holdMechanismRatio;
    }

    public void setHoldMechanismRatio(Double holdMechanismRatio) {
        this.holdMechanismRatio = holdMechanismRatio;
    }

    public Double getHoldPersonalRatio() {
        return holdPersonalRatio;
    }

    public void setHoldPersonalRatio(Double holdPersonalRatio) {
        this.holdPersonalRatio = holdPersonalRatio;
    }

    public Double getConfigSharesRatio() {
        return configSharesRatio;
    }

    public void setConfigSharesRatio(Double configSharesRatio) {
        this.configSharesRatio = configSharesRatio;
    }

    public Double getConfigCashRatio() {
        return configCashRatio;
    }

    public void setConfigCashRatio(Double configCashRatio) {
        this.configCashRatio = configCashRatio;
    }

    public Double getConfigBondRatio() {
        return configBondRatio;
    }

    public void setConfigBondRatio(Double configBondRatio) {
        this.configBondRatio = configBondRatio;
    }

    public Double getConfigAssets() {
        return configAssets;
    }

    public void setConfigAssets(Double configAssets) {
        this.configAssets = configAssets;
    }

    public Double getConfigVoucherRatio() {
        return configVoucherRatio;
    }

    public void setConfigVoucherRatio(Double configVoucherRatio) {
        this.configVoucherRatio = configVoucherRatio;
    }

    public Double getTurnRate() {
        return turnRate;
    }

    public void setTurnRate(Double turnRate) {
        this.turnRate = turnRate;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Double getScalePurchase() {
        return scalePurchase;
    }

    public void setScalePurchase(Double scalePurchase) {
        this.scalePurchase = scalePurchase;
    }

    public Double getScaleRedeem() {
        return scaleRedeem;
    }

    public void setScaleRedeem(Double scaleRedeem) {
        this.scaleRedeem = scaleRedeem;
    }

    public Double getScaleShare() {
        return scaleShare;
    }

    public void setScaleShare(Double scaleShare) {
        this.scaleShare = scaleShare;
    }

    public Double getScaleAssets() {
        return scaleAssets;
    }

    public void setScaleAssets(Double scaleAssets) {
        this.scaleAssets = scaleAssets;
    }

    public Double getScaleAssetsRate() {
        return scaleAssetsRate;
    }

    public void setScaleAssetsRate(Double scaleAssetsRate) {
        this.scaleAssetsRate = scaleAssetsRate;
    }
}
