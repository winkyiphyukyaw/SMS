package sg.edu.sms.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.edu.sms.models.Department;
import sg.edu.sms.models.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer>{
	//public ArrayList<Department> findByStafftName(String department);

}