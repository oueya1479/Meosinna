package kosta.service;

import java.sql.SQLException;
import java.util.List;

import kosta.dao.ReviewDAO;
import kosta.dao.ReviewDAOImpl;
import kosta.dto.Review;

public class ReviewService {
	ReviewDAO dao = new ReviewDAOImpl();
	
	public List<Review> selectByGdCode(String gdCode) throws SQLException{
		List<Review> list = dao.selectByGdCode(gdCode);
		if(list == null) {
			throw new SQLException("해당 상품의 리뷰가 존재하지 않습니다.");
		}
		return list;
	}
}
