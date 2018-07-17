package com.enrandomlabs.jasensanders.v1.superiorconnect.sendservice;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.enrandomlabs.jasensanders.v1.superiorconnect.MainActivity;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class SendMailService extends IntentService {

    //Error Log Tags
    private static final String CLASS_NAME = SendMailService.class.getSimpleName();
    private static final String ESTIMATE_ERROR = CLASS_NAME + "_Estimate_Error";
    private static final String NOTE_ERROR = CLASS_NAME + "_Note_Error";
    private static final String E_WASTE_ERROR = CLASS_NAME + "_E_Waste_Error";

    //Action Flag Constants
    private static final String ACTION_NOTE = "com.enrandomlabs.jasensanders.v1.superiorconnect.SendService.action.NOTE";
    private static final String ACTION_ESTIMATE = "com.enrandomlabs.jasensanders.v1.superiorconnect.SendService.action.REQUEST_ESTIMATE";
    private static final String ACTION_E_WASTE = "com.enrandomlabs.jasensanders.v1.superiorconnect.SendService.action.REQUEST_E_WASTE";

    //Data Keys
    private static final String EXTRA_NAME = "com.enrandomlabs.jasensanders.v1.superiorconnect.SendService.extra.NAME";
    private static final String EXTRA_DETAILS = "com.enrandomlabs.jasensanders.v1.superiorconnect.SendService.extra.DETAILS";

    //Response Strings
    private static final String RESULT_OK = "OK";
    private static final String RESULT_ERROR = "ERROR";
    private static final String RESPONSE_TYPE_NOTE = "NOTE";
    private static final String RESPONSE_TYPE_ESTIMATE = "ESTIMATE";
    private static final String RESPONSE_TYPE_E_WASTE = "E_WASTE";
    private static final String FLAG_SENT = "SENT";


    public SendMailService() {
        super("SendMailService");
    }

    /**
     * Starts this service to perform action Send Note with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */

    public static void startActionSendNote(Context context, String name, String[] details) {
        Intent intent = new Intent(context, SendMailService.class);
        intent.setAction(ACTION_NOTE);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_DETAILS, details);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Request Estimate with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */

    public static void startActionRequestEstimate(Context context, String name, String[] details) {
        Intent intent = new Intent(context, SendMailService.class);
        intent.setAction(ACTION_ESTIMATE);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_DETAILS, details);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Request E-waste Removal with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */

    public static void startActionRequestEwasteRemoval(Context context, String name, String[] details) {
        Intent intent = new Intent(context, SendMailService.class);
        intent.setAction(ACTION_E_WASTE);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_DETAILS, details);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String name = intent.getStringExtra(EXTRA_NAME);
            final String[] details = intent.getStringArrayExtra(EXTRA_DETAILS);
            final String action = intent.getAction();
            
            if (ACTION_NOTE.equals(action)) {
                handleActionNote(name, details);
            } else if (ACTION_ESTIMATE.equals(action)) {
                handleActionEstimate(name, details);
            }else if (ACTION_E_WASTE.equals(action)) {
                handleActionEwaste(name, details);
            }
        }
    }

    /**
     * Handle action Send Note in the provided background thread with the provided
     * parameters.
     */
    //Details[] format: [contactRequested: yes or No, Email Address, Telephone Number, Fax Number, Message]
    private void handleActionNote(String name, String[] details) {

        //Assemble message and send
        final String body = assembleMessage(name, MailContract.NOTE_ANNOUNCEMENT, details);
        try {
            MailSender sender = new MailSender(MailContract.USERNAME, MailContract.PASSWORD);
            sender.sendMail(MailContract.NOTE_SUBJECT,
                    body,
                    MailContract.USERNAME,
                    MailContract.NOTE_EMAIL_ACCNT);
            String[] response = new String[]{RESULT_OK, RESPONSE_TYPE_NOTE, FLAG_SENT};
            sendResponseBack(response);
        } catch (Exception e) {
            Log.e(NOTE_ERROR, e.getMessage(), e);
            String[] errorResponse = new String[]{RESULT_ERROR, RESPONSE_TYPE_NOTE, e.getMessage()};
            sendResponseBack(errorResponse);
        }

    }

    /**
     * Handle action Send estimate Request in the provided background thread with the provided
     * parameters.
     */
    private void handleActionEstimate(String name, String[] details) {

        //Assemble message and send
        final String body = assembleMessage(name, MailContract.ESTIMATE_ANNOUNCEMENT, details);
        try {
            MailSender sender = new MailSender(MailContract.USERNAME, MailContract.PASSWORD);
            sender.sendMail(MailContract.ESTIMATE_SUBJECT,
                    body,
                    MailContract.USERNAME,
                    MailContract.ESTIMATE_EMAIL_ACCNT);
            String[] response = new String[]{RESULT_OK, RESPONSE_TYPE_ESTIMATE, FLAG_SENT};
            sendResponseBack(response);
        } catch (Exception e) {
            Log.e(ESTIMATE_ERROR, e.getMessage(), e);
            String[] errorResponse = new String[]{RESULT_ERROR, RESPONSE_TYPE_ESTIMATE, e.getMessage()};
            sendResponseBack(errorResponse);
        }
    }

    /**
     * Handle action Send E-waste Request in the provided background thread with the provided
     * parameters.
     */
    private void handleActionEwaste(String name, String[] details) {

        //Assemble message and send
        final String body = assembleMessage(name, MailContract.E_WASTE_ANNOUNCEMENT, details);
        try {
            MailSender sender = new MailSender(MailContract.USERNAME, MailContract.PASSWORD);
            sender.sendMail(MailContract.EWASTE_SUBJECT,
                    body,
                    MailContract.USERNAME,
                    MailContract.E_WASTE_EMAIL_ACCNT);
            String[] response = new String[]{RESULT_OK, RESPONSE_TYPE_E_WASTE, FLAG_SENT};
            sendResponseBack(response);
        } catch (Exception e) {
            Log.e(E_WASTE_ERROR, e.getMessage(), e);
            String[] errorResponse = new String[]{RESULT_ERROR, RESPONSE_TYPE_E_WASTE, e.getMessage()};
            sendResponseBack(errorResponse);
        }
    }

    //String Response format:  ["OK or ERROR","Type: NOTE, E_WASTE, or ESTIMATE", "Error Message" or "SENT"]
    private void sendResponseBack(String[] resultsData){
        Intent messageIntent = new Intent(MainActivity.SERVICE_EVENT_SEND);
        messageIntent.putExtra(MainActivity.SERVICE_EXTRA_RESPONSE, resultsData);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(messageIntent);
    }

    private String assembleMessage(String name, String announcement, String[] form_data){
        String message = name + announcement + "\n" + "From Office: " + form_data[1] + "\n"
                + "Contact Requested: " + form_data[5] + "\n" + "Email: " + form_data[2] + "\n"
                + "Telephone: " + form_data[3] + "\n" + "Fax: " + form_data[6] + "\n"
                + "Message: " + "\n\n" + form_data[4];

        return message;
    }
}
