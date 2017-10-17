package com.aatishrana.almamatersample.data;

import com.aatishrana.almamatersample.pojo.Norms;
import com.aatishrana.almamatersample.pojo.Standard;
import com.aatishrana.almamatersample.pojo.Teacher;
import com.aatishrana.almamatersample.pojo.subject.Subject;

import java.util.Set;

/**
 * Created by Aatish on 10/17/2017.
 */

public interface MasterDataRepository
{
    Set<Subject> getAllSubjects();

    Subject getSubjectWithId(int id);


    Set<Teacher> getAllTeachers();

    Teacher getTeacherWithId(int id);

    Set<Teacher> getAllTeachersTeachingSubject(int subject_id);


    Set<Standard> getAllClasses();

    Standard getClassWithId(int id);

    Set<Standard> getAllSectionOfStandard(int standard);

//    Set<Standard> getAllStandardStudyingSubject(int subject_id);


    Set<Norms> getAllNorms();

    Norms getNormWithId(int id);

    Set<Norms> getNormOfSubject(Subject subject);

    Set<Norms> getAllNormsOfStandard(int standard_id);
}
