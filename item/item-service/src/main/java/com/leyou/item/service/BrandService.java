package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exceptions.CustomException;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 品牌查询
     *
     * @param name
     * @param desc
     * @param page
     * @param rowsPerPage
     * @param sortBy
     * @return
     */
    public PageResult<Brand> list(String name, Boolean desc, Integer page, Integer rowsPerPage, String sortBy) {
        // 开启分页
        PageHelper.startPage(page, rowsPerPage);
        // 过滤
        Example example = new Example(Brand.class);
        // 查询
        if (!StringUtils.isEmpty(name)) {
            example.createCriteria().andLike("name", "%" + name + "%")
                    .orEqualTo("letter", name);
        }
        // 排序
        if (!StringUtils.isEmpty(sortBy)) {
            String orderByClause = sortBy + (desc ? " desc" : " asc");
            example.setOrderByClause(orderByClause);
        }
        // 查询
        List<Brand> brands = brandMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(brands)) {
            throw new CustomException(ExceptionEnum.BRAND_NOT_FOND);
        }
        // 返回结果
        return new PageResult<Brand>((long) brands.size(), brands);
    }

    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        // 新增品牌信息
        brandMapper.insertSelective(brand);
        // 新增品牌和分类中间表
        for (Long cid : cids) {
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
        }
    }
}
