package me.laudoak.io;

import me.laudoak.util.Log;

/**
 * Created by laudoak on 16/12/10.
 */
public class BeanFileWriter
{
    private static final String TAG = BeanFileWriter.class.getSimpleName();

    private String path;

    public BeanFileWriter(String path)
    {
        this.path = path;
    }

    public void write(String beanString)
    {
        Log.info(TAG,"write bean string:\n%s",beanString);
    }
}
