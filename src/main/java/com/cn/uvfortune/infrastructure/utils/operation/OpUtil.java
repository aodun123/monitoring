package com.cn.uvfortune.infrastructure.utils.operation;

import com.cn.uvfortune.domain.model.security.ResourceButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/29 11:49
 * @Description:
 */
public class OpUtil {

    public static List transFormation(List<ResourceButton> list) {
        int btid = 0;
        String btname = "";
        String name = "";
        List<Integer> listid = new ArrayList<Integer>();
        List<Operation> list1 = new ArrayList();
        for (ResourceButton r : list) {
            int pageId = r.getPageId();
            listid.add(pageId);
        }
        List<Integer> getlist = getlist(listid);
        if (getlist != null) {
            for (int i = 0; i < getlist.size(); i++) {
                StringBuffer ope = new StringBuffer();
                Integer paid = getlist.get(i);
                if (getlist.get(i).equals(paid)) {
                    Operation rr = new Operation();
                    for (ResourceButton re : list) {
                        int id = re.getPageId();
                        if (paid.equals(id)) {
                            btid = re.getBtnId();
                            btname = re.getBtnName();
                            name = re.getName();
                            ope.append("" + btid + "@" + btname + "|");
                        }
                    }
                    rr.setPageId(paid);
                    rr.setOpeartionArray(ope);
                    rr.setPagename(name);
                    list1.add(rr);
                }
            }
            return list1;
        }
        return null;
    }


    public static List<Integer> getlist(List<Integer> arr) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (Object s : arr) {
            if (Collections.frequency(result, s) < 1) {
                result.add((Integer) s);
            }
        }
        System.out.println(result);
        return result;
    }
}
