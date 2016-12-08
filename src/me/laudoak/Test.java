package me.laudoak;

import java.sql.SQLException;

/**
 * Created by laudoak on 16/12/8.
 */
public class Test
{
    public static void main(String... args) throws IllegalAccessException, SQLException, InstantiationException
    {
        Log.info("test", "beans string:\n%s", SQL2O.getBeanString());
    }
}
