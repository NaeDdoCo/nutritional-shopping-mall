package client;

import ctrl.CTRL;
import utils.Crawling;

public class Client {
	public static void main(String[] args) {

		Crawling.crawling();

		CTRL app = new CTRL();

		app.start();
	}
}
