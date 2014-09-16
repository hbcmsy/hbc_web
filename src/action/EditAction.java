package action;

import com.opensymphony.xwork2.ActionSupport;
import common.Evn;
import common.PageList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Article;
import model.Title;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.ArticleService;

@SuppressWarnings("serial")
public class EditAction extends ActionSupport{
	int article_ID;
    
    /*edit add */
    String edit_title;
    String edit_category;
    String edit_text;
    String edit_image;
    /*add*/
    
    /*
     * list 获取当前用户后 判断权限 按照 state的值输出 列表中文章的类别
     * 初始值为已经发布的公告
     * 如果usr为真 则只输出用户自己的文章 a(all) u(author) e(editor)
     */
    PageList<Title> plist;
    int pageNo=1;
    //输入量
    Evn.ARTICLE state = Evn.ARTICLE.USE;
    String STATE = "ALL";
    String CATEGORY = "g";
    char usr = 'a';
    public String list(){
        HttpServletRequest request = ServletActionContext.getRequest();
        User user =(User)request.getSession().getAttribute("userInfo");
        if(user == null)
            return "list";
        //获取用户名
        List<Title> list = new ArticleService().getArticleList(usr, user.getUser_ID(), CATEGORY, this.getState(STATE));
        plist = new PageList<>(list,list.size(),30,pageNo,"hehe");
        return "list";
    }
    public String add(){
        HttpServletRequest request = ServletActionContext.getRequest();
        User user =(User)request.getSession().getAttribute("userInfo");
       
        request.getSession().setAttribute("article_ID",article_ID);
        if(user==null)
            return this.list();
        if(user.getUser_authority()=='u')
            return this.list();
        ArticleService as = new ArticleService();
        article_ID = as.addArticle(" "," "," ",user.getUser_ID(),Evn.ARTICLE_SAVE_LOCATION.DATABASE);
        edit_title=edit_text="待编辑";
        return "add";
    }
    public String edit(){
        HttpServletRequest request = ServletActionContext.getRequest();
        User user =(User)request.getSession().getAttribute("userInfo");
       
        request.getSession().setAttribute("article_ID",article_ID);
        if(user==null)
            return this.list();
        if(user.getUser_authority()=='u')
            return this.list();
        ArticleService as = new ArticleService();
        Article article = as.getAreticle(article_ID);
        edit_title = article.getArticle_title();
        edit_text = article.getArticle_text();
        edit_category = article.getArticle_category();
        edit_image = article.getArticle_image();
        return "edit";
        
    }
    public String delete(){
        HttpServletRequest request = ServletActionContext.getRequest();
        User user =(User)request.getSession().getAttribute("userInfo");
        if(user==null)
            return this.list();
        if(user.getUser_authority()=='u')
            return this.list();
        new ArticleService().deleteArticle(article_ID);
        return list();
    }
    
    public String save(){
        HttpServletRequest request = ServletActionContext.getRequest();
        User user =(User)request.getSession().getAttribute("userInfo");
       
        request.getSession().removeAttribute("article_ID");
        if(user==null)
            return this.list();
        if(user.getUser_authority()=='u')
            return this.list();
        ArticleService as = new ArticleService();
        Article article = as.getAreticle(article_ID);
        article.setArticle_category(edit_category);
        article.setArticle_title(edit_title);
        article.setArticle_text(edit_text);
        article.setArticle_image(edit_image);
        as.changleArticle(article, article.getArticle_ID(),user.getUser_ID());
        return this.list();
    }
    public String release(){
        HttpServletRequest request = ServletActionContext.getRequest();
        User user =(User)request.getSession().getAttribute("userInfo");
        if(user==null)
            return this.list();
        if(user.getUser_authority()=='u')
            return this.list();
        ArticleService as = new ArticleService();
        as.changleAricleState(article_ID,Evn.ARTICLE.USE);
        return this.list();
    }
    Evn.ARTICLE getState(String STATE){
    	switch(STATE.charAt(0))
    	{
    	case 'A':
    		return Evn.ARTICLE.ALL;
    	case 'U':
    		return Evn.ARTICLE.USE;
    	case 'E':
    		return Evn.ARTICLE.EDIT;
    	case 'D':
    		return Evn.ARTICLE.DELETE;
		default:
			return Evn.ARTICLE.ALL;
    	}
    	 
    } 
    public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	
    public PageList<Title> getPlist() {
        return plist;
    }

    public void setPlist(PageList<Title> plist) {
        this.plist = plist;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public Evn.ARTICLE getState() {
        return state;
    }

    public void setState(Evn.ARTICLE state) {
        this.state = state;
    }
    public int getArticle_ID() {
        return article_ID;
    }

    public void setArticle_ID(int article_ID) {
        this.article_ID = article_ID;
    }

	public char getUsr() {
		return usr;
	}
	public void setUsr(char usr) {
		this.usr = usr;
	}
	public String getCATEGORY() {
		return CATEGORY;
	}
	public void setCATEGORY(String cATEGORY) {
		CATEGORY = cATEGORY;
	}
	public String getEdit_title() {
		return edit_title;
	}
	public void setEdit_title(String edit_title) {
		this.edit_title = edit_title;
	}
	public String getEdit_category() {
		return edit_category;
	}
	public void setEdit_category(String edit_category) {
		this.edit_category = edit_category;
	}
	public String getEdit_text() {
		return edit_text;
	}
	public void setEdit_text(String edit_text) {
		this.edit_text = edit_text;
	}
	public String getEdit_image() {
		return edit_image;
	}
	public void setEdit_image(String edit_image) {
		this.edit_image = edit_image;
	}
    
}
