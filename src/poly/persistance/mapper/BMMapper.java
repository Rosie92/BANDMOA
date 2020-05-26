package poly.persistance.mapper;

import java.util.HashMap;
import java.util.List;

import config.Mapper;
import poly.dto.BMUserInfoDTO;
import poly.dto.COMMENTDTO;
import poly.dto.BMFBDTO;
import poly.dto.BMSRDTO;
import poly.dto.BMQTDTO;
import poly.dto.BMMDTO;

@Mapper("BMMapper")
public interface BMMapper {

	//===========================================================

	BMFBDTO getBMFreeBD(String seq) throws Exception;
	BMSRDTO getBMSrBD(String seq) throws Exception;
	BMQTDTO getBMqtBD(String seq) throws Exception;
	BMMDTO getBMmBD(String seq) throws Exception;

	
	BMUserInfoDTO getinsertLogin_Join(BMUserInfoDTO bDTO) throws Exception;
	//로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기
	BMUserInfoDTO getUserLoginCheck(BMUserInfoDTO pDTO) throws Exception;

	BMUserInfoDTO GUI(BMUserInfoDTO pDTO)throws Exception;
	
	BMUserInfoDTO SelectIdFind(BMUserInfoDTO pDTO) throws Exception;

	BMUserInfoDTO UserMypageCertify(BMUserInfoDTO pDTO) throws Exception;
	
	String IdFind(BMUserInfoDTO pDTO) throws Exception;

	
	//===========================================================

	List<BMFBDTO> getBMFreeB(HashMap<String, Integer> hMap) throws Exception;
	List<BMSRDTO> getBMSrB(HashMap<String, Integer> hMap) throws Exception;
	List<BMQTDTO> getBMqtB(HashMap<String, Integer> hMap) throws Exception;
	List<BMMDTO> getBMmB(HashMap<String, Integer> hMap) throws Exception;

	List<BMUserInfoDTO> getBMUserInfo(HashMap<String, Integer> hMap) throws Exception;
	

	//===========================================================
	
	//회원가입하기(회원정보등록하기)
	int insertBMUserInfo(BMUserInfoDTO pDTO) throws Exception;
	/*
	// 회원가입 전 중복체크하기(DB조회하기)
	BMUserInfoDTO getUserExists(BMUserInfoDTO pDTO) throws Exception;
	*/
	int insertBMFreeBR(BMFBDTO bDTO) throws Exception;
	int insertBMSrBR(BMSRDTO bDTO) throws Exception;
	int insertBMqtBR(BMQTDTO bDTO) throws Exception;
	int insertBMmBR(BMMDTO bDTO) throws Exception;

	int insertLogin_Join(BMUserInfoDTO pDTO) throws Exception;

	int UpdatePw(BMUserInfoDTO pDTO) throws Exception;

	int MypageModify(BMUserInfoDTO pDTO)throws Exception;

	int MypageModify2(BMUserInfoDTO pDTO) throws Exception;

	BMFBDTO ModifyCertify(BMFBDTO pDTO) throws Exception;
	BMSRDTO ModifyCertifySR(BMSRDTO pDTO) throws Exception;
	BMQTDTO ModifyCertifyQT(BMQTDTO pDTO) throws Exception;
	BMMDTO ModifyCertifyM(BMMDTO pDTO) throws Exception;

	BMFBDTO GUI2(BMFBDTO pDTO) throws Exception;
	BMSRDTO GUI2SR(BMSRDTO pDTO) throws Exception;
	BMQTDTO GUI2QT(BMQTDTO pDTO) throws Exception;
	BMMDTO GUI2M(BMMDTO pDTO) throws Exception;

	
	BMFBDTO ModifyCertify2(BMFBDTO pDTO) throws Exception;
	BMSRDTO ModifyCertify2SR(BMSRDTO pDTO) throws Exception;
	BMQTDTO ModifyCertify2QT(BMQTDTO pDTO) throws Exception;
	BMMDTO ModifyCertify2M(BMMDTO pDTO) throws Exception;

	int updateFB(BMFBDTO pDTO) throws Exception;
	int updateSR(BMSRDTO pDTO) throws Exception;
	int updateQT(BMQTDTO pDTO) throws Exception;
	int updateM(BMMDTO pDTO) throws Exception;

	BMFBDTO BMFreeBDModify(BMFBDTO pDTO) throws Exception;
	BMSRDTO BMSrBDModify(BMSRDTO pDTO) throws Exception;
	BMQTDTO BMqtBDModify(BMQTDTO pDTO) throws Exception;
	BMMDTO BMmBDModify(BMMDTO pDTO) throws Exception;

