 public void emailtrigger(Map<String, String> emailDtlsMap, String fromEmail, String fromName)
      throws FilterServicesException, Exception {
    // Get the session object
    Properties properties = new Properties();
    properties.setProperty(AppConstants.SMTP_HOST, AppConstants.EMAIL_HOST);
    Session session = Session.getDefaultInstance(properties);
    for (Map.Entry<String, String> entry : emailDtlsMap.entrySet()) {
      log.info("Email to :  {},", entry.getKey());
      sendEmailUtil(session, fromEmail, entry.getKey(), fromName, entry.getValue());
    }
  }

  private void sendEmailUtil(Session session, String fromEmail, String recipient, String fromName,
      String recepientName) throws FilterServicesException, Exception {
    log.info("sending invite email to : {} from user : {}", recipient, fromEmail);
    log.info("onboarding link : {} emailhelp : {}", appProperties.getEmailOnboardPage(),
        appProperties.getEmailHelpPage());
    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(fromEmail));
    message.setSubject(AppConstants.EMAIL_SUBJECT);
    MimeMultipart multipart = new MimeMultipart(AppConstants.RELATED);
    MimeBodyPart messageBodyPart = new MimeBodyPart();
    String html = AppConstants.EMAIL_HTML_PART_1 + recepientName + AppConstants.EMAIL_HTML_PART_2
        + fromName + AppConstants.EMAIL_HTML_PART_3 + appProperties.getEmailOnboardPage()
        + AppConstants.EMAIL_HTML_PART_4 + appProperties.getEmailHelpPage()
        + AppConstants.EMAIL_HTML_PART_5;
    messageBodyPart.setContent(html, AppConstants.TEXT_HTML);
    multipart.addBodyPart(messageBodyPart);

    Storage storage = StorageOptions.getDefaultInstance().getService();
    byte[] bytes =
        storage.readAllBytes(appProperties.getGcsBucket(), appProperties.getGcsBlobName());
    MimeBodyPart messageBodyPart2 = new MimeBodyPart();
    messageBodyPart2.setDataHandler(new DataHandler(new ByteArrayDataSource(bytes, "image/png")));
    messageBodyPart2.setHeader(AppConstants.CONTENT_ID, AppConstants.IMAGE_HEADER);
    multipart.addBodyPart(messageBodyPart2);

    message.setContent(multipart);
    message.setHeader(AppConstants.X_PRIORITY, AppConstants.ONE);
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
    log.info("executing Transport.send");
    Transport.send(message);
    log.info("Email Sent !");
  }
  
  
  /*  public static final String SMTP_HOST = "mail.smtp.host";
    public static final String TEXT_HTML = "text/html";
  public static final String RELATED = "related";
  public static final String CONTENT_ID = "Content-ID";
    public static final String CHAR_SET = "text/plain; charset=UTF-8";
  public static final String X_PRIORITY = "X-Priority";
  public static final String ONE = "1";
    public static final String EMAIL_HTML_PART_1 = "<style>  img {\n" + "  max-width: 100%;\n"
      + "  height: auto;\n" + "} a:link, a:visited {  background-color: #06F27B;\n"
      + "           color: white; padding: 14px 25px; text-align: left;\n"
      + "          text-decoration: none; display: inline-block;}\n"
      + "          a:hover, a:active {  background-color: #06F27B;}\n" + ".myDiv {\n"
      + "  border: 1px outset black;\n" + "  background-color: white;    \n"
      + "  text-align: left;\n" + "}\n" + "body {" + "width: 60%;" + "margin: auto;" + "}"
      + "          </style>\n"
      + " <div class=\"myDiv\" style=\"width:73.6%\">   <img src=\"cid:image\" > <br> <div talign=\"left\">  <H4> Dear ";
  public static final String EMAIL_HTML_PART_2 = ",</div> </H4> <div talign=\"left\"> <small>";
  public static final String EMAIL_HTML_PART_3 =
      " has invited you to join International Merch Hub. </small></div> <br> <a  href=";
  public static final String EMAIL_HTML_PART_4 =
      " target=\"_blank\">Get Started</a> <br> <div talign=\"left\"> <small> If you have any questions, please contact us <a style=\"color:black;text-align:left;background-color:white\" href=";
  public static final String EMAIL_HTML_PART_5 = ">Help</a> </div> </div>";
  */
