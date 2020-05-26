package poly.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.BMUserInfoDTO;
import poly.dto.COMMENTDTO;
import poly.dto.BMFBDTO;
import poly.dto.BMSRDTO;
import poly.dto.BMQTDTO;
import poly.dto.BMMDTO;
import poly.dto.COMMENTDTO;
import poly.persistance.mapper.BMMapper;
import poly.service.IBMService;
import poly.util.CmmUtil;

@Service("BMService")
public class BMService  implements IBMService{

	@Resource(name="BMMapper")
	private BMMapper BMMapper;
	
	@Override
	public int insertBMFreeBR(BMFBDTO bDTO) throws Exception {
		return BMMapper.insertBMFreeBR(bDTO);
	}
	@Override
	public int insertBMSrBR(BMSRDTO bDTO) throws Exception {
		return BMMapper.insertBMSrBR(bDTO);
	}
	@Override
	public int insertBMqtBR(BMQTDTO bDTO) throws Exception {
		return BMMapper.insertBMqtBR(bDTO);
	}
	@Override
	public int insertBMmBR(BMMDTO bDTO) throws Exception {
		return BMMapper.insertBMmBR(bDTO);
	}

	
	@Override
	public List<BMFBDTO> getBMFreeB(HashMap<String, Integer> hMap) throws Exception {
		return BMMapper.getBMFreeB(hMap);
	}
	@Override
	public List<BMSRDTO> getBMSrB(HashMap<String, Integer> hMap) throws Exception {
		return BMMapper.getBMSrB(hMap);
	}
	@Override
	public List<BMQTDTO> getBMqtB(HashMap<String, Integer> hMap) throws Exception {
		return BMMapper.getBMqtB(hMap);
	}
	@Override
	public List<BMMDTO> getBMmB(HashMap<String, Integer> hMap) throws Exception {
		return BMMapper.getBMmB(hMap);
	}
	@Override
	public List<BMUserInfoDTO> getBMUserInfo(HashMap<String, Integer> hMap) throws Exception {
		return BMMapper.getBMUserInfo(hMap);
	}

	
	
	
	@Override
	public int insertLogin_Join(BMUserInfoDTO pDTO) throws Exception {
		//회원가입성공:1, 아이디중복으로인한 가입취소:2, 기타 에러 발생:0
		System.out.println("service Start");
		int res = BMMapper.insertLogin_Join(pDTO);;
		
		//컨트롤러에서 값이 정상적으로 못넘어오는 경우를 대비하기 위해 사용
		if(pDTO ==null) {
			pDTO = new BMUserInfoDTO();
		}
		
		
		return res;
	}


	@Override
	public BMUserInfoDTO getInsertLogin_Join(BMUserInfoDTO bDTO) throws Exception {
		return null;
	}
	
	@Override
	public List<BMUserInfoDTO> getInserLogin_Join() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기
	 * @param BMUserInfoDTO 로그인을 위한 회원아이디, 비밀번호
	 * @return BMUserInfoDTO 로그인된 회원 아이디 정보
	 */
	@Override
	public int getUserLoginCheck(BMUserInfoDTO pDTO) throws Exception {
		//로그인 성공 :1 , 실패:0
		int res = 0;
		
		
		//로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 mapper 호출하기
		BMUserInfoDTO rDTO = BMMapper.getUserLoginCheck(pDTO);
		
		if(rDTO == null) {
			rDTO = new BMUserInfoDTO();
		}
		  //로그인 성공 여부 체크 시작
		/*
		 * mapper로부터 select 쿼리의 결과로 회원 아이디를 받아왔다면 성공.
		 * DTO의 변수에 값이 있는지 확인하기 처리속도 측면에서 가장 좋은 방법은 변수의 길이를 가져오는 것이다.
		 * 따라서 .length() 하무를 통해 회원아이디의 글자수를 가져와 0보다 큰지 비교한다
		 * 0보다 크다면, 글자가 존재하는 것이기 때문에 값이 존재한다
		 */
		if (CmmUtil.nvl(rDTO.getUser_id()).length()>0) {
			res = 1;
		}
		 //로그인 성공 여부 체크 끝
		return res;
	}

	@Override
	public BMUserInfoDTO GUI(BMUserInfoDTO pDTO) throws Exception {
		return BMMapper.GUI(pDTO);
	}

	@Override
	public int UpdatePw(BMUserInfoDTO pDTO) throws Exception {
		return BMMapper.UpdatePw(pDTO);
	}

	@Override
	public int MypageModify(BMUserInfoDTO pDTO) throws Exception {
		return BMMapper.MypageModify(pDTO);
	}
	
