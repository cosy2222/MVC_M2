package logon;

import common.DBConnPool;
import work.crypt.BCrypt;
import work.crypt.SHA256;

public class LogonDBBean extends DBConnPool{
	
	
	// LogonDBBean ���� ��ü ���� <-- �Ѱ��� ��ü�� �����ؼ� ����
		 //�ܺ��� �ٸ� Ŭ�������� newŰ�� ����ϸ� �������� ��ü�� ������ �� �ִ�
		 //Ư�� Ŭ������ �������� ��ü�� �������� ���ϵ��� �ϰ� ���ϳ��� ��ü�� �����ؼ� ����ؾ� �� ���
				
	
		// �̱��� ���� : �ܺο��� �������� ��ü�� �������� ���ϰ� �ϳ��� ��ü�� �����ؼ� ����ϵ��� ��.
			// 0. private�� �⺻�����ڸ� ����
			// 1. private static ���� ��ü ����
			// 2. ������ ��ü�� �޼ҷ� ��ü�� ���� 
		
	private static LogonDBBean instance = new LogonDBBean();
	
	//LogonDBBean ��ü�� �����ϴ� �޼ҵ�
		// �޼ҵ带 ����ؼ��� ��ü�� ������ �� �ִ�
	public static LogonDBBean getInstance() {
		return instance;
	}
	
