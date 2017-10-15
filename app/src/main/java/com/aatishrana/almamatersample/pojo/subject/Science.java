package com.aatishrana.almamatersample.pojo.subject;

/**
 * Created by Aatish on 10/10/2017.
 */

public class Science implements Subject
{

    private int id;

    public Science(int id)
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
        return "Science";
    }

    @Override
    public String toString()
    {
        return "Science";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Science science = (Science) o;

        return id == science.id;

    }

    @Override
    public int hashCode()
    {
        return id;
    }
}
