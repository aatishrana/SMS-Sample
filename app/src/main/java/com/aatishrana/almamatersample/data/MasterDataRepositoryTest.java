package com.aatishrana.almamatersample.data;

import com.aatishrana.almamatersample.pojo.Norms;
import com.aatishrana.almamatersample.pojo.Standard;
import com.aatishrana.almamatersample.pojo.Teacher;
import com.aatishrana.almamatersample.pojo.subject.English;
import com.aatishrana.almamatersample.pojo.subject.Hindi;
import com.aatishrana.almamatersample.pojo.subject.Math;
import com.aatishrana.almamatersample.pojo.subject.Science;
import com.aatishrana.almamatersample.pojo.subject.SocialScience;
import com.aatishrana.almamatersample.pojo.subject.Subject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Aatish on 10/17/2017.
 */
public class MasterDataRepositoryTest implements MasterDataRepository
{
    private Set<Subject> allSubjects;
    private Set<Teacher> allTeachers;
    private Set<Standard> allClasses;
    private Set<Norms> allNorms;

    public MasterDataRepositoryTest(DataSample dataSample)
    {
        this.allSubjects = dataSample.getAllSubjects();
        this.allTeachers = dataSample.getAllTeachers();
        this.allClasses = dataSample.getAllClasses();
        this.allNorms = dataSample.getAllNorms();
    }

    @Override
    public Set<Subject> getAllSubjects()
    {
        Set<Subject> newSet = new HashSet<>();
        newSet.addAll(allSubjects);
        return newSet;
    }

    @Override
    public Subject getSubjectWithId(int id)
    {
        for (Subject subject : allSubjects)
            if (subject.getId() == id)
                return subject;
        return null;
    }

    @Override
    public Set<Teacher> getAllTeachers()
    {
        return allTeachers;
    }

    @Override
    public Teacher getTeacherWithId(int id)
    {
        for (Teacher teacher : allTeachers)
            if (teacher.getId() == id)
                return teacher;
        return null;
    }

    @Override
    public Set<Teacher> getAllTeachersTeachingSubject(int subject_id)
    {
        Set<Teacher> teacherSet = new HashSet<>();
        for (Teacher teacher : allTeachers)
            for (Subject subject : teacher.getSubjects())
                if (subject.getId() == subject_id)
                    teacherSet.add(teacher);
        return teacherSet;
    }

    @Override
    public Set<Standard> getAllClasses()
    {
        return allClasses;
    }

    @Override
    public Standard getClassWithId(int id)
    {
        for (Standard standard : allClasses)
            if (standard.getId() == id)
                return standard;
        return null;
    }

    @Override
    public Set<Standard> getAllSectionOfStandard(int standard)
    {
        Set<Standard> classes = new HashSet<>();
        for (Standard standard1 : allClasses)
            if (standard1.getStandard() == standard)
                classes.add(standard1);
        return classes;
    }

    @Override
    public Set<Norms> getAllNorms()
    {
        return allNorms;
    }

    @Override
    public Norms getNormWithId(int id)
    {
        for (Norms norms : allNorms)
            if (norms.getId() == id)
                return norms;
        return null;
    }

    @Override
    public Set<Norms> getNormOfSubject(Subject subject)
    {
        Set<Norms> normsSet = new HashSet<>();
        for (Norms norms : allNorms)
            if (norms.getSubject().getId() == subject.getId())
                normsSet.add(norms);
        return normsSet;
    }

    @Override
    public Set<Norms> getAllNormsOfStandard(int standard)
    {
        Set<Norms> normsSet = new HashSet<>();
        for (Norms norms : allNorms)
            if (norms.getStandard() == standard)
                normsSet.add(norms);
        return normsSet;
    }

    @Override
    public Norms getNormOfStandardOfSubject(int standard, Subject subject)
    {
        for (Norms norms : allNorms)
            if (norms.getStandard() == standard && norms.getSubject() == subject)
                return norms;
        return null;
    }

    @Override
    public Map<Subject, Stack<Integer>> getAllNormsOfStandardInStack(int standard)
    {
        Map<Subject, Stack<Integer>> data = new HashMap<>();
        for (Norms norms : allNorms)
            if (norms.getStandard() == standard)
            {
                if (!data.containsKey(norms.getSubject()))
                {
                    Stack<Integer> integerStack = new Stack<>();
                    for (int i = 0; i < norms.getNoOfLectures(); i++)
                        integerStack.push(1);
                    data.put(norms.getSubject(), integerStack);
                } else
                {
                    for (int i = 0; i < norms.getNoOfLectures(); i++)
                        data.get(norms.getSubject()).push(1);
                }
            }
        return data;
    }
}
