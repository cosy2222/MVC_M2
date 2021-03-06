package membership;

import common.DBConnPool;

public class MemberDAO extends DBConnPool {
	
	public MemberDAO() {
		super();        // 부모의 기본 생성자 호출시 con 생성
	}
	
	// 클라이언트의 ID와 Password 값을 받아와서 회원 테이블의 정보와 일치하는지 확인 
	
	public MemberDTO getMemberDTO	(String uid , String upass) {
		
		MemberDTO dto = new MemberDTO();
		String query = "select * from member where id=? and pass=?";
			// 1개의 레코드가 출력되면 : id와 pass가 존재하는 경우 
			// 0개의 레코드가 출력되면 : id와 pass가 하나라도 DB에 존재하지 않는 경우 
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			rs = psmt.executeQuery();
			
			// rs의 값을 DTO에 저장 
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
				
				System.out.println("인증 성공");
			}else {
				System.out.println("인증 실패");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("id , pass 확인시 예외발생");
		}
		
		return dto ; //client 에서 uid , upass 값을 받아와서 DB에서 검색후 검색값을 dto에 담아서 return
		
	}

}
