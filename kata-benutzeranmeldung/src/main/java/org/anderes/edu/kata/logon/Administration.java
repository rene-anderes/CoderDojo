package org.anderes.edu.kata.logon;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.Validate;

public class Administration {

    private Map<String, Registration> registrations = new HashMap<>();
    private Map<String, User> users = new HashMap<>();

    public boolean addRegistration(Registration registration) {
        Validate.notNull(registration, "Der Parameter 'registration' darf nicht null sein.");
        return registrations.put(registration.getRegisternumber(), registration) == null;
    }

    public boolean confirmRegistration(String registernumber) {
        Validate.notNull(registernumber, "Der Parameter 'registernumber' darf nicht null sein.");
        if (registrations.containsKey(registernumber)) {
            final Registration registration = registrations.get(registernumber);
            if (!registration.isValid()) {
                registrations.remove(registernumber);
                return false;
            }
            final User user = registration.confirmed(registernumber);
            users.put(user.getId(), user);
            registrations.remove(registernumber);
            return true;
        }
        return false;
    }

    public Optional<User> getUserByLogon(Logon logon) {
        Validate.notNull(logon, "Der Parameter 'logon' darf nicht null sein.");
        return users.values().stream().filter(u -> u.checkLogon(logon)).findFirst();
    }

    public Optional<User> getUserByEmail(String email) {
        Validate.notBlank(email, "Der Parameter 'email' darf nicht null sein.");
        return users.values().stream().filter(u -> u.getEmail().equals(email)).findAny();
    }

    public Optional<User> getUserByPasswordResetId(String passwordResetId) {
        Validate.notBlank(passwordResetId, "Der Parameter 'passwordResetId' darf nicht null sein.");
        return users.values().stream()
                        .filter(u -> u.getLogon().getPasswordResetId().isPresent())
                        .filter(u -> u.getLogon().getPasswordResetId().get().equals(passwordResetId))
                        .findAny();
    }
}
