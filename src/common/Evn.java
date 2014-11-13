/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

/**
 *
 * @author Administrator
 */
public class Evn {
    public enum ARTICLE_SAVE_LOCATION{DATABASE,FILESYSTEM};
    public enum USER_AUTHORITY{USER,EDITER,ADMIN};
    public enum ARTICLE{USE,EDIT,DELETE,ALL};
    public enum CATEGORY{ANNOUNCMENT,ANIMAL,HISTORY,EXPAND};
    public static Evn.ARTICLE getARTICLE(String STATE){
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
	    	case 'q':
	    		return Evn.ARTICLE.ALL;
	    	case 'u':
	    		return Evn.ARTICLE.USE;
	    	case 'e':
	    		return Evn.ARTICLE.EDIT;
	    	case 'd':
	    		return Evn.ARTICLE.DELETE;
			default:
				return Evn.ARTICLE.ALL;
    	}
    }
    public static Evn.CATEGORY getCATEGORY(String CATEGORY){
    	switch(CATEGORY.charAt(0))
    	{
	    	case 'D':
	    		return Evn.CATEGORY.ANIMAL;
	    	case 'G':
	    		return Evn.CATEGORY.ANNOUNCMENT;
	    	case 'T':
	    		return Evn.CATEGORY.EXPAND;
	    	case 'L':
	    		return Evn.CATEGORY.HISTORY;
	    	case 'd':
	    		return Evn.CATEGORY.ANIMAL;
	    	case 'g':
	    		return Evn.CATEGORY.ANNOUNCMENT;
	    	case 't':
	    		return Evn.CATEGORY.EXPAND;
	    	case 'l':
	    		return Evn.CATEGORY.HISTORY;	
	    	default:
	    		return null;
    	}
    }
    public static String getArticle(ARTICLE state){
    	switch(state)
    	{
	    	case ALL:
	    		return "a";
	    	case USE:
	    		return "u";
	    	case EDIT:
	    		return "e";
	    	case DELETE:
	    		return "d";
			default:
				return null;
    	}
    }
    public static String getCatrgory(CATEGORY category){
    	switch(category)
    	{
	    	case ANIMAL:
	    		return "d";
	    	case ANNOUNCMENT:
	    		return "g";
	    	case EXPAND:
	    		return "t";
	    	case HISTORY:
	    		return "l";
	    	default:
	    		return null;
    	}
    }
    
}
