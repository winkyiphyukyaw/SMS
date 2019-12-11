package sg.edu.sms.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sg.edu.sms.models.Department;
import sg.edu.sms.models.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer>{
	//public ArrayList<Department> findByStafftName(String department);
	@Query("select f from Staff f where f.id= :staffId")
	public Staff findStaffByid(@Param("staffId") int staffId);
	
}