/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Timestamp;

/**
 *
 * @author Administrator
 */
public class Article {
	
    int article_ID;
    String article_title;
    String article_category;
    int article_author;
    int article_editor;
    char article_state;
    String article_author_name;
    Timestamp article_creat_timestamp;
    Timestamp article_edite_timestamp;
    char article_save_location;
    String article_path;
    String article_text;
    String article_image;

    public String getArticle_image() {
		return article_image;
	}

	public void setArticle_image(String article_image) {
		this.article_image = article_image;
	}

	public char getArticle_state() {
        return article_state;
    }

    public void setArticle_state(char article_state) {
        this.article_state = article_state;
    }
    
    public int getArticle_ID() {
        return article_ID;
    }

    public void setArticle_ID(int article_ID) {
        this.article_ID = article_ID;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }


    public int getArticle_author() {
        return article_author;
    }

    public void setArticle_author(int article_author) {
        this.article_author = article_author;
    }

    public int getArticle_editor() {
        return article_editor;
    }

    public void setArticle_editor(int article_editor) {
        this.article_editor = article_editor;
    }

    public String getArticle_author_name() {
        return article_author_name;
    }

    public void setArticle_author_name(String article_author_name) {
        this.article_author_name = article_author_name;
    }

    public Timestamp getArticle_creat_timestamp() {
        return article_creat_timestamp;
    }

    public void setArticle_creat_timestamp(Timestamp article_creat_timestamp) {
        this.article_creat_timestamp = article_creat_timestamp;
    }

    public Timestamp getArticle_edite_timestamp() {
        return article_edite_timestamp;
    }

    public void setArticle_edite_timestamp(Timestamp article_edite_timestamp) {
        this.article_edite_timestamp = article_edite_timestamp;
    }

    public char getArticle_save_location() {
        return article_save_location;
    }

    public void setArticle_save_location(char article_save_location) {
        this.article_save_location = article_save_location;
    }

    public String getArticle_path() {
        return article_path;
    }

    public void setArticle_path(String article_path) {
        this.article_path = article_path;
    }

    public String getArticle_text() {
        return article_text;
    }

    public void setArticle_text(String article_text) {
        this.article_text = article_text;
    }

    public String getArticle_category() {
        return article_category;
    }

    public void setArticle_category(String article_category) {
        this.article_category = article_category;
    }
    
}
