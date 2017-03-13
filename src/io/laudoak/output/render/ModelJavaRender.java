package io.laudoak.output.render;

import io.laudoak.auxiliary.Arguments;
import io.laudoak.config.Config;
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
public class ModelJavaRender extends AbRender {
    public ModelJavaRender(List<TableModel> tables, TypeMapper typeMapper, Config cnf) {
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
            map.put("modelComment", model.getComment());
            map.put("packageName", cnf.getPackageName());
            map.put("date", dateTime());
            map.put("atts", model.getAtts());
            String value = engine.process(map, "Model.java.vm");
            out(structure.getModelPath(), model.getName() + ".java", value);
        }
    }
}
