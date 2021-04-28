package kosta.dao;

import java.sql.SQLException;
import java.util.List;

import kosta.dto.Member;
import kosta.dto.Order;
import kosta.exception.AuthenticationException;

public interface MemberDAO {
	public int insert(Member member) throws SQLException;
	public Member loginCheck(Member member) throws SQLException, AuthenticationException;
	public int update(Member dbMember) throws SQLException;
	public int delete(int mbCode) throws SQLException;
	public List<Member> selectPrivate() throws SQLException;
	public Order getOrderListByMember(int mbCode) throws SQLException;
}
