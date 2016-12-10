package me.laudoak.type;

import me.laudoak.name.HumpNaming;
import me.laudoak.name.Naming;
import me.laudoak.name.SameNaming;

/**
 * Created by laudoak on 16/12/10.
 */
public enum NAMING
{
    SAME(new SameNaming()),
    HUMP(new HumpNaming());

    private Naming naming;

    NAMING(Naming naming)
    {
        this.naming = naming;
    }

    public Naming getNaming()
    {
        return naming;
    }
}
