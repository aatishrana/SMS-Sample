package com.aatishrana.almamatersample.pojo.subject;

/**
 * Created by Aatish on 10/10/2017.
 */

public class History implements Subject
{

    private int id;

    public History(int id)
    {
        this.id = id;
    }

    @Override
    public int getId()
    {
        return 1;
    }

    @Override
    public String getName()
    {
        return "History";
    }

    @Override
    public String toString()
    {
        return "History";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        History history = (History) o;

        return id == history.id;

    }

    @Override
    public int hashCode()
    {
        return id;
    }
}
