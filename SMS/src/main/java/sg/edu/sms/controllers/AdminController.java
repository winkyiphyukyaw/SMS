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
import sg.edu.sms.models.Leavedetails;
import sg.edu.sms.models.Staff;
import sg.edu.sms.models.Student;
import sg.edu.sms.repositories.CourseRepository;
import sg.edu.sms.repositories.DepartmentRepository;
import sg.edu.sms.repositories.LeaveRepository;
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
	private LeaveRepository leavepo;

	public AdminController(StudentRepository sturepo, CourseRepository prepo, DepartmentRepository deptpo,
			StaffRepository staffpo, LeaveRepository leavepo) {
		this.sturepo = sturepo;
		this.prepo = prepo;
		this.deptpo = deptpo;
		this.staffpo = staffpo;
		this.leavepo = leavepo;
	}

	@GetMapping("/AdminHome")
	public String adminHome() {
		return "AdminHome";
	}

	@GetMapping("/listDepartment")
	public String showDepartments(Model model) {
		ArrayList<Department> dlist = new ArrayList<Department>();
		dlist.addAll(deptpo.findAll());
		model.addAttribute("department", dlist);
		return "department";

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
		model.addAttribute("department", department);
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

	@GetMapping("/editLeave12/{id}")
	public String editLeave(Model model, @PathVariable("id") Integer id) {
		Leavedetails leavetails = leavepo.findById(id).get();
		leavepo.delete(leavetails);
		model.addAttribute("leavetails", leavetails);
		return "leaveform2";

	}

	@GetMapping("/deleteLeave2/{id}")
	public String deleteLeave(Model model, @PathVariable("id") Integer id) {
		Leavedetails leavetails = leavepo.findById(id).get();

		leavepo.delete(leavetails);
		return "redirect:/Admin/listLeave2";
	}

}
