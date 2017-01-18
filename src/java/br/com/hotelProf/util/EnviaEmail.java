/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.util;

import br.com.hotelProf.bean.Pessoa;
import br.com.hotelProf.bean.Usuario;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author silvio
 */
public class EnviaEmail {

    /**
     * Metodo para enviar e-mail para usuário cadastrado.
     * @param pessoa
     * @throws MessagingException
     */
    public void enviaEmailSimples(Pessoa pessoa) throws MessagingException {

        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Hotmail
         */
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("tjunior103@hotmail.com", "j1v1s3lv34");
                    }
                });


            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tjunior103@hotmail.com")); //Remetente

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(pessoa.getEmail())); //Destinatário(s)

//            message.setSentDate(new Date());
            message.setSubject("Dados de usuário do Hotel");
            message.setText("Olá " + pessoa.getNome() + "\n\nSegue os dados de usuários do "
                    + "Sistema Hotel Senac: \nLogin: " + pessoa.getUsuario().getLogin() + "\nSenha: "
                    + pessoa.getUsuario().getSenha() + "\n\natenciosamente \nSilvio Junior \nHotel Senac TI");
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);
    }

}
