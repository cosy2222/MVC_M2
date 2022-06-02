package logon02;

import common.DBConnPool;

public class LogonDAO extends DBConnPool {

	private static LogonDAO dao = new LogonDAO();
	
	public static LogonDAO getinstance() {
		return dao;
	}
	
	private LogonDAO () {
		super();
	}
	 
	 
	// 회원가입 
	public void insert(LogonDTO dto) {
		
		
		try {
			String sql = "insert into member02 ( u_id , u_pass , u_name , u_address , u_tel , u_birthday) "
					+ " values (?,?,?,?,?, to_date(?, 'yyyy-mm-dd') )";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getU_id());
			psmt.setString(2, dto.getU_pass());
			psmt.setString(3, dto.getU_name());
			psmt.setString(4, dto.getU_address());
			psmt.setString(5, dto.getU_tel());
			psmt.setString(6, dto.getU_birthday());
			psmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("회원가입중 예외발생");
			System.out.println(dto.getU_birthday());
			
		}finally {
			//dao.close();
		}
		
	}
	
	//id 확인
	public int checkId(String id) {
		
		int x = -1;
		
		try {
			
			String sql = "select u_id from member02 where u_id = ?";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				x = 1;
			}
					
			
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
		
		return x;
	}
	
	
	
	// password 확인 
	public int check(String id , String pwd) {
		
		int x = -1;
		
		try {
			
			String orgPass = pwd;
			
			String sql = "select u_pass from member02 where u_id = ? ";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				String dbPass = rs.getString("u_pass");
				if(dbPass.equals(orgPass)) {
					x = 1;
				}else {
					x = 0;
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("아이디 , 패스워드 확인중 예외발생");
		}finally {
		}
		
		return x;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
