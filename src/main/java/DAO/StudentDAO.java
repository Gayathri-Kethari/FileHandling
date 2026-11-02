package DAO;

import java.util.List;

import Model.Student;

public interface StudentDAO {
	//Insert the Values
	int insertStudent(Student student);
//All Students
    List<Student> getAllStudents();
    
    //Updates Stduents

    int updateStudent(Student student);
    // deleted student

    int deleteStudent(int id);

}
