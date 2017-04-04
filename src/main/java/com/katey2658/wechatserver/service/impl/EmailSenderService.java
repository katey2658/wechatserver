package com.katey2658.wechatserver.service.impl;

/**
 * Created by 11456 on 2017/4/4.
 */

import com.katey2658.wechatserver.config.service.SpringMailConfig;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.xml.soap.MimeHeader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;


/**
 * 邮件发送服务
 */
@Service
public class EmailSenderService {

    private static final String EMAIL_TEXT_TEMPLATE_NAME="text/email-text";
    private static final String EMAIL_SIMPLE_TEMPLATE_NAME="html/email-simple";
    private static final String EMAIL_WITHATTACHMENT_TEMPLATE_NAME="html/email-withattachment";
    private static final String EMAIL_INLINEIMAGE_TEMPLATE_NAME="html/email-inlineimage";
    private static final String EMAIL_EDITABLE_TEMPLATE_CLASSAPTH_RS="classpath:mail/editablehtml/email-editable.html";

    private static final String BACKGROUND_IMAGE="mail/editablehtml/images/background.png";
    private static final String LOGO_BACKGROUND_IMAGE="mail/editablehtml/images/logo-background.png";
    private static final String THYMELEAF_BANNER_IMAGE="mail/editablehtml/images/thymeleaf-banner.png";
    private static final String THYMELEAF_LOGO_IMAGE="mail/editablehtml/images/thymeleaf-logo.png";

    private static final String PNG_MIME="image/png";

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine htmlTemplateEngine;

    @Autowired
    private TemplateEngine textTemplateEngine;

    @Autowired
    private TemplateEngine stringTemplateEngine;

    /**
     * send plain  TEXT mail
     * @param recipientName 收件人
     * @param recipientEmail 内容
     * @param locale 国际化
     */
    public void sendTextMail(final String recipientName, final String recipientEmail, final Locale locale) throws MessagingException {
        final Context ctx=new Context(locale);
        ctx.setVariable("name",recipientName);

        final MimeMessage mimeMessage=this.mailSender.createMimeMessage();
        final MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,"UTF-8");
        helper.setSubject("这是一封简单的邮件");
        helper.setFrom("");
        helper.setTo(recipientEmail);

        final String textContent=this.textTemplateEngine.process(EMAIL_TEXT_TEMPLATE_NAME,ctx);
        helper.setText(textContent);
        this.mailSender.send(mimeMessage);
    }

    /**
     * send HTML mail(simple)
     * @param recipientName
     * @param recipientEmail
     * @param locale
     * @throws MessagingException
     */
    public void sendSimpleMail(final String recipientName,final String recipientEmail,final Locale locale) throws MessagingException {
        final Context ctx=new Context(locale);
        ctx.setVariable("name",recipientName);

        final MimeMessage mimeMessage=this.mailSender.createMimeMessage();
        final MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,"UTF-8");
        helper.setSubject("这是一封邮件(simple)");
        helper.setFrom("");
        helper.setTo(recipientEmail);

        final String htmlContent=this.htmlTemplateEngine.process(EMAIL_SIMPLE_TEMPLATE_NAME, ctx);
        helper.setText(htmlContent);

        this.mailSender.send(mimeMessage);
    }

    /**
     * send a mail with attachment
     * @param recipientName
     * @param recipientEmail
     * @param attachmentFileName
     * @param attachmentBytes
     * @param attachmentContentType
     * @param locale
     * @throws MessagingException
     */
    public void sendMailWithAttachment(final String recipientName,final String recipientEmail,final String attachmentFileName,
                                       final byte[] attachmentBytes,final String attachmentContentType,final Locale locale) throws MessagingException {
        final Context ctx=new Context(locale);
        ctx.setVariable("name",recipientName);

        final MimeMessage mimeMessage=this.mailSender.createMimeMessage();
        final MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true,"utf-8");
        helper.setSubject("这是一封富文本信息邮件");
        helper.setFrom("");
        helper.setTo(recipientEmail);

        final String htmlContent=this.htmlTemplateEngine.process(EMAIL_WITHATTACHMENT_TEMPLATE_NAME,ctx);
        helper.setText(htmlContent);

        final InputStreamSource attachmentSource=new ByteArrayResource(attachmentBytes);
        helper.addAttachment(attachmentFileName,attachmentSource,attachmentContentType);

        this.mailSender.send(mimeMessage);
    }

    /**
     * send  HTML mail with inline image
     * @param recipientName
     * @param recipientEmail
     * @param imageResourceName
     * @param imageBytes
     * @param imageCotnentType
     * @param locale
     * @throws MessagingException
     */
    public void sendMailWithInline(final String recipientName, final String recipientEmail, final String imageResourceName,
                                   final byte[] imageBytes, final String imageCotnentType, final Locale locale) throws MessagingException {
        final Context ctx=new Context(locale);
        ctx.setVariable("name",recipientName);
        ctx.setVariable("imageResourceName",imageResourceName);

        final MimeMessage mimeMessage=this.mailSender.createMimeMessage();
        final MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true,"UTF-8");
        helper.setSubject("这是一封富文本信息邮件（inline)");
        helper.setFrom("");
        helper.setText(recipientEmail);

        final String htmlContent=this.htmlTemplateEngine.process(EMAIL_INLINEIMAGE_TEMPLATE_NAME,ctx);
        helper.setText(htmlContent,true);

        this.mailSender.send(mimeMessage);
    }

    /**
     * Send HTML mail with inline image
     * @return
     * @throws IOException
     */
    public String getEditableMailTemplate() throws IOException {
        final Resource templateResource=this.applicationContext.getResource(EMAIL_EDITABLE_TEMPLATE_CLASSAPTH_RS);
        final InputStream inputStream=templateResource.getInputStream();
        return IOUtils.toString(inputStream, SpringMailConfig.EMAIL_TEMPLATE_ENCODING);
    }

    /**
     * Send HTML mail with  inline image
     * @param recipientName
     * @param recipientEmail
     * @param htmlContent
     * @param locale
     * @throws MessagingException
     */
    public void sendEditableMail(final String recipientName,final String recipientEmail,final String htmlContent,final Locale locale) throws MessagingException {
        final MimeMessage mimeMessage=this.mailSender.createMimeMessage();
        final MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true,"UTF-8");
        helper.setFrom("");
        helper.setTo(recipientEmail);

        final Context ctx=new Context(locale);
        ctx.setVariable("name",recipientName);

        final String output=stringTemplateEngine.process(htmlContent,ctx);
        helper.setText(output,true);

        helper.addInline("background",new ClassPathResource(BACKGROUND_IMAGE),PNG_MIME);
        helper.addInline("logo-background",new ClassPathResource(LOGO_BACKGROUND_IMAGE),PNG_MIME);
        helper.addInline("thymeleaf-banner",new ClassPathResource(THYMELEAF_BANNER_IMAGE),PNG_MIME);
        helper.addInline("thymeleaf-logo",new ClassPathResource(THYMELEAF_LOGO_IMAGE),PNG_MIME);

        this.mailSender.send(mimeMessage);
    }
}
