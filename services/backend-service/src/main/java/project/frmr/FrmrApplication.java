package project.frmr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class FrmrApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrmrApplication.class, args);
		System.out.println("Test for commit!!");
	}

}
