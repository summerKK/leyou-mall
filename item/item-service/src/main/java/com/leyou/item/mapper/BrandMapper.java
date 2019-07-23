package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandMapper extends Mapper<Brand> {
    List<Brand> list();

    @Insert("INSERT INTO tb_category_brand (category_id, brand_id) VALUES (#{cid},#{bid})")
    int insertCategoryBrandId(@Param("cid") Long cid, @Param("bid") Long bid);

    @Delete("Delete from tb_category_brand where brand_id = #{bid}")
    int deleteCategoryByBrandId(@Param("bid") Long bid);

    @Select("SELECT * FROM tb_brand b INNER JOIN tb_category_brand cb on b.id = cb.brand_id WHERE cb.category_id = #{cid}")
    List<Brand> getBrandListByCid(@Param("cid") Long cid);
}
