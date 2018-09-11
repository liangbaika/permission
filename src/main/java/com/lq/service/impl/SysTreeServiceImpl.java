package com.lq.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.lq.mapping.BeanMapper;
import com.lq.model.DeptLevelModel;
import com.lq.model.SysDeptModel;
import com.lq.service.SysDeptService;
import com.lq.service.SysTreeService;
import com.lq.utils.LevelUtils;
import com.sun.org.apache.xml.internal.security.utils.XalanXPathAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: LQ
 * @Date: 2018/9/11 20:10
 * @Description:
 */
@Service
public class SysTreeServiceImpl implements SysTreeService {

    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private BeanMapper beanMapper;

    public List<DeptLevelModel> deptTree() {
        List<SysDeptModel> sysDeptModels = sysDeptService.selectPage(new DeptLevelModel(), null);
        List<DeptLevelModel> deptLevelModels = null;
        if (!CollectionUtils.isEmpty(sysDeptModels)) {
            deptLevelModels = sysDeptModels.stream().map(e -> {
                return DeptLevelModel.adapt(e);
            }).collect(Collectors.toList());
        }
        return deptList2Tree(deptLevelModels);
    }

    private List<DeptLevelModel> deptList2Tree(List<DeptLevelModel> deptLevelModels) {
        if (CollectionUtils.isEmpty(deptLevelModels)) {
            return null;
        }
        Multimap<String, DeptLevelModel> levelDeptMap = ArrayListMultimap.create();
        List<DeptLevelModel> rootList = deptLevelModels.stream()
                .filter(e -> LevelUtils.ROOT.equals(e.getLevel()))
                .sorted(new Comparator<DeptLevelModel>() {
                    @Override
                    public int compare(DeptLevelModel o1, DeptLevelModel o2) {
                        return o1.getSeq() - o2.getSeq();
                    }
                })
                .collect(Collectors.toList());


        return null;

    }

}
