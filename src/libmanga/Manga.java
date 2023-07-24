/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libmanga;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import libtool.HTTPConnect;

/**
 *
 * @author naufal
 */


public class Manga {
    
    
    public static class MangaItem{
        private String judul;
        private String link;
        private String mangaType;
        private Image img;
        private String score;
        private HTTPConnect HC = new HTTPConnect();
        
        public void setJudul(String judul){
            this.judul = judul;
        }
        public void setLink(String link){
            this.link = link;
        }
        public void setMangaType(String mangaType){
            this.mangaType = mangaType;
        }
        public void setImg(String i){
            try {
                this.img = ImageIO.read(HC.getInputStream(i));
            } catch (IOException ex) {
                System.out.println("Error !");
            } catch (IllegalArgumentException e){
                try {
                    this.img = ImageIO.read(new File("img/ErrorImage.png"));
                } catch (IOException ex) {
                    Logger.getLogger(Manga.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        public void setScore(String score){
            this.score = score;
        }
        public String getJudul(){
            return this.judul;
        }
        public String getLink(){
            return this.link;
        }
        public String getMangaType(){
            return this.mangaType;
        }
        public Image getImg(){
            return this.img;
        }
        public String getScore(){
            return this.score;
        }
        public String toJson(){
            String json = "{"+
                    "\"judul\":\""+this.judul+"\","+
                    "\"link\":\""+this.link+"\","+
                    "\"mangatype\":\""+this.mangaType+"\","+
                    "\"img\":\""+this.img+"\","+
                    "\"score\":\""+this.score+"\""+
                    "}";
            return json;
        }
    }
    public static class MangaDetail{
        private String judul;
        private Image img;
        private String altJudul;
        private String [] genre;
        private String rating;
        private String rilis;
        private String type_;
        private String update;
        private String [] other;
        private Chapter chapter;
        private String sinopsis;
        private HTTPConnect HC = new HTTPConnect();
        
        public void setJudul(String judul){
            this.judul = judul;
        }
        public void setImg(String i){
            try {
                this.img = ImageIO.read(HC.getInputStream(i));
            } catch (IOException ex) {
                System.out.println("Error !");
            } catch (IllegalArgumentException e){
                try {
                    this.img = ImageIO.read(new File("img/ErrorImage.png"));
                } catch (IOException ex) {
                    Logger.getLogger(Manga.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        public void setAltJudul(String altJudul){
            this.altJudul = altJudul;
        }
        public void setGenre(String [] genre){
            this.genre = genre;
        }
        public void setRating(String rating){
            this.rating = rating;
        }
        public void setRilis(String rilis){
            this.rilis = rilis;
        }
        public void setType(String type){
            this.type_ = type;
        }
        public void setUpdate(String update){
            this.update = update;
        }
        public void setOther(String [] other){
            this.other = other;
        }
        public void setChapter(Chapter chapter){
            this.chapter = chapter;
        }
        public void setSinopsis(String sinopsis){
            this.sinopsis = sinopsis;
        }
        //Getter
        public String getJudul(){
            return this.judul;
        }
        public Image getImg(){
            return this.img;
        }
        public String getAltJudul(){
            return this.altJudul;
        }
        public String [] getGenre(){
            return this.genre;
        }
        public String getRating(){
            return this.rating;
        }
        public String getRilis(){
            return this.rilis;
        }
        public String getType(){
            return this.type_;
        }
        public String getUpdate(){
            return this.update;
        }
        public String [] getOther(){
            return this.other;
        }
        public Chapter getChapter(){
            return this.chapter;
        }
        public String getSinopsis(){
            return this.sinopsis;
        }
    }
    public static class ChapterItem{
        private String chapterName;
        private String chapterLink;
        private String lastUpdate;
        
        public void setChapterName(String chapterName){
            this.chapterName = chapterName;
        }
        public void setChapterLink(String chapterLink){
            this.chapterLink = chapterLink;
        }
        public void setLastUpdate(String lastUpdate){
            this.lastUpdate = lastUpdate;
        }
        public String getChapterName(){
            return this.chapterName;
        }
        public String getChapterLink(){
            return this.chapterLink;
        }
        public String setLastUpdate(){
            return this.lastUpdate;
        }
    }
    public static class Chapter{
        private ChapterItem [] chapteritem;
        private int index;
        
        public void setChapterItemArray(ChapterItem [] chapteritem){
            this.chapteritem = chapteritem;
        }
        public void setIndex(int index){
            this.index = index;
        }
        public boolean next(){
            if(this.index == 0){
                return false;
            }
            else if(this.index >= chapteritem.length){
                return false;
            }
            else{
                this.index -= 1;
                return true;
            }
        }
        public boolean previous(){
            if(this.index == 0){
                return false;
            }
            else if(this.index >= chapteritem.length){
                return false;
            }
            else{
                this.index += 1;
                return true;
            }
        }
        public ChapterItem getChapter(){
            return this.chapteritem[index];
        }
        public ChapterItem [] getAllChapter(){
            return this.chapteritem;
        }
        
        public int getSize(){
            return chapteritem.length;
        }
    }
}
