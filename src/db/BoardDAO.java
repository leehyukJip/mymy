package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql;
	
	public BoardDAO() {
		// TODO Auto-generated constructor stub
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/oooo");
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int reply(BoardVO vo) {
		int res = 0;
		BoardVO ori = detail(vo.getId());
		
		try {
			sql = "update mvcboard set seq=seq+1 where gid =? and seq>?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, ori.getGid());
			ptmt.setInt(2, ori.getSeq());
			
			ptmt.executeUpdate();
			
			sql = "insert into mvcboard (id,gid,seq,lev,cnt,reg_data,pname,pw,title,content) "
					+ "values(mvcBoard_Seq.nextval,?,?,?,-1,sysdate,?,?,?,?)";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, ori.getGid());
			ptmt.setInt(2, ori.getSeq()+1);
			ptmt.setInt(3, ori.getLev()+1);
			ptmt.setString(4, vo.getPname());
			ptmt.setString(5, vo.getPw());
			ptmt.setString(6, vo.getTitle());
			ptmt.setString(7, vo.getContent());
			
			ptmt.executeUpdate();
			
			sql = "select max(id) from mvcboard";
			ptmt =con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			rs.next();
			res = rs.getInt(1);
			System.out.println(res);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	public void delete(int id) {
		try {
			sql = "Delete from mvcboard where id = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			
			ptmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public BoardVO sch(BoardVO vo) {
		
		try {
			sql = "select * from mvcboard where id=? and pw =?";
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, vo.getId());
			ptmt.setString(2, vo.getPw());
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				BoardVO res = new BoardVO();
				res.setUpfile(rs.getString("upfile"));
				return res;
			}	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int insert(BoardVO vo) {
		
		try {
			sql = "insert into mvcboard (id, gid, seq, lev, cnt, reg_data, pname, pw, title, content, upfile) values (mvcBoard_Seq.nextval,mvcBoard_Seq.nextval,0,0,-1, sysdate, ?,?,?,?,?)";
			ptmt = con.prepareStatement(sql);
			
			ptmt.setString(1, vo.getPname());
			ptmt.setString(2, vo.getPw());
			ptmt.setString(3, vo.getTitle());
			ptmt.setString(4, vo.getContent());
			ptmt.setString(5, vo.getUpfile());
			
			ptmt.executeUpdate();
			
			sql="select max(id) from mvcBoard";
			ptmt = con.prepareStatement(sql);
			rs= ptmt.executeQuery();
			rs.next();
		
			return rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return 0;
	}

	public BoardVO detail(Integer id) {
		
		try {
			sql ="select * from mvcboard where id = ?";
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, id);
			
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setId(id);
				vo.setGid(rs.getInt("gid"));
				vo.setSeq(rs.getInt("seq"));
				vo.setLev(rs.getInt("lev"));
				vo.setCnt(rs.getInt("cnt"));
				vo.setReg_date(rs.getTimestamp("reg_data"));
				vo.setTitle(rs.getString("title"));
				vo.setPname(rs.getString("pname"));
				vo.setContent(rs.getString("content"));
				vo.setUpfile(rs.getString("upfile"));
				System.out.println(vo.getUpfile());
				return vo;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public void modify(BoardVO vo) {
		try {
			sql = "update mvcboard set title=?, pname=?, content=?, upfile = ? where id = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, vo.getTitle());
			ptmt.setString(2, vo.getPname());
			ptmt.setString(3, vo.getContent());
			ptmt.setString(4, vo.getUpfile());
			ptmt.setInt(5, vo.getId());
			
			ptmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void fileDelete(int id) {
		try {
			sql = "update mvcboard set upfile = null where id = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			
			ptmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void addcnt(int id) {
		try {
			sql = "update mvcboard set cnt = cnt+1 where id = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			ptmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public int totalCnt() {
		int res=0;
		try {
			sql = "select count(*) from mvcboard";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			rs.next();
			res = rs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return res;
	}
	
	public ArrayList<BoardVO> list(int start,int end){
		
		ArrayList<BoardVO> res = new ArrayList<>();

		try {
			sql = "select * from (select rownum rnum, tt.* from (select * from mvcboard order by gid desc,seq) tt) where rnum>=? and rnum<=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, start);
			ptmt.setInt(2, end);
			
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				
				vo.setGid(rs.getInt("gid"));
				vo.setSeq(rs.getInt("seq"));
				vo.setLev(rs.getInt("lev"));
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setPname(rs.getString("pname"));
				vo.setReg_date(rs.getTimestamp("reg_data"));
				res.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}finally {
			close();
		}
		return res;
	}
	
	public void close() {
		try {
			if(con!=null) {	con.close();}
			if(ptmt!=null) {	ptmt.close();}
			if(rs!=null) {	rs.close();}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
