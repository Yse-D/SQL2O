package io.laudoak.output.render;

import io.laudoak.config.Config;
import io.laudoak.model.TableModel;
import io.laudoak.output.AbRender;
import io.laudoak.sql.TypeMapper;

import java.util.List;

/**
 * Created by laudoak on 17/3/12.
 */
public class SchemaSqlRender extends AbRender {
    public SchemaSqlRender(List<TableModel> tables, TypeMapper typeMapper, Config cnf) {
        super(tables, typeMapper, cnf);
    }

    @Override
    public void render() {
        //TODO
    }
}
