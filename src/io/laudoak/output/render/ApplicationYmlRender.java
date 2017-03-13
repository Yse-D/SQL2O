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
public class ApplicationYmlRender extends AbRender {
    public ApplicationYmlRender(List<TableModel> tables, TypeMapper typeMapper, Config cnf) {
        super(tables, typeMapper, cnf);
    }

    @Override
    public void render() {
        Map<String, Object> map = new HashMap<>();
        map.put("driverClassName", cnf.getDriverClassName());
        map.put("url", cnf.getUrl());
        map.put("username", cnf.getUsername());
        map.put("password", cnf.getPassword());
        map.put("packageName", cnf.getPackageName());
        String value = engine.process(map, "Application.yml.vm");
        out(structure.getResourcePath(), "application.yml", value);
    }
}
