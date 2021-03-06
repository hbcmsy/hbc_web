/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import common.Evn;
import dao.ArticleDao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Article;
import model.Title;

/**
 *
 * @author Administrator
 */
public class ArticleService {
    public enum FLAG{AUTHOR,EDITOR,ALL};													//查找时对应底层的三种查询方式

    public static FLAG getFlag(String flag){
    	switch (flag.charAt(0)) {
		case 'a':
			return FLAG.ALL;
		case 'e':
			return FLAG.EDITOR;
		case 'u':
			return FLAG.AUTHOR;
		case 'A':
			return FLAG.ALL;
		case 'E':
			return FLAG.EDITOR;
		case 'U':
			return FLAG.AUTHOR;
		default:
			return null;
		}
    }
    public int addArticle(Article article,int user_ID){										
        try {
            return new ArticleDao().addArticle(article, user_ID);
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    public int addArticle(String title,String text,String category,int user_ID,Evn.ARTICLE_SAVE_LOCATION location){
        Article article = new Article();
        article.setArticle_author(user_ID);
        article.setArticle_category(category);
        article.setArticle_author_name(new UserService().getUser(user_ID).getUser_name());
        article.setArticle_creat_timestamp(new Timestamp(System.currentTimeMillis()));
        article.setArticle_edite_timestamp(new Timestamp(System.currentTimeMillis()));
        article.setArticle_editor(user_ID);
        article.setArticle_title(title);
        if(location==Evn.ARTICLE_SAVE_LOCATION.DATABASE){
            article.setArticle_path(" ");
            article.setArticle_save_location('d');
        }else{
            article.setArticle_path(" ");
            article.setArticle_save_location('f');
            return -1;
        }
        article.setArticle_state('e');
        article.setArticle_text(text);
        
        return this.addArticle(article, user_ID);
    }
    public boolean deleteArticle(int article_ID){
         try {
            if(this.getAreticle(article_ID).getArticle_state()=='d')
                return new ArticleDao().deleteArticle(article_ID);
            else
                return new ArticleService().changleAricleState(article_ID, Evn.ARTICLE.DELETE);
         } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean deleteArticle(Article article){
        try {
            if(article.getArticle_state()=='d')
                return new ArticleDao().deleteArticle(article.getArticle_ID());
            else
                return new ArticleService().changleAricleState(article.getArticle_ID(), Evn.ARTICLE.DELETE);
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean changleAricleState(int article_ID,Evn.ARTICLE state){
        try {
            Article article = this.getAreticle(article_ID);
            switch(state){
                case USE:
                    article.setArticle_state('u');
                    break;
                case DELETE:
                    article.setArticle_state('d');
                    break;
                case EDIT:
                    article.setArticle_state('e');
                    break;
                default:       
                    break;
            }
            return new ArticleDao().changleArticle(article, article_ID);
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean changleArticle(Article article,int article_ID,int user_ID){
        try {
            article.setArticle_editor(article_ID);
            article.setArticle_edite_timestamp(new Timestamp(System.currentTimeMillis()));
            return new ArticleDao().changleArticle(article, article_ID);
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public List<Title> getArticleList(Evn.CATEGORY category,Evn.ARTICLE state){
    	return this.getArticleList(ArticleService.FLAG.ALL, -1,category, state);
    }
    public List<Title> getArticleList(ArticleService.FLAG flag,int id,Evn.CATEGORY category,Evn.ARTICLE state){
    	char ch_flag = ' ';
    	char ch_category = ' ';
    	try {
            switch (category) {
				case ANNOUNCMENT:
					ch_category = 'g';
					break;
				case ANIMAL:
					ch_category = 'd';
					break;
				case HISTORY:
					ch_category = 'l';
					break;
				case EXPAND:
					ch_category = 't';
					break;
				default:
					break;
			}
            switch (flag) {
			case AUTHOR:
				ch_flag = 'u';
				break;
			case EDITOR:
				ch_flag = 'e';
				break;
			case ALL:
				ch_flag = 'a';
				break;
			default:
				break;
			}
            switch(state){
            case USE:
                return new ArticleDao().getArticleList(ch_flag,id,ch_category,'u');
            case DELETE:
                return new ArticleDao().getArticleList(ch_flag,id,ch_category,'d');
            case EDIT:
                return new ArticleDao().getArticleList(ch_flag,id,ch_category,'e');
            case ALL:
                return new ArticleDao().getArticleList(ch_flag,id,ch_category,'a');
            default:
                return null;
        }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    /**************************************************************************************************
    											准备弃用
    **************************************************************************************************/
	public Article getAreticle(int article_ID){
        try {
            return new ArticleDao().getArticle(article_ID);
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
	
	
    public List<Title> getArticleList(Evn.ARTICLE state){
        try {
            switch(state){
                case USE:
                    return new ArticleDao().getArticleList('u');
                case DELETE:
                    return new ArticleDao().getArticleList('d');
                case EDIT:
                    return new ArticleDao().getArticleList('e');
                case ALL:
                    return new ArticleDao().getArticleList();
                default:
                    return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<Title> getArticleList(int user_ID,Evn.ARTICLE state){
        try {
            switch(state){
                case USE:
                    return new ArticleDao().getArticleList(user_ID,'u');
                case DELETE:
                    return new ArticleDao().getArticleList(user_ID,'d');
                case EDIT:
                    return new ArticleDao().getArticleList(user_ID,'e');
                case ALL:
                    return new ArticleDao().getArticleList(user_ID);
                default:
                    return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<Title> getArticleList(String name,Evn.ARTICLE state){
        try {
            switch(state){
                case USE:
                    return new ArticleDao().getArticleList(name,'u');
                case DELETE:
                    return new ArticleDao().getArticleList(name,'d');
                case EDIT:
                    return new ArticleDao().getArticleList(name,'e');
                case ALL:
                    return new ArticleDao().getArticleList(name);
                default:
                    return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    /**************************************************************************************************************/
    
 
}

