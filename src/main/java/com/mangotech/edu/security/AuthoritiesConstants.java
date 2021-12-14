package com.mangotech.edu.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String MARKETING = "ROLE_MARKETING";

    public static final String FINANCE = "ROLE_FINANCE";

    public static final String TEACHER_MANAGER = "ROLE_TEACHER_MANAGER";

    public static final String USER = "ROLE_USER";

    public static final String ASSISTANT = "ROLE_ASSISTANT";

    public static final String TEACHER = "ROLE_TEACHER";

    private AuthoritiesConstants() {
    }
}
