package logon;

import common.DBConnPool;
import work.crypt.BCrypt;
import work.crypt.SHA256;

public class LogonDBBean extends DBConnPool{
	
	
	// LogonDBBean 전역 객체 생성 <-- 한개의 객체만 생성해서 공유
		 //외부의 다른 클래스에서 new키를 사용하면 여러개의 객체를 생성할 수 있다
		 //특정 클래스는 여러개의 객체를 생성하지 못하도록 하고 단하나의 객체만 생성해서 사용해야 될 경우
				
	
		// 싱글톤 패턴 : 외부에서 여러개의 객체를 생성하지 못하고 하나의 객체만 공유해서 사용하도록 함.
			// 0. private로 기본생성자를 셋팅
			// 1. private static 으로 객체 생성
			// 2. 생성된 객체를 메소로 객체를 전달 
		
	private static LogonDBBean instance = new LogonDBBean();
	
	//LogonDBBean 객체를 리턴하는 메소드
		// 메소드를 사용해서만 객체를 생성할 수 있다
	public static LogonDBBean getInstance() {
		return instance;
	}
	
	// 기본생성자  private
		// 부모클래스의 기본생성자 호출
	private LogonDBBean () {super();};
	
	
	// 회원가입처리 (registerPro.jsp) 에서 넘어오는 값을 DB에 저장 (Insert)
	public void insertMember(LogonDataBean member) {
		
		SHA256 sha = SHA256.getInsatnce();
		
		try {
				// orgPass : Form 넘겨받은 password
			String orgPass = member.getPasswd();
			String shaPass = sha.Sha256Encrypt(orgPass.getBytes());
			String bcPass = BCrypt.hashpw(shaPass , BCrypt.gensalt());   // bcPass : 암호화된 암호 
			
			String sql = "insert into member01 values (? , ? , ? , ? , ? , ?)";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, member.getId());
//			psmt.setString(2, bcPass);				// 암호화된 passwd
			psmt.setString(2, member.getPasswd());
			psmt.setString(3, member.getName());
			psmt.setTimestamp(4, member.getReg_date());
			psmt.setString(5, member.getAddress());
			psmt.setString(6, member.getTel());
			
			psmt.executeUpdate();
			
			System.out.println("회원가입 완료");
			
			
		}catch(Exception  e) {
			e.printStackTrace();
			System.out.println("회원 정보 DB입력시 예외 발생");
		}finally {
			instance.close();
		}
		
	}
	
	
	// 로그인 처리 (loginPro.jsp) : 폼에서 넘겨받은 ID와 Pass를 DB에서 확인 
			// 사용자 인증처리 , DB의 정보를 수정할떄  , DB의 정보를 삭제할떄 
			// 사용자인증 (MemberCheck.jsp) 에서 사용하는 메소드
	
	public int usercheck(String id , String passwd) {
		int x = -1;		//  x = -1  : 아이디가 존재하지 않는다 
		
		// 복호화 
		SHA256 sha = SHA256.getInsatnce();
		
		try {
			
			String orgPass = passwd;
			String shaPass = sha.getSha256(orgPass.getBytes());
			
			String sql = "select passwd from member01 where id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {		// 아이디가 존재하면
				String dbpasswd = rs.getString("passwd");  // DB에서 가져온 패스워드
				if(BCrypt.checkpw(shaPass, dbpasswd)) {
					x=1;   // 폼에서 넘겨온 패스워드와 DB에서 가져온 패스워드가 일치할떄  x =1
				}else {
					x = -1;  // 패스워드가 일치하지 않을떄 
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("아이디와 패스워드 인증 실패 했습니다");
		}finally {
			instance.close();   // rs , smt , psmt , con 종료 
		}
		
		return x;		
			
	}
	
	
	// 아이디 중복체크 (confirmId.jsp) : 아이디 중복을 확인하는 메소드
	public int confirmId(String id) {
		
		int x = -1;				// x = -1 일때 : 같은 ID가 DB에 존재하지 않음
								// x =  1 일때 : 같은 ID가 DB에 존재함 ( 중복 )
		
		try {
			
			String sql = "select id from member01 where id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString (1,id);
			rs = psmt.executeQuery();
			
			if(rs.next()) { // 아이디가 DB에 존재하는 경우 
				x = 1;
			} else {	// 아이디가 DB에 존재하지 않는 경우 
				x = -1;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ID 중복체크중 예외 발생");
		}
		
		
		return x;
	}
	
	
	
	
	// 회원정보 수정 ( modifyForm.jsp) : DB에서 회원 정보의값을 가져오는 메소드
	
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
			
			if(rs.next()) {	// 해당아이디가 DB에 존재하면 
				String dbpasswd = rs.getString("passwd");  // DB의 패스워드를 변수에 할당
				if (BCrypt.checkpw(shaPass, dbpasswd)) {
					// DB의 passwd와 폼에서 넘겨온 Pass가 같을떄 
						// DB에서 select 레코드를 DTO (LogonDataBean) 에 setter 주입 해서 값을 반환 
					
					//member 객체 생성 후 DB에 rs에 저장된 값을 setter주입후 리턴 
					member = new LogonDataBean();
					
					member.setId(rs.getString("id"));
					member.setName(rs.getString("name"));
					member.setReg_date(rs.getTimestamp("reg_date"));
					member.setAddress(rs.getString("address"));
					member.setTel(rs.getString("tel"));
				}else {
					// DB의 passwd와 폼에서 넘겨온 Pass가 다를떄 처리할 부분 
					
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("회원 정보 읽어오는중 예외발생 ");
		}finally {
			instance.close();
		}
		
		return member;
	}
	
	
	// 수정페이지에서 수정한 내용을 DB에 저장하는 메소드
	// 회원 정보 수정 처리 (modifyPro.jsp) 에서 회원정보 수정을 처리하는 메소드
	public int updateMember(LogonDataBean member) {
		
		int x = -1 ; // update 실패        x = 1 : 성공 
					
		
		// ID와 passwd를 확인후 업데이트 진행 
		
		SHA256 sha = SHA256.getInsatnce();    // 객체 활성화
		
		try {
			String orgPass = member.getPasswd();
			String shaPass = sha.getSha256(orgPass.getBytes());
			
			String sql = "select passwd from member01 where id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, member.getId());
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				//폼에서 넘긴 패스워드와 DB에서 가져온 패스워드가 일치하는 지 확인
				String dbpasswd = rs.getString("passwd");
				if( BCrypt.checkpw(shaPass, dbpasswd)) {    // 두 패스워드가 일치할떄
					// DTO (member) 에서 들어온 값을 DB에 update
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
				System.out.println("아이디가 존재하지 않습니다");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("회원 정보 수정시 예외 발생 ");
		}finally {
			instance.close();
		}
		
		
		return x;
	}
	
	
	// 회원 탈퇴 처리 (deleteProcess.jsp)
	public int deleteMember(String id , String passwd) {
		
		int x = -1 ;    // 탈퇴 실패     x = 1 : 성공 
		
		SHA256 sha  = SHA256.getInsatnce(); // 객체를 호출해서 변수에 할당
		
		try {
			
			String orgPass = passwd;
			String shaPass = sha.getSha256(orgPass.getBytes());
			
			String sql = "select passwd from member01 where id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				
				String dbpasswd = rs.getString("passwd");
				
				if(BCrypt.checkpw(shaPass, dbpasswd)) {  // password 일치하면 delete
					
				sql = "delete member01 where id = ?";
				psmt = con.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.executeUpdate();
				
				x = 1;
				
				}else {
					System.out.println("비밀번호가 일치하지 않습니다 ");
				}
				
			}else {
				System.out.println("존재하지 않는 id 입니다");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("회원탈퇴시 예외가 발생 했습니다");
		}
		
		
		return x;
		
	}
	
	
	
	
	
	
	
	
	

}
