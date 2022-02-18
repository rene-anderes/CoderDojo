package org.anderes.edu.kata.logon;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import static org.anderes.edu.kata.logon.Registration.*;

public class User {
    private String email;
    private String password;
    private String nickname;
    private LocalDate registrationDate;
    private boolean confirmed = false;
    private Optional<Logon> logon = Optional.empty();
    private final String id;
    private LocalDateTime lastModified;
    
    private User() {
        super();
        id = UUID.randomUUID().toString();
    }
    
    public User(String email, String password, Optional<String> optionalNickname) {
        this();
        this.email = email;
        this.password = password;
        this.nickname = optionalNickname.orElse("");
        lastModified = LocalDateTime.now();
    }

    public void setEmail(String email) {
        this.email = email;
        lastModified = LocalDateTime.now();
    }

    public void setPassword(String password) {
        this.password = password;
        lastModified = LocalDateTime.now();
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        lastModified = LocalDateTime.now();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    private void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void isConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
        setRegistrationDate(LocalDate.now());
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setLogon(Logon logon) {
        Validate.notNull(logon);
        validate(logon.getLoginname(), logon.getPassword());
        this.logon = Optional.of(logon);
    }
    
    public Boolean checkLogon(Logon logon) {
        try {
            validate(logon.getLoginname(), logon.getPassword());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private void validate(String loginname, String password) {
        if ((email.equals(loginname) || nickname.equals(loginname)) && this.password.equals(password)) {
            return;
        }
        throw new IllegalArgumentException();
    }

    public Logon getLogon() {
        if (!logon.isPresent()) {
            logon = Optional.of(new Logon(24));
        }
        return logon.get();
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getLastModfied() {
        return lastModified;
    }

    public String generatePassword() {
        password = RandomStringUtils.random(MIN_PASSWORD_LENGHT);
        logon = Optional.empty();
        return password;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
          return false;
        }
        User rhs = (User) obj;
        return new EqualsBuilder().append(id, rhs.id).isEquals();
    }
}
