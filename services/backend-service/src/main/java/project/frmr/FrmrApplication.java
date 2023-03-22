package project.frmr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.frmr.exceptions.BusinessException;
import project.frmr.exceptions.ProblemDetails;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@RestControllerAdvice
public class FrmrApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrmrApplication.class, args);
		System.out.println("Test for commit!!");
	}


	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinessException(BusinessException businessException) {
		ProblemDetails problemDetails = new ProblemDetails();
		problemDetails.setMessage(businessException.getMessage());
		return problemDetails;
	}

}
