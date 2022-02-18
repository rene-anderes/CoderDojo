package org.anderes.edu.kata.logon;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.Validate;

public class Registration {

    public final static int MIN_PASSWORD_LENGHT = 8;
    private LocalDateTime registerDateTime;
    private String registernumber;
    private User user;
    private int validTimeHours;

    public Registration(int validTimeHours) {
        this.validTimeHours = validTimeHours;
    }

    public String register(String email, Optional<String> optionalPassword, Optional<String> optionalNickname) {
        user = new User(email, optionalPassword.orElseGet(() -> RandomStringUtils.random(MIN_PASSWORD_LENGHT)), optionalNickname);
        this.registerDateTime = LocalDateTime.now();
        this.registernumber = UUID.randomUUID().toString();
        return registernumber;
    }

    /*package*/ User getUser() {
        Validate.notNull(user, "Bisher wurde kein Benutzer registriert!");
        return user ;
    }

    public User confirmed(String registernumber) {
        Validate.isTrue(Validate.notNull(registernumber).equals(this.registernumber), "Ungültige Registernumber!");
        user.isConfirmed(true);
        return user;
    }

    public Boolean isValid() {
        return registerDateTime.plusHours(validTimeHours).isAfter(LocalDateTime.now());
    }

    public String getRegisternumber() {
        return registernumber;
    }

}
