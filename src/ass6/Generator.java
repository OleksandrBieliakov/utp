package ass6;

import ass6.extents.*;
import ass6.groups.Department;
import ass6.groups.StudentsGroup;
import ass6.groups.Subject;
import ass6.people.*;

import java.time.LocalDate;
import java.util.*;

public class Generator {

    private static final int STUDENTS_NUMBER = 100;
    private static final int GROUPS_NUMBER = 12;
    private static final int GROUP_SIZE = STUDENTS_NUMBER / GROUPS_NUMBER;
    private int extraStudents = STUDENTS_NUMBER % GROUPS_NUMBER;

    private static final int DEPARTMENTS_NUMBER = 3;
    private static final int TEACHER_NUMBER = 10;
    private static final int DEPARTMENT_SIZE = TEACHER_NUMBER / DEPARTMENTS_NUMBER;
    private int extraTeachers = TEACHER_NUMBER % DEPARTMENTS_NUMBER;

    private static final int SUBJECTS_NUMBER = 10;
    private static final int STUDENTS_PER_SUBJECT = STUDENTS_NUMBER / SUBJECTS_NUMBER;
    private int extraSubjectStudents = STUDENTS_NUMBER % SUBJECTS_NUMBER;

    private static final String[] names =
            {"ANTONI", "JAKUB", "JAN", "SZYMON", "ALEKSANDER", "FRANCISZEK", "FILIP", "WOJCIECH", "MIKOŁAJ", "KACPER",
                    "ADAM", "STANISŁAW", "MARCEL", "MICHAŁ", "MIŁOSZ", "PAWEŁ", "BARTŁOMIEJ", "WIKTOR", "TYMON", "IGOR"};
    private static final String[] surnames =
            {"Nowak", "Kowalski", "Wiśniewski", "Wójcik", "Kowalczyk", "Kamiński", "Lewandowski", "Zieliński", "Szymański",
                    "Woźniak", "Dąbrowski", "Kozłowski", "Jankowski", "Mazur", "Wojciechowski", "Kwiatkowski", "Krawczyk",
                    "Kaczmarek", "Piotrowski", "Grabowski"};

    private static final Random random = new Random();

    private People people = new People();
    private Students students = new Students();
    private Teachers teachers = new Teachers();

    private StudentsGroups studentsGroups = new StudentsGroups();
    private Departments departments = new Departments();
    private Subjects subjects = new Subjects();

    private Iterator<Student> studentsIterator = students.iterator();
    private Iterator<Teacher> teachersIterator = teachers.iterator();
    private Iterator<Department> departmentsIterator = departments.iterator();

    //TODO
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

    //TODO
    public static LocalDate generateBirthDate() {
        return LocalDate.of(1990 + random.nextInt(10), random.nextInt(12), random.nextInt(30));
    }

    public static Nationality generateNationality() {
        Nationality[] nationalities = Nationality.values();
        return nationalities[new Random().nextInt(nationalities.length)];
    }

    //TODO
    public static String generateStudentID() {
        return "";
    }

    public static Degree generateDegree() {
        Degree[] degrees = Degree.values();
        return degrees[new Random().nextInt(degrees.length)];
    }

    //TODO
    public static LocalDate generateHireDate() {
        return LocalDate.of(2014 + random.nextInt(5), random.nextInt(12), random.nextInt(30));
    }

    public static Student generateStudent() {
        return new Student(generatePESEL(), generateName(), generateSurname(), generateBirthDate(), generateNationality(), generateStudentID());
    }

    public static Teacher generateTeacher() {
        return new Teacher(generatePESEL(), generateName(), generateSurname(), generateBirthDate(),
                generateNationality(), generateHireDate(), generateDegree());
    }

    public void generateStudents(int studentsTotalNumber) {
        Student student;
        while (students.size() < studentsTotalNumber) {
            student = generateStudent();
            students.add(student);
            people.add(student);
        }
    }

