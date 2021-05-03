package nationwide.advisory.ria.vul.dbservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import nationwide.advisory.ria.vul.model.Employee;
import nationwide.advisory.ria.vul.model.EmployeeList;
import nationwide.advisory.ria.vul.model.ResponseStatus;

@Repository 
public class EmployeeService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public ResponseStatus addEmploee(Employee employee) {

		ResponseStatus response = new ResponseStatus();

		System.out.println("1st..." +Integer.valueOf(employee.getId())+ " " +employee.getName()+ " " +employee.getEmail()+ " " +employee.getAddress()+ " " + employee.getTelephone());

		Employee existingEmp = getEmploeeById(employee.getId());
		if(existingEmp!=null) {
			response.setStatus("Can not add...");
			response.setMessage(existingEmp.getId() +"-" +existingEmp.getName() +" already exists");
			return response;
		}

		System.out.println("2nd..."+Integer.valueOf(employee.getId())+ " " +employee.getName()+ " " +employee.getEmail()+ " " +employee.getAddress()+ " " + employee.getTelephone());

		String sql = "insert into Employee values (?, ?, ?, ?, ?)";
		int result = jdbcTemplate.update(sql,  Integer.valueOf(employee.getId()), employee.getName(),employee.getEmail(),employee.getAddress(), employee.getTelephone());


		response.setStatus("Failed");
		response.setMessage("Something went wrong");

		if (result == 1) {
			response.setStatus("Success");
			response.setMessage(employee.getId() +"-" +employee.getName() +" added successfully");
		}
		return response;
	}

	public Employee getEmploeeById(String id) {

		String sql = "select * from Employee where id = ?";
		Object[] args = {id};
		//Employee employee = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Employee.class));
		//return employee;
		
		@SuppressWarnings("unchecked")
		Object obj = null;
		
		try {
		obj = (Object)jdbcTemplate.queryForMap(sql,args); 
		} catch(Exception e){
			System.out.println("exception "+e.getMessage());
			return null;
		}
		
		if(obj!=null) {
			
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) obj;
			if(map!=null) {
				Employee employee = new Employee();
				employee.setId(String.valueOf(map.get("id")));
				employee.setName((String)map.get("name"));
				employee.setEmail((String)map.get("email"));
				employee.setAddress((String)map.get("address"));
				employee.setTelephone((String)map.get("telephone"));
				return employee;
			}
		}
		//Map<String, Object> map  = jdbcTemplate.queryForMap(sql,args);
		return null;
	}

	public EmployeeList getEmploeeListByIds(EmployeeList employeeList) {

		String ids = "(";
		for(Employee emp: employeeList.getEmployees()) {
			ids = ids + emp.getId() + "," ;
		}
		ids = ids + ")";
		ids = ids.replace(",)", ")");

		String sql = "select * from Employee where id in "+ids;

		List<Map<String, Object>> mapList  = jdbcTemplate.queryForList(sql);

		List<Employee> employees = new ArrayList<Employee>();
		for(Map<String, Object> map : mapList) {
			Employee emp = new Employee();
			emp.setId(String.valueOf(map.get("id")));
			emp.setName((String)map.get("name"));
			emp.setEmail((String)map.get("email"));
			emp.setAddress((String)map.get("address"));
			emp.setTelephone((String)map.get("telephone"));
			employees.add(emp);
		}
		EmployeeList empList = new EmployeeList();
		empList.setEmployees(employees);

		return empList;
	}

	public EmployeeList getAllEmploees() {

		String sql = "select * from Employee";

		List<Map<String, Object>> mapList  = jdbcTemplate.queryForList(sql);

		List<Employee> employees = new ArrayList<Employee>();
		for(Map<String, Object> map : mapList) {
			Employee emp = new Employee();
			emp.setId(String.valueOf(map.get("id")));
			emp.setName((String)map.get("name"));
			emp.setEmail((String)map.get("email"));
			emp.setAddress((String)map.get("address"));
			emp.setTelephone((String)map.get("telephone"));
			employees.add(emp);
		}
		EmployeeList empList = new EmployeeList();
		empList.setEmployees(employees);

		return empList;
	}

	public ResponseStatus updateEmploee(Employee employee) {

		String sql = "update Employee set email = ? ,address =  ?, telephone =  ? where id = ?";
		int result = jdbcTemplate.update(sql,  employee.getEmail(), employee.getAddress(), employee.getTelephone(), Integer.valueOf(employee.getId()));

		ResponseStatus response = new ResponseStatus();
		response.setStatus("Failed");
		response.setMessage("Something went wrong");

		if (result == 1) {
			response.setStatus("Success");
			response.setMessage(employee.getId() +"-" +employee.getName() +" updated successfully");
		}
		return response;
	}

	public ResponseStatus deleteEmploee(Employee employee) {

		String sql = "delete from Employee where id = ? and name = ? and email = ? and address =  ? and telephone =  ?";
		int result = jdbcTemplate.update(sql,  Integer.valueOf(employee.getId()), employee.getName(), employee.getEmail(), employee.getAddress(), employee.getTelephone());

		ResponseStatus response = new ResponseStatus();
		response.setStatus("Failed");
		response.setMessage("Something went wrong");

		if (result == 1) {
			response.setStatus("Success");
			response.setMessage(employee.getId() +"-" +employee.getName() +" deleted successfully");
		}
		return response;
	}

}
