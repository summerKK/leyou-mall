package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {
    CATEGORY_NOT_FOUND(404, "商品分类未找到"),
    ;
    private int code;
    private String msg;
}
