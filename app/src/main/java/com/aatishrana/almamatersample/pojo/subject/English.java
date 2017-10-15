package com.aatishrana.almamatersample.pojo.subject;

/**
 * Created by Aatish on 10/15/2017.
 */

public class English implements Subject
{
    private int id;

    public English(int id)
    {
        this.id = id;
    }

    @Override
    public int getId()
    {
        return this.id;
    }

    @Override
    public String getName()
    {
        return "English";
    }

    @Override
    public String toString()
    {
        return "English";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        English english = (English) o;

        return id == english.id;

    }

    @Override
    public int hashCode()
    {
        return id;
    }
}
