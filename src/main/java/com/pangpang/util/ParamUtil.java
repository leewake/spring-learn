package com.pangpang.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by leewake on 2017/8/14 0014.
 */
public class ParamUtil {

    public static <T> T getParams(HttpServletRequest request) {
        Map map = new HashMap();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();

            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }

        return (T) map.entrySet();

        /*Set<Map.Entry<String, String>> set = map.entrySet();
        System.out.println("------------------------------");
        for (Map.Entry entry : set) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("------------------------------");*/
    }

}
