package io.laudoak.output;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by laudoak on 17/1/1.
 */
public class TemplateEngine {

    private static final String LOAD_PATH = "io/laudoak/template/";

    static {
        Properties properties = new Properties();
        properties.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        properties.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        Velocity.init(properties);
    }

    static {
        Velocity.init();
    }

    private TemplateEngine() {
    }

    public static TemplateEngine instance() {
        return ClassHolder.INSTANCE;
    }

    public String process(Map<String, Object> map, String tplName) {
        if (null == map || null == tplName) {
            return "error";
        }
        VelocityContext ctx = new VelocityContext();
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            Object value = map.get(key);
            ctx.put(key, value);
        }
        Template template = Velocity.getTemplate(LOAD_PATH + tplName);
        StringWriter sw = new StringWriter();
        template.merge(ctx, sw);
        return sw.toString();
    }

    public String process(String tplName) {
        VelocityContext ctx = new VelocityContext();
        Template template = Velocity.getTemplate(LOAD_PATH + tplName);
        StringWriter sw = new StringWriter();
        template.merge(ctx, sw);
        return sw.toString();
    }

    private static class ClassHolder {
        private final static TemplateEngine INSTANCE = new TemplateEngine();
    }
}
