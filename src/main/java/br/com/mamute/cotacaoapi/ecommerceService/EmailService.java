package br.com.mamute.cotacaoapi.ecommerceService;
/*
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.mamute.cotacaoapi.dto.EmailDto;
import br.com.mamute.cotacaoapi.enums.StatusEmail;
import br.com.mamute.cotacaoapi.model.Email;
import br.com.mamute.cotacaoapi.repository.EmailRepository;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private EmailRepository emailRepository;
		
	public void registrar(EmailDto emailDto) {
		
		Email email = new Email();
		BeanUtils.copyProperties(emailDto, email);
		email.setSendDateEmail(LocalDateTime.now());
		
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(email.getEmailFrom());
			message.setTo(email.getEmailTo());
			message.setSubject(email.getSubject());
			message.setText(email.getTexto());
			emailSender.send(message);
			
			email.setStatusEmail(StatusEmail.ENVIADO);
		} catch (MailException e) {
			email.setStatusEmail(StatusEmail.ERRO);
		}
		finally {
			emailRepository.save(email);
		}				
    }	
	
}*/
