package io.laudoak.output;

import io.laudoak.config.Config;

import java.io.File;

/**
 * Created by laudoak on 17/3/8.
 * <p>
 * 脚手架项目文件结构生成
 */
public class Structure {
    private File projectPath;
    private File srcPath;

    private File mainPath;
    private File javaPackagePath;
    private File servicePath;
    private File serviceImplPath;
    private File modelPath;
    private File mapperInterfacePath;

    private File resourcePath;
    private File mapperXmlPath;

    private Config cnf;

    public Structure(Config cnf) {
        this.cnf = cnf;
        construct();
    }

    private void construct() {
        String packagePaths = cnf.getPackageName().replace(".", "/");
        projectPath = make(cnf.getProjectName());
        srcPath = make(projectPath, "src");
        mainPath = make(srcPath, "main");
        javaPackagePath = make(mainPath, "/java/" + packagePaths);
        servicePath = make(javaPackagePath, "service");
        serviceImplPath = make(servicePath, "impl");
        modelPath = make(javaPackagePath, "model");
        mapperInterfacePath = make(javaPackagePath, "mapper");
        resourcePath = make(mainPath, "resources");
        mapperXmlPath = make(resourcePath, packagePaths + "/mapper");
    }

    private File make(String path) {
        File file = new File(path);
        check(file);
        return new File(path);
    }

    private File make(File parent, String child) {
        File file = new File(parent, child);
        check(file);
        return file;
    }

    private void check(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public File getProjectPath() {
        return projectPath;
    }

    public File getSrcPath() {
        return srcPath;
    }

    public File getMainPath() {
        return mainPath;
    }

    public File getJavaPackagePath() {
        return javaPackagePath;
    }

    public File getServicePath() {
        return servicePath;
    }

    public File getServiceImplPath() {
        return serviceImplPath;
    }

    public File getModelPath() {
        return modelPath;
    }

    public File getMapperInterfacePath() {
        return mapperInterfacePath;
    }

    public File getResourcePath() {
        return resourcePath;
    }

    public File getMapperXmlPath() {
        return mapperXmlPath;
    }
}
