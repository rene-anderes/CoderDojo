package org.anderes.edu.kata.logon;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;

public class UserTest {

    @Test
    public void shouldBeNewUser() {
        // when
        final User user = new User("bill.gates@microsoft.com", "password4bill", Optional.of("bill"));
        //then
        assertThat(user.getEmail(), is("bill.gates@microsoft.com"));
        assertThat(user.getPassword(), is("password4bill"));
        assertThat(user.getNickname(), is("bill"));
        assertThat(user.isConfirmed(), is(false));
        assertThat(isNotBlank(user.getId()), is(true));
        assertThat(user.getLastModfied(), is(not(nullValue())));
    }
    
    @Test
    public void shouldBeConfirm() {
        // given
        final User user = new User("bill.gates@microsoft.com", "password4bill", Optional.of("bill"));
        // when
        user.isConfirmed(true);
        //then
        assertThat(user.getEmail(), is("bill.gates@microsoft.com"));
        assertThat(user.getPassword(), is("password4bill"));
        assertThat(user.getNickname(), is("bill"));
        assertThat(user.isConfirmed(), is(true));
        assertThat(isNotBlank(user.getId()), is(true));
    }
    
    @Test
    public void shouldBeNewLogon() {
        // given
        final User user = new User("bill.gates@microsoft.com", "password4bill", Optional.of("bill"));
        final Logon logon = new Logon(24);
        logon.login("bill.gates@microsoft.com", "password4bill");
        // when
        user.setLogon(logon);
        // then
        assertThat(user.getLogon(), is(not(nullValue())));
        assertThat(user.getLogon(), is(logon));
        assertThat(user.checkLogon(logon), is(true));
    }
    
    @Test
    public void shouldBeNewLogonByNickname() {
        // given
        final User user = new User("bill.gates@microsoft.com", "password4bill", Optional.of("bill"));
        final Logon logon = new Logon(24);
        logon.login("bill", "password4bill");
        // when
        user.setLogon(logon);
        // then
        assertThat(user.getLogon(), is(not(nullValue())));
        assertThat(user.getLogon(), is(logon));
    }
    
    @Test
    public void shouldBeGeneratePassword() {
        // given
        final User user = new User("bill.gates@microsoft.com", "password4bill", Optional.of("bill"));
        final Logon logon = new Logon(24);
        logon.login("bill", "password4bill");
        user.setLogon(logon);
        // when
        final String newPassword = user.generatePassword();
        // then
        assertThat(user.getPassword(), is(newPassword));
        assertThat(user.getLogon(), is(not(nullValue())));
        assertThat(user.getLogon(), is(not((logon))));
    }
}
