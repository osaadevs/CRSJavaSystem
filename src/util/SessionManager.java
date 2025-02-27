package util;

public class SessionManager {
    private static int loggedInStudentId;
    private static String userRole;
    private static int adminId;
    private static String adminName;
    private static String adminRole;

    public static void setStudentId(int studentId) {
        loggedInStudentId = studentId;
    }

    public static int getStudentId() {
        return loggedInStudentId;
    }

    public static void setUserRole(String role) {
        userRole = role;
    }

    public static String getUserRole() {
        return userRole;
    }

    public static void setAdminId(int id) {
        adminId = id;
    }

    public static void setAdminName(String name) {
        adminName = name;
    }

    public static void setAdminRole(String role) {
        adminRole = role;
    }

    public static int getAdminId() {
        return adminId;
    }

    public static String getAdminName() {
        return adminName;
    }

    public static String getAdminRole() {
        return adminRole;
    }

    public static void clearSession() {
        adminId = 0;
        adminName = null;
        adminRole = null;
    }


}
