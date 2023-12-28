package ctrl;

import model.MemberDTO;
import view.VIEW;

public class CTRL {
	private VIEW view;
	private MemberDTO loginINFO;

	public CTRL() {
		view = new VIEW();
		loginINFO = null;
	}

	public void start() {
		while (true) {
			if (loginINFO == null) {
				// 비로그인 상태
				view.printLogoutMenu();
				int action = view.inputAction();
				if (action == 0) {
					break;
				} else if (action == 1) {
				} else if (action == 2) {
				} else if (action == 3) {
				} else if (action == 4) {
				}
//			} else if (user 로그인) {
//				
//			} else if (admin 로그인) {
				
			}
		}
	}
}
