package com.light.mapper.custom;

import java.util.List;
import java.util.Map;

import com.light.pojo.LgNews;

public interface LgNewsCustomMapper {

	List<LgNews> getListWithoutContent(Map paramMap);
}
