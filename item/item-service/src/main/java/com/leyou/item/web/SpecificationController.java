package com.leyou.item.web;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    @RequestMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> findSpecGroupByCid(@PathVariable("cid") Long cid) {
        List<SpecGroup> list = specificationService.findSpecGroupByCid(cid);
        return ResponseEntity.ok(list);
    }
}
