package upload;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/*
函数功能：
	上传多个文件到服务器
实现核心思想：
	1.Servlet3.0中的Part类
	2.@MultipartConfig注解
*/

@MultipartConfig  //核心代码
public class UploadServlet02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html;charset=UTF-8");
		
		request.getParts();
		for(Part pa:request.getParts()) {
			if(pa.getName().startsWith("uploadFileMulti")) {
				String fileName = pa.getSubmittedFileName();// 获取上传文件名称
				
				// 为防止上传文件重名，对文件重新命名;substring是取文件的格式
				String newFileName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
				
				String filePath = "C:/users/kyle/desktop/upload";// 文件存放位置
				/* 如果要将文件保存到的application/images路径下
				// String filePath =
				// getServletContext().getRealPath("/application/images");
				*/

				File f = new File(filePath);
				if (!f.exists()) {
					f.mkdirs();
				}
				pa.write(filePath + "/" + newFileName);
				
				System.out.println("文件上传成功："+filePath+"/"+newFileName);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
