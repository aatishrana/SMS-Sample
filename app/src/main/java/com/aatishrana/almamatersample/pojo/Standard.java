package com.aatishrana.almamatersample.pojo;

/**
 * Created by Aatish on 10/17/2017.
 */

public class Standard
{
    private final int id;
    private final String name;
    private final int standard;
    private final char section;
    private final int noOfStudents;

    public Standard(int id, String name, int standard, char section, int noOfStudents)
    {
        this.id = id;
        this.name = name;
        this.standard = standard;
        this.section = section;
        this.noOfStudents = noOfStudents;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getStandard()
    {
        return standard;
    }

    public char getSection()
    {
        return section;
    }

    public int getNoOfStudents()
    {
        return noOfStudents;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Standard standard1 = (Standard) o;

        if (id != standard1.id) return false;
        if (standard != standard1.standard) return false;
        if (section != standard1.section) return false;
        if (noOfStudents != standard1.noOfStudents) return false;
        return name != null ? name.equals(standard1.name) : standard1.name == null;

    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + standard;
        result = 31 * result + (int) section;
        result = 31 * result + noOfStudents;
        return result;
    }
}
