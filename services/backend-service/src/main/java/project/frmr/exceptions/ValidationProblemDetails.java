package project.frmr.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationProblemDetails extends  ProblemDetails{

    private Map<String,String> validationErrors;

}