	@Override
	public int MypageModify2(BMUserInfoDTO pDTO) throws Exception {

		return BMMapper.MypageModify2(pDTO);
	}

	@Override
	public int SelectIdFind(BMUserInfoDTO pDTO) throws Exception {
		
		int res = 0;
		
		BMUserInfoDTO rDTO = BMMapper.SelectIdFind(pDTO);
		
		if(rDTO == null) {
			rDTO = new BMUserInfoDTO();
		}
		  
		if (CmmUtil.nvl(rDTO.getUser_id()).length()>0) {
			res = 1;
		}
		return res;
	}

	@Override
	public String IdFind(BMUserInfoDTO pDTO) throws Exception {
		return BMMapper.IdFind(pDTO);
	}

	@Override
	public int UserMypageCertify(BMUserInfoDTO pDTO) throws Exception {
		
		int res = 0;
		
		BMUserInfoDTO rDTO = BMMapper.UserMypageCertify(pDTO);
		
		if(rDTO == null) {
			rDTO = new BMUserInfoDTO();
		}
		if (CmmUtil.nvl(rDTO.getUser_id()).length()>0) {
			res = 1;
		}
		return res;
	}

	
	
	@Override
	public BMFBDTO getBMFreeBD(String seq) throws Exception {
		return BMMapper.getBMFreeBD(seq);
	}
	@Override
	public BMSRDTO getBMSrBD(String seq) throws Exception {
		return BMMapper.getBMSrBD(seq);
	}	@Override
	public BMQTDTO getBMqtBD(String seq) throws Exception {
		return BMMapper.getBMqtBD(seq);
	}	@Override
	public BMMDTO getBMmBD(String seq) throws Exception {
		return BMMapper.getBMmBD(seq);
	}

	
	
	
	@Override
	public BMFBDTO ModifyCertify(BMFBDTO pDTO) throws Exception {
			return BMMapper.ModifyCertify(pDTO);
		}
	@Override
	public BMSRDTO ModifyCertifySR(BMSRDTO pDTO) throws Exception {
			return BMMapper.ModifyCertifySR(pDTO);
		}
	@Override
	public BMQTDTO ModifyCertifyQT(BMQTDTO pDTO) throws Exception {
			return BMMapper.ModifyCertifyQT(pDTO);
		}
	@Override
	public BMMDTO ModifyCertifyM(BMMDTO pDTO) throws Exception {
			return BMMapper.ModifyCertifyM(pDTO);
		}
	
	
	
	

	@Override
	public BMFBDTO GUI2(BMFBDTO pDTO) throws Exception {
		return BMMapper.GUI2(pDTO);
	}
	@Override
	public BMSRDTO GUI2SR(BMSRDTO pDTO) throws Exception {
		return BMMapper.GUI2SR(pDTO);
	}
	@Override
	public BMQTDTO GUI2QT(BMQTDTO pDTO) throws Exception {
		return BMMapper.GUI2QT(pDTO);
	}
	@Override
	public BMMDTO GUI2M(BMMDTO pDTO) throws Exception {
		return BMMapper.GUI2M(pDTO);
	}
	
	
	
	

	@Override
	public BMFBDTO ModifyCertify2(BMFBDTO pDTO) throws Exception {
				return BMMapper.ModifyCertify2(pDTO);
	}
	@Override
	public BMSRDTO ModifyCertify2SR(BMSRDTO pDTO) throws Exception {
				return BMMapper.ModifyCertify2SR(pDTO);
	}
	@Override
	public BMQTDTO ModifyCertify2QT(BMQTDTO pDTO) throws Exception {
				return BMMapper.ModifyCertify2QT(pDTO);
	}
	@Override
	public BMMDTO ModifyCertify2M(BMMDTO pDTO) throws Exception {
				return BMMapper.ModifyCertify2M(pDTO);
	}
	
	
	
	

	@Override
	public int BMFreeBDModify(BMFBDTO pDTO) throws Exception {
		
		int res = 0;
		
		BMFBDTO rDTO = BMMapper.BMFreeBDModify(pDTO);
		
		if(rDTO == null) {
			rDTO = new BMFBDTO();
		}
		if (CmmUtil.nvl(rDTO.getBoard_seq()).length()>0) {
			res = 1;
		}
		return res;
	}
	@Override
	public int BMSrBDModify(BMSRDTO pDTO) throws Exception {
		
		int res = 0;
		
		BMSRDTO rDTO = BMMapper.BMSrBDModify(pDTO);
		
		if(rDTO == null) {
			rDTO = new BMSRDTO();
		}
		if (CmmUtil.nvl(rDTO.getBoard_seq()).length()>0) {
			res = 1;
		}
		return res;
	}
	@Override
	public int BMqtBDModify(BMQTDTO pDTO) throws Exception {
		
		int res = 0;
		
		BMQTDTO rDTO = BMMapper.BMqtBDModify(pDTO);
		
		if(rDTO == null) {
			rDTO = new BMQTDTO();
		}
		if (CmmUtil.nvl(rDTO.getBoard_seq()).length()>0) {
			res = 1;
		}
		return res;
	}
	
