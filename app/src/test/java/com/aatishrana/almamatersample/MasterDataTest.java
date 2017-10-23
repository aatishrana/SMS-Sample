package com.aatishrana.almamatersample;

import com.aatishrana.almamatersample.data.MasterDataRepository;
import com.aatishrana.almamatersample.data.MasterDataRepositoryTest;
import com.aatishrana.almamatersample.pojo.Norms;
import com.aatishrana.almamatersample.pojo.Standard;
import com.aatishrana.almamatersample.pojo.Teacher;
import com.aatishrana.almamatersample.pojo.subject.Math;
import com.aatishrana.almamatersample.pojo.subject.Science;
import com.aatishrana.almamatersample.pojo.subject.Subject;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Stack;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Aatish on 10/17/2017.
 */

public class MasterDataTest
{
    private MasterDataRepository repository;
    private Subject english, math, hindi, science;

    @Before
    public void setUp() throws Exception
    {
        repository = new MasterDataRepositoryTest();
        english = repository.getSubjectWithId(104);
        math = repository.getSubjectWithId(101);
        hindi = repository.getSubjectWithId(105);
        science = repository.getSubjectWithId(102);
    }

    @Test
    public void getSubjectWithId()
    {
        assertEquals(new Science(102), repository.getSubjectWithId(102));
    }

    @Test
    public void getTeacherWithId()
    {
        assertEquals(new Teacher(203, "Seema Mahajan", math), repository.getTeacherWithId(203));
    }

    @Test
    public void getAllTeachersTeachingSubject()
    {
        assertEquals(2, repository.getAllTeachersTeachingSubject(102).size());
    }

    @Test
    public void getClassWithId()
    {
        assertEquals(new Standard(303, "7thA", 7, 'A', 30), repository.getClassWithId(303));
    }

    @Test
    public void getAllSectionOfStandard()
    {
        assertEquals(3, repository.getAllSectionOfStandard(9).size());
        assertEquals(2, repository.getAllSectionOfStandard(6).size());
        assertEquals(1, repository.getAllSectionOfStandard(10).size());
    }

    @Test
    public void getNormWithId()
    {
        assertEquals(new Norms(416, 6, english, 7), repository.getNormWithId(416));
    }

    @Test
    public void getNormOfSubject()
    {
        assertEquals(5, repository.getNormOfSubject(english).size());//todo flaky?
    }

    @Test
    public void getAllNormsOfStandard()
    {
        assertEquals(5, repository.getAllNormsOfStandard(7).size());//todo flaky?
    }

    @Test
    public void getAllNormsOfStandardInStackFor6thClass()
    {
        Map<Subject, Stack<Integer>> normData = repository.getAllNormsOfStandardInStack(6);
        assertEquals(7, normData.get(english).size());
        assertEquals(7, normData.get(math).size());
        assertEquals(6, normData.get(hindi).size());
        assertEquals(6, normData.get(science).size());
    }

    @Test
    public void getAllNormsOfStandardInStackFor10thClass()
    {
        Map<Subject, Stack<Integer>> normData = repository.getAllNormsOfStandardInStack(10);
        assertEquals(8, normData.get(english).size());
        assertEquals(8, normData.get(math).size());
        assertEquals(6, normData.get(hindi).size());
        assertEquals(9, normData.get(science).size());
    }

}
