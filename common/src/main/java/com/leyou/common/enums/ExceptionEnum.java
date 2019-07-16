package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {
    CATEGORY_NOT_FOUND(404, "商品分类未找到"),
    BRAND_NOT_FOND(404, "品牌未找到"),
    IMAGE_UPLOAD_INVALID_TYPE(404, "上传失败，文件类型不匹配"),
    IMAGE_UPLOAD_INVALID_CONTENT(404, "上传失败，文件内容不符合要求");
    private int code;
    private String msg;
}
