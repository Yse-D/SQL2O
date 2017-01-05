package me.laudoak.gen.ctrl;

import java.io.File;

/**
 * Created by laudoak on 17/1/2.
 */
public class StructureGenerator
{
    private String projectName;
    private String packageName;

    private File projectPath;
    private File srcPath;

    private File mainPath;
    private File javaPackagePath;
    private File daoPath;
    private File servicePath;
    private File serviceImplPath;
    private File modelPath;

    private File resourcePath;
    private File mapperPath;

    public StructureGenerator(String projectName, String packageName)
    {
        this.projectName = projectName;
        this.packageName = packageName;
        construct();
    }

    private void construct()
    {
        projectPath = new File(projectName);
        chkDirs(projectPath);

        srcPath = new File(projectName, "src");
        chkDir(srcPath);

        mainPath = new File(srcPath, "main");
        chkDir(mainPath);

        String packageNames = packageName.replace(".", "/");
        javaPackagePath = new File(mainPath, "/java/" + packageNames);
        chkDirs(javaPackagePath);

        daoPath = new File(javaPackagePath, "dao");
        chkDir(daoPath);

        servicePath = new File(javaPackagePath, "service");
        chkDir(servicePath);

        serviceImplPath = new File(servicePath, "impl");
        chkDir(serviceImplPath);

        modelPath = new File(javaPackagePath, "model");
        chkDir(modelPath);

        resourcePath = new File(mainPath, "resources");
        chkDir(resourcePath);

        mapperPath = new File(resourcePath, "mapper");
        chkDir(mapperPath);


    }

    private void chkDir(File file)
    {
        if (!file.exists())
        {
            file.mkdir();
        }
    }

    private void chkDirs(File file)
    {
        if (!file.exists())
        {
            file.mkdirs();
        }
    }

    public File getProjectPath()
    {
        return projectPath;
    }

    public File getSrcPath()
    {
        return srcPath;
    }

    public File getMainPath()
    {
        return mainPath;
    }

    public File getJavaPackagePath()
    {
        return javaPackagePath;
    }

    public File getDaoPath()
    {
        return daoPath;
    }

    public File getServicePath()
    {
        return servicePath;
    }

    public File getServiceImplPath()
    {
        return serviceImplPath;
    }

    public File getModelPath()
    {
        return modelPath;
    }

    public File getResourcePath()
    {
        return resourcePath;
    }

    public File getMapperPath()
    {
        return mapperPath;
    }
}
