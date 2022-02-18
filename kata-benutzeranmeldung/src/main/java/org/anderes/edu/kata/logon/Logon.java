package org.anderes.edu.kata.logon;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class Logon {

    private String loginname;
    private String password;
    private LocalDateTime loginDateTime;
    private String token;
    private final int validTimeHours;
    private int validTimeHoursForPwdReset;
    private String passwordResetId;

    public Logon(int validTimeHours) {
        this.validTimeHours = validTimeHours;
    }

    public String login(String loginname, String password) {
        this.loginname = loginname;
        this.password = password;
        this.loginDateTime = LocalDateTime.now();
        this.token = UUID.randomUUID().toString();
        return token;
    }

    public String getLoginname() {
        return loginname;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getLoginDateTime() {
        return loginDateTime;
    }

    public String applyPasswordReset(int validTimeHours) {
        this.validTimeHoursForPwdReset = validTimeHours;
        this.loginDateTime = LocalDateTime.now();
        this.passwordResetId = UUID.randomUUID().toString();
        return passwordResetId;
    }

    public Boolean isValid() {
        return loginDateTime.plusHours(validTimeHours).isAfter(LocalDateTime.now());
    }

    public Boolean isPasswordResetValid() {
        return loginDateTime.plusHours(validTimeHoursForPwdReset).isAfter(LocalDateTime.now());
    }

    public Optional<String> getPasswordResetId() {
        return Optional.ofNullable(passwordResetId);
    }

}
