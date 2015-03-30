package service;

import java.sql.SQLException;
import java.util.List;

import model.Gallery;
import dao.GalleryDao;


import common.Evn;

public class GalleryService {
	    public boolean addGallery(String title,String url,String href,int No,Evn.GALLERY_FLAG flag){
	    	Gallery gallery = new Gallery();
	    	gallery.setGallery_title(title);
	    	gallery.setGallery_url(url);
	    	gallery.setGallery_href(href);
	    	gallery.setGallery_No(No);
	    	gallery.setGallery_flag(Evn.getGALLERY_FLAG(flag).charAt(0));
	    	return this.addGallery(gallery);
	    }
	    public boolean addGallery(Gallery gallery){
	    	try {
				return new GalleryDao().addGallery(gallery);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return false;
			}
	    }
	    public List<Gallery> getGalleryList(){
	        try {
	            return new GalleryDao().getGalleries();
	        } catch (SQLException e) {
	        	e.printStackTrace();
	            return null;
	        }
	    }
	    public List<Gallery> getGalleryList(Evn.GALLERY_FLAG flag){
	    	if(flag == Evn.GALLERY_FLAG.ALL)
	    		return this.getGalleryList();
	    	try {
				return new GalleryDao().getGalleries(Evn.getGALLERY_FLAG(flag).charAt(0));
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return null;
			}
	    }
	    public List<Gallery> getGalleryListOrderByNo(Evn.GALLERY_FLAG flag){
	    	if(flag == Evn.GALLERY_FLAG.ALL)
	    		return this.getGalleryList();
	    	try {
				return new GalleryDao().getGalleriesOrderByNo(Evn.getGALLERY_FLAG(flag).charAt(0));
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return null;
			}
	    }
	    public Gallery getGallery(int ID){
	        try {
	            return new GalleryDao().getGallery(ID);
	        } catch (SQLException e) {
	        	e.printStackTrace();
	            return null;
	        }
	    }
	    public boolean deleteGallery(int ID){
	        try {
	            return new GalleryDao().deleteGallery(ID);
	        } catch (SQLException e) {
	        	e.printStackTrace();
	            return false;
	        }
	    }
	    public boolean deleteGallery(Gallery gallery){
	        try {
	            return new GalleryDao().deleteGallery(gallery);
	        } catch (SQLException e) {
	        	e.printStackTrace();
	            return false;
	        }
	    }
	    public boolean changeGallery(Gallery gallery){
	        try {
	            return new GalleryDao().changeGallery(gallery);
	        } catch (SQLException e) {
	        	e.printStackTrace();
	            return false;
	        }
	    }
	    
}
