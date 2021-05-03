package nationwide.advisory.ria.vul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//public class DemoApplication implements CommandLineRunner{
public class DemoApplication {
	
    /*@Autowired
	private EmployeeService employeeService;*/
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	/*@Override
	public void run(String... args) throws Exception {
		Employee e = new Employee();
		e.setId(665668);
		e.setName("Dipayan");
		e.setEmail("dipayan.biet@gmail.com");
		e.setAddress("Krishnanagar-Birati");
		e.setTelephone("9831800969");
		employeeService.addEmploee(e);
	}*/

}
