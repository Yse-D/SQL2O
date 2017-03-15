package io.laudoak.output.render;

import io.laudoak.auxiliary.Arguments;
import io.laudoak.config.Config;
import io.laudoak.model.BeanAtt;
import io.laudoak.model.BeanModel;
import io.laudoak.model.TableAtt;
import io.laudoak.model.TableModel;
import io.laudoak.output.AbRender;
import io.laudoak.sql.TypeMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laudoak on 17/3/12.
 *
 * MyBatis Mapper xml 文件
 */
public class MapperXmlRender extends AbRender {
    public MapperXmlRender(List<TableModel> tables, TypeMapper typeMapper, Config cnf) {
        super(tables, typeMapper, cnf);
    }

    @Override
    public void render() {
        if (Arguments.isEmpty(tables)) {
            return;
        }
        for (TableModel table : tables) {
            Map<String, Object> map = new HashMap<>();
            BeanModel model = table.convertToBean(typeMapper);
            map.put("model", model.getName());
            map.put("packageName", cnf.getPackageName());
            map.put("table", table.getName());
            map.put("resultMap", resultMap(table));//ignore id,created_at,updated_at column
            map.put("valueMap", valueMap(table));//ignore id,created_at,updated_at column
            map.put("columnMap", columnMap(table));//ignore id,created_at,updated_at column
            map.put("updateMap", updateMap(table));//ignore id,created_at,updated_at column
            map.put("field", "${field}");
            String value = engine.process(map, "Mapper.xml.vm");
            out(structure.getMapperXmlPath(), model.getName() + "Mapper.xml", value);
        }
    }

    private Map<String, String> resultMap(TableModel table) {
        List<TableAtt> atts = table.getAtts();
        List<BeanAtt> atts1 = table.convertToBean(typeMapper).getAtts();
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < atts.size(); i++) {
            TableAtt att = atts.get(i);
            BeanAtt att1 = atts1.get(i);
            if (ignore(att.getName())) {
                continue;
            }
            result.put(att.getName(), att1.getName());
        }
        return result;
    }

    private List<String> valueMap(TableModel table) {
        List<String> values = new ArrayList<>();
        BeanModel model = table.convertToBean(typeMapper);
        List<BeanAtt> atts = model.getAtts();
        for (int i = 0; i < atts.size(); i++) {
            BeanAtt att = atts.get(i);
            if (ignoreBeanAtt(att.getName())) {
                continue;
            }
            values.add(att.getName());
        }
        return values;
    }

    private List<String> columnMap(TableModel table) {
        List<TableAtt> atts = table.getAtts();
        List<String> results = new ArrayList<>();
        for (int i = 0; i < atts.size(); i++) {
            if (ignore(atts.get(i).getName())){
                continue;
            }
            results.add(atts.get(i).getName());
        }
        return results;
    }

    private Map<String, String> updateMap(TableModel table) {
        List<TableAtt> atts = table.getAtts();
        List<BeanAtt> atts1 = table.convertToBean(typeMapper).getAtts();
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < atts.size(); i++) {
            TableAtt att = atts.get(i);
            BeanAtt att1 = atts1.get(i);
            if (ignore(att.getName())) {
                continue;
            }
            result.put(att.getName(), att1.getName());
        }
        return result;
    }

    private boolean ignore(String col){
        return (col.equals("id") || col.equals("created_at") || col.equals("updated_at"));
    }

    private boolean ignoreBeanAtt(String col){
        return (col.equals("id") || col.equals("createdAt") || col.equals("updatedAt"));
    }
}
