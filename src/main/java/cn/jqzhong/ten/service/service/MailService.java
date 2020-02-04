package cn.jqzhong.ten.service.service;

import javax.mail.MessagingException;
import java.util.Map;

/**
 * @author zjq
 * @date 2020/2/4 15:09
 */
public interface MailService {
    /**
     * send simple mail
     * @param to receiver
     * @param subject subject
     * @param content text content
     */
    public  void sendSimpleMail(String to, String subject, String content) ;

    /**
     * send html mail
     * @param to receiver
     * @param subject subject
     * @param content text content
     */
    public  void sendHtmlMail(String to, String subject, String content) ;

    /**
     * send html、Attachments、InlineResource mail
     * @param to
     * @param subject
     * @param content
     */
    public  void sendMail(String to, String subject, String content, String filePath, Map<String,String> imgs) ;
}
