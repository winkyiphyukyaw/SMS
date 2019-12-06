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
import sg.edu.sms.repositories.CourseRepository;

@Controller
@RequestMapping("/faculty")
public class FacultyController {


	@Autowired
	private CourseRepository prepo;
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
			//model.addAttribute("product", product);

			return "courseform";
		}
		prepo.save(course);
		return "redirect:/faculty/list";
	}
	
	@GetMapping("/edit/{id}")
	public String showEditForm(Model model, @PathVariable("id") Integer id) {	
		Course course = prepo.findById(id).get();
		prepo.delete(course);
		model.addAttribute("course", course);
		return "courseform";
	}
	@GetMapping("/delete/{id}")
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

	
//	  @GetMapping("/courses")
//	    public String processFindForm(Course course, BindingResult result, Map<String, Object> model) {
//       		
//	        if (course.getCourseName() == null) {
//	            course.setCourseName("");
//	        }
////	      for (Course c : prepo.findByCourseName(course.getCourseName())) {
////				System.out.println("Course info: " + c.toString());
////			}
//	        ArrayList<Course> results = prepo.findByCourseName(course.getCourseName());
//	        if (results.isEmpty()) {
//	            
//	            result.rejectValue("course", "notFound", "not found");
//	            return "findCourses";
//	            // System.out.println("Not found");
//		} /*
//			 * else if (results.size() == 1) { // 1 owner found 
//			 * course = results.iterator().next();
//			 *  return "" + course.getCourseName();
//			 *   }
//			 */ else {
//	            
//	         // System.out.println(course.getCourseName());
//	           return "faculty/list";
//	        }
//	    }

	}
