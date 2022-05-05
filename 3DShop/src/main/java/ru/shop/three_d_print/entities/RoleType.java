package ru.shop.three_d_print.entities;

public enum RoleType
{
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String roleType;

    RoleType(String name) { this.roleType = name; }

    public String get() { return roleType; }
}
