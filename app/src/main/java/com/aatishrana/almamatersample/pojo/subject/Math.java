package com.aatishrana.almamatersample.pojo.subject;

/**
 * Created by Aatish on 10/10/2017.
 */

public class Math implements Subject
{
    private int id;

    public Math(int id)
    {
        this.id = id;
    }

    @Override
    public int getId()
    {
        return 0;
    }

    @Override
    public String getName()
    {
        return "Maths";
    }

    @Override
    public String toString()
    {
        return "Math";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Math math = (Math) o;

        return id == math.id;

    }

    @Override
    public int hashCode()
    {
        return id;
    }
}