    public void generateTeachers(int teachersTotalNumber) {
        Teacher teacher;
        while (teachers.size() < teachersTotalNumber) {
            teacher = generateTeacher();
            teachers.add(teacher);
            people.add(teacher);
        }
    }

    public StudentsGroup generateStudentsGroup() {
        StudentsGroup studentsGroup = new StudentsGroup("Group" + studentsGroups.size());
        studentsGroups.add(studentsGroup);
        return studentsGroup;
    }

    public Department generateDepartment() {
        Department department = new Department("Department" + departments.size());
        departments.add(department);
        return department;
    }

    public Subject generateSubject() {
        if (departments.size() == 0) generateDepartment();
        if (teachers.size() == 0) generateTeacher();
        if (!departmentsIterator.hasNext()) departmentsIterator = departments.iterator();
        if (!teachersIterator.hasNext()) teachersIterator = teachers.iterator();
        Subject subject = new Subject("Subject" + subjects.size(), departmentsIterator.next(), teachersIterator.next());
        subjects.add(subject);
        return subject;
    }

    public void addStudentToGroup(StudentsGroup studentsGroup) {
        if (students.size() == 0) generateStudent();
        if (!studentsIterator.hasNext()) studentsIterator = students.iterator();
        studentsGroup.add(studentsIterator.next());
    }

    public void fillStudentsGroup(StudentsGroup studentsGroup) {
        while (students.size() < GROUP_SIZE) generateStudent();
        while (studentsGroup.size() < GROUP_SIZE)
            addStudentToGroup(studentsGroup);
        if (extraStudents > 0) {
            addStudentToGroup(studentsGroup);
            extraStudents--;
        }
    }

    public void addTeacherToDepartment(Department department) {
        if (teachers.size() == 0) generateTeacher();
        if (!teachersIterator.hasNext()) teachersIterator = teachers.iterator();
        department.add(teachersIterator.next());
    }

    public void fillDepartment(Department department) {
        while (teachers.size() < DEPARTMENT_SIZE) generateTeacher();
        while (department.size() < DEPARTMENT_SIZE)
            addTeacherToDepartment(department);
        if (extraTeachers > 0) {
            addTeacherToDepartment(department);
            extraTeachers--;
        }
    }

    public void addStudentToSubject(Subject subject) {
        if (students.size() == 0) generateStudent();
        if (!studentsIterator.hasNext()) studentsIterator = students.iterator();
        subject.add(studentsIterator.next());
    }

    public void fillSubject(Subject subject) {
        while (students.size() < STUDENTS_PER_SUBJECT) generateStudent();
        while (subject.size() < STUDENTS_PER_SUBJECT)
            addStudentToSubject(subject);
        if (extraSubjectStudents > 0) {
            addStudentToSubject(subject);
            extraSubjectStudents--;
        }
    }

    public void generateStudentsGroups(int studentsGroupsTotalNumber) {
        while (studentsGroups.size() < studentsGroupsTotalNumber)
            studentsGroups.add(generateStudentsGroup());
    }

    public void generateDepartments(int departmentsTotalNumber) {
        while (departments.size() < departmentsTotalNumber)
            departments.add(generateDepartment());
    }

    public void generateSubjects(int subjectsTotalNumber) {
        while (subjects.size() < subjectsTotalNumber)
            subjects.add(generateSubject());
    }



    private void generateAll() {
        generateStudents(STUDENTS_NUMBER);
        generateTeachers(TEACHER_NUMBER);
        generateStudentsGroups(GROUPS_NUMBER);
        generateDepartments(DEPARTMENTS_NUMBER);
        generateSubjects(SUBJECTS_NUMBER);
    }

    public StudentsGroups getStudentsGroups() {
        return studentsGroups;
    }

    public Departments getDepartments() {
        return departments;
    }

    public Subjects getSubjects() {
        return subjects;
    }

    public People getPeople() {
        return people;
    }

    public Students getStudents() {
        return students;
    }

    public Teachers getTeachers() {
        return teachers;
    }

}
