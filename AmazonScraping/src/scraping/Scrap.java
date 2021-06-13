package scraping;

import java.io.IOException;
import java.util.Scanner;

public class Scrap {
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Scanner scr = new Scanner(System.in);
		String url = "";
		System.out.println("Enter URL:");
		/*User enters the URL for which he/she requires the info.*/
		url = scr.next();
		/*Passing url as an argument in getInfo() method.*/
		ScrapAPI.getInfo(url);
	}

}
