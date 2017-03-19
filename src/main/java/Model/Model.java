package Model;

import Controllers.MD5Coder;

/**
 * Модель данных пользователя
 */
public class Model {
    private int id;
    private final String name;
    private final String surName;
    private final String birthDate;
    private final String email;
    private final String password;

    public Model(int id, String name, String surName, String birthDate, String email, String password){
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
    }

    public Model(String name, String surName, String birthDate, String email, String password){
        this.name = name;
        this.surName = surName;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int   id){this.id = id;}

    public String getSurName() {
        return surName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
