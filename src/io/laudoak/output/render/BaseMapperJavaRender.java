package io.laudoak.output.render;

import io.laudoak.config.Config;
import io.laudoak.model.TableModel;
import io.laudoak.output.AbRender;
import io.laudoak.sql.TypeMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laudoak on 17/3/12.
 */
public class BaseMapperJavaRender extends AbRender {
    public BaseMapperJavaRender(List<TableModel> tables, TypeMapper typeMapper, Config cnf) {
        super(tables, typeMapper, cnf);
    }

    @Override
    public void render() {
        Map<String, Object> map = new HashMap<>();
        map.put("packageName", cnf.getPackageName());
        map.put("date", dateTime());
        String value = engine.process(map, "BaseMapper.java.vm");
        out(structure.getMapperInterfacePath(), "BaseMapper.java", value);
    }
}
