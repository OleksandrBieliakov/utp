package ass6;

import ass6.groups.Department;
import ass6.groups.StudentsGroup;
import ass6.groups.Subject;
import ass6.groupscollections.Departments;
import ass6.groupscollections.StudentsGroups;
import ass6.groupscollections.Subjects;
import ass6.people.Degree;
import ass6.people.Nationality;
import ass6.people.Student;
import ass6.people.Teacher;

import java.time.LocalDate;
import java.util.*;

public class Generator {

    private static final int STUDENTS_NUMBER = 100;
    private static final int GROUPS_NUMBER = 12;
    private static final int GROUP_SIZE = STUDENTS_NUMBER / GROUPS_NUMBER;
    private static final int EXTRA_STUDENTS = STUDENTS_NUMBER - GROUPS_NUMBER * GROUP_SIZE;

    private static final int DEPARTMENTS_NUMBER = 3;
    private static final int TEACHER_NUMBER = 10;
    private static final int DEPARTMENT_SIZE = TEACHER_NUMBER / DEPARTMENTS_NUMBER;
    private static final int EXTRA_TEACHERS = TEACHER_NUMBER - DEPARTMENTS_NUMBER * DEPARTMENT_SIZE;

    private static final int SUBJECTS_NUMBER = 10;
    private static final int STUDENTS_PER_SUBJECT = STUDENTS_NUMBER / SUBJECTS_NUMBER;
    private static final int EXTRA_SUBJECT_STUDENTS = STUDENTS_NUMBER - SUBJECTS_NUMBER * STUDENTS_PER_SUBJECT;

    private static final Random random = new Random();
    private static final String[] names =
            {"ANTONI", "JAKUB", "JAN", "SZYMON", "ALEKSANDER", "FRANCISZEK", "FILIP", "WOJCIECH", "MIKOŁAJ", "KACPER",
                    "ADAM", "STANISŁAW", "MARCEL", "MICHAŁ", "MIŁOSZ", "PAWEŁ", "BARTŁOMIEJ", "WIKTOR", "TYMON", "IGOR"};
    private static final String[] surnames =
            {"Nowak", "Kowalski", "Wiśniewski", "Wójcik", "Kowalczyk", "Kamiński", "Lewandowski", "Zieliński", "Szymański",
                    "Woźniak", "Dąbrowski", "Kozłowski", "Jankowski", "Mazur", "Wojciechowski", "Kwiatkowski", "Krawczyk",
                    "Kaczmarek", "Piotrowski", "Grabowski"};

    private Departments departments = new Departments();
    private StudentsGroups groups = new StudentsGroups();
    private Subjects subjects = new Subjects();

    private Set<StudentsGroup> groupsSet = new HashSet<>();
    private Set<Department> departmentsSet = new HashSet<>();
    private Set<Subject> subjectsSet = new HashSet<>();

    private Set<Student> students = new HashSet<>();
    private Set<Teacher> teachers = new HashSet<>();

    public Generator() {
        generateAll();
    }

    public static String generatePESEL() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 11; ++i)
            sb.append(random.nextInt(10));
        return sb.toString();
    }

    public static String generateName() {
        return names[random.nextInt(names.length)];
    }

    public static String generateSurname() {
        return surnames[random.nextInt(surnames.length)];
    }

    public static LocalDate generateBirthDate() {
        return LocalDate.of(1990 + random.nextInt(10), random.nextInt(12), random.nextInt(30));
    }

    public static LocalDate generateHireDate() {
        return LocalDate.of(2014 + random.nextInt(5), random.nextInt(12), random.nextInt(30));
    }

    public static Student generateStudent() {
        return new Student(generatePESEL(), generateName(), generateSurname(), generateBirthDate(), Nationality.generateNationality());
    }

    public static Teacher generateTeacher() {
        return new Teacher(generatePESEL(), generateName(), generateSurname(), generateBirthDate(),
                Nationality.generateNationality(), generateHireDate(), Degree.generateDegree());
    }

    private void generateStudents() {
        while (students.size() < STUDENTS_NUMBER)
            students.add(generateStudent());
    }

    private void generateTeachers() {
        while (teachers.size() < TEACHER_NUMBER)
            teachers.add(generateTeacher());
    }

    private void fillStudentGroups() {
        Iterator<Student> iterator = students.iterator();
        int extraStudents = EXTRA_STUDENTS;
        int groupsCount = 1;
        while (groups.size() < GROUPS_NUMBER) {
            Set<Student> students = new HashSet<>();
            while (students.size() < GROUP_SIZE && iterator.hasNext()) {
                students.add(iterator.next());
            }
            if (extraStudents > 0 && iterator.hasNext()) {
                students.add(iterator.next());
                extraStudents--;
            }
            StudentsGroup group = new StudentsGroup("Group" + groupsCount++, students);
            groups.add(group);
            groupsSet.add(group);
        }
    }

    private void fillDepartments() {
        Iterator<Teacher> iterator = teachers.iterator();
        int extraTeachers = EXTRA_TEACHERS;
        int departmentsCount = 1;
        while (departments.size() < DEPARTMENTS_NUMBER) {
            Set<Teacher> teachers = new HashSet<>();
            while (teachers.size() < DEPARTMENT_SIZE && iterator.hasNext()) {
                teachers.add(iterator.next());
            }
            if (extraTeachers > 0 && iterator.hasNext()) {
                teachers.add(iterator.next());
                extraTeachers--;
            }
            Department department = new Department("Department" + departmentsCount++, teachers);
            departments.add(department);
            departmentsSet.add(department);
        }
    }

    private void fillSubjects() {
        Iterator<Student> iteratorStudents = students.iterator();
        Iterator<Teacher> iteratorTeachers = teachers.iterator();
        Iterator<Department> iteratorDepartments = departmentsSet.iterator();
        int extraStudents = EXTRA_SUBJECT_STUDENTS;
        int subjectCount = 1;
        while (subjects.size() < SUBJECTS_NUMBER) {
            Set<Student> students = new HashSet<>();
            Teacher teacher;
            Department department;
            while (students.size() < STUDENTS_PER_SUBJECT && iteratorStudents.hasNext()) {
                students.add(iteratorStudents.next());
            }
            if (extraStudents > 0 && iteratorStudents.hasNext()) {
                students.add(iteratorStudents.next());
                extraStudents--;
            }
            if (!iteratorTeachers.hasNext()) iteratorTeachers = teachers.iterator();
            teacher = iteratorTeachers.next();
            if (!iteratorDepartments.hasNext()) iteratorDepartments = departmentsSet.iterator();
            department = iteratorDepartments.next();
            Subject subject = new Subject("Subject" + subjectCount++, department, teacher, students);
            subjects.add(subject);
            subjectsSet.add(subject);
        }
    }

    private void generateAll() {
        generateStudents();
        generateTeachers();
        fillDepartments();
        fillStudentGroups();
        fillSubjects();
    }

    public Departments getDepartments() {
        return departments;
    }

    public StudentsGroups getGroups() {
        return groups;
    }

    public Subjects getSubjects() {
        return subjects;
    }

    public Set<StudentsGroup> getGroupsSet() {
        return groupsSet;
    }

    public Set<Department> getDepartmentsSet() {
        return departmentsSet;
    }

    public Set<Subject> getSubjectsSet() {
        return subjectsSet;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

}
