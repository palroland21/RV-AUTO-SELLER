package com.rv_auto_seller.dto.request;

import com.rv_auto_seller.model.enums.Role;

public class AdminRoleUpdateRequest {
    private Role role;

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
