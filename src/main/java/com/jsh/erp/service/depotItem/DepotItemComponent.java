package com.jsh.erp.service.depotItem;

import com.jsh.erp.service.ICommonQuery;
import com.jsh.erp.utils.Constants;
import com.jsh.erp.utils.QueryUtils;
import com.jsh.erp.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service(value = "depotItem_component")
@DepotItemResource
public class DepotItemComponent implements ICommonQuery {

    @Resource
    private DepotItemService depotItemService;

    @Override
    public Object selectOne(Long id) throws Exception {
        return depotItemService.getDepotItem(id);
    }

    @Override
    public List<?> select(Map<String, String> map)throws Exception {
        return getDepotItemList(map);
    }

    private List<?> getDepotItemList(Map<String, String> map)throws Exception {
        String search = map.get(Constants.SEARCH);
        String name = StringUtil.getInfo(search, "name");
        Integer type = StringUtil.parseInteger(StringUtil.getInfo(search, "type"));
        String remark = StringUtil.getInfo(search, "remark");
        String order = QueryUtils.order(map);
        return depotItemService.select(name, type, remark, QueryUtils.offset(map), QueryUtils.rows(map));
    }

    @Override
    public Long counts(Map<String, String> map)throws Exception {
        String search = map.get(Constants.SEARCH);
        String name = StringUtil.getInfo(search, "name");
        Integer type = StringUtil.parseInteger(StringUtil.getInfo(search, "type"));
        String remark = StringUtil.getInfo(search, "remark");
        return depotItemService.countDepotItem(name, type, remark);
    }

    @Override
    public int insert(String beanJson, HttpServletRequest request)throws Exception {
        return depotItemService.insertDepotItem(beanJson, request);
    }

    @Override
    public int update(String beanJson, Long id)throws Exception {
        return depotItemService.updateDepotItem(beanJson, id);
    }

    @Override
    public int delete(Long id)throws Exception {
        return depotItemService.deleteDepotItem(id);
    }

    @Override
    public int batchDelete(String ids)throws Exception {
        return depotItemService.batchDeleteDepotItem(ids);
    }

    @Override
    public int checkIsNameExist(Long id, String name)throws Exception {
        return depotItemService.checkIsNameExist(id, name);
    }

}
