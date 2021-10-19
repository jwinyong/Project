package web.as.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.as.dao.AsinfoDao;
import web.as.vo.asinfoVO;

@Service
public class AsinfoServiceImpl implements AsinfoService{

	@Autowired
	AsinfoDao asinfodao; // 왜 클래스에 의존할까?
	// 바뀌지 않는 것을 내가 들고 있다! DAO가 바뀌면 IMPL도 바뀐다!
	// 이렇게 하는 이유는?
	// - SRP 개념으로 분리, 바뀔 가능성 거의 없고 설계 관점상
	
	// 뷰 컨트롤러와 멤버, AS 서비스가 바뀌는데 서로 연관이 있어서는 안된다
	// 따라서 이 둘 사이에 인터페이스로 바뀌는데 서로 연관을 없게 해준다
	
	@Override
	public List<asinfoVO> selectAsInfoList(asinfoVO vo) throws Exception {
		return asinfodao.selectAsInfoList(vo);
	}


	@Override
	public asinfoVO selectAsInfo(asinfoVO vo) throws Exception {
		return asinfodao.selectAsInfo(vo);
	}


	@Override
	public int updateAsinfo(asinfoVO vo) throws Exception {
		return asinfodao.updateAsinfo(vo);
	}


	@Override
	public int insertAsinfo(asinfoVO vo) throws Exception {
		return asinfodao.insertAsinfo(vo);
	}


	@Override
	public int deleteAsinfo(asinfoVO vo) throws Exception {
		return asinfodao.deleteAsinfo(vo);
	}
	
	
}
