package com.reaier.engking.mapper.provider;


public interface IProvider {
    //根据表主键获取一行数据
    public String getOneyPramyId(String table, String colnum, Integer primeKeyId);

    public String getRowByPramyId(String table, Integer primeKeyId);

    public String getListByPage(String table, Integer page, Integer size);

    public String getListByPageAndWhere(String table, Integer page, Integer size, String and);

    public String getListByPageAndWhere(String table, Integer page, Integer size, String[] ands, String[] ros);

    public Integer getCount(String table);
}
