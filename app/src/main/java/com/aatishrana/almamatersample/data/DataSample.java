package com.aatishrana.almamatersample.data;

import com.aatishrana.almamatersample.pojo.Norms;
import com.aatishrana.almamatersample.pojo.Standard;
import com.aatishrana.almamatersample.pojo.Teacher;
import com.aatishrana.almamatersample.pojo.subject.Subject;

import java.util.Set;

/**
 * Created by Aatish Rana on 25-Oct-17.
 */

public interface DataSample
{

    Set<Subject> getAllSubjects();

    Set<Teacher> getAllTeachers();

    Set<Standard> getAllClasses();

    Set<Norms> getAllNorms();
}
