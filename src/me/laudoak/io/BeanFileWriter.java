package me.laudoak.io;

import me.laudoak.db.TypeMapper;
import me.laudoak.entity.Bean;
import me.laudoak.entity.Table;
import me.laudoak.name.Naming;
import me.laudoak.util.Arguments;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laudoak on 16/12/10.
 */
public class BeanFileWriter
{
    private static final String TAG = BeanFileWriter.class.getSimpleName();

    private String path;
    private Naming naming;
    private TypeMapper typeMapper;

    public BeanFileWriter(String path, Naming naming, TypeMapper typeMapper)
    {
        this.path = path;
        this.naming = naming;
        this.typeMapper = typeMapper;
    }

    public void write(List<Table> tables)
    {

        List<Bean> beans = new ArrayList<>();
        for (Table table : tables)
        {
//            Log.info(TAG,"table:%s",table.getName());
//            for (MetaData meta:table.getAttributes())
//            {
//                Log.info(TAG,"meta:%s",meta.getName());
//            }
            beans.add(table.convertToBean(naming, typeMapper));
        }
        writeBeans(beans);
    }

    private void writeBeans(List<Bean> beans)
    {
        if (Arguments.isEmpty(beans))
        {
            return;
        }
        for (Bean bean : beans)
        {
            System.out.println(bean.toString());
            write(bean);
        }
    }

    private void write(Bean bean)
    {
        ByteBuffer bb;
        FileOutputStream fos = null;
        FileChannel fc = null;

        try
        {
            File pathFile = new File(path);
            if (!pathFile.exists())
            {
                pathFile.mkdir();
            }
            File beanFile = new File(pathFile, bean.getName() + ".java");
            if (!beanFile.exists())
            {
                beanFile.createNewFile();
            }
            fos = new FileOutputStream(beanFile);
            fc = fos.getChannel();
            bb = ByteBuffer.wrap(bean.toString().getBytes());
            fc.write(bb);

        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (null != fc)
            {
                try
                {
                    fc.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (null != fos)
            {
                try
                {
                    fos.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

}
