package com.boot.security.server.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CateTreeUtil {
    
    
    public List<Cate> getTree(List<Cate> cates) {
        List<Cate> roots = getRootList(cates);
        Map<String, List<Cate>> childMaps = cates.stream().collect(Collectors.groupingBy(Cate::getPid));
        for(Cate root:roots){
            //拼接数据
            forEach(childMaps, root);
        }
        return roots;
    }
    /**
     * 
     * @param collect 所有按父级code分组map
     * @param Type void 当前根节点
     */
    private void forEach(Map<String, List<Cate>> collect, Cate cate) {
        List<Cate> treeMenuNodes = collect.get(cate.getId());
        if(treeMenuNodes != null){
            cate.setCates(treeMenuNodes);
            cate.getCates().forEach(t->{
                forEach(collect,t);//递归设置下级
            });
        }
    }
    
    /**
     * 获取所有根节点
     * TODO。
     * @param list
     * @return List<Type>
     */
    private List<Cate> getRootList(List<Cate> list){
        List<Cate> roots = new ArrayList<Cate>();
        for(Cate cate:list){
            if("0".equals(cate.getPid())){
                roots.add(cate);
            }
        }
        list.removeAll(roots);
        return roots;
    }

}
