
package com.example.springpractice;
import com.example.springpractice.accessingdatajpa.Customer;
import com.example.springpractice.accessingdatajpa.CustomerRepository;
import com.example.springpractice.consumingrestfulapi.Quote;
import com.example.springpractice.crudwithvaadin.Student;
import com.example.springpractice.crudwithvaadin.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MyApplication {
	private static final Logger log = LoggerFactory.getLogger(MyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(StudentRepository repository) {
		return (args) -> {
			// save a couple of students
			repository.save(new Student("Jack", "Bauer"));
			repository.save(new Student("Chloe", "O'Brian"));
			repository.save(new Student("Kim", "Bauer"));
			repository.save(new Student("David", "Palmer"));
			repository.save(new Student("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Student student : repository.findAll()) {
				log.info(student.toString());
			}
			log.info("");

			// fetch an individual student by ID
			Student student = repository.findById(1L).get();
			log.info("Student found with findOne(1L):");
			log.info("--------------------------------");
			log.info(student.toString());
			log.info("");

			// fetch students by last name
			log.info("Student found with findByLastNameStartsWithIgnoreCase('Bauer'):");
			log.info("--------------------------------------------");
			for (Student bauer : repository
					.findByLastNameStartsWithIgnoreCase("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");
		};
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			//  log.info(bauer.toString());
			// }
			log.info("");
		};
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) {
		return args -> {
			Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random",
					Quote.class);
			log.info(quote.toString());
		};
	}
}
            