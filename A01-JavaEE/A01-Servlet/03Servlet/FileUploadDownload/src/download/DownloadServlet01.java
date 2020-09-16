package download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class DownloadServlet01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filePath = "C:\\users\\kyle\\desktop\\upload\\";
		String fileName = "aa.pdf";
		
		File file = new File(filePath+fileName);
		if(file.exists()) {
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
			InputStream inputStream = new FileInputStream(file);
			OutputStream outputStream = response.getOutputStream();
			byte[] b = new byte[1024]; 
			int n ;
			while((n = inputStream.read(b))!=-1){
				outputStream.write(b, 0, n);
			}
			outputStream.close();
			inputStream.close();
			System.out.println("文件下载成功！");
		} else {
			System.out.println("下载失败，服务器端的文件错误！");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
