package poly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.BMFBDTO;
import poly.dto.BMMDTO;
import poly.dto.BMQTDTO;
import poly.dto.BMSRDTO;
import poly.dto.BMUserInfoDTO;
import poly.dto.COMMENTDTO;
import poly.dto.PagingDTO;
import poly.service.IBMService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

@Controller
public class BMController {
	private Logger log = Logger.getLogger(this.getClass().getName());

	@Resource(name = "BMService")
	private IBMService BMService;

	@RequestMapping(value = "/BandMoa/BMIndex")
	public String BMIndex() {
		log.info(this.getClass().getName() + "########인덱스 화면 실행########");

		return "/BandMoa/BMIndex";
	}

	@RequestMapping(value = "/BandMoa/Login")
	public String Login() {
		return "/BandMoa/Login";
	}

	@RequestMapping(value = "/BandMoa/Login_Join")
	public String Login_Join() {
		return "/BandMoa/Login_Join";
	}

	@RequestMapping(value = "/BandMoa/Login_Help")
	public String Login_Help() {
		return "/BandMoa/Login_Help";
	}

	
	
	
	
	@RequestMapping(value = "/BandMoa/FB/BMFreeB")
	public String BMFreeB(HttpServletRequest request, Model model) throws Exception {
		log.info(this.getClass().getName() + "########자유게시판 실행########");

		int page = Integer.parseInt(request.getParameter("Pno"));
		int listCnt = BMService.TotalCountFB(); // 총 게시글 개수

		log.info("int page : " + page);
		log.info("int listCnt : " + listCnt);

		PagingDTO paging = new PagingDTO();

		paging.pageInfo(page, listCnt);
		HashMap<String, Integer> hMap = new HashMap<>();
		int i = paging.getStartList();
		int j = paging.getListSize();
		hMap.put("startlist", i);
		hMap.put("listsize", i + j);

		List<BMFBDTO> bList = new ArrayList<>();

		log.info("bList : " + bList);

		try {
			bList = BMService.getBMFreeB(hMap);
			/*
			 * log.info("bList : " + bList); log.info("bList getBoard_seq : " +
			 * bList.get(0).getBoard_seq()); log.info("bList getUpd_date : " +
			 * bList.get(0).getUpd_date()); log.info("bList getTitle : " +
			 * bList.get(0).getTitle()); log.info("bList getUser_id : " +
			 * bList.get(0).getUser_id());
			 */
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("bList", bList);
		model.addAttribute("paging", paging);

		return "/BandMoa/FB/BMFreeB";
	}

	@RequestMapping(value = "/BandMoa/FB/BMFreeBR")
	public String BMFreeBR(HttpSession session, HttpServletRequest request, ModelMap model) throws Exception {
		log.info("######## 자유게시판 글작성 시작 ##############");

		if (session.getAttribute("SS_USER_ID") == null) {
			model.addAttribute("msg", "로그인 후 다시 시도해주세요");
			model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
			return "/Redirect";

		} else if (session.getAttribute("SS_USER_ID").equals("Admin")) {
			return "/BandMoa/FB/BMFreeBR";

		} else {
			return "/BandMoa/FB/BMFreeBR";
		}
	}

	@RequestMapping(value = "/BandMoa/SR/BMSrB")
	public String BMSrB(HttpServletRequest request, Model model) throws Exception {
		log.info(this.getClass().getName() + "########스터디룸 게시판 실행########");

		int page = Integer.parseInt(request.getParameter("Pno"));
		int listCnt = BMService.TotalCountSR(); // 총 게시글 개수

		log.info("int page : " + page);
		log.info("int listCnt : " + listCnt);

		PagingDTO paging = new PagingDTO();

		paging.pageInfo(page, listCnt);
		HashMap<String, Integer> hMap = new HashMap<>();
		int i = paging.getStartList();
		int j = paging.getListSize();
		hMap.put("startlist", i);
		hMap.put("listsize", i + j);

		List<BMSRDTO> bList = new ArrayList<>();

		log.info("bList : " + bList);

		try {
			bList = BMService.getBMSrB(hMap);
			log.info("bList : " + bList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("bList", bList);
		model.addAttribute("paging", paging);

		return "/BandMoa/SR/BMSrB";
	}

	@RequestMapping(value = "/BandMoa/SR/BMSrBR")
	public String BMSrBR(HttpSession session, HttpServletRequest request, ModelMap model) throws Exception {
		log.info(" ########## 스터디룸 게시판 글작성 시작 #########");

		if (session.getAttribute("SS_USER_ID") == null) {
			model.addAttribute("msg", "로그인 후 다시 시도해주세요");
			model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
			return "/Redirect";

		} else if (session.getAttribute("SS_USER_ID").equals("admin")) {
			return "/BandMoa/SR/BMSrBR";

		} else {
			return "/BandMoa/SR/BMSrBR";
		}
	}

	@RequestMapping(value = "/BandMoa/QT/BMqtB")
	public String BMqtB(HttpServletRequest request, Model model) throws Exception {
		log.info(this.getClass().getName() + "########스터디룸 게시판 실행########");

		int page = Integer.parseInt(request.getParameter("Pno"));
		int listCnt = BMService.TotalCountQT(); // 총 게시글 개수

		log.info("int page : " + page);
		log.info("int listCnt : " + listCnt);

		PagingDTO paging = new PagingDTO();

		paging.pageInfo(page, listCnt);
		HashMap<String, Integer> hMap = new HashMap<>();
		int i = paging.getStartList();
		int j = paging.getListSize();
		hMap.put("startlist", i);
		hMap.put("listsize", i + j);

		List<BMQTDTO> bList = new ArrayList<>();

		log.info("bList : " + bList);

		try {
			bList = BMService.getBMqtB(hMap);
			log.info("bList : " + bList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("bList", bList);
		model.addAttribute("paging", paging);

		return "/BandMoa/QT/BMqtB";
	}

	@RequestMapping(value = "/BandMoa/QT/BMqtBR")
	public String BMqtBR(HttpSession session, HttpServletRequest request, ModelMap model) throws Exception {
		log.info("###### 팁 게시판 글작성 시작 ############");

		if (session.getAttribute("SS_USER_ID") == null) {
			model.addAttribute("msg", "로그인 후 다시 시도해주세요");
			model.addAttribute("url", "/BandMoa/QT/BMqtBR.do");
			return "/Redirect";

		} else if (session.getAttribute("SS_USER_ID").equals("admin")) {
			return "/BandMoa/QT/BMqtBR";

		} else {
			return "/BandMoa/QT/BMqtBR";
		}
	}

	@RequestMapping(value = "/BandMoa/M/BMmB")
	public String BMmB(HttpServletRequest request, Model model) throws Exception {
		log.info(this.getClass().getName() + "########스터디룸 게시판 실행########");

		int page = Integer.parseInt(request.getParameter("Pno"));
		int listCnt = BMService.TotalCountM(); // 총 게시글 개수

		log.info("int page : " + page);
		log.info("int listCnt : " + listCnt);

		PagingDTO paging = new PagingDTO();

		paging.pageInfo(page, listCnt);
		HashMap<String, Integer> hMap = new HashMap<>();
		int i = paging.getStartList();
		int j = paging.getListSize();
		hMap.put("startlist", i);
		hMap.put("listsize", i + j);

		List<BMMDTO> bList = new ArrayList<>();

		log.info("bList : " + bList);

		try {
			bList = BMService.getBMmB(hMap);
			log.info("bList : " + bList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("bList", bList);
		model.addAttribute("paging", paging);

		return "/BandMoa/M/BMmB";
	}

	@RequestMapping(value = "/BandMoa/M/BMmBR")
	public String BMmBR(HttpSession session, HttpServletRequest request, ModelMap model) throws Exception {
		log.info("######## 구인구직 게시판 글작성 시작 ##########");

		if (session.getAttribute("SS_USER_ID") == null) {
			model.addAttribute("msg", "로그인 후 다시 시도해주세요");
			model.addAttribute("url", "/BandMoa/M/BMmBR.do");
			return "/Redirect";

		} else if (session.getAttribute("SS_USER_ID").equals("admin")) {
			return "/BandMoa/M/BMmBR";

		} else {
			return "/BandMoa/M/BMmBR";
		}
	}

	
	
	
	
	
	
	@RequestMapping(value = "/BandMoa/BMFreeBRProc")
	public String BMFreeBRProc(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "########자유게시판 작성Proc 실행########");

		try {
			/* String board_seq = (String)session.getAttribute("SS_BOARD_SEQ"); */
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String board_pw = request.getParameter("board_pw");
			String user_id = (String) session.getAttribute("SS_USER_ID");

			log.info(title);
			log.info(content);
			log.info(board_pw);
			log.info(user_id);

			// 여기부터
			BMFBDTO bDTO = new BMFBDTO();
			/* bDTO.setBoard_seq(board_seq); */
			bDTO.setTitle(title);
			bDTO.setContent(content);
			bDTO.setUser_id(user_id);
			bDTO.setBoard_pw(EncryptUtil.encHashSHA256(board_pw));

			log.info("========================");
			log.info("title : " + title);
			log.info("content : " + content);
			log.info("board_pw : " + board_pw);
			log.info("user_id : " + user_id);
			log.info("========================");

			int res = 0;

			res = BMService.insertBMFreeBR(bDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
				model.addAttribute("msg", "등록되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
				model.addAttribute("msg", "등록에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 이렇게 변경
		return "/Redirect";
	}

	@RequestMapping(value = "/BandMoa/BMSrBRProc")
	public String BMSrBRProc(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "########스터디룸 게시판 작성Proc 실행########");

		try {
			/* String board_seq = (String)session.getAttribute("SS_BOARD_SEQ"); */
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String board_pw = request.getParameter("board_pw");
			String user_id = (String) session.getAttribute("SS_USER_ID");

			log.info(title);
			log.info(content);
			log.info(board_pw);
			log.info(user_id);

			// 여기부터
			BMSRDTO bDTO = new BMSRDTO();
			/* bDTO.setBoard_seq(board_seq); */
			bDTO.setTitle(title);
			bDTO.setContent(content);
			bDTO.setUser_id(user_id);
			bDTO.setBoard_pw(EncryptUtil.encHashSHA256(board_pw));

			log.info("========================");
			log.info("title : " + title);
			log.info("content : " + content);
			log.info("board_pw : " + board_pw);
			log.info("user_id : " + user_id);
			log.info("========================");

			int res = 0;

			res = BMService.insertBMSrBR(bDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
				model.addAttribute("msg", "등록되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
				model.addAttribute("msg", "등록에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 이렇게 변경
		return "/Redirect";
	}

	@RequestMapping(value = "/BandMoa/BMqtBRProc")
	public String BMqtBRProc(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "######## 팁 게시판 작성Proc 실행########");

		try {
			/* String board_seq = (String)session.getAttribute("SS_BOARD_SEQ"); */
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String board_pw = request.getParameter("board_pw");
			String user_id = (String) session.getAttribute("SS_USER_ID");

			log.info(title);
			log.info(content);
			log.info(board_pw);
			log.info(user_id);

			// 여기부터
			BMQTDTO bDTO = new BMQTDTO();
			/* bDTO.setBoard_seq(board_seq); */
			bDTO.setTitle(title);
			bDTO.setContent(content);
			bDTO.setUser_id(user_id);
			bDTO.setBoard_pw(EncryptUtil.encHashSHA256(board_pw));

			log.info("========================");
			log.info("title : " + title);
			log.info("content : " + content);
			log.info("board_pw : " + board_pw);
			log.info("user_id : " + user_id);
			log.info("========================");

			int res = 0;

			res = BMService.insertBMqtBR(bDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
				model.addAttribute("msg", "등록되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
				model.addAttribute("msg", "등록에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 이렇게 변경
		return "/Redirect";
	}

	@RequestMapping(value = "/BandMoa/BMmBRProc")
	public String BMmBRProc(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "######## 구인구직 게시판 작성Proc 실행########");

		try {
			/* String board_seq = (String)session.getAttribute("SS_BOARD_SEQ"); */
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String board_pw = request.getParameter("board_pw");
			String user_id = (String) session.getAttribute("SS_USER_ID");

			log.info(title);
			log.info(content);
			log.info(board_pw);
			log.info(user_id);

			// 여기부터
			BMMDTO bDTO = new BMMDTO();
			/* bDTO.setBoard_seq(board_seq); */
			bDTO.setTitle(title);
			bDTO.setContent(content);
			bDTO.setUser_id(user_id);
			bDTO.setBoard_pw(EncryptUtil.encHashSHA256(board_pw));

			log.info("========================");
			log.info("title : " + title);
			log.info("content : " + content);
			log.info("board_pw : " + board_pw);
			log.info("user_id : " + user_id);
			log.info("========================");

			int res = 0;

			res = BMService.insertBMmBR(bDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/M/BMmB.do?Pno=1");
				model.addAttribute("msg", "등록되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/M/BMmB.do?Pno=1");
				model.addAttribute("msg", "등록에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 이렇게 변경
		return "/Redirect";
	}

	
	
	
	
	
	
	
	@RequestMapping(value = "/BandMoa/FB/BMFreeBD")
	public String BMFreeBD(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "########자유게시판 디테일 실행########");

		String seq = request.getParameter("seq");
		
		BMFBDTO bDTO = new BMFBDTO();
		/*
		 * List<COMMENTDTO> cList = new ArrayList<>(); log.info("cList : " + cList);
		 */
		List<COMMENTDTO> cList = BMService.readReply(seq);
		
		try {
			bDTO = BMService.getBMFreeBD(seq);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("cList", cList);
		model.addAttribute("bDTO", bDTO);
		model.addAttribute("seq", seq);
		return "/BandMoa/FB/BMFreeBD";

	}

	@RequestMapping(value = "/BandMoa/SR/BMSrBD")
	public String BMSrBD(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "######## 스터디룸 게시판 디테일 실행########");

		String seq = request.getParameter("seq");
		
		BMSRDTO bDTO = new BMSRDTO();
	
		List<COMMENTDTO> cList = BMService.readReplySR(seq);
		
		try {
			bDTO = BMService.getBMSrBD(seq);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("cList", cList);
		model.addAttribute("bDTO", bDTO);
		model.addAttribute("seq", seq);
		return "/BandMoa/SR/BMSrBD";

	}
	
	@RequestMapping(value = "/BandMoa/QT/BMqtBD")
	public String BMqtBD(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "######## 팁게시판 디테일 실행########");

		String seq = request.getParameter("seq");
		
		BMQTDTO bDTO = new BMQTDTO();
	
		List<COMMENTDTO> cList = BMService.readReplyQT(seq);
		
		try {
			bDTO = BMService.getBMqtBD(seq);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("cList", cList);
		model.addAttribute("bDTO", bDTO);
		model.addAttribute("seq", seq);
		return "/BandMoa/QT/BMqtBD";

	}
	

	@RequestMapping(value = "/BandMoa/M/BMmBD")
	public String BMmBD(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "########구인구직게시판 디테일 실행########");

		String seq = request.getParameter("seq");
		
		BMMDTO bDTO = new BMMDTO();
	
		List<COMMENTDTO> cList = BMService.readReplyM(seq);
		
		try {
			bDTO = BMService.getBMmBD(seq);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("cList", cList);
		model.addAttribute("bDTO", bDTO);
		model.addAttribute("seq", seq);
		return "/BandMoa/M/BMmBD";

	}

	
	
	
	
	
	
	
	// 회원가입 로직처리
	@RequestMapping(value = "/BandMoa/insertLogin_Join")
	public String insertLogin_Join(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {

		log.info(this.getClass().getName() + ".insertLogin_Join start!");

		// 회원가입 결과에 대한 메시지를 전달할 변수
		String msg = "";
		// 웹(회원정보 입력화면) 에서 받는 정보를 저장할 변수
		BMUserInfoDTO pDTO = null;

		try {
			// 웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장시작
			// 무조건 웹으로 받은 정보는 DTO에 저장하기 위해 임시로 String변수에 저장함

			String user_id = CmmUtil.nvl(request.getParameter("user_id"));
			String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));
			String user_name = CmmUtil.nvl(request.getParameter("user_name"));
			String user_mail = CmmUtil.nvl(request.getParameter("user_mail"));
			String user_tel = CmmUtil.nvl(request.getParameter("user_tel"));

			// 끝, 값을 제대로 받아오는지 확인
			log.info("user_id : " + user_id);
			log.info("user_pw : " + user_pw);
			log.info("user_name : " + user_name);
			log.info("user_mail : " + user_mail);
			log.info("user_tel : " + user_tel);

			// 웹에서 받는 정보를 DTO에 저장 시작. 무조건 웹으로 받은 정보는 DTO에 저장

			// 웹에서 받는 정보를 저장할 변수를 메모리에 올리기
			pDTO = new BMUserInfoDTO();

			pDTO.setUser_id(user_id);
			pDTO.setUser_name(user_name);
			// 비밀 번호는 복호화 되지 않도록 해시 알고리즘으로 암호화
			pDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));
			pDTO.setUser_mail(user_mail);
			pDTO.setUser_tel(user_tel);
			log.info("================");
			log.info(pDTO.getUser_id());
			log.info(pDTO.getUser_pw());
			log.info(pDTO.getUser_name());
			log.info(pDTO.getUser_mail());
			log.info(pDTO.getUser_tel());
			log.info("================");

			// 끝

			// 회원가입
			int res = BMService.insertLogin_Join(pDTO);
			log.info("res : " + res);

			if (res == 1) {
				model.addAttribute("msg", "축하합니다 ! 회원가입되었습니다");
				model.addAttribute("url", "/BandMoa/Login.do");
			} else if (res == 2) {
				model.addAttribute("msg", "이미 가입된 아이디 입니다.");
				model.addAttribute("url", "/BandMoa/Login_Join.do");
			} else {
				model.addAttribute("msg", "이미 가입된 아이디 입니다.");
				model.addAttribute("url", "/BandMoa/Login_Join.do");
			}

		} catch (Exception e) {
			// 저장이 실패되면 사용자에게 보여줄 메시지
			msg = "실패하였습니다 : " + e.toString();
			log.info(e.toString());
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName() + ".insertLogin_Join end!");

			// 변수 초기화 ( 메모리 효율화 시키기 위해 사용함 )
			pDTO = null;
		}

		return "/Redirect";
	}

	/*
	 * @RequestMapping(value = "/BandMoa/msg") public String msg() {
	 * log.info(this.getClass().getName() + "########msg 화면 실행########");
	 * 
	 * return "/BandMoa/Login"; }
	 */

	@RequestMapping(value = "/BandMoa/getUserLoginCheck")
	public String getUserLoginCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + ".getUserLoginCheck start!");

		// 로그인 처리 결과를 저장할 변수 (성공1, 아이디 비밀번호 불일치로 인한 실패 0 , 시스템에러 2)
		int res = 0;

		// 웹에서 받는 정보를 저장할 변수
		BMUserInfoDTO pDTO = null;

		try {
			/*
			 * 웹 에서 받는 정보를 String 변수에 저장 시작 무조건 웹으로 받은 정보는 DTO에 저장히기 위해 임시로 String 변수에 저장함
			 */
			String user_id = CmmUtil.nvl(request.getParameter("user_id"));
			String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));
			// 끝

			log.info("user_id : " + user_id);
			log.info("user_pw : " + user_pw);
			// 비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화함
			user_pw = EncryptUtil.encHashSHA256(user_pw);

			// 웹에서 받는 정보를 저장할 변수를 메모리에 올리기
			pDTO = new BMUserInfoDTO();
			pDTO.setUser_id(user_id);
			pDTO.setUser_pw(user_pw);

			// 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 BMService 호출하기
			res = BMService.getUserLoginCheck(pDTO);

			/*
			 * 로그인을 성공했다면, 회원아이디 정보를 session에 저장함 세션은 톰켓(was)의 메모리에 존재하며, 웹사이트에 접속한
			 * 사람(연결된객체)마다 메모리에 값을 올린다. 예) 톰켓에 100명의 사용자가 로그인 했다면, 사용자 각각 회원 아이디를 메모리에 저장하며
			 * 메모리에 저장된 객체의 수는 100개이다. 따라서 과도한 세션은 톰켓의 메모리 부하를 발생시켜 서버가 다운되는 현사이 있을 수 있기
			 * 때문에, 최소한으로 사용하는 것을 권장한다
			 * 
			 * 스프링에서 세션을 사용하기 위해서는 함수명의 파라미터에 HttpSession session이 존재해야한다 세션은 톰캣의 메모리에 저장되기
			 * 때문에 url마다 전달하는 게 필요하지 않고, 그냥 메모리에서 부르면 되기 때문에 jsp, controller에서 쉽게 불러서 쓸 수 있다
			 */

			if (res == 1) {// 로그인성공
				/*
				 * 세션에 회원아이디 저장하기, 추후 로그인 여부를 체크하기 위해 세션에 값이 존재하는지 체크한다 일반적으로 세션에 저장되는 키는 대문자로
				 * 입력하며, 앞에 SS를 붙인다 Session 단어에서 SS를 가져온것이다
				 */
				pDTO = BMService.GUI(pDTO);

				String name = pDTO.getUser_name();
				String mail = pDTO.getUser_mail();
				String tel = pDTO.getUser_tel();
				String id = pDTO.getUser_id();
				String pw = pDTO.getUser_pw();
				String no = pDTO.getUser_no();
				log.info("user_id :" + id);
				log.info("user_pw :" + pw);
				log.info("user_name :" + name);
				log.info("user_mail :" + mail);
				log.info("user_tel :" + tel);
				log.info("user_no" + no);

				session.setAttribute("SS_USER_ID", id);
				session.setAttribute("SS_USER_PW", pw);
				session.setAttribute("SS_USER_NAME", name);
				session.setAttribute("SS_USER_MAIL", mail);
				session.setAttribute("SS_USER_TEL", tel);
				session.setAttribute("SS_USER_NO", no);
				
				model.addAttribute("msg", "로그인되었습니다.밴드모아에 오신걸 환영합니다!");
				model.addAttribute("url", "/BandMoa/BMIndex.do");
			} else if(res == 0) {
				model.addAttribute("msg", "아이디, 비밀번호가 일치하지 않습니다");
				model.addAttribute("url", "/BandMoa/Login.do");
			} else {
				model.addAttribute("msg", "시스템 오류입니다. 잠시 후 다시 시도해주세요.");
				model.addAttribute("url", "/BandMoa/Login.do");
			}
		} catch (Exception e) {
			// 저장이 실패되면 사용자에게 보여줄 메시지
			res = 2;
			log.info(e.toString());
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName() + ".userlogincheck end");
			/*
			 * 로그인 처리 결과를 jsp에 전달하기 위해 변수 사용 숫자 유형의 데이터 타입은 값을 전달하고 받는데 불편함이 있어 문자 유형
			 * (String)으로 강제 형변환하여 jsp에 전달한다
			 */
			model.addAttribute("res", String.valueOf(res));
			// 변수 초기화(메모리 효율화 시키기 위해 사용함)
			pDTO = null;
		}
		return "/Redirect";

	}

	// 로그아웃.
	@RequestMapping(value = "/BandMoa/Logout")
	public String Logout(HttpSession session, HttpServletRequest request, Model model) throws Exception {
		request.getSession().removeAttribute("SS_USER_ID");
		request.getSession().removeAttribute("SS_USER_PW");
		log.info("세션 꺼짐");
		model.addAttribute("msg", "로그아웃 되었습니다.");
		model.addAttribute("url", "/BandMoa/BMIndex.do");

		return "/Redirect";
	}

	@RequestMapping(value = "/BandMoa/UserMypage")
	public String UserMypage(HttpSession session, HttpServletRequest request, ModelMap model) throws Exception {
		log.info("마이페이지 시작");

		if (session.getAttribute("SS_USER_ID") == null) {
			model.addAttribute("msg", "로그인 후 다시 시도해주세요");
			model.addAttribute("url", "/BandMoa/BMIndex.do");
			return "/Redirect";

		} /*
			 * else if (session.getAttribute("SS_USER_ID").equals("admin")) { return
			 * "/BandMoa/AdminMypage";
			 * 
			 * }
			 */ else {
			return "/BandMoa/UserMypage";
		}
	}

	@RequestMapping(value = "/BandMoa/MypageModify")
	public String MypageModify(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + "회원정보 수정 start!");

		BMUserInfoDTO pDTO = null;

		try {

			String user_name = CmmUtil.nvl(request.getParameter("user_name"));
			String user_mail = CmmUtil.nvl(request.getParameter("user_mail"));
			String user_tel = CmmUtil.nvl(request.getParameter("user_tel"));
			String no = CmmUtil.nvl(request.getParameter("no"));

			log.info("user_name : " + user_name);
			log.info("user_mail : " + user_mail);
			log.info("user_tel : " + user_tel);
			log.info("no : " + no);

			pDTO = new BMUserInfoDTO();
;
			pDTO.setUser_name(user_name);
			pDTO.setUser_mail(user_mail);
			pDTO.setUser_tel(user_tel);
			pDTO.setUser_no(no);

			log.info("================");
			log.info(pDTO.getUser_name());
			log.info(pDTO.getUser_mail());
			log.info(pDTO.getUser_tel());
			log.info(pDTO.getUser_no());
			log.info("================");

			int res = BMService.MypageModify(pDTO);
			log.info("res : " + res);

			if (res == 1) {
				model.addAttribute("msg", "회원 정보가 변경되었습니다. 다시 로그인해주세요.");
				model.addAttribute("url", "/BandMoa/BMIndex.do");
				request.getSession().removeAttribute("SS_USER_ID");
			} else {
				model.addAttribute("msg", "회원 정보 변경에 실패하였습니다");
				model.addAttribute("url", "/BandMoa/UserMypage.do");
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		} finally {
			pDTO = null;
		}
		return "/Redirect";
	}

	@RequestMapping(value = "/BandMoa/MypageModify2")
	public String MypageModify2(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + "비밀번호 수정 start!");

		BMUserInfoDTO pDTO = null;

		try {

			String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));
			String no = CmmUtil.nvl(request.getParameter("no"));

			log.info("user_pw : " + user_pw);
			log.info("no : " + no);

			pDTO = new BMUserInfoDTO();

			pDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));
			pDTO.setUser_no(no);

			log.info("================");
			log.info(pDTO.getUser_pw());
			log.info(pDTO.getUser_no());
			log.info("================");

			int res = BMService.MypageModify2(pDTO);
			log.info("res : " + res);

			if (res == 1) {
				model.addAttribute("msg", "비밀번호가 변경되었습니다. 로그인해주세요.");
				model.addAttribute("url", "/BandMoa/Login.do");
				request.getSession().removeAttribute("SS_USER_ID");
			} else {
				model.addAttribute("msg", "비밀번호 변경에 실패하였습니다");
				model.addAttribute("url", "/BandMoa/UserMypage2.do");
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		} finally {
			pDTO = null;
		}
		return "/Redirect";
	}

	@RequestMapping(value = "/BandMoa/Login_IdFind")
	public String Login_IdFind() {
		log.info(this.getClass().getName() + "아이디 찾기 실행");

		return "/BandMoa/Login_IdFind";
	}

	@RequestMapping(value = "/BandMoa/Login_PwFind")
	public String Login_PwFind() {
		log.info(this.getClass().getName() + "비밀번호 찾기 실행");

		return "/BandMoa/Login_PwFind";
	}

	@RequestMapping(value = "/BandMoa/SelectIdFind")
	public String SelectIdFind(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + "아이디 찾기 스타트");

		BMUserInfoDTO pDTO = null;

		try {

			String user_name = CmmUtil.nvl(request.getParameter("user_name"));
			String user_mail = CmmUtil.nvl(request.getParameter("user_mail"));
			String user_tel = CmmUtil.nvl(request.getParameter("user_tel"));

			log.info("user_name : " + user_name);
			log.info("user_mail : " + user_mail);
			log.info("user_tel : " + user_tel);

			pDTO = new BMUserInfoDTO();

			pDTO.setUser_name(user_name);
			pDTO.setUser_mail(user_mail);
			pDTO.setUser_tel(user_tel);

			log.info("================");
			log.info(pDTO.getUser_name());
			log.info(pDTO.getUser_mail());
			log.info(pDTO.getUser_tel());
			log.info("================");

			int res = BMService.SelectIdFind(pDTO);
			log.info("res : " + res);

			if (res == 1) {

				String IdFind = CmmUtil.nvl(BMService.IdFind(pDTO));
				log.info("아이디 : " + IdFind);

				model.addAttribute("msg", user_name + " 님의 아이디는 " + IdFind + " 입니다.");
				model.addAttribute("url", "/BandMoa/Login.do");
			} else {
				model.addAttribute("msg", "확인에 실패하였습니다.");
				model.addAttribute("url", "/BandMoa/Login_IdFind.do");
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		} finally {
			pDTO = null;
		}
		return "/Redirect";
	}

	@RequestMapping(value = "/BandMoa/UserMypage2")
	public String UserMypage2() {
		log.info("비밀번호수정 시작");
		return "/BandMoa/UserMypage2";
	}

	@RequestMapping(value = "/BandMoa/UserMypageCertify.do")
	public String UserMypageCertify(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + "비밀번호 찾기 개인정보 인증 스타트");

		BMUserInfoDTO pDTO = null;

		try {

			String user_id = CmmUtil.nvl(request.getParameter("user_id"));
			String user_mail = CmmUtil.nvl(request.getParameter("user_mail"));
			String user_tel = CmmUtil.nvl(request.getParameter("user_tel"));
			String no = CmmUtil.nvl(request.getParameter("no"));

			log.info("user_id : " + user_id);
			log.info("user_mail : " + user_mail);
			log.info("user_tel : " + user_tel);
			log.info("no : " + no);

			pDTO = new BMUserInfoDTO();

			pDTO.setUser_id(user_id);
			pDTO.setUser_mail(user_mail);
			pDTO.setUser_tel(user_tel);
			pDTO.setUser_no(no);

			log.info("================");
			log.info(pDTO.getUser_id());
			log.info(pDTO.getUser_mail());
			log.info(pDTO.getUser_tel());
			log.info(pDTO.getUser_no());
			log.info("================");

			int res = BMService.UserMypageCertify(pDTO);
			log.info("res : " + res);

			if (res == 1) {
				model.addAttribute("msg", "인증되었습니다. 비밀번호 수정 창으로 이동합니다.");
				model.addAttribute("url", "/BandMoa/UserMypage2.do");

				pDTO = BMService.GUI(pDTO);

				String id = pDTO.getUser_id();
				String mail = pDTO.getUser_mail();
				String tel = pDTO.getUser_tel();
				String user_no = pDTO.getUser_no();
				log.info("user_id :" + id);
				log.info("user_mail :" + mail);
				log.info("user_tel :" + tel);
				log.info("user_no" + user_no);

				session.setAttribute("SS_USER_ID", id);
				session.setAttribute("SS_USER_MAIL", mail);
				session.setAttribute("SS_USER_TEL", tel);
				session.setAttribute("SS_USER_NO", user_no);

				model.addAttribute("res", String.valueOf(res));

			} else {
				model.addAttribute("msg", "인증에 실패하였습니다.");
				model.addAttribute("url", "/BandMoa/Login_PwFind.do");
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}

		return "/Redirect";
	}
	
	
	@RequestMapping(value="/BandMoa/UserDeleteTry")
	public String UserDeleteTry() {
		return "/BandMoa/UserDeleteTry";
	}
	
	@RequestMapping(value = "/BandMoa/UserDelete")
	   public String UserDelete(HttpServletRequest request, HttpServletResponse response, ModelMap model,
	         HttpSession session) throws Exception {

	      log.info(this.getClass().getName() + "########## 회원탈퇴 시작 ###########");
	      
	      BMUserInfoDTO pDTO = null;
	      try {	  
	    String user_id = CmmUtil.nvl(request.getParameter("user_id"));
	    String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));
    	log.info("request.getParameter 값");
	 	log.info("user_id : " + user_id);
    	log.info("user_pw : " + user_pw);

	   	pDTO = new BMUserInfoDTO();
	   	pDTO.setUser_id(user_id);
	   	pDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));
	   	
	   	log.info("================");
	   	log.info("pDTO.getUser_id");
	   	log.info(pDTO.getUser_id());
	   	log.info("pDTO.getUser_pw");
	   	log.info(pDTO.getUser_pw());
	   	log.info("================");
	   	
	   	int result = BMService.UserDelete(pDTO);
	   	log.info("result : " + result);
        
	      if (result > 0) {
	    	 session.invalidate();
	         model.addAttribute("msg", "회원 탈퇴 되었습니다. 이용해주셔서 감사합니다.");
	         model.addAttribute("url", "/BandMoa/BMIndex.do");

	      } else {
	         model.addAttribute("msg", "회원정보가 일치하지 않습니다.");
	         model.addAttribute("url", "/BandMoa/UserDeleteTry.do");
	     }} catch (Exception e) {
	         log.info(e.toString());
	         e.printStackTrace();
	      }finally {
				pDTO = null;
			}


	      return "/Redirect";
	   }
	
	

	@RequestMapping(value = "/BandMoa/ModifyCertifyTry")
	public String ModifyCertifyTry(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "#### 자유게시판 수정/삭제창 이동 전 로그인 확인 ####");

		String seq = request.getParameter("seq");

		BMFBDTO pDTO = new BMFBDTO();

		try {
			pDTO = new BMFBDTO();
			pDTO.setBoard_seq(seq);
			pDTO = BMService.ModifyCertify(pDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("pDTO", pDTO);
		model.addAttribute("seq", seq);

		if (session.getAttribute("SS_USER_ID") == null) {
			model.addAttribute("msg", "로그인 후 다시 시도해주세요");
			model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
			return "/Redirect";

		} else if (session.getAttribute("SS_USER_ID").equals("admin")) {
			return "/BandMoa/FB/ModifyCertifyFB";

		} else {
			return "/BandMoa/FB/ModifyCertifyFB";
		}

	}

	@RequestMapping(value = "/BandMoa/ModifyCertifyTrySR")
	public String ModifyCertifyTrySR(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "#### 스터디룸 게시판 수정/삭제창 이동 전 로그인 확인 ####");

		String seq = request.getParameter("seq");

		BMSRDTO pDTO = new BMSRDTO();

		try {
			pDTO = new BMSRDTO();
			pDTO.setBoard_seq(seq);
			pDTO = BMService.ModifyCertifySR(pDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("pDTO", pDTO);
		model.addAttribute("seq", seq);

		if (session.getAttribute("SS_USER_ID") == null) {
			model.addAttribute("msg", "로그인 후 다시 시도해주세요");
			model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
			return "/Redirect";

		} else if (session.getAttribute("SS_USER_ID").equals("admin")) {
			return "/BandMoa/SR/ModifyCertifySR";

		} else {
			return "/BandMoa/SR/ModifyCertifySR";
		}

	}

	@RequestMapping(value = "/BandMoa/ModifyCertifyTryQT")
	public String ModifyCertifyTryQT(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "#### 팁 게시판 수정/삭제창 이동 전 로그인 확인 ####");

		String seq = request.getParameter("seq");

		BMQTDTO pDTO = new BMQTDTO();

		try {
			pDTO = new BMQTDTO();
			pDTO.setBoard_seq(seq);
			pDTO = BMService.ModifyCertifyQT(pDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("pDTO", pDTO);
		model.addAttribute("seq", seq);

		if (session.getAttribute("SS_USER_ID") == null) {
			model.addAttribute("msg", "로그인 후 다시 시도해주세요");
			model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
			return "/Redirect";

		} else if (session.getAttribute("SS_USER_ID").equals("admin")) {
			return "/BandMoa/QT/ModifyCertifyQT";

		} else {
			return "/BandMoa/QT/ModifyCertifyQT";
		}

	}

	@RequestMapping(value = "/BandMoa/ModifyCertifyTryM")
	public String ModifyCertifyTryM(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "#### 자유게시판 수정/삭제창 이동 전 로그인 확인 ####");

		String seq = request.getParameter("seq");

		BMMDTO pDTO = new BMMDTO();

		try {
			pDTO = new BMMDTO();
			pDTO.setBoard_seq(seq);
			pDTO = BMService.ModifyCertifyM(pDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("pDTO", pDTO);
		model.addAttribute("seq", seq);

		if (session.getAttribute("SS_USER_ID") == null) {
			model.addAttribute("msg", "로그인 후 다시 시도해주세요");
			model.addAttribute("url", "/BandMoa/M/BMmB.do?Pno=1");
			return "/Redirect";

		} else if (session.getAttribute("SS_USER_ID").equals("admin")) {
			return "/BandMoa/M/ModifyCertifyM";

		} else {
			return "/BandMoa/M/ModifyCertifyM";
		}

	}

	@RequestMapping(value = "/BandMoa/FB/ModifyCertifyFB")
	public String ModifyCertifyFB(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "자유게시판 글 수정/삭제창으로 이동");

		String seq = request.getParameter("seq");

		BMFBDTO pDTO = new BMFBDTO();

		try {
			pDTO = new BMFBDTO();
			pDTO.setBoard_seq(seq);
			pDTO = BMService.ModifyCertify(pDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("pDTO", pDTO);
		model.addAttribute("seq", seq);

		return "/BandMoa/FB/ModifyCertifyFB";
	}

	@RequestMapping(value = "/BandMoa/SR/ModifyCertifySR")
	public String ModifyCertifySR(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "스터디룸 게시판 글 수정/삭제창으로 이동");

		String seq = request.getParameter("seq");

		BMSRDTO pDTO = new BMSRDTO();

		try {
			pDTO = new BMSRDTO();
			pDTO.setBoard_seq(seq);
			pDTO = BMService.ModifyCertifySR(pDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("pDTO", pDTO);
		model.addAttribute("seq", seq);

		return "/BandMoa/FB/ModifyCertify";
	}

	@RequestMapping(value = "/BandMoa/QT/ModifyCertifyQT")
	public String ModifyCertify(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + " 팁 게시판 글 수정/삭제창으로 이동");

		String seq = request.getParameter("seq");

		BMQTDTO pDTO = new BMQTDTO();

		try {
			pDTO = new BMQTDTO();
			pDTO.setBoard_seq(seq);
			pDTO = BMService.ModifyCertifyQT(pDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("pDTO", pDTO);
		model.addAttribute("seq", seq);

		return "/BandMoa/QT/ModifyCertifyQT";
	}

	@RequestMapping(value = "/BandMoa/M/ModifyCertifyM")
	public String ModifyCertifyM(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "구인구직 게시판 글 수정/삭제창으로 이동");

		String seq = request.getParameter("seq");

		BMMDTO pDTO = new BMMDTO();

		try {
			pDTO = new BMMDTO();
			pDTO.setBoard_seq(seq);
			pDTO = BMService.ModifyCertifyM(pDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("pDTO", pDTO);
		model.addAttribute("seq", seq);

		return "/BandMoa/M/ModifyCertifyM";
	}

	@RequestMapping(value = "/BandMoa/ModifyCertify2")
	public String ModifyCertify2(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + "자유게시판 글 비번 인증 스타트 스타트");

		BMFBDTO pDTO = null;

		try {

			String board_pw = CmmUtil.nvl(request.getParameter("board_pw"));
			String seq = request.getParameter("seq");

			log.info("board_pw : " + board_pw);
			log.info("seq : " + seq);

			pDTO = new BMFBDTO();

			pDTO.setBoard_pw(EncryptUtil.encHashSHA256(board_pw));
			pDTO.setBoard_seq(seq);

			log.info("================");
			log.info(pDTO.getBoard_pw());
			log.info(pDTO.getBoard_seq());
			log.info("================");

			BMFBDTO res = BMService.ModifyCertify2(pDTO);
			log.info("res : " + res);

			if (res != null) {
				pDTO = BMService.GUI2(pDTO);

				String title = pDTO.getTitle();
				String content = pDTO.getContent();
				String upd_date = pDTO.getUpd_date();
				String user_id = pDTO.getUser_id();

				log.info("title :" + title);
				log.info("content : " + content);
				log.info("upd_date : " + upd_date);
				log.info("user_id : " + user_id);
				log.info("board_pw : " + board_pw);
				log.info("board_seq : " + seq);

				model.addAttribute("pDTO", pDTO);
				model.addAttribute("seq", seq);

			}	else {
				model.addAttribute("msg", "인증에 실패하였습니다. 게시판 목록으로 이동합니다.");
				model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
				return "/Redirect";
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}

		return "/BandMoa/FB/BMFreeBDModify";
	}

	@RequestMapping(value = "/BandMoa/ModifyCertify2SR")
	public String ModifyCertify2SR(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + "스터디룸 게시판 글 비번 인증 스타트 스타트");

		BMSRDTO pDTO = null;

		try {

			String board_pw = CmmUtil.nvl(request.getParameter("board_pw"));
			String seq = request.getParameter("seq");

			log.info("board_pw : " + board_pw);
			log.info("seq : " + seq);

			pDTO = new BMSRDTO();

			pDTO.setBoard_pw(EncryptUtil.encHashSHA256(board_pw));
			pDTO.setBoard_seq(seq);

			log.info("================");
			log.info(pDTO.getBoard_pw());
			log.info(pDTO.getBoard_seq());
			log.info("================");

			BMSRDTO res = BMService.ModifyCertify2SR(pDTO);
			log.info("res : " + res);

			if (res != null) {
				pDTO = BMService.GUI2SR(pDTO);

				String title = pDTO.getTitle();
				String content = pDTO.getContent();
				String upd_date = pDTO.getUpd_date();
				String user_id = pDTO.getUser_id();
				/*
				 * String board_pw = pDTO.getBoard_pw(); String board_seq = pDTO.getBoard_seq();
				 */

				log.info("title :" + title);
				log.info("content : " + content);
				log.info("upd_date : " + upd_date);
				log.info("user_id : " + user_id);
				log.info("board_pw : " + board_pw);
				log.info("board_seq : " + seq);

				model.addAttribute("pDTO", pDTO);
				model.addAttribute("seq", seq);

			} else {
				model.addAttribute("msg", "인증에 실패하였습니다. 게시판 목록으로 이동합니다.");
				model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
				return "/Redirect";
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}

		return "/BandMoa/SR/BMSrBDModify";
	}

	@RequestMapping(value = "/BandMoa/ModifyCertify2QT")
	public String ModifyCertify2QT(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + "팁 글 비번 인증 스타트 스타트");

		BMQTDTO pDTO = null;

		try {

			String board_pw = CmmUtil.nvl(request.getParameter("board_pw"));
			String seq = request.getParameter("seq");

			log.info("board_pw : " + board_pw);
			log.info("seq : " + seq);

			pDTO = new BMQTDTO();

			pDTO.setBoard_pw(EncryptUtil.encHashSHA256(board_pw));
			pDTO.setBoard_seq(seq);

			log.info("================");
			log.info(pDTO.getBoard_pw());
			log.info(pDTO.getBoard_seq());
			log.info("================");

			BMQTDTO res = BMService.ModifyCertify2QT(pDTO);
			log.info("res : " + res);

			if (res != null) {
				pDTO = BMService.GUI2QT(pDTO);

				String title = pDTO.getTitle();
				String content = pDTO.getContent();
				String upd_date = pDTO.getUpd_date();
				String user_id = pDTO.getUser_id();
				/*
				 * String board_pw = pDTO.getBoard_pw(); String board_seq = pDTO.getBoard_seq();
				 */

				log.info("title :" + title);
				log.info("content : " + content);
				log.info("upd_date : " + upd_date);
				log.info("user_id : " + user_id);
				log.info("board_pw : " + board_pw);
				log.info("board_seq : " + seq);

				model.addAttribute("pDTO", pDTO);
				model.addAttribute("seq", seq);

			} else {
				model.addAttribute("msg", "인증에 실패하였습니다. 게시판 목록으로 이동합니다.");
				model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
				return "/Redirect";
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}

		return "/BandMoa/Qt/BMqtDModify";
	}

	@RequestMapping(value = "/BandMoa/ModifyCertify2M")
	public String ModifyCertify2M(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + "구인구직 게시판 글 비번 인증 스타트 스타트");

		BMMDTO pDTO = null;

		try {

			String board_pw = CmmUtil.nvl(request.getParameter("board_pw"));
			String seq = request.getParameter("seq");

			log.info("board_pw : " + board_pw);
			log.info("seq : " + seq);

			pDTO = new BMMDTO();

			pDTO.setBoard_pw(EncryptUtil.encHashSHA256(board_pw));
			pDTO.setBoard_seq(seq);

			log.info("================");
			log.info(pDTO.getBoard_pw());
			log.info(pDTO.getBoard_seq());
			log.info("================");

			BMMDTO res = BMService.ModifyCertify2M(pDTO);
			log.info("res : " + res);

			if (res != null) {
				pDTO = BMService.GUI2M(pDTO);

				String title = pDTO.getTitle();
				String content = pDTO.getContent();
				String upd_date = pDTO.getUpd_date();
				String user_id = pDTO.getUser_id();
				/*
				 * String board_pw = pDTO.getBoard_pw(); String board_seq = pDTO.getBoard_seq();
				 */

				log.info("title :" + title);
				log.info("content : " + content);
				log.info("upd_date : " + upd_date);
				log.info("user_id : " + user_id);
				log.info("board_pw : " + board_pw);
				log.info("board_seq : " + seq);

				model.addAttribute("pDTO", pDTO);
				model.addAttribute("seq", seq);

			} else {
				model.addAttribute("msg", "인증에 실패하였습니다. 게시판 목록으로 이동합니다.");
				model.addAttribute("url", "/BandMoa/M/BMmB.do?Pno=1");
				return "/Redirect";
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}

		return "/BandMoa/M/BMmBDModify";
	}

	@RequestMapping(value = "/BandMoa/ModifyCertify3")
	public String ModifyCertify3(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + "자유게시판 글 관리자 비밀번호 인증 스타트 스타트");

		BMFBDTO pDTO = null;
		BMUserInfoDTO bDTO = null;

		try {

			String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));
			String user_id = (String) session.getAttribute("SS_USER_ID");
			String seq = request.getParameter("seq");

			log.info("user_id : " + user_id);
			log.info("user_pw : " + user_pw);
			log.info("seq : " + seq);

			pDTO = new BMFBDTO();
			pDTO.setBoard_seq(seq);

			bDTO = new BMUserInfoDTO();
			bDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));
			bDTO.setUser_id(user_id);
				
			
			log.info("================");
			log.info("관리자 : ");
			log.info(bDTO.getUser_pw());
			log.info(bDTO.getUser_id());
			log.info("게시글 시퀀스");
			log.info(pDTO.getBoard_seq());
			log.info("================");

			int res = BMService.ModifyCertify3(bDTO);
			log.info("res : " + res);

			if (res == 1) {
				pDTO = BMService.GUI4(pDTO);

				String title = pDTO.getTitle();
				String content = pDTO.getContent();
				String upd_date = pDTO.getUpd_date();
				String board_pw = pDTO.getBoard_pw();

				log.info("title :" + title);
				log.info("content : " + content);
				log.info("upd_date : " + upd_date);
				log.info("user_id : " + user_id);
				log.info("board_pw : " + board_pw);
				log.info("board_seq : " + seq);

				model.addAttribute("pDTO", pDTO);
				model.addAttribute("seq", seq);

			}	else {
				model.addAttribute("msg", "인증에 실패하였습니다. 게시판 목록으로 이동합니다.");
				model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
				return "/Redirect";
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}

		return "/BandMoa/FB/BMFreeBDModify";
	}
	
	@RequestMapping(value = "/BandMoa/ModifyCertify3SR")
	public String ModifyCertify3SR(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + "스터디룸 게시판 글 관리자 비밀번호 인증 스타트 스타트");

		BMSRDTO pDTO = null;
		BMUserInfoDTO bDTO = null;

		try {

			String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));
			String user_id = (String) session.getAttribute("SS_USER_ID");
			String seq = request.getParameter("seq");

			log.info("user_id : " + user_id);
			log.info("user_pw : " + user_pw);
			log.info("seq : " + seq);

			pDTO = new BMSRDTO();
			pDTO.setBoard_seq(seq);

			bDTO = new BMUserInfoDTO();
			bDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));
			bDTO.setUser_id(user_id);
				
			
			log.info("================");
			log.info("관리자 : ");
			log.info(bDTO.getUser_pw());
			log.info(bDTO.getUser_id());
			log.info("게시글 시퀀스");
			log.info(pDTO.getBoard_seq());
			log.info("================");

			int res = BMService.ModifyCertify3SR(bDTO);
			log.info("res : " + res);

			if (res == 1) {
				pDTO = BMService.GUI4SR(pDTO);

				String title = pDTO.getTitle();
				String content = pDTO.getContent();
				String upd_date = pDTO.getUpd_date();
				String board_pw = pDTO.getBoard_pw();

				log.info("title :" + title);
				log.info("content : " + content);
				log.info("upd_date : " + upd_date);
				log.info("user_id : " + user_id);
				log.info("board_pw : " + board_pw);
				log.info("board_seq : " + seq);

				model.addAttribute("pDTO", pDTO);
				model.addAttribute("seq", seq);

			}	else {
				model.addAttribute("msg", "인증에 실패하였습니다. 게시판 목록으로 이동합니다.");
				model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
				return "/Redirect";
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}

		return "/BandMoa/SR/BMSrBDModify";
	}
	
	@RequestMapping(value = "/BandMoa/ModifyCertify3QT")
	public String ModifyCertify3QT(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + "팁 게시판 글 관리자 비밀번호 인증 스타트 스타트");

		BMQTDTO pDTO = null;
		BMUserInfoDTO bDTO = null;

		try {

			String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));
			String user_id = (String) session.getAttribute("SS_USER_ID");
			String seq = request.getParameter("seq");

