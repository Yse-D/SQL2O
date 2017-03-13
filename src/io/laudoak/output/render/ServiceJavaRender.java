package io.laudoak.output.render;

import io.laudoak.auxiliary.Arguments;
import io.laudoak.config.Config;
import io.laudoak.config.Naming;
import io.laudoak.model.BeanModel;
import io.laudoak.model.TableModel;
import io.laudoak.output.AbRender;
import io.laudoak.sql.TypeMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laudoak on 17/3/12.
 */
public class ServiceJavaRender extends AbRender {
    public ServiceJavaRender(List<TableModel> tables, TypeMapper typeMapper, Config cnf) {
        super(tables, typeMapper, cnf);
    }

    @Override
    public void render() {
        if (Arguments.isEmpty(beans)) {
            return;
        }
        for (BeanModel model : beans) {
            Map<String, Object> map = new HashMap<>();
            map.put("model", model.getName());
            map.put("packageName", cnf.getPackageName());
            map.put("date", dateTime());
            map.put("modelParam", Naming.modelAsParam(model.getName()));
            String value = engine.process(map, "Service.java.vm");
            out(structure.getServicePath(), model.getName() + "Service.java", value);
        }
    }
}
