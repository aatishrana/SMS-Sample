package com.aatishrana.almamatersample.pojo;

import com.aatishrana.almamatersample.pojo.subject.Subject;

/**
 * Created by Aatish on 10/16/2017.
 */

public class SubjectLoad
{
    private Subject subject;
    private int totalLoad;

    public SubjectLoad(Subject subject, int totalLoad)
    {
        this.subject = subject;
        this.totalLoad = totalLoad;
    }

    public Subject getSubject()
    {
        return subject;
    }

    public int getTotalLoad()
    {
        return totalLoad;
    }
}
