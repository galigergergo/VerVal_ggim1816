package service;

import domain.Grade;
import domain.Homework;
import domain.Pair;
import domain.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class ServiceTest {

    public static Service service;

    @org.junit.jupiter.api.BeforeAll
    public static void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();
        Validator<Grade> gradeValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        HomeworkXMLRepository fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "homework.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(gradeValidator, "grades.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void findAllStudents() {
    }

    @org.junit.jupiter.api.Test
    void findAllHomework() {
    }

    @org.junit.jupiter.api.Test
    void findAllGrades() {
    }

    @org.junit.jupiter.api.Test
    void saveStudent() {
    }

    @org.junit.jupiter.api.Test
    @DisplayName("checking if homework save works")
    void saveValidHomeworkOld() {
        Homework hw = new Homework("77", "some easy homework", 6, 2);
        int result = service.saveHomework(hw.getID(), hw.getDescription(), hw.getDeadline(), hw.getStartline());
        assertEquals(0, result);
        //assertTrue(result == 1);
        service.deleteHomework(hw.getID());
    }

    @org.junit.jupiter.api.Test
    void saveGrade() {
    }

    @org.junit.jupiter.api.Test
    void deleteStudent() {
    }

    @org.junit.jupiter.api.Test
    void deleteHomework() {
    }

    @org.junit.jupiter.api.Test
    void updateStudent() {
    }

    @org.junit.jupiter.api.Test
    void updateHomework() {
    }

    @org.junit.jupiter.api.Test
    void extendDeadline() {
    }

    @org.junit.jupiter.api.Test
    void createStudentFile() {
    }

    @Test
    void assertAllTest(){
        Student s = new Student("99", "Johan", 533);
        assertAll(
                "student",
                () -> assertEquals("99", s.getID()),
                () -> assertEquals("Johan", s.getName())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, 55, 533})
    void testStudentAddByGroup(int group){
        assumeTrue(group >= 0);
        Student s = new Student("99", "Johan", group);
        int result = service.saveStudent(s.getID(), s.getName(), s.getGroup());
        assertEquals(1, result);
        service.deleteStudent(s.getID());
    }

    @Test
    void saveValidStudent(){
        Student s = new Student("999", "Bela", 522);
        int result = service.saveStudent(s.getID(), s.getName(), s.getGroup());
        assertEquals(1, result);
        service.deleteStudent(s.getID());
    }

    @Test
    void deleteSavedStudent(){
        Student s = new Student("333", "Bela", 522);
        service.saveStudent(s.getID(), s.getName(), s.getGroup());
        int result = service.deleteStudent(s.getID());
        assertEquals(1, result);
    }

    @Test
    void updateSavedStudent(){
        Student s = new Student("333", "Bela", 522);
        service.saveStudent(s.getID(), s.getName(), s.getGroup());
        int result = service.updateStudent(s.getID(), "Jani", 523);
        assertEquals(1, result);
    }

    @Test
    void saveValidHomework(){
        Homework hw = new Homework("999", "math homework", 8, 6);
        int result = service.saveHomework(hw.getID(), hw.getDescription(), hw.getDeadline(), hw.getStartline());
        assertEquals(1, result);
        service.deleteHomework(hw.getID());
    }

    @Test
    void deleteSavedHomework(){
        Homework hw = new Homework("333", "math homework", 8, 6);
        service.saveHomework(hw.getID(), hw.getDescription(), hw.getDeadline(), hw.getStartline());
        int result = service.deleteHomework(hw.getID());
        assertEquals(1, result);
    }

    @Test
    void updateSavedHomework(){
        Homework hw = new Homework("333", "math homework", 8, 6);
        service.saveHomework(hw.getID(), hw.getDescription(), hw.getDeadline(), hw.getStartline());
        int result = service.updateHomework(hw.getID(), "inf homework", 7, 5);
        assertEquals(1, result);
    }

    @Test
    void saveValidGrade(){
        String idStudent = "1111";
        String idHw = "2222";

        Student s = new Student(idStudent, "Bela", 522);
        service.saveStudent(s.getID(), s.getName(), s.getGroup());

        Homework hw = new Homework(idHw, "math homework", 8, 6);
        service.saveHomework(hw.getID(), hw.getDescription(), hw.getDeadline(), hw.getStartline());

        int result = service.saveGrade(idStudent, idHw, 6.0, 34, "feedback");
        assertEquals(1, result);

        service.deleteHomework(idHw);
        service.deleteStudent(idStudent);
    }

    @Test
    void saveInvalidGrade(){
        String idStudent = "9999";
        String idHw = "7777";
        int result = service.saveGrade(idStudent, idHw, 6.0, 34, "feedback");
        assertEquals(1, result);
    }
}