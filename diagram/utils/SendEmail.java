package utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Třída {@code SendEmail} poskytuje funkce pro odesílání emailů s přílohami
 * pomocí SMTP serveru Seznam.cz. Zahrnuje metody pro ověření emailových adres a
 * odeslání emailu s konkrétní přílohou příjemci.
 */
public class SendEmail {

    /**
     * Uživatelské jméno pro emailový účet.
     */
    final protected static String username = "javaSemestralProject@seznam.cz";

    /**
     * Heslo pro emailový účet.
     */
    final protected static String password = "A123456";

    /**
     * Emailová adresa, ze které bude email odeslán.
     */
    final protected static String fromEmail = "javaSemestralProject@seznam.cz";

    /**
     * Odesílá email s danou přílohou na specifikovanou emailovou adresu.
     *
     * @param attachedFile soubor, který má být připojen k emailu
     * @param toEmail emailová adresa příjemce
     * @param tournamentName název turnaje, který bude zahrnut v předmětu a těle
     * emailu
     * @return {@code true}, pokud byl email úspěšně odeslán; {@code false},
     * pokud došlo k chybě
     */
    public static boolean sendEmail(File attachedFile, String toEmail, String tournamentName) {
        if (isEmailValid(toEmail)) {
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.host", "smtp.seznam.cz");
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");

            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            MimeMessage msg = new MimeMessage(session);
            try {
                msg.setFrom(new InternetAddress(fromEmail));
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
                msg.setSubject("Soupiska na turnaj " + tournamentName + " klubu TJ AŠ Mladá Boleslav");

                Multipart emailContent = new MimeMultipart();

                MimeBodyPart textBodyPart = new MimeBodyPart();
                textBodyPart.setText("Dobrý den, zasílám vám soupisku na turnaj " + tournamentName + " za šachový klub TJ AŠ Mladá Boleslav."
                        + "\nTento email je automaticky generovaný, neodpovídejte na něj.");

                MimeBodyPart attachment = new MimeBodyPart();
                attachment.attachFile(attachedFile);

                emailContent.addBodyPart(textBodyPart);
                emailContent.addBodyPart(attachment);

                msg.setContent(emailContent);
                Transport.send(msg);
            } catch (MessagingException | IOException e) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Ověřuje formát emailové adresy.
     *
     * @param toEmail emailová adresa, která má být ověřena
     * @return {@code true}, pokud je emailová adresa platná; {@code false},
     * pokud je neplatná
     */
    public static boolean isEmailValid(String toEmail) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        if (toEmail == null) {
            return false;
        }
        return pattern.matcher(toEmail).matches();
    }
}
