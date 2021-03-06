/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package action;


import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
    int article_ID;										//文章ID 参数
    Article article;									//文章内容 参数
    String json;										//异步json 用来实现瀑布流
    PageList<Title> plist;								//分页文章列表
    int pageNo=1;										//页码
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
    	List<Title> list = new ArticleService().getArticleList(Evn.CATEGORY.ANNOUNCMENT,Evn.ARTICLE.USE);
    	int i = 0;
    	int j = list.size()-1;
    	while(i<j)
    	{
    		Title temp = list.get(i);
    		list.set(i,list.get(j));
    		list.set(j,temp);
    		i++;
    		j--;
    	}
    	plist = new PageList<>(list,list.size(),10,pageNo,"hehe");
    	return "listInfo";
    }
    public String ListAnimal(){
    	List<Title> list = new ArticleService().getArticleList(Evn.CATEGORY.ANIMAL,Evn.ARTICLE.USE);
    	JSONArray jsonArray = new JSONArray();
    	for(Title a:list){
    		JSONObject json = new JSONObject();
    		json.put("ID", a.getArticle_ID());
        	json.put("title",a.getArticle_title());
        	json.put("img", a.getArticle_image());
        	jsonArray.add(json);
    	}
    	json = jsonArray.toString();    	        	
    	return "listImg";
    }
    public String ListHistory(){
    	List<Title> list = new ArticleService().getArticleList(Evn.CATEGORY.HISTORY,Evn.ARTICLE.USE);
    	JSONArray jsonArray = new JSONArray();
    	for(Title a:list){
    		JSONObject json = new JSONObject();
    		json.put("ID", a.getArticle_ID());
        	json.put("title",a.getArticle_title());
        	json.put("img", a.getArticle_image());
        	jsonArray.add(json);
    	}
    	json = jsonArray.toString();    	        	
    	return "listImg";
    }
    public String ListExpand(){
    	List<Title> list = new ArticleService().getArticleList(Evn.CATEGORY.EXPAND,Evn.ARTICLE.USE);
    	JSONArray jsonArray = new JSONArray();
    	for(Title a:list){
    		JSONObject json = new JSONObject();
    		json.put("ID", a.getArticle_ID());
        	json.put("title",a.getArticle_title());
        	json.put("img", a.getArticle_image());
        	jsonArray.add(json);
    	}
    	json = jsonArray.toString();    	        	
    	return "listImg";
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
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
}
