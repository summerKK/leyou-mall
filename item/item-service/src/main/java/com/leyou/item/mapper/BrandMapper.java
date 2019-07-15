package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandMapper extends Mapper<Brand> {
    List<Brand> list();
}
