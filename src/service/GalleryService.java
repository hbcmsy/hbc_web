package service;

import java.sql.SQLException;
import java.util.List;

import model.Gallery;
import dao.GalleryDao;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GalleryService {
	    public boolean addGallery(String title,String url,String href){
	    	Gallery gallery = new Gallery();
	    	gallery.setGallery_title(title);
	    	gallery.setGallery_url(url);
	    	gallery.setGallery_href(href);
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
	        } catch (SQLException ex) {
	            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
	            return null;
	        }
	    }
	    public Gallery getGallery(int ID){
	        try {
	            return new GalleryDao().getGallery(ID);
	        } catch (SQLException ex) {
	            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
	            return null;
	        }
	    }
	    public boolean deleteGallery(int ID){
	        try {
	            return new GalleryDao().deleteGallery(ID);
	        } catch (SQLException ex) {
	            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
	            return false;
	        }
	    }
	    public boolean deleteGallery(Gallery gallery){
	        try {
	            return new GalleryDao().deleteGallery(gallery);
	        } catch (SQLException ex) {
	            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
	            return false;
	        }
	    }
	    public boolean changeGallery(Gallery gallery){
	        try {
	            return new GalleryDao().changeUser(gallery);
	        } catch (SQLException ex) {
	            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
	            return false;
	        }
	    }
	    
}
