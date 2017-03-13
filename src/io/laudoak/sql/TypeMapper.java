package io.laudoak.sql;

import java.util.Map;

/**
 * Created by laudoak on 17/3/8.
 */
public class TypeMapper {

    private Map<String, String> typeMap;

    public TypeMapper(Map<String, String> typeMap) {
        this.typeMap = typeMap;
    }

    public String JTYPE(String sqlType) {
        if (sqlType == null || sqlType.equals("")) {
            return "unknown";
        }
        String javaType = typeMap.get(sqlType.toUpperCase());
        return javaType == null ? "unknown" : javaType;
    }
}
