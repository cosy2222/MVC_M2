package logon;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64Test {

	public static void main(String[] args) {
		base64(); 
	}
	
	public static void base64() {
		
		String text = "1234";   // 인코딩할 데이터를 변수에 등록
								// 인코딩 : 암호화 
		// 인코딩 전 텍스트를 byte 배열에 저장
		byte[] targetBytes = text.getBytes();
		
		Encoder encoder = Base64.getEncoder();
			// 메소드를 사용해서 Encoder객체 생성
		
		byte [] encodeByte = encoder.encode(targetBytes);
		String encoderText = new String(encodeByte);    // 암호화된 데이터를 String 형식으로 적용 
		
		System.out.println("=========인코딩=============");
		System.out.println("인코딩 전 데이터 : " + text);
		System.out.println("인코딩 후 데이터 : " + encoderText);
			
		
		System.out.println("==========디코딩 (복호화) ==============");
		
		//디코딩
		Decoder decoder = Base64.getDecoder();    // Decoder 객체 생성
		byte[] decoderBytes = decoder.decode(encodeByte);
		byte[] decoderBytes2 = decoder.decode(encoderText);
		
		String decoderText = new String (decoderBytes);
		String decoderText2 = new String (decoderBytes2);
		
		System.out.println("디코딩된 데이터 : " + decoderText);
		System.out.println("디코딩된 데이터 : " + decoderText2);
	}

}
