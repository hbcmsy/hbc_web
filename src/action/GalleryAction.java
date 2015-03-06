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

import common.Evn;


@SuppressWarnings("serial")
public class GalleryAction extends ActionSupport{
	String json;
	int gallery_ID = -1;
	String gallery_title;
	String gallery_url;
	String gallery_href;
	String gallery_flag;
	public String getGalleries(){
		List<Gallery> list = new GalleryService().getGalleryList(Evn.getGALLERY_FLAG(gallery_flag));
    	JSONArray jsonArray = new JSONArray();
    	for(Gallery a:list){
    		JSONObject json = new JSONObject();
    		json.put("gallery_ID", a.getGallery_ID());
        	json.put("gallery_title",a.getGallery_title());
        	json.put("gallery_url", a.getGallery_url());
        	json.put("gallery_href", a.getGallery_href());
        	json.put("gallery_flag", a.getGallery_flag());
        	jsonArray.add(json);
    	}
    	json = jsonArray.toString();  
		return "galleryJson";
	}
	public String getGallery(){
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
	public String addGallery(){
		if(!this.isUser())
			return null;
		JSONObject jsonObject = new JSONObject();
		if(new GalleryService().addGallery(gallery_title, gallery_url, gallery_href,Evn.GALLERY_FLAG.EDIT))
			jsonObject.put("gallery_add", "true");
		else
			jsonObject.put("gallery_add", "false");
		json = jsonObject.toString();
		return "galleryJson";
	}
	public String changeGallery(){
		if(!this.isUser())
			return null;
		JSONObject jsonObject = new JSONObject();
		Gallery gallery = new Gallery();
		gallery.setGallery_ID(gallery_ID);
		gallery.setGallery_title(gallery_title);
		gallery.setGallery_url(gallery_url);
		gallery.setGallery_href(gallery_href);
		gallery.setGallery_flag(gallery_flag.charAt(0));
		if(new GalleryService().changeGallery(gallery))
			jsonObject.put("gallery_change", "true");
		else
			jsonObject.put("gallery_change", "false");
		json = jsonObject.toString();
		return "galleryJson";
	}
	public String deleteGallery(){
		if(!this.isUser())
			return null;
		JSONObject jsonObject = new JSONObject();
		Gallery gallery = new GalleryService().getGallery(gallery_ID);
		if(Evn.GALLERY_FLAG.DELETE == Evn.getGALLERY_FLAG(gallery.getGallery_flag()+"")){
			if(new GalleryService().deleteGallery(gallery_ID))
				jsonObject.put("gallery_delete", "true");
			else
				jsonObject.put("gallery_delete", "false");
			json = jsonObject.toString();
			return "galleryJson";
		}else {
			gallery.setGallery_flag(Evn.getGALLERY_FLAG(Evn.GALLERY_FLAG.DELETE).charAt(0));
			if(new GalleryService().changeGallery(gallery))
				jsonObject.put("gallery_delete", "true");
			else
				jsonObject.put("gallery_delete", "false");
			json = jsonObject.toString();
			return "galleryJson";
		}
		
	}
	public String releaseGallery(){
		if(!this.isUser())
			return null;
		JSONObject jsonObject = new JSONObject();
		Gallery gallery = new GalleryService().getGallery(gallery_ID);
		gallery.setGallery_flag(Evn.getGALLERY_FLAG(Evn.GALLERY_FLAG.USE).charAt(0));
		if(new GalleryService().changeGallery(gallery))
			jsonObject.put("gallery_release", "true");
		else
			jsonObject.put("gallery_release", "false");
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
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public int getGallery_ID() {
		return gallery_ID;
	}
	public void setGallery_ID(int gallery_ID) {
		this.gallery_ID = gallery_ID;
	}
	public String getGallery_title() {
		return gallery_title;
	}
	public void setGallery_title(String gallery_title) {
		this.gallery_title = gallery_title;
	}
	public String getGallery_url() {
		return gallery_url;
	}
	public void setGallery_url(String gallery_url) {
		this.gallery_url = gallery_url;
	}
	public String getGallery_href() {
		return gallery_href;
	}
	public void setGallery_href(String gallery_href) {
		this.gallery_href = gallery_href;
	}
	public String getGallery_flag() {
		return gallery_flag;
	}
	public void setGallery_flag(String gallery_flag) {
		this.gallery_flag = gallery_flag;
	}
	
}
