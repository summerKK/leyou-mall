package com.leyou.item.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_spu")
@Data
public class Spu implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String title;
    private String subTitle;
    private Long cid1;
    private Long cid2;
    private Long cid3;
    private Long brandId;
    private Boolean saleable;
    private Boolean valid;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date lastUpdateTime;

    @Transient
    private String cname;
    @Transient
    private String bname;
}
