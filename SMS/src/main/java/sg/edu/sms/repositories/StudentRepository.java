package sg.edu.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.sms.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	//@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE Student s SET s.gpa = :gpa WHERE s.id = :sid")
	int updateGpa(@Param("sid") int id, @Param("gpa") double gpa);
}