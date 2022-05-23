package model2.mvcboard;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import fileupload.FileUtil;
import utils.JSFunction;

@WebServlet("/mvcboard/write.do")
public class WriteController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get ��� ��û ó��
		// List.jsp (view ������) ���� �۾��⸦ Ŭ�� ������ �۾��� �������� 
		
		// ���������� ���� 
		request.getRequestDispatcher("/mvcboard/Write.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Post ��� ��û ó�� 
		// Write.jsp ���������� ������ Ŭ�������� form���� �Ѿ���� ������ ���� ó��
		
		// Form���� ������ �����ϹǷ� cos.jar ���̺귯���� ��ü ������ ������ ���� �޾ƾ� �Ѵ� 
		
		//1. ���� ���ε� ó�� ===============================
		
			// saveDirectory ������ ���ε��� ������ ������ ������ �������� ��θ� ����
			String saveDirectory = request.getServletContext().getRealPath("/Uploads");
		
			// maxPostSize : ���ε��� �ִ� �뷮 ( web.xml <== 1MB ) 
			ServletContext application = getServletContext();
			int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
		
		
			//���� ���ε� ��ü����
			MultipartRequest mr = FileUtil.uploadFile(request , saveDirectory , maxPostSize);
			
			if (mr == null) {   // ��ü ���� ���� ( 1mb�̻� ���� ���۽�)
				JSFunction.alertLocation(response, "÷�� �뷮�� �ʰ� �Ǿ����ϴ�", "../mvcboard/write.do");
			}
		
		
			
		//2. ���� ���ε� �� ó�� ( Form�� ���� �� ó��) 
			// ������ �Ѱܹ��� ���� �޾Ƽ� DTO(VO) �� Setter ������ �ϰ� DAO�� Insert �޼ҵ忡 ������ . 
			MVCBoardDTO dto = new MVCBoardDTO();
			dto.setName(mr.getParameter("name"));
			dto.setTitle(mr.getParameter("title"));
			dto.setContent(mr.getParameter("content"));
			dto.setPass(mr.getParameter("pass"));
			
			// ���� ���� �̸��� ���� �����̸� ����
			String filename = mr.getFilesystemName("ofile");   // client�� ÷������ ������ �ּ�
			System.out.println(filename);
			
			if(filename != null) { // ÷�������� ������� �ʴٸ�
				
				// ���ο� �����̸����� �����ؼ� ������ ������  (������ �ش������� ������ ��찡 �����Ƿ�)
				String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
				System.out.println(now);
				//Ȯ���ڸ� �߶� ����
				String ext = filename.substring(filename.lastIndexOf("."));
					System.out.println("ext" + ext);
				// ������ ������ �����̸� ����
				String newFilename = now + ext;
				System.out.println(newFilename);
				
				//���ϸ� ����
				File oldfile = new File(saveDirectory + File.separator + filename);
				File newfile = new File(saveDirectory + File.separator + newFilename);
					System.out.println("oldfile"+oldfile);
					System.out.println("newfile"+newfile);
				oldfile.renameTo(newfile);
				
				//DTO�� Setter 
				dto.setOfile(filename);			// ���� �����̸�
				dto.setSfile(newFilename);		// ������ ����� �����̸� 
				
			}
			
			
			//DTO�� ��ü�� DAO�� insertWrite(dto) �޼ҵ带 ȣ���ؼ� DB�� ����
				MVCBoardDAO dao = new MVCBoardDAO();
				
				int result = dao.insertWrite(dto);
				
				//��ü ���� �޼ҵ�ȣ��  ( rs , stmt , psmt , con ��� ����)
				dao.close();
			
				// �۾��� �����ϋ� �̵��� ������ 
				if (result ==1) {  // �۾��� ����
					response.sendRedirect("../mvcboard/list.do");
				}
				
				// �۾��� �����ϋ� �̵��� ������ 
				if (result ==0) {  // �۾��� ����
					response.sendRedirect("../mvcboard/write.do");
				}
			
			
			
			
			
			
			
			
			
	}

}
