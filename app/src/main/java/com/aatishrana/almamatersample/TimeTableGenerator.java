package com.aatishrana.almamatersample;

import com.aatishrana.almamatersample.data.MasterDataRepository;
import com.aatishrana.almamatersample.pojo.ConfigVariables;
import com.aatishrana.almamatersample.pojo.Norms;
import com.aatishrana.almamatersample.pojo.Standard;
import com.aatishrana.almamatersample.pojo.SubjectTeacher;
import com.aatishrana.almamatersample.pojo.subject.Subject;
import com.aatishrana.almamatersample.pojo.Teacher;
import com.aatishrana.almamatersample.pojo.timetable.TimeTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
        List<Standard> allStandards = new ArrayList<>();
        allStandards.addAll(allSections);
//        createTimeTable(totalLoad, teachers, allStandards, configVariables);
        createTimeTable(teachers, allStandards, configVariables);
    }


    /**
     * Currently it is assumed that the teachers who teach maths
     * can teach maths for all provided student group
     */
    void checkAllTeachersAvailable(Set<Teacher> teachers, Set<Subject> subjects)
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

    Map<Subject, Integer> calculateTotalLoad(Set<Subject> allSubjects)
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

    void loadAssigningCheck(Map<Subject, Integer> totalLoad, ConfigVariables configVariables)
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

    void createTimeTable(Set<Teacher> teachers, List<Standard> allSections, ConfigVariables configVariables)
    {
//        Map<Standard, Map<Integer, SubjectTeacher>> timeTable = new HashMap<>();
        SubjectTeacher[][][] data = new SubjectTeacher[configVariables.getNoOfWorkWeek()][configVariables.getNoOfLecturesInADay()][allSections.size()];

        //outer loop for all work week (Monday to Saturday)
        for (int day = 0; day < configVariables.getNoOfWorkWeek(); day++)
        {
            log("Day:" + day);

            //inner loop for lecture no. (1st lecture to 8th lecture)
            for (int lecture = 0; lecture < configVariables.getNoOfLecturesInADay(); lecture++)
            {
                log("\tLecture no:" + lecture);

                //create list of teachers from set
                List<Teacher> tempTeachers = new ArrayList<>();
                tempTeachers.addAll(teachers);

                //create empty list of assigned teachers
                List<Teacher> assignedTeachers = new ArrayList<>();

                //get free and assigned teachers
                Map<Boolean, List<Teacher>> allTeachers = getAllFreeTeachers(tempTeachers, assignedTeachers);

                //inner inner loop for all classes (6thA to 10thC)
                for (int sectionIndex = 0; sectionIndex < allSections.size(); sectionIndex++)
                {
                    Standard standard = allSections.get(sectionIndex);
                    log("\t\tFor " + standard.getStandard() + " " + standard.getSection());

                    //get all free teachers from allTeachers
                    List<Teacher> freeTeachers = allTeachers.get(false);


                    Random random = new Random();
                    //todo later get teacher based on subject it teaches and subject we need to fill
                    Teacher selectedTeacher = freeTeachers.get(random.nextInt(freeTeachers.size()));
                    assignedTeachers.add(selectedTeacher);
                    allTeachers = getAllFreeTeachers(tempTeachers, assignedTeachers);

                    SubjectTeacher dataItem;
                    if (selectedTeacher.getSubjects().size() == 1)
                    {
                        dataItem = new SubjectTeacher(selectedTeacher.getSubjects().iterator().next(), selectedTeacher);
                    } else
                    {
                        dataItem = new SubjectTeacher(null, null);
                    }
                    log("\t\t\t " + dataItem);
                    data[day][lecture][sectionIndex] = dataItem;
                }
            }
        }
        log(Arrays.deepToString(data));
    }

    Map<Boolean, List<Teacher>> getAllFreeTeachers(List<Teacher> allTeachers, List<Teacher> assignedTeachers)
    {
        Map<Boolean, List<Teacher>> data = new HashMap<>();

        List<Teacher> unAssignedTeachers = new ArrayList<>();
        for (Teacher teacher : allTeachers)
            if (!assignedTeachers.contains(teacher))
                unAssignedTeachers.add(teacher);

        data.put(true, assignedTeachers);
        data.put(false, unAssignedTeachers);

        return data;
    }

    private void log(String msg)
    {
        System.out.println(msg);
    }

}
