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

    /**
     * 基金编码
     */
    @Property
    private String code;
    /**
     * 基金名称
     */
    @Property
    private String name;
    /**
     * 类型（混合|债券等）
     */
    @Property
    private String type;
    /**
     * 创建时间
     */
    @Property
    private LocalDateTime establishTime;
    /**
     * 规模
     */
    @Property
    private Double scale;
    /**
     * 份额
     */
    @Property
    private Double share;
    /**
     * 交易状态
     */
    @Property
    private Integer status;
    /**
     * 内部持有占比
     */
    @Property
    private Double holdInsideRatio;
    /**
     * 机构持有占比
     */
    @Property
    private Double holdMechanismRatio;
    /**
     * 个人持有占比
     */
    @Property
    private Double holdPersonalRatio;
    /**
     * 股票占比
     */
    @Property
    private Double configSharesRatio;
    /**
     * 现金占比
     */
    @Property
    private Double configCashRatio;
    /**
     * 债券占比
     */
    @Property
    private Double configBondRatio;
    /**
     * 净资产
     */
    @Property
    private Double configAssets;
    /**
     * 存托凭证占净比
     */
    @Property
    private Double configVoucherRatio;
    /**
     * 换手率
     */
    @Property
    private Double turnRate;
    /**
     * 风格（大盘成长等）
     */
    @Property
    private String style;
    /**
     * 期间申购份额
     */
    @Property
    private Double scalePurchase;
    /**
     * 期间赎回份额
     */
    @Property
    private Double scaleRedeem;
    /**
     * 期末总份额
     */
    @Property
    private Double scaleShare;
    /**
     * 期末净资产
     */
    @Property
    private Double scaleAssets;
    /**
     * 净资产变动率
     */
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
