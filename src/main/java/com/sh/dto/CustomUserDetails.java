package com.sh.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1l;

	private ArrayList<UserDTO> userDTOs;
	private UserDTO userDTO;

	public CustomUserDetails(ArrayList<UserDTO> userDTOs) {

		this.userDTOs = userDTOs;
		this.userDTO = userDTOs.get(0);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (UserDTO user : userDTOs) {
			//System.out.println("UserDTO : " + user.getROLE_NAME());
			authorities.add(new SimpleGrantedAuthority(user.getROLE_NAME()));
		}

		return authorities;
	}

	public ArrayList<String> getUserRoles() {
		ArrayList<String> roles = new ArrayList<String>();

		for (UserDTO user : userDTOs) {
			roles.add(user.getROLE_NAME());
		}
		return roles;
	}

	public boolean isRoleChecked(String role) {

		for (UserDTO user : userDTOs) {
			if (user.getROLE_NAME().equals(role)) {
				return true;
			}
		}
		return false;
	}

	public String getUserCode() {
		return String.valueOf(userDTO.getUSER_CODE());
	}

	public String getUserRegDate() {
		return userDTO.getREG_DATE();
	}

	public String getName() {
		return userDTO.getNAME();
	}

	@Override
	public String getUsername() {
		return userDTO.getUSER_ID();
	}

	@Override
	public String getPassword() {
		return userDTO.getUSER_PW();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "UserDetailsDTO [userDTO=" + userDTO.toString() + "]";
	}

}