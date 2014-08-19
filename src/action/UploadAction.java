/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package action;

import com.opensymphony.xwork2.ActionSupport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class UploadAction extends ActionSupport{
    File upload;  
    String uploadContentType;  
    String uploadFileName;  
    int article_ID;
    String CKEditorFuncNum;
    public String execute(){
    	System.err.print("execute start");
    	InputStream is = null;
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            article_ID =(int)request.getSession().getAttribute("article_ID");
            
            is = new FileInputStream(upload);
            String uploadPath = ServletActionContext.getServletContext().getRealPath("articleImage")+"\\"+String.valueOf(article_ID);
            System.err.print("uploadPath:"+uploadPath);
            String fileName = java.util.UUID.randomUUID().toString();  
            File dir = new File(uploadPath);
            System.err.print("准备创建文件夹");
            if(!dir.exists())
                dir.mkdir();
            System.err.print("创建目录完成");
            fileName += uploadFileName.substring(uploadFileName.length() - 4);
            File toFile = new File(uploadPath+"\\"+fileName);
            System.err.print("创建文件");
            if(!toFile.exists())
                toFile.createNewFile();
            System.err.print("创建文件完成");
            OutputStream os = new FileOutputStream(toFile);
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }   is.close();
            os.close();
            is.close();
            HttpServletResponse response = ServletActionContext.getResponse();  
            response.setCharacterEncoding("UTF-8");  
            PrintWriter out = response.getWriter();  
            String callback = ServletActionContext.getRequest().getParameter("CKEditorFuncNum");    
            out.println("<script type=\"text/javascript\">");    
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + "/articleImage/"+String.valueOf(article_ID)+"/" + fileName + "','')");    
            out.println("</script>");  
            System.err.print("成功");
            return SUCCESS;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UploadAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public String image() {
    	Properties props=System.getProperties();
    	String separator = props.getProperty("file.separator");
    	System.err.println("execute start");
        InputStream is = null;
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            article_ID =(int)request.getSession().getAttribute("article_ID");
            is = new FileInputStream(upload);
            String uploadPath = ServletActionContext.getServletContext().getRealPath("articleImage")+separator+String.valueOf(article_ID);
            System.err.println("uploadPath:"+uploadPath);
            String fileName = java.util.UUID.randomUUID().toString(); 
            File dir = new File(uploadPath);
            System.err.printf("准备创建文件夹 ");
            if(!dir.exists())
                dir.mkdir();
            System.err.println("创建目录:"+uploadPath);
            fileName += uploadFileName.substring(uploadFileName.length() - 4);
            File toFile = new File(uploadPath,fileName);
            System.err.printf("创建文件"+toFile.toString());
            if(!toFile.exists())
                toFile.createNewFile();
            System.err.printf("创建文件完成");
            OutputStream os = new FileOutputStream(toFile);
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            System.err.printf("写入完成");
            is.close();
            os.close();
            System.err.printf("回写");
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("UTF-8");  
            PrintWriter out = response.getWriter();  
            String callback = ServletActionContext.getRequest().getParameter("CKEditorFuncNum");    
            out.println("<script type=\"text/javascript\">");    
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + "/articleImage/"+String.valueOf(article_ID)+"/" + fileName + "','')");    
            out.println("</script>");  
            System.err.printf("成功");
            return null;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UploadAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
}
