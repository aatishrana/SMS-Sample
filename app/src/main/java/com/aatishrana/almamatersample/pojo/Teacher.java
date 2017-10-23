package com.aatishrana.almamatersample.pojo;

import com.aatishrana.almamatersample.pojo.subject.Subject;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aatish on 10/10/2017.
 */

public class Teacher
{
    private final int id;
    private final String name;
    private final Set<Subject> primarySubjects;
    private final Set<Subject> secondarySubjects;

    public Teacher(int id, String name, Set<Subject> subjects, Set<Subject> secondarySubjects)
    {
        this.id = id;
        this.name = name;
        this.primarySubjects = subjects;
        this.secondarySubjects = secondarySubjects;
    }

    public Teacher(int id, String name, Subject subject, Subject secondarySubject)
    {
        this.id = id;
        this.name = name;
        this.primarySubjects = new HashSet<>();
        this.primarySubjects.add(subject);

        this.secondarySubjects = new HashSet<>();
        if (secondarySubject != null)
            this.secondarySubjects.add(secondarySubject);
    }

    public Teacher(int id, String name, Subject subject)
    {
        this(id, name, subject, null);
    }

    public Teacher(int id, String name, Set<Subject> subjects)
    {
        this(id, name, subjects, null);
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public Set<Subject> getSubjects()
    {
        return primarySubjects;
    }

    public Set<Subject> getSecondarySubjects()
    {
        return secondarySubjects;
    }

    @Override
    public String toString()
    {
//        return "Teacher{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", subjects=" + primarySubjects +
//                ", secondary subjects=" + secondarySubjects +
//                '}';

        return "Teacher{" + name + '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (id != teacher.id) return false;
        return name != null ? name.equals(teacher.name) : teacher.name == null;

    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
