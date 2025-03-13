package com.devsurfer.purepicks.product.controller;

import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.h5.IndexVo;
import com.devsurfer.purepicks.product.service.IndexService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/13 20:37
 * description TODO
 */
@Tag(name = "首页接口管理")
@RestController
@RequestMapping("/api/product/index")
@AllArgsConstructor
@CrossOrigin
public class IndexController {

    private final IndexService indexService;

    @Operation(summary = "获取首页信息")
    @GetMapping
    public ResultUtil<IndexVo> findIndexDate() {
        return ResultUtil.ok(indexService.findIndexDate());
    }

}
