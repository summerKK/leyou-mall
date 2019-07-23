package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exceptions.CustomException;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.pojo.Spu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

@Service
public class GoodsService {

    @Autowired
    SpuMapper spuMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    BrandService brandService;

    public PageResult<Spu> list(String key, Boolean saleable, Integer page, Integer rows) {
        // 开启分页
        PageHelper.startPage(page, rows);
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(key)) {
            criteria.andLike("title", "%" + key + "%");
        }
        if (saleable != null) {
            criteria.andEqualTo("saleable", saleable);
        }
        example.setOrderByClause("last_update_time DESC");
        // 查询
        List<Spu> spuList = spuMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(spuList)) {
            throw new CustomException(ExceptionEnum.GOODS_NOT_FOUND);
        }
        // 获取商品分类
        getGoodsExtraAttr(spuList);

        return new PageResult<Spu>((long) spuList.size(), spuList);
    }

    private void getGoodsExtraAttr(List<Spu> spuList) {
        for (Spu spu : spuList) {
            spu.setCname(categoryService.getCategoryNameByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3())));
            spu.setBname(brandService.selectById(spu.getBrandId()).getName());
        }
    }
}
