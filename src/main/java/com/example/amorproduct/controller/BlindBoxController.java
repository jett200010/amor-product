package com.example.amorproduct.controller;

import com.example.amorproduct.domain.BlindBoxRule;
import com.example.amorproduct.domain.vo.BlindBoxVO;
import com.example.amorproduct.service.BlindBoxService;
import com.example.amorproduct.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blind-boxes")
@CrossOrigin(origins = "*")
public class BlindBoxController {

    @Autowired
    private BlindBoxService blindBoxService;

    /**
     * 获取所有可用盲盒（动态生成）
     * GET /api/blind-boxes
     */
    @GetMapping
    public R<List<BlindBoxVO>> getAllBlindBoxes() {
        try {
            List<BlindBoxVO> blindBoxes = blindBoxService.getAllActiveBlindBoxes();
            return R.ok("查询成功", blindBoxes);
        } catch (Exception e) {
            return R.fail("查询盲盒失败：" + e.getMessage());
        }
    }

    /**
     * 根据规则ID生成盲盒
     * GET /api/blind-boxes/generate/{ruleId}
     */
    @GetMapping("/generate/{ruleId}")
    public R<BlindBoxVO> generateBlindBox(@PathVariable String ruleId) {
        try {
            BlindBoxVO blindBox = blindBoxService.generateBlindBoxByRuleId(ruleId);
            if (blindBox != null) {
                return R.ok("生成成功", blindBox);
            }
            return R.notFound("规则不存在或无法生成盲盒");
        } catch (Exception e) {
            return R.fail("生成盲盒失败：" + e.getMessage());
        }
    }

    /**
     * 创建盲盒规则
     * POST /api/blind-boxes/rules
     */
    @PostMapping("/rules")
    public R<String> createRule(@RequestBody BlindBoxRule rule) {
        try {
            String ruleId = blindBoxService.createRule(rule);
            return R.ok("创建成功", ruleId);
        } catch (Exception e) {
            return R.fail("创建规则失败：" + e.getMessage());
        }
    }

    /**
     * 更新盲盒规则
     * PUT /api/blind-boxes/rules/{ruleId}
     */
    @PutMapping("/rules/{ruleId}")
    public R<String> updateRule(@PathVariable String ruleId, @RequestBody BlindBoxRule rule) {
        try {
            rule.setRuleId(ruleId);
            boolean success = blindBoxService.updateRule(rule);
            if (success) {
                return R.ok("更新成功");
            }
            return R.fail("更新失败");
        } catch (Exception e) {
            return R.fail("更新规则失败：" + e.getMessage());
        }
    }

    /**
     * 删除盲盒规则
     * DELETE /api/blind-boxes/rules/{ruleId}
     */
    @DeleteMapping("/rules/{ruleId}")
    public R<String> deleteRule(@PathVariable String ruleId) {
        try {
            boolean success = blindBoxService.deleteRule(ruleId);
            if (success) {
                return R.ok("删除成功");
            }
            return R.fail("删除失败");
        } catch (Exception e) {
            return R.fail("删除规则失败：" + e.getMessage());
        }
    }
}

