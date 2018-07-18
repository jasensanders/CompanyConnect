package com.enrandomlabs.jasensanders.v1.superiorconnect;

import android.util.Log;

import com.enrandomlabs.jasensanders.v1.superiorconnect.sendservice.MailContract;
import com.enrandomlabs.jasensanders.v1.superiorconnect.sendservice.MailSender;

import org.junit.Test;

public class MailSendTest {

    private static final String LOG_TAG = MailSendTest.class.getSimpleName();

    private static final String name = "MailSendTest";
    private static final String[] details = new String[]{"MailSendTest", "OfficeOfTesting", "jasen@superiorjanitorialsystems.com", "(408)377-8720", "Hello Mail Test!!!", "true", "(555)555-7777"};

    private String body = assembleMessage(name, MailContract.E_WASTE_ANNOUNCEMENT, details);

    @Test
    public void mailSender(){
        try {

            MailSender sender = new MailSender(MailContract.USERNAME, MailContract.PASSWORD);
            sender.sendMail(MailContract.EWASTE_SUBJECT,
                    body,
                    MailContract.USERNAME,
                    MailContract.E_WASTE_EMAIL_ACCNT);
        }
        catch (Exception e){
            Log.e(LOG_TAG, "Test Failed: ", e);
        }
    }

    private String assembleMessage(String name, String announcement, String[] form_data){
        String message = name + announcement + "\n" + "From Office: " + form_data[1] + "\n"
                + "Contact Requested: " + form_data[5] + "\n" + "Email: " + form_data[2] + "\n"
                + "Telephone: " + form_data[3] + "\n" + "Fax: " + form_data[6] + "\n"
                + "Message: " + "\n\n" + form_data[4];

        return message;
    }
}
