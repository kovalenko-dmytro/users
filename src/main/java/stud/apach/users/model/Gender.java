package stud.apach.users.model;

import java.util.EnumSet;

public enum Gender {
    Male, Female;

    public static EnumSet getAllGenders() {
        return EnumSet.allOf(Gender.class);
    }
}
