package ru.zinnur.service.forms;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserForm {
    private String name;
    private String login;
    private String password;
    private String birthDate;
}