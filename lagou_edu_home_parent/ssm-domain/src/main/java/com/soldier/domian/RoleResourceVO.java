package com.soldier.domian;

import java.util.List;

public class RoleResourceVO {

    private Integer roleId;
    private List<Integer> resourceIdList;

    @Override
    public String toString() {
        return "RoleResourceVO{" +
                "roleId=" + roleId +
                ", resourceIdList=" + resourceIdList +
                '}';
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getResourceIdList() {
        return resourceIdList;
    }

    public void setResourceIdList(List<Integer> resourceIdList) {
        this.resourceIdList = resourceIdList;
    }
}
