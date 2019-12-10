package sg.edu.sms.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.sms.models.Course;
import sg.edu.sms.models.Student;
import sg.edu.sms.repositories.CourseRepository;
import sg.edu.sms.repositories.StudentRepository;

@Controller
@RequestMapping("/faculty")
public class FacultyController {
	@Autowired
	private final StudentRepository sturepo;
	private CourseRepository prepo;

	public FacultyController(StudentRepository sturepo, CourseRepository prepo) {
		this.sturepo = sturepo;
		this.prepo = prepo;
	}

	// Master List
	@GetMapping("/masterlist")
	public String facultyHome(Model model) {
		ArrayList<Student> slist = new ArrayList<Student>();
		ArrayList<Course> clist = new ArrayList<Course>();
		slist.addAll(sturepo.findAll());
		clist.addAll(prepo.findAll());
		model.addAttribute("courses", clist);
		model.addAttribute("students", slist);
		return "masterList";
	}

	// Student's CRUD

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

	@GetMapping("/score")
	public String scoreCard(Model model) {
		ArrayList<Student> students = new ArrayList<Student>();
		students.addAll(sturepo.findAll());

		model.addAttribute("students", students);
		return "scorecard";

	}

	@GetMapping("/edit/{id}")
	public String editScore(Model model, @PathVariable("id") Integer id) {
		Student student = sturepo.findById(id).get();

		model.addAttribute("student", student);
		return "editform";

	}

	@GetMapping("/saveEdit")
	public String saveEdit(@ModelAttribute Student student) {

		// sturepo.updateGpa(student.getId(), student.getGpa());
		sturepo.delete(student);
		sturepo.save(student);
		return "forward:/faculty/listStudent";
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
	public String saveProduct(@Valid @ModelAttribute Course course, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "courseform";
		}
		prepo.save(course);
		return "redirect:/faculty/list";
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
		return "redirect:/faculty/list";
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
//	      for (Course c : prepo.findByCourseName(course.getCourseName())) {
//				System.out.println("Course info: " + c.toString());
//			}
		ArrayList<Course> results = prepo.findByCourseName(course.getCourseName());
		if (results.isEmpty()) {
			result.rejectValue("courseName", "notFound", "not found");
			model.put("courseFind", course);
			return "findCourses";			
		} else if (results.size()>0) {
			course = results.iterator().next();
			model.put("course", course);
			return "courseDetails";
		} else {
			return "redirect:/faculty/list";
		}
	}

	@GetMapping("/course/findStudentByCourse")
	public String getStudentsByCourseName(Map<String, Object> model) {
		model.put("findStuByCourseName", new Course());
		return "findStusByCourseName";
	}
	
	@GetMapping("/findStudsByCN")
	public String findStudsByCourseName(Course course, BindingResult result, Model model) {

		ArrayList<Student> slist = new ArrayList<Student>();
		if (course.getCourseName() == null) {
			course.setCourseName("");
		}
//	      for (Student s : prepo.findStudentByCourseName(course.getCourseName())) {
//				System.out.println("Student info: " + s.getStudentName());
//			}
		ArrayList<Student> results = prepo.findStudentByCourseName(course.getCourseName());
		if (results.isEmpty()) {
			String s="Course Name does not found";
			model.addAttribute("findStuByCourseName", course);
	            return "findStusByCourseName";
		} else  {
			// found
			
			slist.addAll(prepo.findStudentByCourseName(course.getCourseName()));
			model.addAttribute("studentFindByCourse", slist);
			return "StusByCourseName";
		} 
	}
}
