package org.anderes.edu.kata.logon;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.apache.commons.lang3.StringUtils.*;

import java.io.IOException;

import javax.mail.MessagingException;

import org.junit.Test;

public class LogonTest {

    @Test
    public void shouldBeLogin() {
        // given
        final Logon logon = new Logon(24);
        // when
        final String token = logon.login("bill.gates@microsoft.com", "password4bill");
        // then
        assertThat(token, is(not(nullValue())));
        assertThat(logon.getLoginname(), is("bill.gates@microsoft.com"));
        assertThat(logon.getPassword(), is("password4bill"));
        assertThat(logon.getLoginDateTime(), is(not(nullValue())));
        assertThat(logon.isValid(), is(true));
    }
    
    @Test
    public void applyPasswordReset() throws MessagingException, IOException {
        // given
        final Logon logon = new Logon(24);
        // when
        final String passwordResetId = logon.applyPasswordReset(24);
        // then
        assertThat(isNotBlank(passwordResetId), is(true));
        assertThat(logon.getPasswordResetId().isPresent(), is(true));
        assertThat(isNotBlank(logon.getPasswordResetId().get()), is(true));
        assertThat(logon.getPasswordResetId().get().length(), is(36));
        assertThat(logon.isPasswordResetValid(), is(true));
    }
}
