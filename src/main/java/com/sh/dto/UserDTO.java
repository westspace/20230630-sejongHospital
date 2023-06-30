package com.sh.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDTO{
	private int USER_CODE;
	private String REG_DATE;
	private String NAME;
	private String AGENCY;
	private String DEPARTMENT;
	private String EMAIL;
	private String USER_ID;
	private String USER_PW;
	private String ROLE_CODE;
	private String ROLE_NAME;

	private UserDTO userDTO;
	private List<UserDTO> userDTOList;

}
