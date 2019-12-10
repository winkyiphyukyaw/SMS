package sg.edu.sms.repositories;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.sms.models.Course;
import sg.edu.sms.models.Student;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
	public ArrayList<Course> findByCourseName(String courseName);
	@Transactional
	@Query("SELECT s FROM      Course c JOIN c.student s WHERE c.courseName = :courseName ")
	public ArrayList<Student> findStudentByCourseName(String courseName);

}
