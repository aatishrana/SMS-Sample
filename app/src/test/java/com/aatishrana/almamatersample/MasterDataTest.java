package com.aatishrana.almamatersample;

import com.aatishrana.almamatersample.data.MasterDataRepository;
import com.aatishrana.almamatersample.data.MasterDataRepositoryTest;
import com.aatishrana.almamatersample.pojo.Norms;
import com.aatishrana.almamatersample.pojo.Standard;
import com.aatishrana.almamatersample.pojo.Teacher;
import com.aatishrana.almamatersample.pojo.subject.Science;
import com.aatishrana.almamatersample.pojo.subject.Subject;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Aatish on 10/17/2017.
 */

public class MasterDataTest
{
    private MasterDataRepository repository;

    @Before
    public void setUp() throws Exception
    {
        repository = new MasterDataRepositoryTest();
    }

    @Test
    public void getSubjectWithId()
    {
        assertEquals(new Science(102), repository.getSubjectWithId(102));
    }

    @Test
    public void getTeacherWithId()
    {
        Subject math = repository.getSubjectWithId(101);
        assertEquals(new Teacher(3, "Seema Mahajan", math), repository.getTeacherWithId(3));
    }

    @Test
    public void getAllTeachersTeachingSubject()
    {
        assertEquals(2, repository.getAllTeachersTeachingSubject(102).size());
    }

    @Test
    public void getClassWithId()
    {
        assertEquals(new Standard(3, "7thA", 7, 'A', 30), repository.getClassWithId(3));
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
        Subject english = repository.getSubjectWithId(104);
        assertEquals(new Norms(16, 6, english, 7), repository.getNormWithId(16));
    }

    @Test
    public void getNormOfSubject()
    {
        Subject english = repository.getSubjectWithId(104);
        assertEquals(5, repository.getNormOfSubject(english).size());//todo flaky?
    }

    @Test
    public void getAllNormsOfStandard()
    {
        assertEquals(5, repository.getAllNormsOfStandard(7).size());//todo flaky?
    }

}
