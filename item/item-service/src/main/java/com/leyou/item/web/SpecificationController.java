package com.leyou.item.web;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("params")
    public ResponseEntity<List<SpecParam>> findSpecParamList(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "searching", required = false) Boolean searching
    ) {
        List<SpecParam> list = specificationService.findSpecParamList(gid, cid, searching);
        return ResponseEntity.ok(list);
    }
}
