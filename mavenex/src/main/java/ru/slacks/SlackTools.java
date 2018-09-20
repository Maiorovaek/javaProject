package ru.slacks;
import com.github.seratch.jslack.*;
import com.github.seratch.jslack.api.methods.SlackApiException;
import com.github.seratch.jslack.api.methods.request.channels.ChannelsListRequest;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import com.ullink.slack.simpleslackapi.*;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.impl.ChannelHistoryModuleFactory;
import static java.util.stream.Collectors.toList;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

public class SlackTools {

   String token="xoxp-436849664212-438400903270-438441916787-82d8b29333372b89f43e6b4f50c6aa7e";

   static final Slack slack = Slack.getInstance();
    List<String> channels = slack.methods().channelsList(ChannelsListRequest.builder().token(token).build())
            .getChannels().stream()
            .map(c -> c.getId()).collect(toList());


    public SlackTools() throws IOException, SlackApiException {

    }

    public void getChannels() throws IOException, SlackApiException {
        System.out.println("---------------Channels---------------");
        for (String chan : channels)
            System.out.println(chan);
    }


    public void getMessage() throws IOException  {
        Properties p = new Properties();
        p.put("mail.smtp.host", "smtp.yandex.ru");//протокол передачи сообщений, или smtp.gmail.com
        p.put("mail.smtp.socketFactory.port", 465);
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.auth", true);
        p.put("mail.smtp.port", 465);
        Scanner in = new Scanner(System.in);
                    System.out.print("Enter your e-mail ");
                    String user = in.nextLine();
        System.out.println("Enter your  password");
        String password = in.nextLine();

        Session s = Session.getDefaultInstance(p,
                new javax.mail.Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(user, password);}});

        System.out.print("Enter usernameto ");
        String userto = in.nextLine();



        for(String chan : channels){
            SlackSession sessiont = SlackSessionFactory.createWebSocketSlackSession(token);
            sessiont.connect();
            ChannelHistoryModule channelHistoryModule = ChannelHistoryModuleFactory.createChannelHistoryModule(sessiont);
            List<SlackMessagePosted> messages = channelHistoryModule.fetchHistoryOfChannel(chan)
                    .stream()
                    .collect(toList());
            System.out.println("---------------Messages- " + chan + "--------------");

            for (SlackMessagePosted message : messages) {
                System.out.println("E-mail:" + message.getUser().getUserMail() +  ", message: " + message.getMessageContent() );
                try {
                    Message mess = new MimeMessage(s);
                    mess.setFrom(new InternetAddress(user));

                    mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userto));
                    mess.setSubject(message.getMessageContent().toString());
                    mess.setText(chan);
                    Transport.send(mess);
                    /*              JOptionPane.showMessageDialog(null, "Письмо отправлено" );*/
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Что то пошло не так" + ex);
                }

            }

        }
    }

    public static void main(String[] args) throws IOException, SlackApiException {
        SlackTools sl = new SlackTools();
        sl.getChannels();
        sl.getMessage();
        System.out.println("Send all message!");
    }
}

