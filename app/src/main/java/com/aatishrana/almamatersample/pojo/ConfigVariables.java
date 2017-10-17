package com.aatishrana.almamatersample.pojo;

/**
 * Created by Aatish on 10/15/2017.
 * <p>
 * ConfigVariables are some properties on basis of which many important
 * decisions are made which can alter the output of entire system
 */
public class ConfigVariables
{
    /**
     * maxContinuity describes the number of maximum lectures that can be assigned
     * to a teacher continuously i:e  with out a break, in a particular day.
     * eg:- maxContinuity=2, 1st lecture is assigned, 2nd is assigned, 3rd has to be a free lecture
     */
    private int maxContinuity;

    /**
     * minFreeLectures describes the number of lectures that must be given free in a particular day
     */
    private int minFreeLectures;

    /**
     * carryOverClassIncharge describes whether of not the class InCharge of a student group
     * must be set as class InCharge of that same student group in the next session
     */
    private boolean carryOverClassIncharge;

    /**
     * delegateToNonSubjectTeacher will define if in the event of a shortage of teachers while
     * creating the timetable, whether that subject's teaching should be allotted to a teacher
     * of different subject
     */
    private boolean delegateToNonSubjectTeacher;

    /**
     * noOfLectureInADay defines how many lectures are going to be in a day
     */
    private int noOfLecturesInADay;

    /**
     * noOfDays there will be classes
     */
    private int noOfWorkWeek;

    public ConfigVariables()
    {
        this.maxContinuity = 2;
        this.minFreeLectures = 2;
        this.carryOverClassIncharge = true;
        this.delegateToNonSubjectTeacher = true;
        this.noOfLecturesInADay = 6;
        this.noOfWorkWeek = 6;
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

    public void setNoOfWorkWeek(int noOfWorkWeek)
    {
        this.noOfWorkWeek = noOfWorkWeek;
    }

    public int getMaxContinuity()
    {
        return maxContinuity;
    }

    public int getMinFreeLectures()
    {
        return minFreeLectures;
    }

    public boolean getCarryOverClassIncharge()
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

    public int getNoOfWorkWeek()
    {
        return noOfWorkWeek;
    }
}
