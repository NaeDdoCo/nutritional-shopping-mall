package utils;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.ProductDAO;
import model.ProductDTO;

public class Crawling {
	public static void crawling() {
		String[] urls = {
				"https://pilly.kr/product/omega3",
				"https://pilly.kr/product/milkthistle",
		};
		int PK = 1001;
		
		for (String url : urls) {
			scrapPage(url, PK++);
		}
	}

	private static void scrapPage(String url, int pk) {

		Document doc = null;

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ProductDTO pDTO = new ProductDTO();

		// PID
		pDTO.setPID(pk);

		// costPrice
		// regularPrice
		// sellingPrice
		Elements elems = doc.select("div.price"); // "13,500원"
		Iterator<Element> itr = elems.iterator();
		if (itr.hasNext()) {
			String strPrice = itr.next().text();
			strPrice = strPrice.substring(0, strPrice.length() - 1);
			strPrice = strPrice.replace(String.valueOf(','), "");

			System.out.println(strPrice);
			int selling = Integer.parseInt(strPrice);
			pDTO.setCostPrice(selling - 2000);
			pDTO.setRegularPrice(selling - 1000);
			pDTO.setSellingPrice(selling);
		}

		// cnt
		pDTO.setCnt(30);

		elems = doc.select("dd");
		itr = elems.iterator();
		int cnt = 0;
		while (itr.hasNext()) {
			Element e = itr.next();
			
			if (cnt == 0) {

				// pName
				String pName = e.text(); // 필리 오메가3
				String[] strs = pName.split("\\s+");
				if (strs.length > 1) {
					pName = strs[1];
				}
				pDTO.setpName(pName);

			} else if (cnt == 3) {

				// exp
				String exp = e.text().split(" /")[0];
				pDTO.setExp(exp);

			} else if (cnt == 6) {

				String str = e.text();

				// usage
				String[] strs = str.split(" 1일 섭취량 당 함량 : ");
				String usage = "";
				usage = strs[0].split("1일 섭취량 : ")[1];
				pDTO.setUsage(usage);

				// ingredient
				if (strs.length > 1) {
					str = strs[1];
				}
				strs = str.split(" ※ ");
				str = strs[0];
				pDTO.setIngredient(str);

			} else if (cnt == 7) {

				// category
				String str = e.text();
				if (str.contains("눈")) {
					pDTO.setCategory("눈");
				} else if (str.contains("피부")) {
					pDTO.setCategory("피부");
				} else if (str.contains("간")) {
					pDTO.setCategory("간");
				}
			}

			cnt++;
		}
//		System.out.println(pDTO);
		ProductDAO pDAO = new ProductDAO();
		if (pDAO.insert(pDTO)) {
			System.out.println("크롤링 성공!!");
		} else {
			System.out.println("크롤링 실패...");
		}
	}
}
