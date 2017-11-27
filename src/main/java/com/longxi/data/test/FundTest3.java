package com.longxi.data.test;

/**
 * @author longxi.cwl
 * @date 2017/11/27
 */
public class FundTest3 {

    public static void main(String[] args) {
        String index = "DROP INDEX `idx_code` ON `fund_value_%04d`;\n"
            + "DROP INDEX `idx_publish_time` ON `fund_value_%04d`;";

        String drop = "DROP TABLE `fund_value_%04d`;";

        String table = "CREATE TABLE `fund_value_%04d` (\n"
            + "`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',\n"
            + "`gmt_create` datetime NOT NULL COMMENT '创建时间',\n"
            + "`gmt_modified` datetime NOT NULL COMMENT '修改时间',\n"
            + "`code` char(20) NOT NULL COMMENT '编码',\n"
            + "`value` decimal(20,4) NOT NULL COMMENT '净值',\n"
            + "`total_value` decimal(20,4) NOT NULL COMMENT '累计净值',\n"
            + "`increase` decimal(20,2) NOT NULL COMMENT '日增长率',\n"
            + "`publish_time` datetime NOT NULL COMMENT '报告时间',\n"
            + "PRIMARY KEY (`id`) ,\n"
            + "INDEX `idx_code` (`code` ASC),\n"
            + "INDEX `idx_publish_time` (`publish_time` ASC)\n"
            + ")\n"
            + "ENGINE = InnoDB\n"
            + "DEFAULT CHARACTER SET = utf8\n"
            + "COMMENT = '净值表';";

        for (int i = 0; i < 128; i++) {
            System.out.println(String.format(table, i, i));
        }
    }
}
