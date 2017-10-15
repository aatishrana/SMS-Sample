package com.aatishrana.almamatersample.pojo.subject;

/**
 * Created by Aatish on 10/15/2017.
 */

public class Hindi implements Subject
{
    private int id;

    public Hindi(int id)
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
        return "Hindi";
    }

    @Override
    public String toString()
    {
        return "Hindi";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hindi hindi = (Hindi) o;

        return id == hindi.id;

    }

    @Override
    public int hashCode()
    {
        return id;
    }
}
