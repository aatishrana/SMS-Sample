package com.aatishrana.almamatersample.pojo;


import com.aatishrana.almamatersample.pojo.subject.Subject;

/**
 * Created by Aatish on 10/17/2017.
 */

public class SubjectTeacher
{
    private final Subject subject;
    private final Teacher teacher;

    public SubjectTeacher(Subject subject, Teacher teacher)
    {
        this.subject = subject;
        this.teacher = teacher;
    }

    public Subject getSubject()
    {
        return subject;
    }

    public Teacher getTeacher()
    {
        return teacher;
    }

    @Override
    public String toString()
    {
        return "SubjectTeacher{" +
                "subject=" + subject +
                ", teacher=" + teacher +
                '}';
    }
}
