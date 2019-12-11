package sg.edu.sms.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.edu.sms.models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	//public ArrayList<Department> findByDeptName(String department);

}
