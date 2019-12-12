package sg.edu.sms.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.edu.sms.models.Leavedetails;

@Repository
public interface LeaveRepository extends JpaRepository<Leavedetails, Integer>{
	//public ArrayList<Leavedetails> findByLeaveName(String leavedetails);

}
