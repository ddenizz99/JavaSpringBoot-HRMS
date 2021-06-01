package egedeniz.hrms.core.utilities.mail;

public interface MailService {
	void sendSimpleMessage(String to, String subject, String text);
}
