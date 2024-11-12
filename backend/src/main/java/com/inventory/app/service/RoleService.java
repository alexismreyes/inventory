package com.inventory.app.service;
import com.inventory.app.entity.Role;
import java.util.List;

public interface RoleService {
   Role createRole(Role role);
   Role getRoleById(Long id);
   Role updateRole(Long id, Role updatedRole);
   void deleteRole(Long id);
   List<Role> getAllRoles();
}
