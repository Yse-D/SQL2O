package io.laudoak.output;

import io.laudoak.auxiliary.Arguments;
import io.laudoak.auxiliary.Logger;
import io.laudoak.config.Config;
import io.laudoak.model.TableModel;
import io.laudoak.output.render.ApplicationJavaRender;
import io.laudoak.output.render.ApplicationYmlRender;
import io.laudoak.output.render.BaseMapperJavaRender;
import io.laudoak.output.render.BaseServiceJavaRender;
import io.laudoak.output.render.MapperJavaRender;
import io.laudoak.output.render.MapperXmlRender;
import io.laudoak.output.render.ModelJavaRender;
import io.laudoak.output.render.PomXmlRender;
import io.laudoak.output.render.SchemaSqlRender;
import io.laudoak.output.render.ServiceImplJavaRender;
import io.laudoak.output.render.ServiceJavaRender;
import io.laudoak.sql.TypeMapper;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laudoak on 17/3/12.
 */
public class RenderCenter {
    private static final String TAG = RenderCenter.class.getSimpleName();

    protected List<AbRender> renderList;

    private Config config;
    private TypeMapper typeMapper;
    private List<TableModel> tables;

    public RenderCenter(Config config, TypeMapper typeMapper, List<TableModel> tables) {
        this.config = config;
        this.typeMapper = typeMapper;
        this.tables = tables;
        renderList = new ArrayList<>();
    }

    private void register(Class clz) {
        Class[] paramTypes = {List.class, TypeMapper.class, Config.class};
        Object[] params = {tables, typeMapper, config};
        try {
            Constructor constructor = clz.getConstructor(paramTypes);
            AbRender render = (AbRender) constructor.newInstance(params);
            renderList.add(render);
        } catch (Exception e) {
            Logger.error(TAG, "construct class error,class>%s,cause>%s:", clz.getName(), e.getMessage());
            e.printStackTrace();
        }
    }

    public void render() {
        register(ApplicationJavaRender.class);
        register(ApplicationYmlRender.class);
        register(BaseMapperJavaRender.class);
        register(BaseServiceJavaRender.class);
        register(MapperJavaRender.class);
        register(MapperXmlRender.class);
        register(ModelJavaRender.class);
        register(PomXmlRender.class);
        register(SchemaSqlRender.class);
        register(ServiceImplJavaRender.class);
        register(ServiceJavaRender.class);
        if (!Arguments.isEmpty(renderList)) {
            for (AbRender render : renderList) {
                render.render();
            }
        }
    }
}
