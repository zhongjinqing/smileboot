package cn.jqzhong.ten.controller;

import cn.jqzhong.ten.service.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Set;

/**
 * @author zjq
 * @date 2020/2/4 15:23
 */
@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @RequestMapping("/mail-simple")
    public void sendSimpleMail(String to, String content, String subject){
        mailService.sendSimpleMail(to, subject, content);
        content = "<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail(to,subject,content);
    }
    @RequestMapping("/mail-html")
    public void sendHtmlMail(String to, String content, String subject){
        content = "<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail(to,subject,content);
    }
    @RequestMapping("/mail-all")
    public void sendMail(String to, String subject){
        HashMap<String, String> imgs = new HashMap<>();
        imgs.put("one", "one.jpg");
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>这是有图片的邮件：");
        Set<String> keys = imgs.keySet();
        for (String key : keys) {
            String img = "<img src=\'cid:" + key + "\' >";
            sb.append(img);
        }

        sb.append("</body></html>");
        mailService.sendMail(to,subject,sb.toString(),"G:\\wacoder\\boot\\src\\main\\resources\\one.jpg",imgs);
    }
}
