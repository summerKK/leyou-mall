package com.leyou.item.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "tb_sku")
@Data
public class Sku implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long spuId;
    private String title;
    private String images;
    private BigDecimal price;
    private String indexes;
    private String ownSpec;
    private Boolean enable;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date lastUpdateTime;
}
