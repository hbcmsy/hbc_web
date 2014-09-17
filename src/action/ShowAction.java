/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import common.Evn;
import common.PageList;
import model.Article;
import model.Title;
import service.ArticleService;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class ShowAction extends ActionSupport{
    int article_ID;
    Article article;
    
    PageList<Title> plist;
    int pageNo=1;
    public String show(){
        article = new ArticleService().getAreticle(article_ID);
        //System.out.print("查看 action.showAction");
        return "show";
    }
    public String info(){
        article = new ArticleService().getAreticle(article_ID);
        //System.out.print("查看 action.showAction");
        return "info";
    }
    public String listInfo(){
    	List<Title> list = new ArticleService().getArticleList('a',-1,"g",Evn.ARTICLE.USE);
    	plist = new PageList<>(list,list.size(),10,pageNo,"hehe");
    	return "listInfo";
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
	public int getArticle_ID() {
        return article_ID;
    }

    public void setArticle_ID(int article_ID) {
        this.article_ID = article_ID;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
