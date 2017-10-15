package com.aatishrana.almamatersample.pojo;

/**
 * Created by Aatish on 10/15/2017.
 * <p>
 * ConfigVariables are some properties on basis of which many important
 * decisions are made which can alter the output of entire system
 */
public class ConfigVariables
{
    private int maxContinuity;
    private int minFreeLectures;
    private boolean carryOverClassIncharge;
    private boolean delegateToNonSubjectTeacher;
    private int noOfLecturesInADay;

    public ConfigVariables()
    {
        this.maxContinuity = 2;
        this.minFreeLectures = 2;
        this.carryOverClassIncharge = true;
        this.delegateToNonSubjectTeacher = true;
        this.noOfLecturesInADay = 6;
    }

    public void setMaxContinuity(int maxContinuity)
    {
        this.maxContinuity = maxContinuity;
    }

    public void setMinFreeLectures(int minFreeLectures)
    {
        this.minFreeLectures = minFreeLectures;
    }

    public void setNoOfLecturesInADay(int noOfLecturesInADay)
    {
        this.noOfLecturesInADay = noOfLecturesInADay;
    }

    public void setCarryOverClassIncharge(boolean carryOverClassIncharge)
    {
        this.carryOverClassIncharge = carryOverClassIncharge;
    }

    public void setDelegateToNonSubjectTeacher(boolean delegateToNonSubjectTeacher)
    {
        this.delegateToNonSubjectTeacher = delegateToNonSubjectTeacher;
    }

    public int getMaxContinuity()
    {
        return maxContinuity;
    }

    public int getMinFreeLectures()
    {
        return minFreeLectures;
    }

    public boolean isCarryOverClassIncharge()
    {
        return carryOverClassIncharge;
    }

    public boolean isDelegateToNonSubjectTeacher()
    {
        return delegateToNonSubjectTeacher;
    }

    public int getNoOfLecturesInADay()
    {
        return noOfLecturesInADay;
    }
}
