package com.sh.common;

import java.util.HashMap;
import java.util.Map;

public class Maps {
    public static Map<String, Object> json(Object... params) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", params[0]);
        map.put("msg", params[1]);
        if(params.length == 3) {
            map.put("data", params[2]);
        }
        return map;
    }
}

