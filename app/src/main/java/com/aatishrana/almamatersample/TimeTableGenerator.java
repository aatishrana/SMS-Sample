package com.aatishrana.almamatersample;

import com.aatishrana.almamatersample.data.MasterDataRepository;
import com.aatishrana.almamatersample.pojo.ConfigVariables;
import com.aatishrana.almamatersample.pojo.Norms;
import com.aatishrana.almamatersample.pojo.Standard;
import com.aatishrana.almamatersample.pojo.subject.Subject;
import com.aatishrana.almamatersample.pojo.Teacher;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Aatish on 10/10/2017.
 */

public class TimeTableGenerator
{
    private MasterDataRepository repository;

    public TimeTableGenerator(MasterDataRepository repository)
    {
        this.repository = repository;
    }

    public void makeYearlyTimeTable(Set<Teacher> teachers, Set<Standard> allSections,
                                    Set<Subject> allSubjects, ConfigVariables configVariables)
    {
        //check if all teachers of all subjects are available
        checkAllTeachersAvailable(teachers, allSubjects);

        //calculate total no. of load of a subject for all student groups from adding norms
        Map<Subject, Integer> totalLoad = calculateTotalLoad(allSubjects);

        //check if there are enough teachers to handle the load
        loadAssigningCheck(totalLoad, configVariables);

        //start creating time table
    }


    /**
     * Currently it is assumed that the teachers who teach maths
     * can teach maths for all provided student group
     */
    public void checkAllTeachersAvailable(Set<Teacher> teachers, Set<Subject> subjects)
    {
        for (Subject subject : subjects)
        {
            boolean found = false;
            for (Teacher teacher : teachers)
            {
                if (teacher.getSubjects().contains(subject))
                {
                    found = true;
                    log(subject.getName() + " Teacher Found");
                    break;
                }
            }
            if (!found)
                throw new RuntimeException("Teachers of " + subject.getName() + " not available");
        }
    }

    public Map<Subject, Integer> calculateTotalLoad(Set<Subject> allSubjects)
    {
        Map<Subject, Integer> totalLoad = new HashMap<>();
        for (Subject subject : allSubjects)
            totalLoad.put(subject, 0);


        for (int _class = 6; _class <= 10; _class++)
        {
            Set<Norms> norms = repository.getAllNormsOfStandard(_class);
            Set<Standard> sections = repository.getAllSectionOfStandard(_class);
            for (int i = 0; i < sections.size(); i++)
                for (Norms normsFor_class : norms)
                {
                    if (totalLoad.containsKey(normsFor_class.getSubject()))
                    {
                        int lectures = normsFor_class.getNoOfLectures();
                        int count = totalLoad.get(normsFor_class.getSubject());
                        totalLoad.put(normsFor_class.getSubject(), (count + lectures));
                    }
                }
        }
        return totalLoad;
    }

    public void loadAssigningCheck(Map<Subject, Integer> totalLoad, ConfigVariables configVariables)
    {
        for (Map.Entry<Subject, Integer> load : totalLoad.entrySet())
        {
            int totalNoOfLectures = configVariables.getNoOfWorkWeek() * configVariables.getNoOfLecturesInADay();
            Set<Teacher> teachers = repository.getAllTeachersTeachingSubject(load.getKey().getId());
            int totalMaxLoadOfTeachersForOneSubject = totalNoOfLectures * teachers.size();

            System.out.print(load.getKey().getName() + " Load(" + load.getValue() + ") / Max(");
            System.out.println(totalMaxLoadOfTeachersForOneSubject + ")");

            if (load.getValue() > totalMaxLoadOfTeachersForOneSubject)
                if (!configVariables.isDelegateToNonSubjectTeacher())
                    throw new RuntimeException(load.getKey() + " load is greater then available teachers. Load = "
                            + load.getValue() + ", No. of teachers available = "
                            + teachers.size() + ", Max no. of lectures in a week for 1 teacher = "
                            + totalNoOfLectures);
                else
                {
                    // todo if delegate is true find other teacher to give this subject to
                }

        }
    }

    private void log(String msg)
    {
        System.out.println(msg);
    }

}
