package com.lq.controller;

import com.google.common.collect.Lists;
import com.lq.entity.SysAclModule;
import com.lq.entity.SysUser;
import com.lq.factory.ResponseEnvelopFactory;
import com.lq.model.SysDeptModel;
import com.lq.repository.SysAclModuleRepository;
import com.lq.repository.SysUserRepository;
import com.lq.service.SysDeptService;
import com.lq.utils.ApplicationContextHelper;
import com.lq.utils.JsonMapper;
import com.lq.utils.ParamValidator;
import com.lq.vo.ResponseEnvelope;
import com.lq.vo.TestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private SysDeptService sysDeptService;

    @GetMapping("/hello.json")
    public String test() {
        return "test  hi  sucess";
    }

    @GetMapping("/validate.json")
    public ResponseEnvelope testParam(TestVO vo) {
        log.info("validating... vo {}", vo);
        SysUserRepository sysAclModuleRepository = ApplicationContextHelper.popBean(SysUserRepository.class);
        SysUser sysUser = sysAclModuleRepository.selectByPrimaryKey(1);
        log.info(JsonMapper.obj2String(sysUser));
//        ParamValidator.check(vo);
        return ResponseEnvelopFactory.success("success");
    }

    private Comparator<SysDeptModel> comparator = new Comparator<SysDeptModel>() {
        @Override
        public int compare(SysDeptModel s1, SysDeptModel s2) {
            return s2.getSeq() - s1.getSeq();
        }
    };

    //测试组织部门树
    @PostMapping("/tree.json")
    public ResponseEnvelope toTree() {
        //查询所有的组织结构
        List<SysDeptModel> treeModels = sysDeptService.selectPage(new SysDeptModel(), null);
        Iterator<SysDeptModel> iterator = treeModels.iterator();
        //找出顶级组织机构（这里我们定义 顶级的pid为0 ）
        ArrayList<SysDeptModel> rootNodes = Lists.newArrayList();
        while (iterator.hasNext()) {
            SysDeptModel node = iterator.next();
            if (node.getParentId() == 0) {
                rootNodes.add(node);
                iterator.remove();
            }
        }
        //为当前的组织机构排序（根据seq值）
        if (!rootNodes.isEmpty()) {
            rootNodes.sort(comparator);
        }
        //每隔从顶级开始递归寻找他们的子节点
        if (!treeModels.isEmpty() && !rootNodes.isEmpty()) {
            rootNodes.forEach(rootNode -> constructTree(rootNode, treeModels));
        }
        //返回结果
        return ResponseEnvelopFactory.success(rootNodes);
    }

    /**
     * 构造树
     *
     * @param parentNode 父节点
     * @param treeModels 剩余节点
     */
    private void constructTree(SysDeptModel parentNode, List<SysDeptModel> treeModels) {
        Iterator<SysDeptModel> iterator = treeModels.iterator();
        //保存子节点
        List<SysDeptModel> childrens = new ArrayList<>();

        while (iterator.hasNext()) {
            SysDeptModel node = iterator.next();
            //找出下一级的节点
            if (parentNode.getId().equals(node.getParentId())) {
                childrens.add(node);
                iterator.remove();
            }
        }
        //为当前节点排序
        if (!childrens.isEmpty()) {
            childrens.sort(comparator);
        }
        //设置当前子节点为当前父节点的子集
        if (!CollectionUtils.isEmpty(childrens)) {
            parentNode.setChild(childrens);
        }
        //递归进行上述步骤  当我们的子节点没有时就一个顶级组织的递归就结束了
        //当treeModels 空时 我们所有的顶级节点都把递归执行完了 就结束了
        if (!CollectionUtils.isEmpty(treeModels) && !childrens.isEmpty()) {
            childrens.forEach(node -> constructTree(node, treeModels));
        }
    }
}
