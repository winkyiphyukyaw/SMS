package sg.edu.sms.controllers;

import java.util.ArrayList;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.sms.models.Course;
import sg.edu.sms.models.Department;
import sg.edu.sms.models.Staff;
import sg.edu.sms.models.Student;
import sg.edu.sms.repositories.CourseRepository;
import sg.edu.sms.repositories.DepartmentRepository;
import sg.edu.sms.repositories.StaffRepository;
import sg.edu.sms.repositories.StudentRepository;

@Controller
@RequestMapping("/Admin")
public class AdminController {
	@Autowired
	private final StudentRepository sturepo;
	private CourseRepository prepo;
	private DepartmentRepository deptpo;
	private StaffRepository staffpo;
	
	public AdminController(StudentRepository sturepo, CourseRepository prepo, DepartmentRepository deptpo,StaffRepository staffpo) {
		this.sturepo = sturepo;
		this.prepo = prepo;
		this.deptpo=deptpo;
		this.staffpo=staffpo;
	}
	
	
	// Course's CRUD

		@GetMapping("/list")
		public String listAll(Model model) {
			ArrayList<Course> clist = new ArrayList<Course>();
			clist.addAll(prepo.findAll());
			model.addAttribute("courses", clist);
			return "course";
		}

		@GetMapping("/addCourse")
		public String showAddForm(Model model) {
			Course course = new Course();
			model.addAttribute("course", course);
			return "courseform";
		}

		@GetMapping("/save")
		public String saveCourse(@Valid @ModelAttribute Course course, BindingResult bindingResult) {
			if (bindingResult.hasErrors()) {
				return "courseform";
			}
			prepo.save(course);
			return "redirect:/Admin/list";
		}

		@GetMapping("/editCourse/{id}")
		public String showEditForm(Model model, @PathVariable("id") Integer id) {
			Course course = prepo.findById(id).get();
			prepo.delete(course);
			model.addAttribute("course", course);
			return "courseform";
		}

		@GetMapping("/deleteCourse/{id}")
		public String deleteMethod(Model model, @PathVariable("id") Integer id) {
			Course course = prepo.findById(id).get();
			prepo.delete(course);
			return "redirect:/Admin/list";
		}

		@GetMapping("/course/find")
		public String initFindForm(Map<String, Object> model) {
			model.put("courseFind", new Course());
			return "findCourses";
		}

		@GetMapping("/courses")
		public String processFindForm(Course course, BindingResult result, Map<String, Object> model) {

			if (course.getCourseName() == null) {
				course.setCourseName("");
			}

			ArrayList<Course> results = prepo.findByCourseName(course.getCourseName());
			if (results.isEmpty()) {
				result.rejectValue("courseName", "notFound", "not found");
				model.put("courseFind", course);
				return "findCourses";
				// System.out.println("Not found");
			} else if (results.size() == 1) {
				// 1 owner found
				course = results.iterator().next();
				model.put("course", course);
				return "courseDetails";
			} else {

				// System.out.println(course.getCourseName());
				return "redirect:/Admin/list";
			}
		}
	
		@GetMapping("/listDepartment")
		public String showDepartments(Model model) {
			ArrayList<Department> dlist = new ArrayList<Department>();
			dlist.addAll(deptpo.findAll());
			model.addAttribute("departments", dlist);
			return "departments";

		}
	
		@GetMapping("/createDepartment")
		public String createDepartment(Model model) {
			Department department = new Department();
			model.addAttribute("department", department);

			return "departmentform";

		}
		@GetMapping("/saveDepartment")
		public String saveDepartment(@Valid @ModelAttribute Department department, BindingResult bindingResult) {
			if (bindingResult.hasErrors()) {
				return "Depatmentform";
			}
			deptpo.save(department);
			return "forward:/Admin/listDepartment";
		}

		@GetMapping("/editDepartment/{id}")
		public String editDepartment(Model model, @PathVariable("id") Integer id) {
			Department department = deptpo.findById(id).get();
			deptpo.delete(department);
			model.addAttribute("department",department);
			return "departmentform";

		}

		@GetMapping("/deleteDepartment/{id}")
		public String deleteDepartment(Model model, @PathVariable("id") Integer id) {
			Department department = deptpo.findById(id).get();

			deptpo.delete(department);
			return "redirect:/Admin/listdepartment";
		}
	
		@GetMapping("/listStaff")
		public String showStaffs(Model model) {
			ArrayList<Staff> stflist = new ArrayList<Staff>();
			stflist.addAll(staffpo.findAll());
			model.addAttribute("staffs", stflist);
			return "staffs";

		}

		@GetMapping("/createStaff")
		public String createStaff(Model model) {
			Staff staff = new Staff();
			model.addAttribute("staff", staff);

			return "staffform";

		}

		@GetMapping("/saveStaff")
		public String saveStaff(@Valid @ModelAttribute Staff staff, BindingResult bindingResult) {
			if (bindingResult.hasErrors()) {
				return "staffform";
			}
			staffpo.save(staff);
			return "forward:/Admin/listStaff";
		}

		@GetMapping("/editStaff/{id}")
		public String editStaff(Model model, @PathVariable("id") Integer id) {
			Staff staff = staffpo.findById(id).get();
			staffpo.delete(staff);
			model.addAttribute("staff", staff);
			return "staffform";

		}

		@GetMapping("/deleteSaff/{id}")
		public String deleteStaff(Model model, @PathVariable("id") Integer id) {
			Staff staff = staffpo.findById(id).get();

			staffpo.delete(staff);
			return "redirect:/Admin/listStaff";
		}
		
		
		@GetMapping("/listStudent")
		public String showStudents(Model model) {
			ArrayList<Student> slist = new ArrayList<Student>();
			slist.addAll(sturepo.findAll());
			model.addAttribute("students", slist);
			return "students";

		}

		@GetMapping("/createStudent")
		public String createStudent(Model model) {
			Student student = new Student();
			model.addAttribute("student", student);

			return "studentform";

		}

		@GetMapping("/saveStudent")
		public String saveStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult) {
			if (bindingResult.hasErrors()) {
				return "studentform";
			}
			sturepo.save(student);
			return "forward:/faculty/listStudent";
		}

		@GetMapping("/editStudent/{id}")
		public String editStudent(Model model, @PathVariable("id") Integer id) {
			Student student = sturepo.findById(id).get();
			sturepo.delete(student);
			model.addAttribute("student", student);
			return "studentform";

		}

		@GetMapping("/deleteStudent/{id}")
		public String deleteStudent(Model model, @PathVariable("id") Integer id) {
			Student student = sturepo.findById(id).get();

			sturepo.delete(student);
			return "redirect:/faculty/listStudent";
		}
		
		
		
}
