package me.laudoak.gen.ctrl;

import me.laudoak.db.DBStarter;
import me.laudoak.db.TypeMapper;
import me.laudoak.gen.ApplicationGenerator;
import me.laudoak.gen.ApplicationPropertiesGenerator;
import me.laudoak.gen.BaseDaoGenerator;
import me.laudoak.gen.BeanGenerator;
import me.laudoak.gen.DaoGenerator;
import me.laudoak.gen.MapperGenerator;
import me.laudoak.gen.ModelGenerator;
import me.laudoak.gen.MyBatisConfigGenerator;
import me.laudoak.gen.POMGenerator;
import me.laudoak.gen.ServiceGenerator;
import me.laudoak.gen.ServiceImplGenerator;
import me.laudoak.model.Table;
import me.laudoak.name.Naming;
import me.laudoak.util.Arguments;
import me.laudoak.util.Log;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laudoak on 17/1/1.
 */
public class GeneratorCtrl
{
    private static final String TAG = GeneratorCtrl.class.getSimpleName();

    List<Generator> generators;

    private Naming naming;
    private TypeMapper typeMapper;
    private String projectName;
    private String packageName;
    private List<Table> tables;

    private DBStarter dbStarter;

    public GeneratorCtrl(DBStarter dbStarter, List<Table> tables)
    {
        this.dbStarter = dbStarter;

        this.naming = dbStarter.getNaming().getNaming();
        this.typeMapper = dbStarter.getTypeMapper();
        this.projectName = dbStarter.getProjectName();
        this.packageName = dbStarter.getPackageName();

        this.tables = tables;

        generators = new ArrayList<>();
    }

    public void regist(Class clz)
    {
        Class[] paramTypes = {Naming.class, TypeMapper.class, String.class, String.class, List.class, DBStarter.class};
        Object[] params = {naming, typeMapper, projectName, packageName, tables, dbStarter};
        try
        {
            Constructor constructor = clz.getConstructor(paramTypes);
            Generator generator = (Generator) constructor.newInstance(params);
            generators.add(generator);

        } catch (Exception e)
        {
            Log.error(TAG, "class name:", clz.getName());
            e.printStackTrace();
        }
    }

    public void export()
    {
        regist(ApplicationGenerator.class);
        regist(BaseDaoGenerator.class);
//        regist(BeanGenerator.class);
        regist(ModelGenerator.class);
        regist(DaoGenerator.class);
        regist(MapperGenerator.class);
        regist(MyBatisConfigGenerator.class);
        regist(POMGenerator.class);
        regist(ServiceGenerator.class);
        regist(ServiceImplGenerator.class);
        regist(ApplicationPropertiesGenerator.class);

        if (!Arguments.isEmpty(generators))
        {
            for (Generator gen : generators)
            {
                gen.export();
            }
        }
    }
}
