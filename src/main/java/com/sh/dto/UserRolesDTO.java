package com.sh.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserRolesDTO {
	private int ROLE_CODE;
	private String ROLE_NAME;
}
