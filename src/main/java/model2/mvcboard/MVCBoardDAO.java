package model2.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool {
	
	// 기본생성자 호출시 부모 클래스를 호출
	public MVCBoardDAO() {
		super();    // DBConnPool객체의 기본 생성자 호출 , DBCP에서 con객체 활성화 
	}
	
	public int seletcCount(Map<String,Object> map) {
		int totalCount = 0;
		String query = "select count(*) from mvcboard ";	// 레코드의 총갯수 반환
		if(map.get("searchWord") != null) {					// 검색기능을 사용했을시 where 
			query += "where"  + map.get("searchField") + " " + "like '%' " + map.get("searchWord");
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount= rs.getInt(1);
			
		}catch(Exception e) {
			System.out.println("게시물 카운트중 예외 발생 ");
		}
		
		return totalCount;
	}
	
	// 검색조건에 맞는 게시물갯수를 반환합니다 
			//DataBase에서 Select한 결과 값을 DTO에 담아서 리턴 시켜줌 
	public List<MVCBoardDTO> selectListPage (Map<String,Object>map){
		List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
		String query = " select * from (select Tb.* , ROWNUM rNUM FROM (select * from mvcboard " ;
		
		if(map.get("searchWord") != null) {
			query += " where " + map.get("searchField") + " like '%" +map.get("searchWord") + "%'" ;
		}
		
		query += "  order by Tb ) where rNUm between ? and ?" ;
		
		try {//psmt 객체 생성후 쿼리 실행
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString() );
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();    // DataBase에서 Select 한 결과값을 rs에 저장 
			
			//rs에 저장돤 값을 DTO에 저장 ==> DTO 객체를 List 에 add 
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
				
				board.add(dto);    // List에 DB의 rs의 하나의 레코드의 값을 dto에 저장하고   dto를 List에 저장 
					
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return board;
		
	}
	
	

	// 목록검색 (select) :  주어진 일련 번호에 해당하는 값을 DTO에 담아 반환합니다 (한페이지 read)
	
	// 데이터 삽입(insert)
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
	
	
	// 데이터 수정 (Update)
	
	// 데이터 삭제 (delete)
	
	// 데이터 검색 (Select 특정조건으로 ) 
	

}