	@Override
	public int BMmBDModify(BMMDTO pDTO) throws Exception {
		
		int res = 0;
		
		BMMDTO rDTO = BMMapper.BMmBDModify(pDTO);
		
		if(rDTO == null) {
			rDTO = new BMMDTO();
		}
		if (CmmUtil.nvl(rDTO.getBoard_seq()).length()>0) {
			res = 1;
		}
		return res;
	}
	
	
	
	

	@Override
	public BMFBDTO GUI3(BMFBDTO pDTO) throws Exception {
		return BMMapper.GUI3(pDTO);
	}
	@Override
	public BMSRDTO GUI3SR(BMSRDTO pDTO) throws Exception {
		return BMMapper.GUI3SR(pDTO);
	}
	@Override
	public BMQTDTO GUI3QT(BMQTDTO pDTO) throws Exception {
		return BMMapper.GUI3QT(pDTO);
	}
	@Override
	public BMMDTO GUI3M(BMMDTO pDTO) throws Exception {
		return BMMapper.GUI3M(pDTO);
	}
	
	
	
	

	@Override
	public int updateFB(BMFBDTO pDTO) throws Exception {
		return BMMapper.updateFB(pDTO);
	}
	@Override
	public int updateSR(BMSRDTO pDTO) throws Exception {
		return BMMapper.updateSR(pDTO);
	}
	@Override
	public int updateQT(BMQTDTO pDTO) throws Exception {
		return BMMapper.updateQT(pDTO);
	}
	@Override
	public int updateM(BMMDTO pDTO) throws Exception {
		return BMMapper.updateM(pDTO);
	}
	
	
	

	@Override
	public int deleteFB(BMFBDTO pDTO) throws Exception {
		return BMMapper.deleteFB(pDTO);
	}
	@Override
	public int deleteSR(BMSRDTO pDTO) throws Exception {
		return BMMapper.deleteSR(pDTO);
	}
	@Override
	public int deleteQT(BMQTDTO pDTO) throws Exception {
		return BMMapper.deleteQT(pDTO);
	}
	@Override
	public int deleteM(BMMDTO pDTO) throws Exception {
		return BMMapper.deleteM(pDTO);
	}
	
	
	
	
	
	@Override
	public int TotalCountFB() throws Exception {
		return BMMapper.TotalCountFB();
	}
	@Override
	public int TotalCountSR() throws Exception {
		return BMMapper.TotalCountSR();
	}
	@Override
	public int TotalCountQT() throws Exception {
		return BMMapper.TotalCountQT();
	}
	@Override
	public int TotalCountM() throws Exception {
		return BMMapper.TotalCountM();
	}
	@Override
	public int TotalCountUserInfo() throws Exception {
		return BMMapper.TotalCountUserInfo();
	}
	
	
	
	
	
	@Override
	public String checkId(BMUserInfoDTO pDTO) throws Exception {
		return BMMapper.checkId(pDTO);
	}
	@Override
	public int UserDelete(BMUserInfoDTO pDTO) throws Exception {
		return BMMapper.UserDelete(pDTO);
	}
	
	
	
	@Override
	public int ModifyCertify3(BMUserInfoDTO bDTO) throws Exception {
		return BMMapper.ModifyCertify3(bDTO);
	}
	@Override
	public int ModifyCertify3SR(BMUserInfoDTO bDTO) throws Exception {
		return BMMapper.ModifyCertify3SR(bDTO);
	}
	@Override
	public int ModifyCertify3QT(BMUserInfoDTO bDTO) throws Exception {
		return BMMapper.ModifyCertify3QT(bDTO);
	}
	@Override
	public int ModifyCertify3M(BMUserInfoDTO bDTO) throws Exception {
		return BMMapper.ModifyCertify3M(bDTO);
	}
	
	
	
	
	
