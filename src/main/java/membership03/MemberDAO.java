package membership03;

import common.DBConnPool;

public class MemberDAO extends DBConnPool {
	
	public MemberDAO() {
		super();        // �θ��� �⺻ ������ ȣ��� con ����
	}
	
	// Ŭ���̾�Ʈ�� ID�� Password ���� �޾ƿͼ� ȸ�� ���̺��� ������ ��ġ�ϴ��� Ȯ�� 
	
	public MemberDTO getMemberDTO	(String uid , String upass) {
		
		MemberDTO dto = new MemberDTO();
		String query = "select * from member03 where id=? and pass=?";
			// 1���� ���ڵ尡 ��µǸ� : id�� pass�� �����ϴ� ��� 
			// 0���� ���ڵ尡 ��µǸ� : id�� pass�� �ϳ��� DB�� �������� �ʴ� ��� 
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			rs = psmt.executeQuery();
			
			// rs�� ���� DTO�� ���� 
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
				dto.setGrade(rs.getString(5));
				
				System.out.println("���� ����");
			}else {
				System.out.println("���� ����");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("id , pass Ȯ�ν� ���ܹ߻�");
		}
		
		return dto ; //client ���� uid , upass ���� �޾ƿͼ� DB���� �˻��� �˻����� dto�� ��Ƽ� return
		
	}

}
