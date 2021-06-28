package nationwide.advisory.ria.vul.controller;

import nationwide.advisory.ria.vul.dbservice.EmployeeService;
import nationwide.advisory.ria.vul.model.Employee;
import nationwide.advisory.ria.vul.model.EmployeeList;
import nationwide.advisory.ria.vul.model.ResponseStatus;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/employee") 
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public String hello() {
		return "Application is up and running";
	}
	
	@PostMapping("/add")
	public ResponseStatus addEmployee(@RequestBody Employee employee) {
		System.out.print("add");
		ResponseStatus response = employeeService.addEmploee(employee);
		return response;
	}
	
	@PostMapping("/update")
	public ResponseStatus updateEmployee(@RequestBody Employee employee) {
		ResponseStatus response = employeeService.updateEmploee(employee);
		return response;
	}
	
	@PostMapping("/delete")
	public ResponseStatus deleteEmploee(@RequestBody Employee employee) {
		ResponseStatus response = employeeService.deleteEmploee(employee);
		return response;
	}
	
	@PostMapping("/list")
	public EmployeeList getEmployeeListByIds(@RequestBody EmployeeList employeeList) {
		EmployeeList employees  = employeeService.getEmploeeListByIds(employeeList);
		return employees;
	}

	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") String id) {
		Employee employee  = employeeService.getEmploeeById(id);
		return employee;
	}
	
	@GetMapping("/all")
	public EmployeeList getAllEmployees() {
		EmployeeList employees  = employeeService.getAllEmploees();
		return employees;
	}
	
}
