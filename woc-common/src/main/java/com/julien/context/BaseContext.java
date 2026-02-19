package com.julien.context;

public class BaseContext {

    private static ThreadLocal<String> userIdHolder = new ThreadLocal<>();
    private static ThreadLocal<Integer> roleHolder = new ThreadLocal<>();

    private static ThreadLocal<Integer> comIdHolder = new ThreadLocal<>();
    
    public static void setCurrentId(String id) {
        userIdHolder.set(id);
    }

    public static String getCurrentId() {
        return userIdHolder.get();
    }

    public static void removeCurrentId() {
        userIdHolder.remove();
    }

    public static void setCurrentRole(Integer role) {
        roleHolder.set(role);
    }

    public static Integer getCurrentRole() {
        return roleHolder.get();
    }

    public static void removeCurrentRole() {
        roleHolder.remove();
    }

    public static void setCurrentComId(Integer comId) {
        comIdHolder.set(comId);
    }

    public static Integer getCurrentComId() {
        return comIdHolder.get();
    }

    public static void removeCurrentComId() {
        comIdHolder.remove();
    }
}

