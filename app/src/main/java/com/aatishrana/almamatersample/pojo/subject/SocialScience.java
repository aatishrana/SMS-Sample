package com.aatishrana.almamatersample.pojo.subject;

/**
 * Created by Aatish on 10/10/2017.
 */

public class SocialScience implements Subject
{

    private int id;

    public SocialScience(int id)
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
        return "SocialScience";
    }

    @Override
    public String toString()
    {
        return "SocialScience";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocialScience socialScience = (SocialScience) o;

        return id == socialScience.id;

    }

    @Override
    public int hashCode()
    {
        return id;
    }
}
