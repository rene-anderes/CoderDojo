package org.anderes.edu.kata.logon;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Rule;
import org.junit.Test;

import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.util.ServerSetupTest;

public class SendMailTest {
    
    private final Path sendMailProperties = Paths.get("target/test-classes/configurationForTesting.properties");

    @Rule
    public final GreenMailRule greenMail = new GreenMailRule(ServerSetupTest.ALL);
    
    @Test
    public void shouldBeSendEmail() throws MessagingException, IOException {
       // given
       final SendMail sendmail = new SendMail(sendMailProperties);
       
       // when
       final boolean ok = sendmail.sendMessage("bill.gates@microsoft.com", "don't call us, we call you", "benefit");
       
       // then
       assertThat(ok, is(true));
       final MimeMessage[] messages = greenMail.getReceivedMessages();
       assertThat(messages.length, is(1));
       final Address[] addresses = messages[0].getAllRecipients();
       assertThat(addresses.length, is(1));
       assertThat(addresses[0].toString(), is("bill.gates@microsoft.com"));
       assertThat(messages[0].getContent().toString(), is("don't call us, we call you\r\n"));
       assertThat(messages[0].getSubject(), is("benefit"));
       assertThat(messages[0].getFrom()[0].toString(), is("noreplay@anderes.org"));
    }
}