	@Override
	public BMFBDTO GUI4(BMFBDTO pDTO) throws Exception {
		return BMMapper.GUI4(pDTO);
	}
	@Override
	public BMSRDTO GUI4SR(BMSRDTO pDTO) throws Exception {
		return BMMapper.GUI4SR(pDTO);
	}
	@Override
	public BMQTDTO GUI4QT(BMQTDTO pDTO) throws Exception {
		return BMMapper.GUI4QT(pDTO);
	}
	@Override
	public BMMDTO GUI4M(BMMDTO pDTO) throws Exception {
		return BMMapper.GUI4M(pDTO);
	}
	@Override
	public BMUserInfoDTO AdminUserInfoModify(String user_no) throws Exception {
		return BMMapper.AdminUserInfoModify(user_no);
	}
	@Override
	public int AdminUserInfoModify2(BMUserInfoDTO pDTO) throws Exception {
		return BMMapper.AdminUserInfoModify2(pDTO);
	}
	@Override
	public String AdminUserDelete(BMUserInfoDTO pDTO) throws Exception {
		return BMMapper.AdminUserDelete(pDTO);
	}
	@Override
	public int AdminUserDelete2(BMUserInfoDTO bDTO) throws Exception {
		return BMMapper.AdminUserDelete2(bDTO);
	}

	
	
	
	@Override
	public List<COMMENTDTO> readReply(String seq) throws Exception {
		return BMMapper.readReply(seq);
	}
	@Override
	public List<COMMENTDTO> readReplySR(String seq) throws Exception {
		return BMMapper.readReplySR(seq);
	}
	@Override
	public List<COMMENTDTO> readReplyQT(String seq) throws Exception {
		return BMMapper.readReplyQT(seq);
	}
	@Override
	public List<COMMENTDTO> readReplyM(String seq) throws Exception {
		return BMMapper.readReplyM(seq);
	}
	
	
	@Override
	public int insertComment(COMMENTDTO bDTO) throws Exception {
		return BMMapper.insertComment(bDTO);
	}
	@Override
	public int insertCommentSR(COMMENTDTO bDTO) throws Exception {
		return BMMapper.insertCommentSR(bDTO);
	}
	@Override
	public int insertCommentQT(COMMENTDTO bDTO) throws Exception {
		return BMMapper.insertCommentQT(bDTO);
	}
	@Override
	public int insertCommentM(COMMENTDTO bDTO) throws Exception {
		return BMMapper.insertCommentM(bDTO);
	}
	
	
	@Override
	public COMMENTDTO CommentModifyTry(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentModifyTry(pDTO);
	}
	@Override
	public COMMENTDTO CommentModifyTrySR(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentModifyTrySR(pDTO);
	}
	@Override
	public COMMENTDTO CommentModifyTryQT(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentModifyTryQT(pDTO);
	}
	@Override
	public COMMENTDTO CommentModifyTryM(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentModifyTryM(pDTO);
	}
	
	
	@Override
	public int CommentCertify(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentCertify(pDTO);
	}
	@Override
	public int CommentCertifySR(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentCertifySR(pDTO);
	}
	@Override
	public int CommentCertifyQT(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentCertifyQT(pDTO);
	}
	@Override
	public int CommentCertifyM(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentCertifyM(pDTO);
	}
	
	
	
	@Override
	public COMMENTDTO CommentCertify2(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentCertify2(pDTO);
	}
	@Override
	public COMMENTDTO CommentCertify2SR(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentCertify2SR(pDTO);
	}
	@Override
	public COMMENTDTO CommentCertify2QT(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentCertify2QT(pDTO);
	}
	@Override
	public COMMENTDTO CommentCertify2M(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentCertify2M(pDTO);
	}
	
	
	@Override
	public int CommentUpdate(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentUpdate(pDTO);
	}
	@Override
	public int CommentUpdateSR(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentUpdateSR(pDTO);
	}
	@Override
	public int CommentUpdateQT(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentUpdateQT(pDTO);
	}
	@Override
	public int CommentUpdateM(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentUpdateM(pDTO);
	}
	
	
	@Override
	public int CommentDelete(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentDelete(pDTO);
	}
	@Override
	public int CommentDeleteSR(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentDeleteSR(pDTO);
	}
	@Override
	public int CommentDeleteQT(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentDeleteQT(pDTO);
	}
	@Override
	public int CommentDeleteM(COMMENTDTO pDTO) throws Exception {
		return BMMapper.CommentDeleteM(pDTO);
	}
	
	
	@Override
	public COMMENTDTO selectRe(COMMENTDTO pDTO) throws Exception {
		return BMMapper.selectRe(pDTO);
	}
	@Override
	public COMMENTDTO selectReSR(COMMENTDTO pDTO) throws Exception {
		return BMMapper.selectReSR(pDTO);
	}
	@Override
	public COMMENTDTO selectReQT(COMMENTDTO pDTO) throws Exception {
		return BMMapper.selectReQT(pDTO);
	}
	@Override
	public COMMENTDTO selectReM(COMMENTDTO pDTO) throws Exception {
		return BMMapper.selectReM(pDTO);
	}
	
	


	




	



}


	
	
	