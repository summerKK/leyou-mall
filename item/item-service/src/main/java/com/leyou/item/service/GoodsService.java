package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exceptions.CustomException;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.SkuMapper;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.mapper.StockMapper;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.pojo.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class GoodsService {

    @Autowired
    SpuMapper spuMapper;
    @Autowired
    SpuDetailMapper spuDetailMapper;
    @Autowired
    SkuMapper skuMapper;
    @Autowired
    StockMapper stockMapper;
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

    public void addGoods(Spu spu) {
        // 添加spu
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spu.getCreateTime());
        spuMapper.insertSelective(spu);

        // spu_detail
        SpuDetail spuDetail = spu.getSpuDetail();
        spuDetail.setSpuId(spu.getId());
        int count = spuDetailMapper.insertSelective(spuDetail);
        if (count != 1) {
            throw new CustomException(ExceptionEnum.GOODS_SAVE_ERROR);
        }

        // sku
        List<Stock> stockList = new ArrayList<>();
        for (Sku sku : spu.getSkus()) {
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            sku.setSpuId(spu.getId());

            count = skuMapper.insertSelective(sku);
            if (count != 1) {
                throw new CustomException(ExceptionEnum.GOODS_SAVE_ERROR);
            }

            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stockList.add(stock);
        }

        // 批量插入stock
        count = stockMapper.insertList(stockList);
        if (count != stockList.size()) {
            throw new CustomException(ExceptionEnum.GOODS_SAVE_ERROR);
        }
    }
}
