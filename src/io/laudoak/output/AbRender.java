package io.laudoak.output;

import io.laudoak.auxiliary.Arguments;
import io.laudoak.config.Config;
import io.laudoak.model.BeanModel;
import io.laudoak.model.TableModel;
import io.laudoak.sql.TypeMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by laudoak on 17/3/8.
 */
public abstract class AbRender {
    protected List<TableModel> tables;
    protected List<BeanModel> beans;
    protected TypeMapper typeMapper;
    protected Structure structure;

    protected Config cnf;
    protected static TemplateEngine engine;

    static {
        engine = TemplateEngine.instance();
    }

    public AbRender(List<TableModel> tables, TypeMapper typeMapper, Config cnf) {
        this.tables = tables;
        this.typeMapper = typeMapper;
        this.cnf = cnf;
        init();
    }

    private void init() {
        structure = new Structure(cnf);
        beans = new ArrayList<>();
        if (Arguments.isEmpty(tables)) {
            return;
        }
        for (TableModel table : tables) {
            beans.add(table.convertToBean(typeMapper));
        }
    }

    protected String dateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    protected void out(File path, String fName, String value) {
        ByteBuffer bb;
        FileOutputStream fos = null;
        FileChannel fc = null;

        try {
            File beanFile = new File(path, fName);
            if (!beanFile.exists()) {
                beanFile.createNewFile();
            }
            fos = new FileOutputStream(beanFile);
            fc = fos.getChannel();
            bb = ByteBuffer.wrap(value.getBytes());
            fc.write(bb);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fc) {
                try {
                    fc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public abstract void render();
}
