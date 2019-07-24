package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {
    CATEGORY_NOT_FOUND(404, "商品分类未找到"),
    GOODS_NOT_FOUND(404, "商品未找到"),
    BRAND_NOT_FOND(404, "品牌未找到"),
    SPEC_GROUP_NOT_FOUND(404, "分组不存在"),
    SPEC_PARAM_NOT_FOUND(404, "规格参数不存在"),
    IMAGE_UPLOAD_INVALID_TYPE(500, "上传失败，文件类型不匹配"),
    IMAGE_UPLOAD_INVALID_CONTENT(500, "上传失败，文件内容不符合要求"),
    GOODS_SAVE_ERROR(500, "商品插入失败");
    private int code;
    private String msg;
}
