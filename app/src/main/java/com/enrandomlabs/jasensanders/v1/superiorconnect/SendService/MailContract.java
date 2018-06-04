package com.enrandomlabs.jasensanders.v1.superiorconnect.SendService;

import com.enrandomlabs.jasensanders.v1.superiorconnect.BuildConfig;

public class MailContract {


    //Server login credentials
    public static final String SEND_MAIL_SERVER = BuildConfig.SEND_MAIL_SERVER;
    public static final String USERNAME = BuildConfig.SEND_MAIL_USERNAME;
    public static final String PASSWORD = BuildConfig.SEND_MAIL_PW;

    //Service Email Addresses
    public static final String NOTE_EMAIL_ACCNT = BuildConfig.SEND_NOTE;
    public static final String ESTIMATE_EMAIL_ACCNT = BuildConfig.SEND_ESTIMATE;
    public static final String E_WASTE_EMAIL_ACCNT = BuildConfig.SEND_EWASTE;

    //Service Email Subjects
    public static final String NOTE_SUBJECT = "New Note for the Janitors!";
    public static final String EWASTE_SUBJECT = "New Request for E-waste Removal!";
    public static final String ESTIMATE_SUBJECT = "New Request for Estimate!";

    //Service Email Announcements
    public static final String NOTE_ANNOUNCEMENT = " has sent a NOTE to the Janitors.";
    public static final String ESTIMATE_ANNOUNCEMENT = " has requested an Estimate.";
    public static final String E_WASTE_ANNOUNCEMENT = " has requested E-Waste removal.";


}
