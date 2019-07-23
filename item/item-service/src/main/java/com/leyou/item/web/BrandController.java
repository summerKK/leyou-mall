package com.leyou.item.web;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        brandService.saveBrand(brand, cids);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> editBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        brandService.editBrand(brand, cids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteBrand(@RequestParam("id") Long id) {
        brandService.deleteBrand(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> getBrandListByCid(@PathVariable("cid") Long cid) {
        return ResponseEntity.ok(brandService.getBrandListByCid(cid));
    }
}
