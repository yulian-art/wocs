package com.julien.context;

public class BaseContext {

    private static ThreadLocal<Long> userIdHolder = new ThreadLocal<>();
    private static ThreadLocal<String> roleHolder = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        userIdHolder.set(id);
    }

    public static Long getCurrentId() {
        return userIdHolder.get();
    }

    public static void removeCurrentId() {
        userIdHolder.remove();
    }

    public static void setCurrentRole(String role) {
        roleHolder.set(role);
    }

    public static String getCurrentRole() {
        return roleHolder.get();
    }

    public static void removeCurrentRole() {
        roleHolder.remove();
    }
}

