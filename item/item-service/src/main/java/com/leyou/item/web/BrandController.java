package com.leyou.item.web;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @RequestMapping("list")
    public ResponseEntity<PageResult<Brand>> list(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rowsPerPage", defaultValue = "5") Integer perPage,
            @RequestParam(value = "sortBy", required = false) String sortBy) {

        return ResponseEntity.ok(brandService.list(name, desc, page, perPage, sortBy));

    }
}
