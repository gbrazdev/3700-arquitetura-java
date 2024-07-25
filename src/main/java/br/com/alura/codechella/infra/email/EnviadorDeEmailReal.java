package br.com.alura.codechella.infra.email;

import br.com.alura.codechella.domain.email.EnviadorDeEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class EnviadorDeEmailReal implements EnviadorDeEmail {

    @Autowired
    private JavaMailSender emailSender;

    @Async
    public void enviar(String destinatario, String assunto, String mensagem) {
        try {
            var email = new SimpleMailMessage();
            email.setFrom("naoresponda@codechella.com.br");
            email.setTo(destinatario);
            email.setSubject(assunto);
            email.setText(mensagem);
            emailSender.send(email);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar email!", e);
        }
    }

}
