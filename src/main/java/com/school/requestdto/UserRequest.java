package com.school.requestdto;

import com.school.enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Builder =>no mapping to UserRequest 
public class UserRequest {
	 @NotNull(message ="username cannot be null")
	 private String userName;
	 @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	 @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must"
				+ " contain at least one letter, one number, one special character")
	 private String password;
	 @NotNull(message ="First name cannot be null")
	 @NotBlank(message= "First name cannot be blank")
	 private String firstName;
	 @NotEmpty(message ="last name cannot be null")
	 private String lastName;
	 private long contactNo;
	 @NotBlank(message="email cannot be blank")
	 @Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	 private String email;
	 private UserRole userRole;
	
}
