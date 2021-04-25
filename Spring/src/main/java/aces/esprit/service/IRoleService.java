package aces.esprit.service;

import java.util.List;

import aces.esprit.entity.Role;

public interface IRoleService {
	void addRole(Role role);
	List<Role> getRoles();
	void deleteRole(Role role);
	void updateRole(Role role);
	
	Role getRoleById(int id);
	Role getRoleByName(String name);
	
}
