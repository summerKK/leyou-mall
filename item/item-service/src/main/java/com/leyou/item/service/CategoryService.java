package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exceptions.CustomException;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper mapper;

    public List<Category> queryCategoryListByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        List<Category> list = mapper.select(category);
        if (CollectionUtils.isEmpty(list)) {
            throw new CustomException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }
}
