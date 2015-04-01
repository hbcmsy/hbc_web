package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Gallery;
import common.SqlHelper;

public class GalleryDao {
    public Gallery getGallery(int ID) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select * from hbc_gallery where gallery_ID=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            Gallery data = new Gallery();
            if(rs.next()){
                data.setGallery_ID(rs.getInt(1));
                data.setGallery_title(rs.getString(2));
                data.setGallery_url(rs.getString(3));
                data.setGallery_href(rs.getString(4));
                data.setGallery_flag(rs.getString(5).charAt(0));
                data.setGallery_No(rs.getInt(6));
            }
            return data;
        }finally {
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
        }
    }
    public List<Gallery> getGalleries(char flag) throws SQLException{
    	 Connection con = null;
         PreparedStatement ps = null;
         ResultSet rs = null;
         try{
             con = SqlHelper.connect();
             String sql = "select * from hbc_gallery where gallery_flag = ?";
             ps = con.prepareStatement(sql);
             ps.setString(1, flag+"");
             rs = ps.executeQuery();
             List<Gallery> list = new ArrayList<>();
             while(rs.next()){
                 Gallery data = new Gallery();
                 data.setGallery_ID(rs.getInt(1));
                 data.setGallery_title(rs.getString(2));
                 data.setGallery_url(rs.getString(3));
                 data.setGallery_href(rs.getString(4));
                 data.setGallery_flag(flag);
                 data.setGallery_No(rs.getInt(5));
                 list.add(data);
             }
             return list;
		}finally {																			//释放资源
	        SqlHelper.closeResult(rs);
	        SqlHelper.closePreparedStatement(ps);
	        SqlHelper.closeConneciton(con);  
		}
	}
    public List<Gallery> getGalleries() throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select * from hbc_gallery";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Gallery> list = new ArrayList<>();
            while(rs.next()){
                Gallery data = new Gallery();
                data.setGallery_ID(rs.getInt(1));
                data.setGallery_title(rs.getString(2));
                data.setGallery_url(rs.getString(3));
                data.setGallery_href(rs.getString(4));
                data.setGallery_flag(rs.getString(5).charAt(0));
                data.setGallery_No(rs.getInt(6));
                list.add(data);
            }
            return list;
        }finally{
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);
        }
    }
    public List<Gallery> getGalleriesOrderByNo(char flag) throws SQLException{
   	 Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select * from hbc_gallery where gallery_flag = ? Order By gallery_No desc , gallery_ID desc";
            ps = con.prepareStatement(sql);
            ps.setString(1, flag+"");
            rs = ps.executeQuery();
            List<Gallery> list = new ArrayList<>();
            while(rs.next()){
                Gallery data = new Gallery();
                data.setGallery_ID(rs.getInt(1));
                data.setGallery_title(rs.getString(2));
                data.setGallery_url(rs.getString(3));
                data.setGallery_href(rs.getString(4));
                data.setGallery_flag(flag);
                data.setGallery_No(rs.getInt(6));
                list.add(data);
            }
            return list;
		}finally {																			//释放资源
	        SqlHelper.closeResult(rs);
	        SqlHelper.closePreparedStatement(ps);
	        SqlHelper.closeConneciton(con);  
		}
	}
    public boolean changeGallery(Gallery gallery) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = SqlHelper.connect();
            String sql = "update hbc_gallery set gallery_title = ? , gallery_url = ? ,gallery_href = ? ,gallery_flag = ? ,gallery_No = ? where gallery_ID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,gallery.getGallery_title());
            ps.setString(2,gallery.getGallery_url());
            ps.setString(3,gallery.getGallery_href());
            ps.setString(4, gallery.getGallery_flag()+"");
            ps.setInt(5, gallery.getGallery_No());
            ps.setInt(6,gallery.getGallery_ID());
            ps.executeUpdate();
            return true;
        }finally{
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);
        }
    }
    public boolean deleteGallery(Gallery gallery) throws SQLException{
        this.deleteGallery(gallery.getGallery_ID());
    	return true;
    }
    public boolean deleteGallery(int ID) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = SqlHelper.connect();
            String sql = "delete frome  hbc_gallery where gallery_ID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,ID);
            ps.executeUpdate();
            return true;
        }finally{
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);
        }
    }
    public boolean addGallery(Gallery gallery) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = SqlHelper.connect();
            String sql = "insert into hbc_gallery (gallery_title,gallery_url,gallery_href,gallery_flag,gallery_No)value(?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            //ps.setInt(1,user.getUser_ID());
	            ps.setString(1,gallery.getGallery_title());
	            ps.setString(2,gallery.getGallery_url());
	            ps.setString(3,gallery.getGallery_href());
	            ps.setString(4,gallery.getGallery_flag()+"");
	            ps.setInt(5, gallery.getGallery_No());
	            ps.executeUpdate();
	            return true;
        }finally{
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);
        }
    }
}

