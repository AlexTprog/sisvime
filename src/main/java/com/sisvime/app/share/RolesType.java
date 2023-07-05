package com.sisvime.app.share;

public enum RolesType {
    ADMIN,
    PACIENTE,
    DOCTOR,
    JEFA_ENFERMERIA;

    public int getIndex() {
        return ordinal() + 1;
    }
}