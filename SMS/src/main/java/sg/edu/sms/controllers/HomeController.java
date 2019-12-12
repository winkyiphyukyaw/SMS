package sg.edu.sms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import sg.edu.sms.models.Staff;
import sg.edu.sms.models.Student;
import sg.edu.sms.models.UserSession;
import sg.edu.sms.repositories.StaffRepository;
import sg.edu.sms.repositories.StudentRepository;


@Controller
@RequestMapping("/sms")
public class HomeController {
	@Autowired
	StudentRepository stdrepo;
	
	@Autowired
	StaffRepository Frepo;
	@GetMapping("/home")
	public String getHome() {
		return "home";
	}	
	
	@GetMapping("/Studentlogin")
	public String StudentLoginPage(Model model) {
		model.addAttribute("student", new Student());
		return "Studentlogin";
	}

	@GetMapping("/logout")
	public String getLogoutPage(SessionStatus status) {
		status.setComplete();
		return "home";
	}

	@PostMapping("/Sauthenticate")
	public String getSAuthentication(@ModelAttribute("student") Student student, BindingResult bindingResult)
	{
		int userId = student.getId();
		String Password = student.getPassword();

		Student s = stdrepo.findStudentByid(userId);
		
		if (s.getPassword().equals(Password)) {
			return "redirect:/student/Home";
		}
		
		else
			return "Studentlogin";
	}
	
	@GetMapping("/Facultylogin")
	public String FacultyLoginPage(Model model) {
		model.addAttribute("staff", new Staff());
		return "Facultylogin";
	}
	
	@PostMapping("/Fauthenticate")
	public String getFAuthentication(@ModelAttribute("staff") Staff staff, BindingResult bindingResult)
	{
		int userId = staff.getId();
		String Password = staff.getPassword();
		
		Staff st = Frepo.findStaffByid(userId);
		if (st.getPassword().equals(Password)) 
		{
			return "redirect:/faculty/masterlist";
		}
		else
			return "Facultylogin";
	}
	
	
	@GetMapping("/Adminlogin")
	public String AdminLoginPage(Model model) {
		model.addAttribute("staff", new Staff());
		return "Adminlogin";
	}
	
	@PostMapping("/Adauthenticate")
	public String getAdAuthentication(@ModelAttribute("staff") Staff staff, BindingResult bindingResult)
	{
		int userId = staff.getId();
		String Password = staff.getPassword();
		
		Staff st = Frepo.findStaffByid(userId);
		if (st.getPassword().equals(Password)) 
		{
			return "redirect:/Admin/AdminHome";
		}
		else
			return "Adminlogin";
	}
	
}
