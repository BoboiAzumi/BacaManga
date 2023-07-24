/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libmanga;

import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import libmanga.Manga.Chapter;
import libmanga.Manga.ChapterItem;
import libmanga.Manga.MangaDetail;
import libmanga.Manga.MangaItem;
import libtool.HTTPConnect;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author naufal / BoboiAzumi
 */
public class Komikcast {
    //URL
    String url = "https://komikcast.io/";
    HTTPConnect HC = new HTTPConnect();
    
    public MangaItem [] index(){
        
        //GET
        String g = HC.get(url+"/daftar-komik/");
        
        //Parsing Parent
        Document html = Jsoup.parse(g, "UTF-8");
        Element e = html.getElementsByClass("list-update").get(0);
        Elements es = e.getElementsByClass("list-update_item");
        MangaItem items[] = new MangaItem[es.size()];
        
        //Indexing
        int index = 0;
        
        //Parsing Element
        for (Element i: es){
            //Instance
            MangaItem item = new MangaItem();
            
            //Setter
            item.setMangaType(i.getElementsByClass("type").get(0).text());
            item.setJudul(i.getElementsByTag("h3").get(0).text());
            item.setLink(i.getElementsByTag("a").get(0).attr("href"));
            item.setImg(i.getElementsByTag("img").get(0).attr("src"));
            item.setScore(i.getElementsByClass("numscore").get(0).text());
            
            //Append
            items[index] = item;
            
            //Index ++
            index++;
        }
        return items;
    }
    
    public MangaDetail getMangaDetail(String link){
        MangaDetail result = new MangaDetail();
        int i;
        //GET
        String g = HC.get(link);
        
        //Parsing parent
        Document html = Jsoup.parse(g);
        
        //Parsing Element
        result.setJudul(html.getElementsByClass("komik_info-content-body-title").get(0).text());
        result.setImg(html.getElementsByClass("komik_info-content-thumbnail-image").attr("src"));
        result.setAltJudul(html.getElementsByClass("komik_info-content-native").get(0).text());
        result.setRating(html.getElementsByTag("strong").get(0).text().replace("Rating ", ""));
        result.setRilis(html.getElementsByClass("komik_info-content-info-release").get(0).text().replace("Released:", "").strip());
        result.setType(html.getElementsByClass("komik_info-content-info-type").get(0).text().replace("Type:", "").strip());
        result.setUpdate(html.getElementsByClass("komik_info-content-update").get(0).text().replace("Updated on:", "").strip());
        result.setSinopsis(html.getElementsByClass("komik_info-description-sinopsis").get(0).text());
        
        //Parsing Genres
        Elements genres_element = html.getElementsByClass("genre-item");
        String [] genres = new String[genres_element.size()];
        i = 0;
        for(Element e : genres_element){
            genres[i] = e.text();
            i++;
        }
        result.setGenre(genres);
        
        //Parsing Other
        Elements other_elements = html.getElementsByClass("komik_info-content-info");
        String [] other = new String[other_elements.size()];
        i = 0;
        for(Element e : other_elements){
            other[i] = e.text();
            i++;
        }
        result.setOther(other);
        
        //Parsing Chapter Item
        Elements chapter_elements = html.getElementsByClass("komik_info-chapters-item");
        ChapterItem [] chapteritem = new ChapterItem[chapter_elements.size()];
        Chapter chapter = new Chapter();
        i = 0;
        for(Element e : chapter_elements){
            chapteritem[i] = new ChapterItem();
            chapteritem[i].setChapterName(e.getElementsByClass("chapter-link-item").text());
            chapteritem[i].setChapterLink(e.getElementsByClass("chapter-link-item").attr("href"));
            chapteritem[i].setLastUpdate(e.getElementsByClass("chapter-link-time").text().strip());
            i++;
        }
        chapter.setChapterItemArray(chapteritem);
        result.setChapter(chapter);
        
        return result;
    }
    
    public Image [] getChapterImage(String link){
        Image [] img = null;
        
        //GET
        String result = HC.get(link);
        
        //Parsing Parent
        Document html = Jsoup.parse(result);
        
        //Parsing Elements
        Elements es = html.getElementsByClass("alignnone");
        
        img = new Image[es.size()];
        int i = 0;
        //Parsing Image
        for(Element e : es){
            try {
                img[i] = ImageIO.read(HC.getInputStream(e.attr("src")));
            } catch (IOException ex) {
                Logger.getLogger(Komikcast.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        }
        return img;
    }
    
    public MangaItem [] search(String q){
        
        //GET
        String g = HC.get(url+"/?s="+q);
        
        //Parsing Parent
        Document html = Jsoup.parse(g, "UTF-8");
        Element e = html.getElementsByClass("list-update").get(0);
        Elements es = e.getElementsByClass("list-update_item");
        MangaItem items[] = new MangaItem[es.size()];
        
        //Indexing
        int index = 0;
        
        //Parsing Element
        for (Element i: es){
            //Instance
            MangaItem item = new MangaItem();
            
            //Setter
            item.setMangaType(i.getElementsByClass("type").get(0).text());
            item.setJudul(i.getElementsByTag("h3").get(0).text());
            item.setLink(i.getElementsByTag("a").get(0).attr("href"));
            item.setImg(i.getElementsByTag("img").get(0).attr("src"));
            item.setScore(i.getElementsByClass("numscore").get(0).text());
            
            //Append
            items[index] = item;
            
            //Index ++
            index++;
        }
        return items;
    }
}
