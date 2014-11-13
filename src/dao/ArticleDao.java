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
 * @author yyf 
 */
public class ArticleDao {
    /**
     * 添加文章的数据库处理
     * @param article
     * 增加的文章的内容
     * @param user_ID
     * 添加的文章的作者
     * @return
     * 返回值为添加的文章的article_ID,如果返回失败则返回-1
     */
	public int addArticle(Article article,int user_ID)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            article.setArticle_editor(user_ID);												//修改/设置文章的user_ID
            con = SqlHelper.connect();
            String sql = "INSERT INTO hbc_article (article_title, article_author, "
            		+ "article_editor, article_author_name,article_creat_timestamp,"
            		+ "article_edite_timestamp, article_save_location, article_path,"
                    + " article_text, article_state,article_category,article_image) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";									//sql语句
            ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);					//建立预处理,设置返回ID
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
            ps.setString(12, article.getArticle_image());
            
            ps.executeUpdate();
            int id = -1;																	//初始化返回的article_ID,
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            return id;
        }finally {																			//释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
        }
    }//end of addArticle(Article article,int user_ID)
	
	/**
	 * 删除文章,内部使用article_ID实现删除
	 * @param title
	 * 所要删除的文章
	 * @return 
	 * 成功返回true
	 */
    public boolean deleteArticle(Title title)throws SQLException{
        return this.deleteArticle(title.getArticle_ID());
    }//end of deleteArticle(Title title)
    
    /**
     * 删除文章
     * @param article_ID
     * 需要删除的文章的article_ID
     * @return
     * 成功返回true
     * @throws SQLException
     */
    public boolean deleteArticle(int article_ID)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = SqlHelper.connect();
            String sql = "DELETE from hbc_article where article_ID = ?";					//sql语句
            ps = con.prepareStatement(sql);													//建立预处理
            ps.setInt(1,article_ID);
            ps.executeUpdate();
            return true;
        }finally {																			//释放资源
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
        }
    }//end of deleteArticle(int article_ID)
    
    /**
     * 修改文章
     * @param article
     * 已经修改过的文章,并利用其中的article_ID进行查找,故article_ID不要修改
     * @param user_ID
     * 修改文章的用户的user_ID.用于在数据库中标识文章的最后修改人
     * @return
     * 成功返回true
     * @throws SQLException
     */
    public boolean changleArticle(Article article,int user_ID)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = SqlHelper.connect();
            String sql = "UPDATE hbc_article SET article_title = ?, article_author = ?, "
                    + "article_editor = ?, article_author_name = ?, article_creat_timestamp = ?"
                    + ",article_edite_timestamp = ?, article_save_location = ?"
                    + ", article_path = ?, article_text = ?, article_state = ? ,"
                    + "article_category = ?,article_image = ? where article_ID = ?";		//写sql语句
            ps = con.prepareStatement(sql);													//建立预处理
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
            ps.setString(12, article.getArticle_image());
            ps.setInt(13, user_ID);
            ps.executeUpdate();
            return true;
        }finally {//释放资源
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
        }
    }//end of changleArticle(Article article,int user_ID)
    
    /**
     * 利用article_ID取得真个文章.这是获取文章内容的唯一方法,其他查询语句只能获取文章信息,不能获取内容
     * @param article_ID
     * 需要得到的文章的article_ID
     * @return
     * 返回文章
     * @throws SQLException
     */
    public Article getArticle(int article_ID)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select * from hbc_article  where article_ID = ?";					//写sql语句
            ps = con.prepareStatement(sql);													//建立预处理
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
                article.setArticle_image(rs.getString("article_image"));
            }
            return article;
        }finally {																			//释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
        }
    }//end of Article getArticle(int article_ID)
    
    /**
     * 获取所有文章信息的列表
     * @return
     * 所有文章的List<Title> 
     * @throws SQLException
     */
    public List<Title> getArticleList()throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select article_ID,article_title, article_author,"
            		+ " article_editor, article_author_name,"
                    + " article_creat_timestamp,article_edite_timestamp,"
                    + "article_state,article_category,article_image from hbc_article";		//写sql语句
            ps = con.prepareStatement(sql);													//建立预处理
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
                title.setArticle_image(rs.getString(10));
                list.add(title);
            }
            return list;
    }finally {																				//释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
        }
    }//end of getArticleList()
    
    /**
     * 获取处于某个状态的文章标题列表
     * @param state
     * 需要获取的文章状态  u已发布 d回收站 e正在编辑没有发布的
     * @return
     * 需要获取的List<Title>
     * @throws SQLException
     */
    public List<Title> getArticleList(char state)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select article_ID,article_title, article_author, "
            		+ "article_editor, article_author_name,article_creat_timestamp"
            		+ ",article_edite_timestamp,article_state,article_category"
            		+ ",article_image from hbc_article where article_state = ?";			//写sql语句
            ps = con.prepareStatement(sql);													//建立预处理
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
                title.setArticle_image(rs.getString(10));
                list.add(title);
            }
            return list;
        }finally {																			//释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
        }
    }//end of getArticleList(char state)
    
    /**
     * 根据作者和文章状态进行文章标题的查询
     * @param authorName
     * 所要查询的文章标题列表的作者名字
     * @param state
     * 所要查询的文章标题列表的状态
     * @see ArticleDao#getArticleList(char state)
     * @return
     * 文章标题列表 List<Title>
     * @throws SQLException
     */
    public List<Title> getArticleList(String authorName,char state)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select article_ID,article_title, article_author,"
            		+ "article_editor,article_author_name,"
            		+ "article_creat_timestamp,article_edite_timestamp,"
            		+ "article_state,article_category ,article_image "
            		+ "from hbc_article  where article_state = ? "
            		+ "and article_author_name = ?";										//写sql语句
            ps = con.prepareStatement(sql);													//建立预处理
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
                title.setArticle_image(rs.getString(10));
                list.add(title);
            }
            return list;
        }finally {																			//释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
        }
    }//end of getArticleList(String authorName,char state)
    
    /**
     * 根据作者的user_ID和文章状态进行查询
     * @param author_ID
     * 作者的user_ID
     * @param state
     * 文章的状态
     * @see ArticleDao#getArticleList(char state)
     * @return
     * @throws SQLException
     */
    public List<Title> getArticleList(int author_ID,char state)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select article_ID,article_title, article_author,"
            		+ "article_editor,article_author_name,"
            		+ "article_creat_timestamp,article_edite_timestamp,"
                    + "article_state,article_category ,article_image "
                    + "from hbc_article  where article_state = ? "
                    + "and article_author = ?";												//写sql语句
            ps = con.prepareStatement(sql);													//建立预处理
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
                title.setArticle_image(rs.getString(10));
                list.add(title);
            }
            return list;
        }finally {																			//释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
        }
    }// end of getArticleList(int author_ID,char state)
    
    /**
     * 获取文章标题列表
     * @param authorName
     * 要获取的文章的用户名
     * @return
     * List<Article>
     * @throws SQLException
     */
    public List<Title> getArticleList(String authorName)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select article_ID,article_title, article_author,"
            		+ "article_editor,article_author_name,"
            		+ "article_creat_timestamp,article_edite_timestamp,"
                    + "article_state,article_category,article_image "
                    + "from hbc_article  where article_author = ?";							//写sql语句
            ps = con.prepareStatement(sql);													//建立预处理
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
                title.setArticle_image(rs.getString(10));
                list.add(title);
            }
            return list;
        }finally {																			//释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
        }
    }//end of getArticleList(String authorName)
    
    /**
     * 根据文章作者的user_ID获取文章列表
     * @param author_ID
     * 作者的user_ID
     * @return
     * List<Title>
     * @throws SQLException
     */
    public List<Title> getArticleList(int author_ID)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select article_ID,article_title, article_author,"
            		+ "article_editor,article_author_name,"
            		+ "article_creat_timestamp,article_edite_timestamp,"
                    + "article_state,article_category,article_image "
                    + "from hbc_article  where article_author = ?";							//写sql语句
            ps = con.prepareStatement(sql);													//建立预处理
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
                title.setArticle_image(rs.getString(10));
                list.add(title);
            }
            return list;
    }finally {																				//释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
        }
    }//end of getArticleList(int author_ID)
    
    /**
     * 根据最后的修改者进行查询
     * @param editor_ID
     * 最后修改者的user_ID
     * @param state
     * 文章状态
     * @see ArticleDao#getArticleList(char state)
     * @return
     * List<Article>
     * @throws SQLException
     */
    public List<Title> getArticleListByEditor(int editor_ID,char state)throws  SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select article_ID,article_title, article_author,"
            		+ "article_editor,article_author_name,"
            		+ "article_creat_timestamp,article_edite_timestamp,"
                    + "article_state,article_category,article_image "
                    + "from hbc_article  where article_editor = ? and article_state = ?";	//写sql语句
            ps = con.prepareStatement(sql);													//建立预处理
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
                title.setArticle_image(rs.getString(10));
                list.add(title);
            }
            return list;
        }finally {																			//释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
        }
    }//end of getArticleListByEditor(int editor_ID,char state)
    
    /**
     * 根据最后的修改者进行查询
     * @param editor_ID
     * 最后修改者的user_ID
     * @return
     * List<Title>
     * @throws SQLException
     */
    public List<Title> getArticleListByEditor(int editor_ID)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = SqlHelper.connect();
            String sql = "select article_ID,article_title, article_author,"
            		+ "article_editor,article_author_name,"
            		+ "article_creat_timestamp,article_edite_timestamp,"
                    + "article_state,article_category,article_image "
                    + "from hbc_article  where article_editor = ?";							//写sql语句
            ps = con.prepareStatement(sql);													//建立预处理
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
                title.setArticle_image(rs.getString(10));
                list.add(title);
            }
            return list;
        }finally {																			//释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
        }
    }//end of getArticleListByEditor
    
    /**
     * 根据作者,最后修改者,分类和状态进行联合查询
     * @param flag
     * 表示查询的是所有(a),根据作者(u).根据最后修改者(e) 如果 flag是a那么 id=-1
     * @param id
     * 用户ID,如果查询作者则为作者ID,查询最后修改者则为最后修改者ID,查询所有时忽略为-1
     * @param category
     * 为文章分类 若不分category则category为 g  分裂为g(公告) d(动物园) l(历史回顾馆) t(拓展区)
     * @param state
     * 文章状态 u e d a(所有)
     * @see ArticleDao#getArticleList(char state)
     * @return
     * @throws SQLException
     */
    public List<Title> getArticleList(char flag,int id,char category,char state) throws SQLException{
    	String sql = "select article_ID,article_title, article_author,"
    			+ "article_editor,article_author_name,article_creat_timestamp,"
    			+ "article_edite_timestamp,article_state,article_category,"
    			+ "article_image from hbc_article where article_category like ? ";			//编写基础查询,附带category
    	switch(flag){
    		case 'a':
    			break;																		//查询所有则不增加其他语句
    		case 'u':
    			sql+="and article_author=? ";												//增加其他查询语句
    			break;
    		case 'e':
    			sql+="and article_editor=? ";
    			break;
			default:
				return null;
    	}
    	if(state!='a')																		//根据查询的state确定是否追加state的查询
    		sql+="and article_state=?";
    	
    	/*sql语句构造完毕*/
    	
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
		    con = SqlHelper.connect();
		    ps = con.prepareStatement(sql);													//建立预处理
	    	ps.setString(1,category+"%");													//传参加入匹配符号,保证匹配
		    if(flag=='a'){																	//根据flag和state状态确定  参数state和id是否需要传以及参数的位置
		    	if(state!='a')
		    		ps.setString(2,state+"");
		    }
		    else{
		    	if(state!='a'){
		    		ps.setInt(2,id);
		    		ps.setString(3,state+"");
		    	}else
		    	{
		    		ps.setInt(2,id);
		    	}
		    }
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
		        title.setArticle_image(rs.getString(10));
		        list.add(title);
		    }
		    return list;
		}finally {																			//释放资源
	        SqlHelper.closeResult(rs);
	        SqlHelper.closePreparedStatement(ps);
	        SqlHelper.closeConneciton(con);  
		}
	}//end of List<Title> getArticleList(char flag,int id,String category,char state)
}//end of Class ArticleDao