	// �⺻������  private
		// �θ�Ŭ������ �⺻������ ȣ��
	private LogonDBBean () {super();};
	
	
	// ȸ������ó�� (registerPro.jsp) ���� �Ѿ���� ���� DB�� ���� (Insert)
	public void insertMember(LogonDataBean member) {
		
		SHA256 sha = SHA256.getInsatnce();
		
		try {
				// orgPass : Form �Ѱܹ��� password
			String orgPass = member.getPasswd();
			String shaPass = sha.Sha256Encrypt(orgPass.getBytes());
			String bcPass = BCrypt.hashpw(shaPass , BCrypt.gensalt());   // bcPass : ��ȣȭ�� ��ȣ 
			
			String sql = "insert into member01 values (? , ? , ? , ? , ? , ?)";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, member.getId());
//			psmt.setString(2, bcPass);				// ��ȣȭ�� passwd
			psmt.setString(2, member.getPasswd());
			psmt.setString(3, member.getName());
			psmt.setTimestamp(4, member.getReg_date());
			psmt.setString(5, member.getAddress());
			psmt.setString(6, member.getTel());
			
			psmt.executeUpdate();
			
			System.out.println("ȸ������ �Ϸ�");
			
			
		}catch(Exception  e) {
			e.printStackTrace();
			System.out.println("ȸ�� ���� DB�Է½� ���� �߻�");
		}finally {
			instance.close();
		}
		
	}
	
	
	// �α��� ó�� (loginPro.jsp) : ������ �Ѱܹ��� ID�� Pass�� DB���� Ȯ�� 
			// ����� ����ó�� , DB�� ������ �����ҋ�  , DB�� ������ �����ҋ� 
			// ��������� (MemberCheck.jsp) ���� ����ϴ� �޼ҵ�
	
	public int usercheck(String id , String passwd) {
		int x = -1;		//  x = -1  : ���̵� �������� �ʴ´� 
		
		// ��ȣȭ 
		SHA256 sha = SHA256.getInsatnce();
		
		try {
			
			String orgPass = passwd;
			String shaPass = sha.getSha256(orgPass.getBytes());
			
			String sql = "select passwd from member01 where id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {		// ���̵� �����ϸ�
				String dbpasswd = rs.getString("passwd");  // DB���� ������ �н�����
				if(BCrypt.checkpw(shaPass, dbpasswd)) {
					x=1;   // ������ �Ѱܿ� �н������ DB���� ������ �н����尡 ��ġ�ҋ�  x =1
				}else {
					x = -1;  // �н����尡 ��ġ���� ������ 
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("���̵�� �н����� ���� ���� �߽��ϴ�");
		}finally {
			instance.close();   // rs , smt , psmt , con ���� 
		}
		
		return x;		
			
	}
	
	
	// ���̵� �ߺ�üũ (confirmId.jsp) : ���̵� �ߺ��� Ȯ���ϴ� �޼ҵ�
	public int confirmId(String id) {
		
		int x = -1;				// x = -1 �϶� : ���� ID�� DB�� �������� ����
								// x =  1 �϶� : ���� ID�� DB�� ������ ( �ߺ� )
		
		try {
			
			String sql = "select id from member01 where id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString (1,id);
			rs = psmt.executeQuery();
			
			if(rs.next()) { // ���̵� DB�� �����ϴ� ��� 
				x = 1;
			} else {	// ���̵� DB�� �������� �ʴ� ��� 
				x = -1;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ID �ߺ�üũ�� ���� �߻�");
		}
		
		
		return x;
	}
	
	
	
	
	// ȸ������ ���� ( modifyForm.jsp) : DB���� ȸ�� �����ǰ��� �������� �޼ҵ�
	
	public LogonDataBean getMember (String id , String passwd) {
		LogonDataBean member = null ;
		SHA256 sha = SHA256.getInsatnce();
		
		
		try {
			
			String orgPass = passwd;
			String shaPass = sha.getSha256(orgPass.getBytes());
			
			String sql = "select * from member01 where id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {	// �ش���̵� DB�� �����ϸ� 
				String dbpasswd = rs.getString("passwd");  // DB�� �н����带 ������ �Ҵ�
				if (BCrypt.checkpw(shaPass, dbpasswd)) {
					// DB�� passwd�� ������ �Ѱܿ� Pass�� ������ 
						// DB���� select ���ڵ带 DTO (LogonDataBean) �� setter ���� �ؼ� ���� ��ȯ 
					
					//member ��ü ���� �� DB�� rs�� ����� ���� setter������ ���� 
					member = new LogonDataBean();
					
					member.setId(rs.getString("id"));
					member.setName(rs.getString("name"));
					member.setReg_date(rs.getTimestamp("reg_date"));
					member.setAddress(rs.getString("address"));
					member.setTel(rs.getString("tel"));
				}else {
					// DB�� passwd�� ������ �Ѱܿ� Pass�� �ٸ��� ó���� �κ� 
					
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ȸ�� ���� �о������ ���ܹ߻� ");
		}finally {
			instance.close();
		}
		
		return member;
	}
	
	
	// �������������� ������ ������ DB�� �����ϴ� �޼ҵ�
	// ȸ�� ���� ���� ó�� (modifyPro.jsp) ���� ȸ������ ������ ó���ϴ� �޼ҵ�
	public int updateMember(LogonDataBean member) {
		
		int x = -1 ; // update ����        x = 1 : ���� 
					
		
		// ID�� passwd�� Ȯ���� ������Ʈ ���� 
		
		SHA256 sha = SHA256.getInsatnce();    // ��ü Ȱ��ȭ
		
		try {
			String orgPass = member.getPasswd();
			String shaPass = sha.getSha256(orgPass.getBytes());
			
			String sql = "select passwd from member01 where id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, member.getId());
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				//������ �ѱ� �н������ DB���� ������ �н����尡 ��ġ�ϴ� �� Ȯ��
				String dbpasswd = rs.getString("passwd");
				if( BCrypt.checkpw(shaPass, dbpasswd)) {    // �� �н����尡 ��ġ�ҋ�
					// DTO (member) ���� ���� ���� DB�� update
					sql = "update member01 set name = ? , address = ? , tel = ?  where id = ?";
					psmt = con.prepareStatement(sql);
					psmt.setString(1, rs.getString("name"));
					psmt.setString(2, rs.getString("address"));
					psmt.setString(3, rs.getString("tel"));
					psmt.setString(4, rs.getString("id"));
					psmt.executeUpdate();
					
					x = 1;
					
				}
			
			}else {
				System.out.println("���̵� �������� �ʽ��ϴ�");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ȸ�� ���� ������ ���� �߻� ");
		}finally {
			instance.close();
		}
		
		
		return x;
	}
	
	
	// ȸ�� Ż�� ó�� (deleteProcess.jsp)
	public int deleteMember(String id , String passwd) {
		
		int x = -1 ;    // Ż�� ����     x = 1 : ���� 
		
		SHA256 sha  = SHA256.getInsatnce(); // ��ü�� ȣ���ؼ� ������ �Ҵ�
		
		try {
			
			String orgPass = passwd;
			String shaPass = sha.getSha256(orgPass.getBytes());
			
			String sql = "select passwd from member01 where id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				
				String dbpasswd = rs.getString("passwd");
				
				if(BCrypt.checkpw(shaPass, dbpasswd)) {  // password ��ġ�ϸ� delete
					
				sql = "delete member01 where id = ?";
				psmt = con.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.executeUpdate();
				
				x = 1;
				
				}else {
					System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ� ");
				}
				
			}else {
				System.out.println("�������� �ʴ� id �Դϴ�");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ȸ��Ż��� ���ܰ� �߻� �߽��ϴ�");
		}
		
		
		return x;
		
	}
	
	
	
	
	
	
	
	
	

}