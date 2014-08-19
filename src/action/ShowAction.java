/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package action;

import com.opensymphony.xwork2.ActionSupport;

import model.Article;
import service.ArticleService;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class ShowAction extends ActionSupport{
    int article_ID;
    Article article;
    public String show(){
        article = new ArticleService().getAreticle(article_ID);
        System.out.print("查看 action.showAction");
        return "show";
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
