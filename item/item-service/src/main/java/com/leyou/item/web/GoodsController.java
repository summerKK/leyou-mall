package com.leyou.item.web;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Spu;
import com.leyou.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("spu/list")
    public ResponseEntity<PageResult<Spu>> list(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "saleable", defaultValue = "false") Boolean saleable,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows) {

        PageResult<Spu> list = goodsService.list(key, saleable, page, rows);
        return ResponseEntity.ok(list);
    }

    @PostMapping("goods")
    public ResponseEntity<Void> addGoods(@RequestBody Spu spu) {
        goodsService.addGoods(spu);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
