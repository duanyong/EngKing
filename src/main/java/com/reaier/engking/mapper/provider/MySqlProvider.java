package com.reaier.engking.mapper.provider;

import com.reaier.engking.bean.AbstractBean;
import org.apache.ibatis.jdbc.SQL;

public class MySqlProvider implements IProvider {
    private String tableName;

    @Override
    public String getOneyPramyId(String table, String column, Integer primeKeyId) {
        return new SQL() {
            {
                SELECT(column);
                FROM(table);
                WHERE(table + "_id=" + primeKeyId);
            }
        }.toString();
    }

    @Override
    public String getRowByPramyId(String table, Integer primeKeyId) {
        return new SQL() {
            {
                SELECT("*");
                FROM(table);
                WHERE(table + "_id=" + primeKeyId);
            }
        }.toString();
    }

    @Override
    public String getListByPage(String table, Integer page, Integer size) {
        return new SQL() {
            {
                SELECT("*");
                FROM(table);
                WHERE(table + "_id=" + primeKeyId);
            }
        }.toString();
        return null;
    }

    @Override
    public Integer getCount(String table) {
        return null;
    }
}
