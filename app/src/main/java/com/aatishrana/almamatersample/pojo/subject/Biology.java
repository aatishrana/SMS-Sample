package com.aatishrana.almamatersample.pojo.subject;

/**
 * Created by Aatish on 10/10/2017.
 */

public class Biology implements Subject
{

    private int id;

    public Biology(int id)
    {
        this.id = id;
    }

    @Override
    public int getId()
    {
        return 2;
    }

    @Override
    public String getName()
    {
        return "Biology";
    }

    @Override
    public String toString()
    {
        return "Biology";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Biology biology = (Biology) o;

        return id == biology.id;

    }

    @Override
    public int hashCode()
    {
        return id;
    }
}
