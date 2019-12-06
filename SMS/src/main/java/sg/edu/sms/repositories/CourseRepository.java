package sg.edu.sms.repositories;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sg.edu.sms.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
	public ArrayList<Course> findByCourseName(String courseName);

}
