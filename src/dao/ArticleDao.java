/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import common.SqlHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Article;
import model.Title;


/**
 *
 * @author Administrator
 */
public class ArticleDao {
    public int addArticle(Article article,int user_ID)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            article.setArticle_editor(user_ID);
            con = SqlHelper.connect();
            String sql = "INSERT INTO hbc_article (article_title, article_author, article_editor, article_author_name,"
                    + " article_creat_timestamp,article_edite_timestamp, article_save_location, article_path,"
                    + " article_text, article_state,article_category) VALUES (?,?,?,?,?,?,?,?,?,?,?)";// (2)写sql语句
            ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);// (3)建立预处理
            ps.setString(1,article.getArticle_title());
            ps.setInt(2,article.getArticle_author());
            ps.setInt(3,article.getArticle_editor());
            ps.setString(4,article.getArticle_author_name());
            ps.setTimestamp(5,article.getArticle_creat_timestamp());
            ps.setTimestamp(6,article.getArticle_edite_timestamp());
            ps.setString(7,article.getArticle_save_location()+"");
            ps.setString(8,article.getArticle_path());
            ps.setString(9,article.getArticle_text());
            ps.setString(10,article.getArticle_state()+"");
            ps.setString(11,article.getArticle_category());
            
            ps.executeUpdate();
            int id = -1;
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            return id;
        }finally {// 4.释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
	}
        //return -1;
    }
    public boolean deleteArticle(Title title)throws SQLException{
        return this.deleteArticle(title.getArticle_ID());
    }
    public boolean deleteArticle(int article_ID)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = SqlHelper.connect();
            String sql = "DELETE from hbc_article where article_ID = ?";// (2)写sql语句
            ps = con.prepareStatement(sql);// (3)建立预处理
            ps.setInt(1,article_ID);
            ps.executeUpdate();
            return true;
        }finally {// 4.释放资源
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
	}
    }
    public boolean changleArticle(Article article,int user_ID)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = SqlHelper.connect();
            String sql = "UPDATE hbc_article SET article_title = ?, article_author = ?, "
                    + "article_editor = ?, article_author_name = ?, article_creat_timestamp = ?"
                    + ",article_edite_timestamp = ?, article_save_location = ?"
                    + ", article_path = ?, article_text = ?, article_state = ? ,article_category = ? where article_ID = ?";// (2)写sql语句
            ps = con.prepareStatement(sql);// (3)建立预处理
            ps.setString(1,article.getArticle_title());
            ps.setInt(2,article.getArticle_author());
            ps.setInt(3,article.getArticle_editor());
            ps.setString(4,article.getArticle_author_name());
            ps.setTimestamp(5,article.getArticle_creat_timestamp());
            ps.setTimestamp(6,article.getArticle_edite_timestamp());
            ps.setString(7,article.getArticle_save_location()+"");
            ps.setString(8,article.getArticle_path());
            ps.setString(9,article.getArticle_text());
            ps.setString(10,article.getArticle_state()+"");
            ps.setString(11,article.getArticle_category());
            ps.setInt(12, user_ID);
            ps.executeUpdate();
            return true;
        }finally {// 4.释放资源
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
	}
    }
    public List<Title> getArticleList()throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select article_ID,article_title, article_author, article_editor, article_author_name,"
                    + " article_creat_timestamp,article_edite_timestamp,article_state,article_category from hbc_article";// (2)写sql语句
            ps = con.prepareStatement(sql);// (3)建立预处理
            rs = ps.executeQuery();
            List<Title> list = new ArrayList<>();
            while(rs.next()){
                Title title =new Title();
                title.setArticle_ID(rs.getInt(1));
                title.setArticle_title(rs.getString(2));
                title.setArticle_author(rs.getInt(3));
                title.setArticle_editor(rs.getInt(4));
                title.setArticle_author_name(rs.getString(5));
                title.setArticle_creat_timestamp(rs.getTimestamp(6));
                title.setArticle_edite_timestamp(rs.getTimestamp(7));
                title.setArticle_state(rs.getString(8).charAt(0));
                title.setArticle_category(rs.getString(9));
                list.add(title);
            }
            return list;
        }finally {// 4.释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
	}
    }
    public List<Title> getArticleList(char state)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select article_ID,article_title, article_author, article_editor, article_author_name,"
                    + " article_creat_timestamp,article_edite_timestamp,article_state,article_category from hbc_article where article_state = ?";// (2)写sql语句
            ps = con.prepareStatement(sql);// (3)建立预处理
            ps.setString(1, state+"");
            rs = ps.executeQuery();
            List<Title> list = new ArrayList<>();
            while(rs.next()){
                Title title =new Title();
                title.setArticle_ID(rs.getInt(1));
                title.setArticle_title(rs.getString(2));
                title.setArticle_author(rs.getInt(3));
                title.setArticle_editor(rs.getInt(4));
                title.setArticle_author_name(rs.getString(5));
                title.setArticle_creat_timestamp(rs.getTimestamp(6));
                title.setArticle_edite_timestamp(rs.getTimestamp(7));
                title.setArticle_state(rs.getString(8).charAt(0));
                title.setArticle_category(rs.getString(9));
                list.add(title);
            }
            return list;
        }finally {// 4.释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
	}
    }
    public List<Title> getArticleList(String authorName,char state)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select article_ID,article_title, article_author, article_editor,"
                    + "article_author_name,article_creat_timestamp,article_edite_timestamp,"
                    + "article_state,article_category from hbc_article  where article_state = ? and article_author_name = ?";// (2)写sql语句
            ps = con.prepareStatement(sql);// (3)建立预处理
            ps.setString(1,state+"");
            ps.setString(2,authorName);
            rs = ps.executeQuery();
            List<Title> list = new ArrayList<>();
            while(rs.next()){
                Title title =new Title();
                title.setArticle_ID(rs.getInt(1));
                title.setArticle_title(rs.getString(2));
                title.setArticle_author(rs.getInt(3));
                title.setArticle_editor(rs.getInt(4));
                title.setArticle_author_name(rs.getString(5));
                title.setArticle_creat_timestamp(rs.getTimestamp(6));
                title.setArticle_edite_timestamp(rs.getTimestamp(7));
                title.setArticle_state(rs.getString(8).charAt(0));
                title.setArticle_category(rs.getString(9));
                list.add(title);
            }
            return list;
        }finally {// 4.释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
	}
    }
    public List<Title> getArticleList(int author_ID,char state)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select article_ID,article_title, article_author, article_editor,"
                    + "article_author_name,article_creat_timestamp,article_edite_timestamp,"
                    + "article_state,article_category from hbc_article  where article_state = ? and article_author = ?";// (2)写sql语句
            ps = con.prepareStatement(sql);// (3)建立预处理
            ps.setString(1,state+"");
            ps.setInt(2,author_ID);
            rs = ps.executeQuery();
            List<Title> list = new ArrayList<>();
            while(rs.next()){
                Title title =new Title();
                title.setArticle_ID(rs.getInt(1));
                title.setArticle_title(rs.getString(2));
                title.setArticle_author(rs.getInt(3));
                title.setArticle_editor(rs.getInt(4));
                title.setArticle_author_name(rs.getString(5));
                title.setArticle_creat_timestamp(rs.getTimestamp(6));
                title.setArticle_edite_timestamp(rs.getTimestamp(7));
                title.setArticle_state(rs.getString(8).charAt(0));
                title.setArticle_category(rs.getString(9));
                list.add(title);
            }
            return list;
        }finally {// 4.释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
	}
    }
    public List<Title> getArticleList(String authorName)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select article_ID,article_title, article_author, article_editor,"
                    + "article_author_name,article_creat_timestamp,article_edite_timestamp,"
                    + "article_state,article_category from hbc_article  where article_author = ?";// (2)写sql语句
            ps = con.prepareStatement(sql);// (3)建立预处理
            ps.setString(1,authorName);
            rs = ps.executeQuery();
            List<Title> list = new ArrayList<>();
            while(rs.next()){
                Title title =new Title();
                title.setArticle_ID(rs.getInt(1));
                title.setArticle_title(rs.getString(2));
                title.setArticle_author(rs.getInt(3));
                title.setArticle_editor(rs.getInt(4));
                title.setArticle_author_name(rs.getString(5));
                title.setArticle_creat_timestamp(rs.getTimestamp(6));
                title.setArticle_edite_timestamp(rs.getTimestamp(7));
                title.setArticle_state(rs.getString(8).charAt(0));
                title.setArticle_category(rs.getString(9));
                list.add(title);
            }
            return list;
        }finally {// 4.释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
	}
    }
    public List<Title> getArticleList(int author_ID)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select article_ID,article_title, article_author, article_editor,"
                    + "article_author_name,article_creat_timestamp,article_edite_timestamp,"
                    + "article_state,article_category from hbc_article  where article_author = ?";// (2)写sql语句
            ps = con.prepareStatement(sql);// (3)建立预处理
            ps.setInt(1,author_ID);
            rs = ps.executeQuery();
            List<Title> list = new ArrayList<>();
            while(rs.next()){
                Title title =new Title();
                title.setArticle_ID(rs.getInt(1));
                title.setArticle_title(rs.getString(2));
                title.setArticle_author(rs.getInt(3));
                title.setArticle_editor(rs.getInt(4));
                title.setArticle_author_name(rs.getString(5));
                title.setArticle_creat_timestamp(rs.getTimestamp(6));
                title.setArticle_edite_timestamp(rs.getTimestamp(7));
                title.setArticle_state(rs.getString(8).charAt(0));
                title.setArticle_category(rs.getString(9));
                list.add(title);
            }
            return list;
        }finally {// 4.释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
	}
    }
     public List<Title> getArticleListByEditor(int editor_ID,char state)throws  SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select article_ID,article_title, article_author, article_editor,"
                    + "article_author_name,article_creat_timestamp,article_edite_timestamp,"
                    + "article_state,article_category from hbc_article  where article_editor = ? and article_state = ?";// (2)写sql语句
            ps = con.prepareStatement(sql);// (3)建立预处理
            ps.setInt(1,editor_ID);
            ps.setString(2,state+"");
            rs = ps.executeQuery();
            List<Title> list = new ArrayList<>();
            while(rs.next()){
                Title title =new Title();
                title.setArticle_ID(rs.getInt(1));
                title.setArticle_title(rs.getString(2));
                title.setArticle_author(rs.getInt(3));
                title.setArticle_editor(rs.getInt(4));
                title.setArticle_author_name(rs.getString(5));
                title.setArticle_creat_timestamp(rs.getTimestamp(6));
                title.setArticle_edite_timestamp(rs.getTimestamp(7));
                title.setArticle_state(rs.getString(8).charAt(0));
                title.setArticle_category(rs.getString(9));
                list.add(title);
            }
            return list;
        }finally {// 4.释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
	}
    }
    public List<Title> getArticleListByEditor(int editor_ID)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select article_ID,article_title, article_author, article_editor,"
                    + "article_author_name,article_creat_timestamp,article_edite_timestamp,"
                    + "article_state,article_category from hbc_article  where article_editor = ?";// (2)写sql语句
            ps = con.prepareStatement(sql);// (3)建立预处理
            ps.setInt(1,editor_ID);
            rs = ps.executeQuery();
            List<Title> list = new ArrayList<>();
            while(rs.next()){
                Title title =new Title();
                title.setArticle_ID(rs.getInt(1));
                title.setArticle_title(rs.getString(2));
                title.setArticle_author(rs.getInt(3));
                title.setArticle_editor(rs.getInt(4));
                title.setArticle_author_name(rs.getString(5));
                title.setArticle_creat_timestamp(rs.getTimestamp(6));
                title.setArticle_edite_timestamp(rs.getTimestamp(7));
                title.setArticle_state(rs.getString(8).charAt(0)); 
                title.setArticle_category(rs.getString(9));
                list.add(title);
            }
            return list;
        }finally {// 4.释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
	}
    }
    public Article getArticle(int article_ID)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select * from hbc_article  where article_ID = ?";// (2)写sql语句
            ps = con.prepareStatement(sql);// (3)建立预处理
            ps.setInt(1,article_ID);
            rs = ps.executeQuery();
            Article article = new Article();
            while(rs.next()){
                article.setArticle_ID(article_ID);
                article.setArticle_author(rs.getInt("article_author"));
                article.setArticle_editor(rs.getInt("article_editor"));
                article.setArticle_author_name(rs.getString("article_author_name"));
                article.setArticle_creat_timestamp(rs.getTimestamp("article_creat_timestamp"));
                article.setArticle_edite_timestamp(rs.getTimestamp("article_edite_timestamp"));
                article.setArticle_save_location(rs.getString("article_save_location").charAt(0));
                article.setArticle_path(rs.getString("article_path"));
                article.setArticle_text(rs.getString("article_text"));
                article.setArticle_state(rs.getString("article_state").charAt(0));
                article.setArticle_title(rs.getString("article_title"));
                article.setArticle_category(rs.getString("article_category"));
            }
            return article;
        }finally {// 4.释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
	}
    }
}
