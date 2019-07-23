package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exceptions.CustomException;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryCategoryListByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        List<Category> list = categoryMapper.select(category);
        if (CollectionUtils.isEmpty(list)) {
            throw new CustomException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }

    public List<Category> queryCategoryListByBrandId(Long bid) {
        List<Category> categoryList = categoryMapper.queryCategoryListByBrandId(bid);
        if (CollectionUtils.isEmpty(categoryList)) {
            throw new CustomException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return categoryList;
    }

    public String getCategoryNameByIds(List<Long> ids) {
        List<String> stringList = categoryMapper
                .selectByIdList(ids)
                .stream()
                .map(Category::getName)
                .collect(toList());
        if (CollectionUtils.isEmpty(stringList)) {
            throw new CustomException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return String.join("/", stringList);
    }
}
