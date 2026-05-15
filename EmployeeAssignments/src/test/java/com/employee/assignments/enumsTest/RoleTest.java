package com.employee.assignments.enumsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.employee.assignments.enums.Role;

class RoleTest {

    @Test
    void testEnumValues() {

        Role[] roles = Role.values();

        assertEquals(3, roles.length);
        assertEquals(Role.DEVELOPER, roles[0]);
        assertEquals(Role.HR, roles[1]);
        assertEquals(Role.PROJECT_MANAGER, roles[2]);
    }

    @Test
    void testValueOf() {

        assertEquals(Role.DEVELOPER, Role.valueOf("DEVELOPER"));
        assertEquals(Role.HR, Role.valueOf("HR"));
        assertEquals(Role.PROJECT_MANAGER, Role.valueOf("PROJECT_MANAGER"));
    }
}