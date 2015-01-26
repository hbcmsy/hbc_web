package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import model.Gallery;
import model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.GalleryService;

import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class GalleryAction extends ActionSupport{
	String json;
	
	int gallery_ID = -1;
	String gallery_title;
	String gallery_url;
	String gallery_href;
	String getGalleries(){
		if(!this.isUser())
			return null;
		List<Gallery> list = new GalleryService().getGalleryList();
    	JSONArray jsonArray = new JSONArray();
    	for(Gallery a:list){
    		JSONObject json = new JSONObject();
    		json.put("gallery_ID", a.getGallery_ID());
        	json.put("gallery_title",a.getGallery_title());
        	json.put("gallery_url", a.getGallery_url());
        	json.put("gallery_href", a.getGallery_href());
        	jsonArray.add(json);
    	}
    	json = jsonArray.toString();  
		return "galleryJson";
	}
	String getGallery(){
		if(!this.isUser())
			return null;
		if(gallery_ID == -1){
			return "galleryJson";
		}
		Gallery a = new GalleryService().getGallery(gallery_ID);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("gallery_ID", a.getGallery_ID());
    	jsonObject.put("gallery_title",a.getGallery_title());
    	jsonObject.put("gallery_url", a.getGallery_url());
    	jsonObject.put("gallery_href", a.getGallery_href());
    	json = jsonObject.toString(); 
		return "galleryJson";
	}
	String addGallery(){
		if(!this.isUser())
			return null;
		JSONObject jsonObject = new JSONObject();
		if(new GalleryService().addGallery(gallery_title, gallery_url, gallery_href))
			jsonObject.put("gallery_add", "true");
		else
			jsonObject.put("gallery_add", "false");
		json = jsonObject.toString();
		return "galleryJson";
	}
	String changeGallery(){
		if(!this.isUser())
			return null;
		JSONObject jsonObject = new JSONObject();
		Gallery gallery = new Gallery();
		gallery.setGallery_ID(gallery_ID);
		gallery.setGallery_title(gallery_title);
		gallery.setGallery_url(gallery_url);
		gallery.setGallery_href(gallery_href);
		if(new GalleryService().changeGallery(gallery))
			jsonObject.put("gallery_change", "true");
		else
			jsonObject.put("gallery_change", "false");
		json = jsonObject.toString();
		return "galleryJson";
	}
	String deleteGallery(){
		if(!this.isUser())
			return null;
		JSONObject jsonObject = new JSONObject();
		if(new GalleryService().deleteGallery(gallery_ID))
			jsonObject.put("gallery_delete", "true");
		else
			jsonObject.put("gallery_delete", "false");
		json = jsonObject.toString();
		return "galleryJson";
	}
	boolean isUser(){
		HttpServletRequest request = ServletActionContext.getRequest();
		User user =(User)request.getSession().getAttribute("userInfo");		//获取用户名
		if(user == null)
			return false;
		else 
			return true;
	}
	
}
