package scraping;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScrapAPI {
	public static void getInfo(String url) throws IOException{
		String title ="";
		String image="";
		String description = "";
		String price="";
		String reviews="";
		Document doc = (Document) Jsoup.connect(url).timeout(8000).get();
		/*Initializing Elements' object with the *left side* of the Amazon's webpage to get the Image URL.*/
		Elements body = doc.select("div#leftCol");
		/*Looping through all the <div> inside the div with id="leftCol" using the Element object e.*/
		for(Element e : body.select("div#leftCol"))
		{
			image = e.select("div#imageBlock_feature_div div#imageBlock div div div div div#main-image-container ul li span span div#imgTagWrapperId img").attr("src");
		}
		/*Defining the body now to *center* of the webpage, to get rest of the information.*/
		body = doc.select("div#centerCol");
		/*Looping through all the <div> inside the div with id="centerCol" using the Element object e.*/
		for(Element e : body.select("div#centerCol"))
		{
			title = e.select("div#title_feature_div div#titleSection h1#title span#productTitle").text().trim();
			description = e.select("div#featurebullets_feature_div div#feature-bullets ul li span").text().trim();
			/*Getting type of price, to check weather it's a "deal price" or "normal price", on the basis of the ID provided.*/
			String priceCheck = e.select("div#desktop_unifiedPrice div#unifiedPrice_feature_div div#price table.a-lineitem tbody tr").attr("id");
			if(priceCheck.equals("priceblock_dealprice_row")){
				price = e.select("div#desktop_unifiedPrice div#unifiedPrice_feature_div div#price table.a-lineitem tbody tr#priceblock_dealprice_row td.a-span12 span#priceblock_dealprice").text().trim();
			}
			else{
				price = e.select("div#desktop_unifiedPrice div#unifiedPrice_feature_div div#price table.a-lineitem tbody tr#priceblock_ourprice_row td.a-span12 span#priceblock_ourprice").text().trim();
			}
			reviews = e.select("div#averageCustomerReviews_feature_div div#averageCustomerReviews span a#acrCustomerReviewLink span#acrCustomerReviewText").text().trim();
			/*Printing all the required details on the console.*/
			System.out.println("Product Title => "+title);
			System.out.println("Image URL => "+image);
			System.out.println("Product Description => "+description);
			System.out.println("Price => "+price);
			System.out.println("Total Product Reviews => "+reviews);
		}
	}
}
