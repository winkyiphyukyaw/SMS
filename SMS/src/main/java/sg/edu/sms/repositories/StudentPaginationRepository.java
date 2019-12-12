package sg.edu.sms.repositories;

//Repository for pagination


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sg.edu.sms.models.Student;

@Repository
public interface StudentPaginationRepository extends PagingAndSortingRepository<Student, Integer> {

}
