package com.linktrust.fuyao.utils;

import com.google.gson.Gson;
import net.sf.json.JSONObject;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/11.
 */
public class JSONUtil {

    public static JSONObject toJson(Map<?, ?> map) {
        return JSONObject.fromObject(new Gson().toJsonTree(map).toString());
    }

}
