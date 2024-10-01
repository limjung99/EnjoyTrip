package com.enjoytrip.dto.auth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Data
public class Login {
	@NotNull
	private String id;

	@NotNull
	private String password;
}
