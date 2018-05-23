package net.common.util.mail;

public interface Mail {
	public void send(String toAddress, String subject, String msg);
}
