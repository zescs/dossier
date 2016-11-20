package com.zescs.dossier.repository.data.mapper;

import com.zescs.dossier.model.data.bean.DataClock;

public interface DataClockMapper {
    int deleteByPrimaryKey(Integer systemClock);

    int insert(DataClock record);

    int insertSelective(DataClock record);

    DataClock selectByPrimaryKey(Integer systemClock);

    int updateByPrimaryKeySelective(DataClock record);

    int updateByPrimaryKey(DataClock record);
}