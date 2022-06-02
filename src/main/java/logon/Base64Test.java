package logon;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64Test {

	public static void main(String[] args) {
		base64(); 
	}
	
	public static void base64() {
		
		String text = "1234";   // ���ڵ��� �����͸� ������ ���
								// ���ڵ� : ��ȣȭ 
		// ���ڵ� �� �ؽ�Ʈ�� byte �迭�� ����
		byte[] targetBytes = text.getBytes();
		
		Encoder encoder = Base64.getEncoder();
			// �޼ҵ带 ����ؼ� Encoder��ü ����
		
		byte [] encodeByte = encoder.encode(targetBytes);
		String encoderText = new String(encodeByte);    // ��ȣȭ�� �����͸� String �������� ���� 
		
		System.out.println("=========���ڵ�=============");
		System.out.println("���ڵ� �� ������ : " + text);
		System.out.println("���ڵ� �� ������ : " + encoderText);
			
		
		System.out.println("==========���ڵ� (��ȣȭ) ==============");
		
		//���ڵ�
		Decoder decoder = Base64.getDecoder();    // Decoder ��ü ����
		byte[] decoderBytes = decoder.decode(encodeByte);
		byte[] decoderBytes2 = decoder.decode(encoderText);
		
		String decoderText = new String (decoderBytes);
		String decoderText2 = new String (decoderBytes2);
		
		System.out.println("���ڵ��� ������ : " + decoderText);
		System.out.println("���ڵ��� ������ : " + decoderText2);
	}

}
