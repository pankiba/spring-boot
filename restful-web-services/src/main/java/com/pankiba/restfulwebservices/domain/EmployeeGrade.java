package com.pankiba.restfulwebservices.domain;

public enum EmployeeGrade {

    Developer(1001, "Developer"), Lead(1002, "Lead"), Architect(1003, "Architect");

    private final int levelCode;
    private final String level;

    EmployeeGrade(int levelCode, String level) {
        this.levelCode = levelCode;
        this.level = level;
    }

    public int getLevelCode() {
        return this.levelCode;
    }

    public String getLevel() {
        return level;
    }
}