	BMFBDTO GUI3(BMFBDTO pDTO) throws Exception;
	BMSRDTO GUI3SR(BMSRDTO pDTO) throws Exception;
	BMQTDTO GUI3QT(BMQTDTO pDTO) throws Exception;
	BMMDTO GUI3M(BMMDTO pDTO) throws Exception;
	
	
	int deleteFB(BMFBDTO pDTO) throws Exception;
	int deleteSR(BMSRDTO pDTO) throws Exception;
	int deleteQT(BMQTDTO pDTO) throws Exception;
	int deleteM(BMMDTO pDTO) throws Exception;
	
	
	
	int TotalCountFB() throws Exception;
	int TotalCountSR() throws Exception;
	int TotalCountQT() throws Exception;
	int TotalCountM() throws Exception;
	int TotalCountUserInfo() throws Exception;
	
	
	
	String checkId(BMUserInfoDTO pDTO) throws Exception;
	int UserDelete(BMUserInfoDTO pDTO) throws Exception;
	
	
	int ModifyCertify3(BMUserInfoDTO bDTO) throws Exception;
	int ModifyCertify3SR(BMUserInfoDTO bDTO) throws Exception;
	int ModifyCertify3QT(BMUserInfoDTO bDTO) throws Exception;
	int ModifyCertify3M(BMUserInfoDTO bDTO) throws Exception;
	
	
	BMFBDTO GUI4(BMFBDTO pDTO) throws Exception;
	BMSRDTO GUI4SR(BMSRDTO pDTO) throws Exception;
	BMQTDTO GUI4QT(BMQTDTO pDTO) throws Exception;
	BMMDTO GUI4M(BMMDTO pDTO) throws Exception;
	BMUserInfoDTO AdminUserInfoModify(String user_no) throws Exception;
	int AdminUserInfoModify2(BMUserInfoDTO pDTO) throws Exception;
	String AdminUserDelete(BMUserInfoDTO pDTO) throws Exception;
	int AdminUserDelete2(BMUserInfoDTO bDTO) throws Exception;
	
	List<COMMENTDTO> readReply(String seq) throws Exception;
	List<COMMENTDTO> readReplySR(String seq) throws Exception;
	List<COMMENTDTO> readReplyQT(String seq) throws Exception;
	List<COMMENTDTO> readReplyM(String seq) throws Exception;
	
	int insertComment(COMMENTDTO bDTO) throws Exception;
	int insertCommentSR(COMMENTDTO bDTO) throws Exception;
	int insertCommentQT(COMMENTDTO bDTO) throws Exception;
	int insertCommentM(COMMENTDTO bDTO) throws Exception;
	
	COMMENTDTO CommentModifyTry(COMMENTDTO pDTO) throws Exception;
	COMMENTDTO CommentModifyTrySR(COMMENTDTO pDTO) throws Exception;
	COMMENTDTO CommentModifyTryQT(COMMENTDTO pDTO) throws Exception;
	COMMENTDTO CommentModifyTryM(COMMENTDTO pDTO) throws Exception;
	
	int CommentCertify(COMMENTDTO pDTO) throws Exception;
	int CommentCertifySR(COMMENTDTO pDTO) throws Exception;
	int CommentCertifyQT(COMMENTDTO pDTO) throws Exception;
	int CommentCertifyM(COMMENTDTO pDTO) throws Exception;
	
	COMMENTDTO CommentCertify2(COMMENTDTO pDTO) throws Exception;
	COMMENTDTO CommentCertify2SR(COMMENTDTO pDTO) throws Exception;
	COMMENTDTO CommentCertify2QT(COMMENTDTO pDTO) throws Exception;
	COMMENTDTO CommentCertify2M(COMMENTDTO pDTO) throws Exception;
	
	int CommentUpdate(COMMENTDTO pDTO) throws Exception;
	int CommentUpdateSR(COMMENTDTO pDTO) throws Exception;
	int CommentUpdateQT(COMMENTDTO pDTO) throws Exception;
	int CommentUpdateM(COMMENTDTO pDTO) throws Exception;
	
	int CommentDelete(COMMENTDTO pDTO) throws Exception;
	int CommentDeleteSR(COMMENTDTO pDTO) throws Exception;
	int CommentDeleteQT(COMMENTDTO pDTO) throws Exception;
	int CommentDeleteM(COMMENTDTO pDTO) throws Exception;
	
	COMMENTDTO selectRe(COMMENTDTO pDTO) throws Exception;
	COMMENTDTO selectReSR(COMMENTDTO pDTO) throws Exception;
	COMMENTDTO selectReQT(COMMENTDTO pDTO) throws Exception;
	COMMENTDTO selectReM(COMMENTDTO pDTO) throws Exception;
	
	
	


	
	
	

}