			log.info("user_id : " + user_id);
			log.info("user_pw : " + user_pw);
			log.info("seq : " + seq);

			pDTO = new BMQTDTO();
			pDTO.setBoard_seq(seq);

			bDTO = new BMUserInfoDTO();
			bDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));
			bDTO.setUser_id(user_id);
				
			
			log.info("================");
			log.info("관리자 : ");
			log.info(bDTO.getUser_pw());
			log.info(bDTO.getUser_id());
			log.info("게시글 시퀀스");
			log.info(pDTO.getBoard_seq());
			log.info("================");

			int res = BMService.ModifyCertify3QT(bDTO);
			log.info("res : " + res);

			if (res == 1) {
				pDTO = BMService.GUI4QT(pDTO);

				String title = pDTO.getTitle();
				String content = pDTO.getContent();
				String upd_date = pDTO.getUpd_date();
				String board_pw = pDTO.getBoard_pw();

				log.info("title :" + title);
				log.info("content : " + content);
				log.info("upd_date : " + upd_date);
				log.info("user_id : " + user_id);
				log.info("board_pw : " + board_pw);
				log.info("board_seq : " + seq);

				model.addAttribute("pDTO", pDTO);
				model.addAttribute("seq", seq);

			}	else {
				model.addAttribute("msg", "인증에 실패하였습니다. 게시판 목록으로 이동합니다.");
				model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
				return "/Redirect";
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}

		return "/BandMoa/QT/BMqtBDModify";
	}
	
	
	@RequestMapping(value = "/BandMoa/ModifyCertify3M")
	public String ModifyCertify3M(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + "구인구직 게시판 글 관리자 비밀번호 인증 스타트 스타트");

		BMMDTO pDTO = null;
		BMUserInfoDTO bDTO = null;

		try {

			String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));
			String user_id = (String) session.getAttribute("SS_USER_ID");
			String seq = request.getParameter("seq");

			log.info("user_id : " + user_id);
			log.info("user_pw : " + user_pw);
			log.info("seq : " + seq);

			pDTO = new BMMDTO();
			pDTO.setBoard_seq(seq);

			bDTO = new BMUserInfoDTO();
			bDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));
			bDTO.setUser_id(user_id);
				
			
			log.info("================");
			log.info("관리자 : ");
			log.info(bDTO.getUser_pw());
			log.info(bDTO.getUser_id());
			log.info("게시글 시퀀스");
			log.info(pDTO.getBoard_seq());
			log.info("================");

			int res = BMService.ModifyCertify3M(bDTO);
			log.info("res : " + res);

			if (res == 1) {
				pDTO = BMService.GUI4M(pDTO);

				String title = pDTO.getTitle();
				String content = pDTO.getContent();
				String upd_date = pDTO.getUpd_date();
				String board_pw = pDTO.getBoard_pw();

				log.info("title :" + title);
				log.info("content : " + content);
				log.info("upd_date : " + upd_date);
				log.info("user_id : " + user_id);
				log.info("board_pw : " + board_pw);
				log.info("board_seq : " + seq);

				model.addAttribute("pDTO", pDTO);
				model.addAttribute("seq", seq);

			}	else {
				model.addAttribute("msg", "인증에 실패하였습니다. 게시판 목록으로 이동합니다.");
				model.addAttribute("url", "/BandMoa/M/BMmB.do?Pno=1");
				return "/Redirect";
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}

		return "/BandMoa/M/BMmBDModify";
	}
	
	
	@RequestMapping(value = "/BandMoa/FB/BMFreeBDModify")
	public String BMFreeBDModify(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info(this.getClass().getName() + "########자유게시판 글 수정창 실행########");

		String seq = request.getParameter("seq");

		BMFBDTO pDTO = new BMFBDTO();
		pDTO.setBoard_seq(seq);

		try {
			pDTO = new BMFBDTO();
			/* pDTO.setBoard_seq(seq); */
			int res = 0;
			res = BMService.BMFreeBDModify(pDTO);
			log.info("res : " + res);

			if (res == 1) {

				pDTO = BMService.GUI3(pDTO);

				String title = pDTO.getTitle();
				String content = pDTO.getContent();
				String upd_date = pDTO.getUpd_date();
				String user_id = pDTO.getUser_id();
				String board_pw = pDTO.getBoard_pw();
				String board_seq = pDTO.getBoard_seq();

				log.info("title :" + title);
				log.info("content : " + content);
				log.info("upd_date : " + upd_date);
				log.info("user_id : " + user_id);
				log.info("board_pw : " + board_pw);
				log.info("board_seq : " + board_seq);

				model.addAttribute("pDTO", pDTO);
				return "/BandMoa/FB/BMFreeBDModify";

			} else {
				model.addAttribute("msg", "오류가 발생했습니다. 다시 시도해주세요");
				model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * model.addAttribute("pDTO", pDTO); model.addAttribute("seq", seq); return
		 * "/BandMoa/BMFreeBDModify";
		 */
		return "/Redirect";
	}

	@RequestMapping(value = "/BandMoa/SR/BMSrBDModify")
	public String BMSrBDModify(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info(this.getClass().getName() + "########스터디룸 게시판 글 수정창 실행########");

		String seq = request.getParameter("seq");

		BMSRDTO pDTO = new BMSRDTO();
		pDTO.setBoard_seq(seq);

		try {
			pDTO = new BMSRDTO();
			/* pDTO.setBoard_seq(seq); */
			int res = 0;
			res = BMService.BMSrBDModify(pDTO);
			log.info("res : " + res);

			if (res == 1) {

				pDTO = BMService.GUI3SR(pDTO);

				String title = pDTO.getTitle();
				String content = pDTO.getContent();
				String upd_date = pDTO.getUpd_date();
				String user_id = pDTO.getUser_id();
				String board_pw = pDTO.getBoard_pw();
				String board_seq = pDTO.getBoard_seq();

				log.info("title :" + title);
				log.info("content : " + content);
				log.info("upd_date : " + upd_date);
				log.info("user_id : " + user_id);
				log.info("board_pw : " + board_pw);
				log.info("board_seq : " + board_seq);

				model.addAttribute("pDTO", pDTO);
				return "/BandMoa/SR/BMSrBDModify";

			} else {
				model.addAttribute("msg", "오류가 발생했습니다. 다시 시도해주세요");
				model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * model.addAttribute("pDTO", pDTO); model.addAttribute("seq", seq); return
		 * "/BandMoa/BMFreeBDModify";
		 */
		return "/Redirect";
	}

	@RequestMapping(value = "/BandMoa/QT/BMqtBDModify")
	public String BMqtBDModify(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info(this.getClass().getName() + "########팁 게시판 글 수정창 실행########");

		String seq = request.getParameter("seq");

		BMQTDTO pDTO = new BMQTDTO();
		pDTO.setBoard_seq(seq);

		try {
			pDTO = new BMQTDTO();
			/* pDTO.setBoard_seq(seq); */
			int res = 0;
			res = BMService.BMqtBDModify(pDTO);
			log.info("res : " + res);

			if (res == 1) {

				pDTO = BMService.GUI3QT(pDTO);

				String title = pDTO.getTitle();
				String content = pDTO.getContent();
				String upd_date = pDTO.getUpd_date();
				String user_id = pDTO.getUser_id();
				String board_pw = pDTO.getBoard_pw();
				String board_seq = pDTO.getBoard_seq();

				log.info("title :" + title);
				log.info("content : " + content);
				log.info("upd_date : " + upd_date);
				log.info("user_id : " + user_id);
				log.info("board_pw : " + board_pw);
				log.info("board_seq : " + board_seq);

				model.addAttribute("pDTO", pDTO);
				return "/BandMoa/QT/BMqtBDModify";

			} else {
				model.addAttribute("msg", "오류가 발생했습니다. 다시 시도해주세요");
				model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * model.addAttribute("pDTO", pDTO); model.addAttribute("seq", seq); return
		 * "/BandMoa/BMFreeBDModify";
		 */
		return "/Redirect";
	}

	@RequestMapping(value = "/BandMoa/M/BMmBDModify")
	public String BMmBDModify(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info(this.getClass().getName() + "######## 구인구직 게시판 글 수정창 실행########");

		String seq = request.getParameter("seq");

		BMMDTO pDTO = new BMMDTO();
		pDTO.setBoard_seq(seq);

		try {
			pDTO = new BMMDTO();
			/* pDTO.setBoard_seq(seq); */
			int res = 0;
			res = BMService.BMmBDModify(pDTO);
			log.info("res : " + res);

			if (res == 1) {

				pDTO = BMService.GUI3M(pDTO);

				String title = pDTO.getTitle();
				String content = pDTO.getContent();
				String upd_date = pDTO.getUpd_date();
				String user_id = pDTO.getUser_id();
				String board_pw = pDTO.getBoard_pw();
				String board_seq = pDTO.getBoard_seq();

				log.info("title :" + title);
				log.info("content : " + content);
				log.info("upd_date : " + upd_date);
				log.info("user_id : " + user_id);
				log.info("board_pw : " + board_pw);
				log.info("board_seq : " + board_seq);

				model.addAttribute("pDTO", pDTO);
				return "/BandMoa/M/BMmBDModify";

			} else {
				model.addAttribute("msg", "오류가 발생했습니다. 다시 시도해주세요");
				model.addAttribute("url", "/BandMoa/M/BMmB.do?Pno=1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * model.addAttribute("pDTO", pDTO); model.addAttribute("seq", seq); return
		 * "/BandMoa/BMFreeBDModify";
		 */
		return "/Redirect";
	}

	@RequestMapping(value = "/BandMoa/BMFreeBDModify2")
	public String BMFreeBDModify2(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info(this.getClass().getName() + "######## 자유게시판 글 수정하기 실행########");
		String seq = request.getParameter("seq");

		BMFBDTO pDTO = new BMFBDTO();
		pDTO.setBoard_seq(seq);

		try {
			String title = (String) request.getParameter("title");
			String content = (String) request.getParameter("content");
			String board_pw = (String) request.getParameter("board_pw");

			log.info("seq : " + seq);
			log.info("title : " + title);
			log.info("content : " + content);
			log.info("board_pw : " + board_pw);

			pDTO.setTitle(title);
			pDTO.setContent(content);
			pDTO.setBoard_pw(EncryptUtil.encHashSHA256(board_pw));

			log.info("========================");
			log.info("Board_seq : " + seq);
			log.info("title : " + title);
			log.info("content : " + content);
			log.info("board_pw : " + board_pw);
			log.info("========================");

			int res = BMService.updateFB(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
				model.addAttribute("msg", "수정되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
				model.addAttribute("msg", "수정에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Redirect";

	}

	@RequestMapping(value = "/BandMoa/BMSrBDModify2")
	public String BMSrBDModify2(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info(this.getClass().getName() + "########스터디룸 게시판 글 수정하기 실행########");
		
		String seq = request.getParameter("seq");
		
		BMSRDTO pDTO = new BMSRDTO();
		pDTO.setBoard_seq(seq);

		try {
			
			String title = (String) request.getParameter("title");
			String content = (String) request.getParameter("content");
			String board_pw = (String) request.getParameter("board_pw");

			log.info("seq : " + seq);
			log.info("title : " + title);
			log.info("content : " + content);
			log.info("board_pw : " + board_pw);
			
			pDTO.setTitle(title);
			pDTO.setContent(content);
			pDTO.setBoard_pw(EncryptUtil.encHashSHA256(board_pw));

			log.info("========================");
			log.info("Board_seq : " + seq);
			log.info("title : " + title);
			log.info("content : " + content);
			log.info("board_pw : " + board_pw);
			log.info("========================");

			int res = BMService.updateSR(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
				model.addAttribute("msg", "수정되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
				model.addAttribute("msg", "수정에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Redirect";

	}

	@RequestMapping(value = "/BandMoa/BMqtBDModify2")
	public String BMqtBDModify2(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info(this.getClass().getName() + "########팁 게시판 글 수정하기 실행########");
		String seq = request.getParameter("seq");

		BMQTDTO pDTO = new BMQTDTO();
		pDTO.setBoard_seq(seq);

		try {
			String title = (String) request.getParameter("title");
			String content = (String) request.getParameter("content");
			String board_pw = (String) request.getParameter("board_pw");

			log.info("seq : " + seq);
			log.info("title : " + title);
			log.info("content : " + content);
			log.info("board_pw : " + board_pw);

			pDTO.setTitle(title);
			pDTO.setContent(content);
			pDTO.setBoard_pw(EncryptUtil.encHashSHA256(board_pw));

			log.info("========================");
			log.info("Board_seq : " + seq);
			log.info("title : " + title);
			log.info("content : " + content);
			log.info("board_pw : " + board_pw);
			log.info("========================");

			int res = BMService.updateQT(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
				model.addAttribute("msg", "수정되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
				model.addAttribute("msg", "수정에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Redirect";

	}

	@RequestMapping(value = "/BandMoa/BMmBDModify2")
	public String BMmBDModify2(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info(this.getClass().getName() + "########구인구직 게시판 글 수정하기 실행########");
		String seq = request.getParameter("seq");

		BMMDTO pDTO = new BMMDTO();
		pDTO.setBoard_seq(seq);

		try {
			String title = (String) request.getParameter("title");
			String content = (String) request.getParameter("content");
			String board_pw = (String) request.getParameter("board_pw");

			log.info("seq : " + seq);
			log.info("title : " + title);
			log.info("content : " + content);
			log.info("board_pw : " + board_pw);

			pDTO.setTitle(title);
			pDTO.setContent(content);
			pDTO.setBoard_pw(EncryptUtil.encHashSHA256(board_pw));

			log.info("========================");
			log.info("Board_seq : " + seq);
			log.info("title : " + title);
			log.info("content : " + content);
			log.info("board_pw : " + board_pw);
			log.info("========================");

			int res = BMService.updateM(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/M/BMmB.do?Pno=1");
				model.addAttribute("msg", "수정되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/M/BMmB.do?Pno=1");
				model.addAttribute("msg", "수정에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Redirect";

	}

	@RequestMapping(value = "/BandMoa/BMFreeBDDelete")
	public String BMFreeBDDelete(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info(this.getClass().getName() + "########자유게시판 글 삭제하기 실행########");
		try {
			String seq = request.getParameter("seq");

			BMFBDTO pDTO = new BMFBDTO();
			pDTO.setBoard_seq(seq);
			log.info("seq : " + seq);

			int res = BMService.deleteFB(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
				model.addAttribute("msg", "삭제되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
				model.addAttribute("msg", "삭제에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Redirect";

	}

	@RequestMapping(value = "/BandMoa/BMSrBDDelete")
	public String BMSrBDDelete(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info(this.getClass().getName() + "########스터디룸 게시판 글 삭제하기 실행########");
		try {
			String seq = request.getParameter("seq");

			BMSRDTO pDTO = new BMSRDTO();
			pDTO.setBoard_seq(seq);
			log.info("seq : " + seq);

			int res = BMService.deleteSR(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
				model.addAttribute("msg", "삭제되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
				model.addAttribute("msg", "삭제에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Redirect";

	}

	@RequestMapping(value = "/BandMoa/BMqtBDDelete")
	public String BMqtBDDelete(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info(this.getClass().getName() + "########팁 게시판 글 삭제하기 실행########");
		try {
			String seq = request.getParameter("seq");

			BMQTDTO pDTO = new BMQTDTO();
			pDTO.setBoard_seq(seq);
			log.info("seq : " + seq);

			int res = BMService.deleteQT(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
				model.addAttribute("msg", "삭제되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
				model.addAttribute("msg", "삭제에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Redirect";

	}

	@RequestMapping(value = "/BandMoa/BMmBDDelete")
	public String BMmBDDelete(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info(this.getClass().getName() + "########구인구직 게시판 글 삭제하기 실행########");
		try {
			String seq = request.getParameter("seq");

			BMMDTO pDTO = new BMMDTO();
			pDTO.setBoard_seq(seq);
			log.info("seq : " + seq);

			int res = BMService.deleteM(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/M/BMmB.do?Pno=1");
				model.addAttribute("msg", "삭제되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/M/BMmB.do?Pno=1");
				model.addAttribute("msg", "삭제에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Redirect";

	}

	@RequestMapping(value = "/BandMoa/CHM/CHMAP")
	public String CHMAP() throws Exception {
		log.info("######## 키워드로 공연장 찾기 실행 ########");
		return "/BandMoa/CHM/CHMAP";
	}

	@RequestMapping(value = "/BandMoa/CHM/CHMAP2")
	public String CHMAP2() throws Exception {
		log.info("######## 내 주변 공연장 찾기 실행 ########");
		return "/BandMoa/CHM/CHMAP2";
	}

	@RequestMapping(value = "idCheck")
	public @ResponseBody String checkId(HttpServletRequest request) throws Exception {
		log.info("유효성 검사 시작");
		String user_id = request.getParameter("user_id");
		log.info("user_id : " + user_id);
		BMUserInfoDTO pDTO = new BMUserInfoDTO();
		pDTO.setUser_id(user_id);
		String result = BMService.checkId(pDTO);
		log.info("유효성 검사 결과 : " + result);
		log.info("유효성 검사 종료");
		return result;
	}
	

	@RequestMapping(value="/BandMoa/InfoList")
	public String InfoList(HttpServletRequest request, Model model) throws Exception {
		log.info(this.getClass().getName() + "########유저 정보 리스트 실행########");

		int page = Integer.parseInt(request.getParameter("Pno"));
		System.out.println(page);
		int listCnt = BMService.TotalCountUserInfo(); // 총 게시글 개수
		
		log.info("int page : " + page);
		log.info("int listCnt : " + listCnt);

		PagingDTO paging = new PagingDTO();

		paging.pageInfo(page, listCnt);
		HashMap<String, Integer> hMap = new HashMap<>();
		int i = paging.getStartList();
		int j = paging.getListSize();
		hMap.put("startlist", i);
		hMap.put("listsize", i + j);

		List<BMUserInfoDTO> bList = new ArrayList<>();

		log.info("bList : " + bList);

		try {
			bList = BMService.getBMUserInfo(hMap);


		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("bList", bList);
		model.addAttribute("paging", paging);

		return "/BandMoa/InfoList";
	}
	
	@RequestMapping(value = "/BandMoa/AdminUserInfoModify")
	public String AdminUserInfoModify(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "관리자 회원정보 수정 start!");
		
		String user_no = request.getParameter("user_no");
		log.info("user_no : " + user_no);
		
		BMUserInfoDTO pDTO = new BMUserInfoDTO();

		try {
			pDTO = BMService.AdminUserInfoModify(user_no);
			log.info("user_name : " + pDTO.getUser_name());
			log.info("user_mail : " + pDTO.getUser_mail());
			log.info("user_tel : " + pDTO.getUser_tel());

		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}
		model.addAttribute("pDTO", pDTO);
		model.addAttribute("user_no", user_no);
		//모델을 써야 jsp로 넘길 수 있고 jsp에서 값을 받을 수 있음
		return "/BandMoa/AdminUserInfoModify";
	}
	
	@RequestMapping(value = "/BandMoa/AdminUserInfoModify2")
	public String AdminUserInfoModify2(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "관리자권한 회원정보 수정 start!");

		BMUserInfoDTO pDTO = new BMUserInfoDTO();
		
		try {
			
			String user_name = CmmUtil.nvl(request.getParameter("user_name"));
			String user_mail = CmmUtil.nvl(request.getParameter("user_mail"));
			String user_tel = CmmUtil.nvl(request.getParameter("user_tel"));
			String user_no = CmmUtil.nvl(request.getParameter("user_no"));
			log.info("user_no : " + user_no);
			log.info("user_name : " + user_name);
			log.info("user_mail : " + user_mail);
			log.info("user_tel : " + user_tel);
		
			pDTO = new BMUserInfoDTO();
			pDTO.setUser_name(user_name);
			pDTO.setUser_mail(user_mail);
			pDTO.setUser_tel(user_tel);
			pDTO.setUser_no(user_no);
					
			int res = BMService.AdminUserInfoModify2(pDTO);
			log.info("res : " + res);

			if (res == 1) {
				model.addAttribute("msg", "회원 정보가 변경되었습니다.");
				model.addAttribute("url", "/BandMoa/InfoList.do?Pno=1");
			} else {
				model.addAttribute("msg", "회원 정보 변경에 실패하였습니다");
				model.addAttribute("url", "/BandMoa/InfoList.do?Pno=1");
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}
		return "/Redirect";
	}
	
	@RequestMapping(value="/BandMoa/AdminUserDeleteTry")
	public String AdminUserDeleteTry(HttpServletRequest request,ModelMap model) throws Exception {
		BMUserInfoDTO pDTO = new BMUserInfoDTO();
		String user_no = CmmUtil.nvl(request.getParameter("user_no"));
		log.info("user_no : " + user_no);
		pDTO = new BMUserInfoDTO();
		pDTO.setUser_no(user_no);
		model.addAttribute("pDTO", pDTO);
		model.addAttribute("user_no", user_no);
		return "/BandMoa/AdminUserDeleteTry";
	}
	
	@RequestMapping(value = "/BandMoa/AdminUserDelete")
	   public String AdminUserDelete(HttpServletRequest request, HttpServletResponse response, ModelMap model,
	         HttpSession session) throws Exception {
	      log.info(this.getClass().getName() + "########## 관리자권한 회원탈퇴 시작 ###########");
	      String user_no = CmmUtil.nvl(request.getParameter("user_no"));
	      String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));
	      
	      BMUserInfoDTO pDTO = new BMUserInfoDTO();
	      BMUserInfoDTO bDTO = new BMUserInfoDTO();
	      
	      try {	  
	   	pDTO = new BMUserInfoDTO();
	   	pDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));
	   	
	   	bDTO = new BMUserInfoDTO();
	   	bDTO.setUser_no(user_no);
	   	
	   	log.info("================");
	   	log.info("pDTO.getUser_no");
	   	log.info(bDTO.getUser_no());
	   	log.info("pDTO.getUser_pw");
	   	log.info(pDTO.getUser_pw());
	   	log.info("================");
	   	
	   	String result = BMService.AdminUserDelete(pDTO);
	   	log.info("result : " + result);
     
	   	if (result != null) {
	   		int res = BMService.AdminUserDelete2(bDTO);
	   		if( res > 0) {
	   		model.addAttribute("msg", "회원 탈퇴 되었습니다.");
	        model.addAttribute("url", "/BandMoa/InfoList.do?Pno=1");
	   		}else {
		   	model.addAttribute("msg", "회원 탈퇴에 실패했습니다.");
		    model.addAttribute("url", "/BandMoa/InfoList.do?Pno=1");
	   		}
	   	} else {
	   		model.addAttribute("msg", "관리자 비밀번호가 일치하지 않습니다.");
	        model.addAttribute("url", "/BandMoa/AdminUserDeleteTry.do");
	   	}} catch (Exception e) {
	         log.info(e.toString());
	         e.printStackTrace();
	   	}
	   		
	      return "/Redirect";
	   }
	
	
	@RequestMapping(value = "/BandMoa/CommentProc")
	public String CommentProc(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "######## 댓글 작성Proc 실행########");

		try {

			String content = request.getParameter("content");
			String pw = request.getParameter("pw");
			String writer = (String) session.getAttribute("SS_USER_ID");
			String board_seq = request.getParameter("seq");
			log.info("content : " + content);
			log.info("pw : " + pw);
			log.info("writer : " + writer);
			log.info("board_seq : " + board_seq);

			// 여기부터
			COMMENTDTO bDTO = new COMMENTDTO();
			bDTO.setBoard_seq(board_seq);
			bDTO.setContent(content);
			bDTO.setWriter(writer);
			bDTO.setPw(EncryptUtil.encHashSHA256(pw));

			log.info("========================");
			log.info("board_seq : " + board_seq);
			log.info("content : " + content);
			log.info("pw : " + pw);
			log.info("writer : " + writer);
			log.info("========================");

			int res = 0;

			res = BMService.insertComment(bDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
				model.addAttribute("msg", "등록되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
				model.addAttribute("msg", "등록에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 이렇게 변경
		return "/Redirect";
	}
	
	@RequestMapping(value = "/BandMoa/CommentProcSR")
	public String CommentProcSR(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "######## 댓글 작성Proc 실행########");

		try {

			String content = request.getParameter("content");
			String pw = request.getParameter("pw");
			String writer = (String) session.getAttribute("SS_USER_ID");
			String board_seq = request.getParameter("seq");
			log.info("content : " + content);
			log.info("pw : " + pw);
			log.info("writer : " + writer);
			log.info("board_seq : " + board_seq);

			// 여기부터
			COMMENTDTO bDTO = new COMMENTDTO();
			bDTO.setBoard_seq(board_seq);
			bDTO.setContent(content);
			bDTO.setWriter(writer);
			bDTO.setPw(EncryptUtil.encHashSHA256(pw));

			log.info("========================");
			log.info("board_seq : " + board_seq);
			log.info("content : " + content);
			log.info("pw : " + pw);
			log.info("writer : " + writer);
			log.info("========================");

			int res = 0;

			res = BMService.insertCommentSR(bDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
				model.addAttribute("msg", "등록되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
				model.addAttribute("msg", "등록에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 이렇게 변경
		return "/Redirect";
	}
	
	@RequestMapping(value = "/BandMoa/CommentProcQT")
	public String CommentProcQT(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "######## 댓글 작성Proc 실행########");

		try {

			String content = request.getParameter("content");
			String pw = request.getParameter("pw");
			String writer = (String) session.getAttribute("SS_USER_ID");
			String board_seq = request.getParameter("seq");
			log.info("content : " + content);
			log.info("pw : " + pw);
			log.info("writer : " + writer);
			log.info("board_seq : " + board_seq);

			// 여기부터
			COMMENTDTO bDTO = new COMMENTDTO();
			bDTO.setBoard_seq(board_seq);
			bDTO.setContent(content);
			bDTO.setWriter(writer);
			bDTO.setPw(EncryptUtil.encHashSHA256(pw));

			log.info("========================");
			log.info("board_seq : " + board_seq);
			log.info("content : " + content);
			log.info("pw : " + pw);
			log.info("writer : " + writer);
			log.info("========================");

			int res = 0;

			res = BMService.insertCommentQT(bDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
				model.addAttribute("msg", "등록되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
				model.addAttribute("msg", "등록에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 이렇게 변경
		return "/Redirect";
	}
	
	
	@RequestMapping(value = "/BandMoa/CommentProcM")
	public String CommentProcM(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "######## 댓글 작성Proc 실행########");

		try {

			String content = request.getParameter("content");
			String pw = request.getParameter("pw");
			String writer = (String) session.getAttribute("SS_USER_ID");
			String board_seq = request.getParameter("seq");
			log.info("content : " + content);
			log.info("pw : " + pw);
			log.info("writer : " + writer);
			log.info("board_seq : " + board_seq);

			// 여기부터
			COMMENTDTO bDTO = new COMMENTDTO();
			bDTO.setBoard_seq(board_seq);
			bDTO.setContent(content);
			bDTO.setWriter(writer);
			bDTO.setPw(EncryptUtil.encHashSHA256(pw));

			log.info("========================");
			log.info("board_seq : " + board_seq);
			log.info("content : " + content);
			log.info("pw : " + pw);
			log.info("writer : " + writer);
			log.info("========================");

			int res = 0;

			res = BMService.insertCommentM(bDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/M/BMmB.do?Pno=1");
				model.addAttribute("msg", "등록되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/M/BMmB.do?Pno=1");
				model.addAttribute("msg", "등록에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 이렇게 변경
		return "/Redirect";
	}
	
	
	
	
	@RequestMapping(value = "/BandMoa/CommentModifyTry")
	public String CommentModifyTry(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "#### 댓글 수정/삭제창 이동 전 로그인 확인 ####");

		String rno = request.getParameter("rno");

		COMMENTDTO pDTO = new COMMENTDTO();

		try {
			pDTO = new COMMENTDTO();
			pDTO.setRno(rno);
			/*
			 * pDTO = BMService.CommentModifyTry(pDTO); log.info("setRno : " + rno);
			 * log.info("pDTO : " + pDTO);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		/* model.addAttribute("pDTO", pDTO); */
		model.addAttribute("rno", rno);

		if (session.getAttribute("SS_USER_ID") == null) {
			model.addAttribute("msg", "로그인 후 다시 시도해주세요");
			model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
			return "/Redirect";

		} else if (session.getAttribute("SS_USER_ID").equals("admin")) {
			return "/BandMoa/COMMENT/CommentModifyTry";

		} else {
			return "/BandMoa/COMMENT/CommentModifyTry";
		}

	}
	@RequestMapping(value = "/BandMoa/CommentModifyTrySR")
	public String CommentModifyTrySR(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "#### 댓글 수정/삭제창 이동 전 로그인 확인 ####");

		String rno = request.getParameter("rno");

		COMMENTDTO pDTO = new COMMENTDTO();

		try {
			pDTO = new COMMENTDTO();
			pDTO.setRno(rno);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("rno", rno);

		if (session.getAttribute("SS_USER_ID") == null) {
			model.addAttribute("msg", "로그인 후 다시 시도해주세요");
			model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
			return "/Redirect";

		} else if (session.getAttribute("SS_USER_ID").equals("admin")) {
			return "/BandMoa/COMMENT/CommentModifyTrySR";

		} else {
			return "/BandMoa/COMMENT/CommentModifyTrySR";
		}

	}
	@RequestMapping(value = "/BandMoa/CommentModifyTryQT")
	public String CommentModifyTryQT(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "#### 댓글 수정/삭제창 이동 전 로그인 확인 ####");

		String rno = request.getParameter("rno");

		COMMENTDTO pDTO = new COMMENTDTO();

		try {
			pDTO = new COMMENTDTO();
			pDTO.setRno(rno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("rno", rno);

		if (session.getAttribute("SS_USER_ID") == null) {
			model.addAttribute("msg", "로그인 후 다시 시도해주세요");
			model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
			return "/Redirect";

		} else if (session.getAttribute("SS_USER_ID").equals("admin")) {
			return "/BandMoa/COMMENT/CommentModifyTryQT";

		} else {
			return "/BandMoa/COMMENT/CommentModifyTryQT";
		}

	}
	@RequestMapping(value = "/BandMoa/CommentModifyTryM")
	public String CommentModifyTryM(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "#### 댓글 수정/삭제창 이동 전 로그인 확인 ####");

		String rno = request.getParameter("rno");

		COMMENTDTO pDTO = new COMMENTDTO();

		try {
			pDTO = new COMMENTDTO();
			pDTO.setRno(rno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("rno", rno);

		if (session.getAttribute("SS_USER_ID") == null) {
			model.addAttribute("msg", "로그인 후 다시 시도해주세요");
			model.addAttribute("url", "/BandMoa/M/BMmB.do?Pno=1");
			return "/Redirect";

		} else if (session.getAttribute("SS_USER_ID").equals("admin")) {
			return "/BandMoa/COMMENT/CommentModifyTryM";

		} else {
			return "/BandMoa/COMMENT/CommentModifyTryM";
		}

	}
	
	@RequestMapping(value = "/BandMoa/CommentCertify")
	public String CommentCertify(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "댓글 비밀번호 인증 스타트");
		
		COMMENTDTO pDTO = null;
		/* BMUserInfoDTO bDTO = null; */

		try {

			String pw = CmmUtil.nvl(request.getParameter("pw"));
			String writer = (String) session.getAttribute("SS_USER_ID");
			String rno = request.getParameter("rno");

			log.info("pw : " + pw);
			log.info("rno : " + rno);

			pDTO = new COMMENTDTO();
			pDTO.setRno(rno);
			pDTO.setPw(EncryptUtil.encHashSHA256(pw));
			pDTO.setWriter(writer);
			/*
			 * bDTO = new BMUserInfoDTO();
			 * bDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));
			 * bDTO.setUser_id(user_id);
			 */

			int res = BMService.CommentCertify(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				pDTO = BMService.CommentCertify2(pDTO);

				String board_seq = pDTO.getBoard_seq();
				String content = pDTO.getContent();
				log.info("최종확인");
				log.info("board_seq : " + board_seq);
				log.info("rno : " + rno);
				log.info("content : " + content);
				log.info("writer : " + writer);
				log.info("pw : " + pw);
				

				model.addAttribute("pDTO", pDTO);
				model.addAttribute("rno", rno);

			}else {
				model.addAttribute("msg", "인증에 실패하였습니다. 게시판 목록으로 이동합니다.");
				model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
				return "/Redirect";
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}

		return "/BandMoa/COMMENT/CommentModify";
	}
	@RequestMapping(value = "/BandMoa/CommentCertifySR")
	public String CommentCertifySR(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "댓글 비밀번호 인증 스타트");
		
		COMMENTDTO pDTO = null;

		try {

			String pw = CmmUtil.nvl(request.getParameter("pw"));
			String writer = (String) session.getAttribute("SS_USER_ID");
			String rno = request.getParameter("rno");

			log.info("pw : " + pw);
			log.info("rno : " + rno);

			pDTO = new COMMENTDTO();
			pDTO.setRno(rno);
			pDTO.setPw(EncryptUtil.encHashSHA256(pw));
			pDTO.setWriter(writer);

			int res = BMService.CommentCertifySR(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				pDTO = BMService.CommentCertify2SR(pDTO);

				String board_seq = pDTO.getBoard_seq();
				String content = pDTO.getContent();
				log.info("최종확인");
				log.info("board_seq : " + board_seq);
				log.info("rno : " + rno);
				log.info("content : " + content);
				log.info("writer : " + writer);
				log.info("pw : " + pw);
				

				model.addAttribute("pDTO", pDTO);
				model.addAttribute("rno", rno);

			}else {
				model.addAttribute("msg", "인증에 실패하였습니다. 게시판 목록으로 이동합니다.");
				model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
				return "/Redirect";
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}

		return "/BandMoa/COMMENT/CommentModifySR";
	}
	@RequestMapping(value = "/BandMoa/CommentCertifyQT")
	public String CommentCertifyQT(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "댓글 비밀번호 인증 스타트");
		
		COMMENTDTO pDTO = null;
		/* BMUserInfoDTO bDTO = null; */

		try {

			String pw = CmmUtil.nvl(request.getParameter("pw"));
			String writer = (String) session.getAttribute("SS_USER_ID");
			String rno = request.getParameter("rno");

			log.info("pw : " + pw);
			log.info("rno : " + rno);

			pDTO = new COMMENTDTO();
			pDTO.setRno(rno);
			pDTO.setPw(EncryptUtil.encHashSHA256(pw));
			pDTO.setWriter(writer);

			int res = BMService.CommentCertifyQT(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				pDTO = BMService.CommentCertify2QT(pDTO);

				String board_seq = pDTO.getBoard_seq();
				String content = pDTO.getContent();
				log.info("최종확인");
				log.info("board_seq : " + board_seq);
				log.info("rno : " + rno);
				log.info("content : " + content);
				log.info("writer : " + writer);
				log.info("pw : " + pw);
				

				model.addAttribute("pDTO", pDTO);
				model.addAttribute("rno", rno);

			}else {
				model.addAttribute("msg", "인증에 실패하였습니다. 게시판 목록으로 이동합니다.");
				model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
				return "/Redirect";
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}

		return "/BandMoa/COMMENT/CommentModifyQT";
	}
	@RequestMapping(value = "/BandMoa/CommentCertifyM")
	public String CommentCertifyM(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "댓글 비밀번호 인증 스타트");
		
		COMMENTDTO pDTO = null;

		try {

			String pw = CmmUtil.nvl(request.getParameter("pw"));
			String writer = (String) session.getAttribute("SS_USER_ID");
			String rno = request.getParameter("rno");

			log.info("pw : " + pw);
			log.info("rno : " + rno);

			pDTO = new COMMENTDTO();
			pDTO.setRno(rno);
			pDTO.setPw(EncryptUtil.encHashSHA256(pw));
			pDTO.setWriter(writer);

			int res = BMService.CommentCertifyM(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				pDTO = BMService.CommentCertify2M(pDTO);

				String board_seq = pDTO.getBoard_seq();
				String content = pDTO.getContent();
				log.info("최종확인");
				log.info("board_seq : " + board_seq);
				log.info("rno : " + rno);
				log.info("content : " + content);
				log.info("writer : " + writer);
				log.info("pw : " + pw);
				
				model.addAttribute("pDTO", pDTO);
				model.addAttribute("rno", rno);

			}else {
				model.addAttribute("msg", "인증에 실패하였습니다. 게시판 목록으로 이동합니다.");
				model.addAttribute("url", "/BandMoa/M/BMmB.do?Pno=1");
				return "/Redirect";
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}

		return "/BandMoa/COMMENT/CommentModifyM";
	}
	
	
	
	
	
	@RequestMapping(value = "/BandMoa/CommentCertify2")
	public String CommentCertify2(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + " 댓글 관리자 비밀번호 인증 스타트 스타트");

		COMMENTDTO pDTO = null;
		BMUserInfoDTO bDTO = null;
		String rno = request.getParameter("rno");
		String user_id = (String) session.getAttribute("SS_USER_ID");
		
		try {
			String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));
			log.info("user_pw : " + user_pw);
			log.info("rno : " + rno);
			
			
			pDTO = new COMMENTDTO();
			pDTO.setRno(rno);

			bDTO = new BMUserInfoDTO();
			bDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));
			bDTO.setUser_id(user_id);
			log.info("================");
			log.info("관리자 : ");
			log.info(bDTO.getUser_pw());
			log.info(bDTO.getUser_id());
			log.info("댓글번호 : ");
			log.info(pDTO.getRno());
			log.info("================");

			int res = BMService.ModifyCertify3(bDTO);
			log.info("res : " + res);

			if (res == 1) {
				pDTO = BMService.selectRe(pDTO);

				String board_seq = pDTO.getBoard_seq();
				String content = pDTO.getContent();
				String writer = pDTO.getWriter();
				
				log.info("board_seq : " + board_seq);
				log.info("content : " + content);
				log.info("rno : " + rno);
				log.info("writer : " + writer);

				model.addAttribute("pDTO", pDTO);
				model.addAttribute("rno", rno);
			}	else {
				model.addAttribute("msg", "인증에 실패하였습니다. 게시판 목록으로 이동합니다.");
				model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
				return "/Redirect";
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}

		return "/BandMoa/COMMENT/CommentModify";
	}
	@RequestMapping(value = "/BandMoa/CommentCertify2SR")
	public String CommentCertify2SR(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + " 댓글 관리자 비밀번호 인증 스타트 스타트");

		COMMENTDTO pDTO = null;
		BMUserInfoDTO bDTO = null;
		String rno = request.getParameter("rno");
		String user_id = (String) session.getAttribute("SS_USER_ID");
		
		try {
			String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));
			log.info("user_pw : " + user_pw);
			log.info("rno : " + rno);
			
			
			pDTO = new COMMENTDTO();
			pDTO.setRno(rno);

			bDTO = new BMUserInfoDTO();
			bDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));
			bDTO.setUser_id(user_id);
			log.info("================");
			log.info("관리자 : ");
			log.info(bDTO.getUser_pw());
			log.info(bDTO.getUser_id());
			log.info("댓글번호 : ");
			log.info(pDTO.getRno());
			log.info("================");

			int res = BMService.ModifyCertify3SR(bDTO);
			log.info("res : " + res);

			if (res == 1) {
				pDTO = BMService.selectReSR(pDTO);

				String board_seq = pDTO.getBoard_seq();
				String content = pDTO.getContent();
				String writer = pDTO.getWriter();
				
				log.info("board_seq : " + board_seq);
				log.info("content : " + content);
				log.info("rno : " + rno);
				log.info("writer : " + writer);

				model.addAttribute("pDTO", pDTO);
				model.addAttribute("rno", rno);
			}	else {
				model.addAttribute("msg", "인증에 실패하였습니다. 게시판 목록으로 이동합니다.");
				model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
				return "/Redirect";
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}

		return "/BandMoa/COMMENT/CommentModifySR";
	}
	@RequestMapping(value = "/BandMoa/CommentCertify2QT")
	public String CommentCertify2QT(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + " 댓글 관리자 비밀번호 인증 스타트 스타트");

		COMMENTDTO pDTO = null;
		BMUserInfoDTO bDTO = null;
		String rno = request.getParameter("rno");
		String user_id = (String) session.getAttribute("SS_USER_ID");
		
		try {
			String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));
			log.info("user_pw : " + user_pw);
			log.info("rno : " + rno);
			
			
			pDTO = new COMMENTDTO();
			pDTO.setRno(rno);

			bDTO = new BMUserInfoDTO();
			bDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));
			bDTO.setUser_id(user_id);
			log.info("================");
			log.info("관리자 : ");
			log.info(bDTO.getUser_pw());
			log.info(bDTO.getUser_id());
			log.info("댓글번호 : ");
			log.info(pDTO.getRno());
			log.info("================");

			int res = BMService.ModifyCertify3QT(bDTO);
			log.info("res : " + res);

			if (res == 1) {
				pDTO = BMService.selectReQT(pDTO);

				String board_seq = pDTO.getBoard_seq();
				String content = pDTO.getContent();
				String writer = pDTO.getWriter();
				
				log.info("board_seq : " + board_seq);
				log.info("content : " + content);
				log.info("rno : " + rno);
				log.info("writer : " + writer);

				model.addAttribute("pDTO", pDTO);
				model.addAttribute("rno", rno);
			}	else {
				model.addAttribute("msg", "인증에 실패하였습니다. 게시판 목록으로 이동합니다.");
				model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
				return "/Redirect";
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}

		return "/BandMoa/COMMENT/CommentModifyQT";
	}
	@RequestMapping(value = "/BandMoa/CommentCertify2M")
	public String CommentCertify2M(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + " 댓글 관리자 비밀번호 인증 스타트 스타트");

		COMMENTDTO pDTO = null;
		BMUserInfoDTO bDTO = null;
		String rno = request.getParameter("rno");
		String user_id = (String) session.getAttribute("SS_USER_ID");
		
		try {
			String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));
			log.info("user_pw : " + user_pw);
			log.info("rno : " + rno);
			
			
			pDTO = new COMMENTDTO();
			pDTO.setRno(rno);

			bDTO = new BMUserInfoDTO();
			bDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));
			bDTO.setUser_id(user_id);
			log.info("================");
			log.info("관리자 : ");
			log.info(bDTO.getUser_pw());
			log.info(bDTO.getUser_id());
			log.info("댓글번호 : ");
			log.info(pDTO.getRno());
			log.info("================");

			int res = BMService.ModifyCertify3M(bDTO);
			log.info("res : " + res);

			if (res == 1) {
				pDTO = BMService.selectReM(pDTO);

				String board_seq = pDTO.getBoard_seq();
				String content = pDTO.getContent();
				String writer = pDTO.getWriter();
				
				log.info("board_seq : " + board_seq);
				log.info("content : " + content);
				log.info("rno : " + rno);
				log.info("writer : " + writer);

				model.addAttribute("pDTO", pDTO);
				model.addAttribute("rno", rno);
			}	else {
				model.addAttribute("msg", "인증에 실패하였습니다. 게시판 목록으로 이동합니다.");
				model.addAttribute("url", "/BandMoa/M/BMmB.do?Pno=1");
				return "/Redirect";
			}
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
		}

		return "/BandMoa/COMMENT/CommentModifyM";
	}
	
	
	@RequestMapping(value = "/BandMoa/CommentModifyGo")
	public String CommentModifyGo(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "######## 댓글 수정하기 실행########");
		String board_seq = request.getParameter("board_seq");
		String rno = request.getParameter("AAA");
		String writer = request.getParameter("writer");
		log.info("rno : " + rno);
		log.info("board_seq : " + board_seq);
		log.info("writer : " + writer);
		
		COMMENTDTO pDTO = new COMMENTDTO();
		pDTO.setBoard_seq(board_seq);
		pDTO.setRno(rno);
		pDTO.setWriter(writer);

		try {
			String content = (String) request.getParameter("content");
			String pw = (String) request.getParameter("pw");
			pDTO.setContent(content);
			pDTO.setPw(EncryptUtil.encHashSHA256(pw));

			log.info("========================");
			log.info("Board_seq : " + board_seq);
			log.info("rno : " + rno);
			log.info("content : " + content);
			log.info("pw : " + pw);
			log.info("writer : " + writer);
			log.info("========================");

			int res = BMService.CommentUpdate(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
				model.addAttribute("msg", "수정되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
				model.addAttribute("msg", "수정에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Redirect";

	}
	@RequestMapping(value = "/BandMoa/CommentModifyGoSR")
	public String CommentModifyGoSR(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "######## 댓글 수정하기 실행########");
		String board_seq = request.getParameter("board_seq");
		String rno = request.getParameter("AAA");
		String writer = request.getParameter("writer");
		log.info("rno : " + rno);
		log.info("board_seq : " + board_seq);
		log.info("writer : " + writer);
		
		COMMENTDTO pDTO = new COMMENTDTO();
		pDTO.setBoard_seq(board_seq);
		pDTO.setRno(rno);
		pDTO.setWriter(writer);

		try {
			String content = (String) request.getParameter("content");
			String pw = (String) request.getParameter("pw");
			pDTO.setContent(content);
			pDTO.setPw(EncryptUtil.encHashSHA256(pw));

			log.info("========================");
			log.info("Board_seq : " + board_seq);
			log.info("rno : " + rno);
			log.info("content : " + content);
			log.info("pw : " + pw);
			log.info("writer : " + writer);
			log.info("========================");

			int res = BMService.CommentUpdateSR(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
				model.addAttribute("msg", "수정되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
				model.addAttribute("msg", "수정에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Redirect";

	}
	@RequestMapping(value = "/BandMoa/CommentModifyGoQT")
	public String CommentModifyGoQT(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "######## 댓글 수정하기 실행########");
		String board_seq = request.getParameter("board_seq");
		String rno = request.getParameter("AAA");
		String writer = request.getParameter("writer");
		log.info("rno : " + rno);
		log.info("board_seq : " + board_seq);
		log.info("writer : " + writer);
		
		COMMENTDTO pDTO = new COMMENTDTO();
		pDTO.setBoard_seq(board_seq);
		pDTO.setRno(rno);
		pDTO.setWriter(writer);

		try {
			String content = (String) request.getParameter("content");
			String pw = (String) request.getParameter("pw");
			pDTO.setContent(content);
			pDTO.setPw(EncryptUtil.encHashSHA256(pw));

			log.info("========================");
			log.info("Board_seq : " + board_seq);
			log.info("rno : " + rno);
			log.info("content : " + content);
			log.info("pw : " + pw);
			log.info("writer : " + writer);
			log.info("========================");

			int res = BMService.CommentUpdateQT(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
				model.addAttribute("msg", "수정되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
				model.addAttribute("msg", "수정에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Redirect";

	}
	@RequestMapping(value = "/BandMoa/CommentModifyGoM")
	public String CommentModifyGoM(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "######## 댓글 수정하기 실행########");
		String board_seq = request.getParameter("board_seq");
		String rno = request.getParameter("AAA");
		String writer = request.getParameter("writer");
		log.info("rno : " + rno);
		log.info("board_seq : " + board_seq);
		log.info("writer : " + writer);
		
		COMMENTDTO pDTO = new COMMENTDTO();
		pDTO.setBoard_seq(board_seq);
		pDTO.setRno(rno);
		pDTO.setWriter(writer);

		try {
			String content = (String) request.getParameter("content");
			String pw = (String) request.getParameter("pw");
			pDTO.setContent(content);
			pDTO.setPw(EncryptUtil.encHashSHA256(pw));

			log.info("========================");
			log.info("Board_seq : " + board_seq);
			log.info("rno : " + rno);
			log.info("content : " + content);
			log.info("pw : " + pw);
			log.info("writer : " + writer);
			log.info("========================");

			int res = BMService.CommentUpdateM(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/M/BMmB.do?Pno=1");
				model.addAttribute("msg", "수정되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/M/BMmB.do?Pno=1");
				model.addAttribute("msg", "수정에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Redirect";

	}
	
	
	@RequestMapping(value = "/BandMoa/CommentDelete")
	public String CommentDelete(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info(this.getClass().getName() + "######## 댓글 삭제하기 실행########");
		try {
			String rno = request.getParameter("rno");

			COMMENTDTO pDTO = new COMMENTDTO();
			pDTO.setRno(rno);
			log.info("rno : " + rno);

			int res = BMService.CommentDelete(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
				model.addAttribute("msg", "삭제되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/FB/BMFreeB.do?Pno=1");
				model.addAttribute("msg", "삭제에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Redirect";

	}
	@RequestMapping(value = "/BandMoa/CommentDeleteSR")
	public String CommentDeleteSR(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info(this.getClass().getName() + "######## 댓글 삭제하기 실행########");
		try {
			String rno = request.getParameter("rno");

			COMMENTDTO pDTO = new COMMENTDTO();
			pDTO.setRno(rno);
			log.info("rno : " + rno);

			int res = BMService.CommentDeleteSR(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
				model.addAttribute("msg", "삭제되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/SR/BMSrB.do?Pno=1");
				model.addAttribute("msg", "삭제에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Redirect";

	}
	@RequestMapping(value = "/BandMoa/CommentDeleteQT")
	public String CommentDeleteQT(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info(this.getClass().getName() + "######## 댓글 삭제하기 실행########");
		try {
			String rno = request.getParameter("rno");

			COMMENTDTO pDTO = new COMMENTDTO();
			pDTO.setRno(rno);
			log.info("rno : " + rno);

			int res = BMService.CommentDeleteQT(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
				model.addAttribute("msg", "삭제되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/QT/BMqtB.do?Pno=1");
				model.addAttribute("msg", "삭제에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Redirect";

	}
	@RequestMapping(value = "/BandMoa/CommentDeleteM")
	public String CommentDeleteM(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info(this.getClass().getName() + "######## 댓글 삭제하기 실행########");
		try {
			String rno = request.getParameter("rno");

			COMMENTDTO pDTO = new COMMENTDTO();
			pDTO.setRno(rno);
			log.info("rno : " + rno);

			int res = BMService.CommentDeleteM(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/BandMoa/M/BMmB.do?Pno=1");
				model.addAttribute("msg", "삭제되었습니다.");
			} else {
				model.addAttribute("url", "/BandMoa/MM/BMmB.do?Pno=1");
				model.addAttribute("msg", "삭제에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Redirect";

	}
}
