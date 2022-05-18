package model2.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool {
	
	// �⺻������ ȣ��� �θ� Ŭ������ ȣ��
	public MVCBoardDAO() {
		super();    // DBConnPool��ü�� �⺻ ������ ȣ�� , DBCP���� con��ü Ȱ��ȭ 
	}
	
	public int seletcCount(Map<String,Object> map) {
		int totalCount = 0;
		String query = "select count(*) from mvcboard ";	// ���ڵ��� �Ѱ��� ��ȯ
		if(map.get("searchWord") != null) {					// �˻������ ��������� where 
			query += "where"  + map.get("searchField") + " " + "like '%' " + map.get("searchWord");
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount= rs.getInt(1);
			
		}catch(Exception e) {
			System.out.println("�Խù� ī��Ʈ�� ���� �߻� ");
		}
		
		return totalCount;
	}
	
	// �˻����ǿ� �´� �Խù������� ��ȯ�մϴ� 
			//DataBase���� Select�� ��� ���� DTO�� ��Ƽ� ���� ������ 
	public List<MVCBoardDTO> selectListPage (Map<String,Object>map){
		List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
		String query = " select * from (select Tb.* , ROWNUM rNUM FROM (select * from mvcboard " ;
		
		if(map.get("searchWord") != null) {
			query += " where " + map.get("searchField") + " like '%" +map.get("searchWord") + "%'" ;
		}
		
		query += "  order by Tb ) where rNUm between ? and ?" ;
		
		try {//psmt ��ü ������ ���� ����
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString() );
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();    // DataBase���� Select �� ������� rs�� ���� 
			
			//rs�� ����� ���� DTO�� ���� ==> DTO ��ü�� List �� add 
			while(rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				
				board.add(dto);    // List�� DB�� rs�� �ϳ��� ���ڵ��� ���� dto�� �����ϰ�   dto�� List�� ���� 
					
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return board;
		
	}
	
	

	// ��ϰ˻� (select) :  �־��� �Ϸ� ��ȣ�� �ش��ϴ� ���� DTO�� ��� ��ȯ�մϴ� (�������� read)
	
	// ������ ����(insert)
	public int insertWrite (MVCBoardDTO dto) {
		int result = 0;
		try {
			String query = "insert into mvcboard(idx,name,title,content,ofile,sfile,pass)"
					+ "values( seq_board_num.nextval, ? , ? , ? , ? ,? ,? )";
			
			
			psmt = con.prepareStatement(query); 
			
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass());
			
			result = psmt.executeUpdate(); 		
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;   
		
	}
	
	
	// ������ ���� (Update)
	
	// ������ ���� (delete)
	
	// ������ �˻� (Select Ư���������� ) 
	

}
