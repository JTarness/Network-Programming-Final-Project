package com.helloworld.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class UserDao {
 
		public String findUserpsw(String username){
			String psw = null;
			Connection con =null;
			PreparedStatement pstmt =null;
			ResultSet rs = null;
			try {
				con=new JDBCHelper().getConnection();
				String sql = "SELECT * FROM useraccount WHERE username=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, username);
				rs = pstmt.executeQuery();
				if(rs==null){
					return null;
				}
				if(rs.next()){
					psw=rs.getString("password");
				}else{
					psw=null;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null)pstmt.close();
					if(con!=null)con.close();
					} 
				catch (SQLException e) {		
				}
			}
			return psw;
		}
		public String findUsername(String phone){
			String username = null;
			Connection con =null;
			PreparedStatement pstmt =null;
			ResultSet rs = null;
			try {
				con=new JDBCHelper().getConnection();
				String sql = "SELECT * FROM useraccount WHERE phoneNum=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, phone);
				rs = pstmt.executeQuery();
				if(rs==null){
					return null;
				}
				if(rs.next()){
					username=rs.getString("username");
				}else{
					username=null;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null)pstmt.close();
					if(con!=null)con.close();
					} 
				catch (SQLException e) {		
				}
			}
			return username;
		}
		public String findUUID(String code){
			String uuid = null;
			Connection con =null;
			PreparedStatement pstmt =null;
			ResultSet rs = null;
			try {
				con=new JDBCHelper().getConnection();
				String sql = "SELECT * FROM verifycode WHERE code=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, code);
				rs = pstmt.executeQuery();
				if(rs==null){
					return null;
				}
				if(rs.next()){
					uuid=rs.getString("UUID");
				}else{
					uuid=null;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null)pstmt.close();
					if(con!=null)con.close();
					} 
				catch (SQLException e) {		
										}
			}
			return uuid;
		}
		public String findverifycode(String uuid){
			String code = null;
			Connection con =null;
			PreparedStatement pstmt =null;
			ResultSet rs = null;
			try {
				con=new JDBCHelper().getConnection();
				String sql = "SELECT * FROM verifycode WHERE UUID=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, uuid);
				rs = pstmt.executeQuery();
				if(rs==null){
					return null;
				}
				if(rs.next()){
					code=rs.getString("code");
				}else{
					code=null;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null)pstmt.close();
					if(con!=null)con.close();
					} 
				catch (SQLException e) {		
										}
			}
			return code;
		}
		public void newPassword(String username, String newPsw) {
			Connection con =null;
			PreparedStatement pstmt =null;
			try {
				con=new JDBCHelper().getConnection();
				String sql = "UPDATE useraccount SET password=? WHERE username=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,newPsw);
				pstmt.setString(2, username);
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null)pstmt.close();
					if(con!=null)con.close();
					} 
				catch (SQLException e) {		
					e.printStackTrace();
				}
			}
		}
		public void addUser(int id,String username,String psw,String phoneNum){
			Connection con =null;
			PreparedStatement pstmt =null;
			try {
				con=new JDBCHelper().getConnection();
				String sql = "INSERT INTO useraccount VALUES(?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.setString(2, username);
				pstmt.setString(3, psw);
				pstmt.setString(4, phoneNum);
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null)pstmt.close();
					if(con!=null)con.close();
					} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		public void deleteUUID(String code){
			Connection con =null;
			PreparedStatement pstmt =null;
			try {
				con=new JDBCHelper().getConnection();
				String sql = "DELETE FROM verifycode WHERE CODE=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, code);
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null)pstmt.close();
					if(con!=null)con.close();
					} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		public void addUUID(String code,String uuid) {
			Connection con =null;
			PreparedStatement ps =null;
			try {
				con=new JDBCHelper().getConnection();
				String sql = "INSERT INTO verifycode VALUES(?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, code);
				ps.setString(2, uuid);
				ps.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(ps!=null)ps.close();
					if(con!=null)con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		public boolean isRegistered(String username) {
			Connection con =null;
			ResultSet rs=null;
			PreparedStatement pstmt=null;
			try {
				con=new JDBCHelper().getConnection();
				String sql = "SELECT COUNT(*) FROM useraccount WHERE username=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, username);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					int count=rs.getInt("COUNT(*)");
					if(count>0) {
					return true;
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null)pstmt.close();
					if(con!=null)con.close();
					} 
				catch (SQLException e) {		
				}
			}
			return false;
		}
		public boolean newPhoneNum(String phone) {
			Connection con =null;
			ResultSet rs=null;
			PreparedStatement pstmt=null;
			try {
				con=new JDBCHelper().getConnection();
				String sql = "SELECT COUNT(*) FROM useraccount WHERE phoneNum=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, phone);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					int count=rs.getInt("COUNT(*)");
					if(count>0) {
					return false;
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null)pstmt.close();
					if(con!=null)con.close();
					} 
				catch (SQLException e) {		
										}
			}
			return true;
		}
		public String testPhoneNum(String phone){
			Connection con =null;
			PreparedStatement pstmt =null;
			ResultSet rs = null;
			try {
				con=new JDBCHelper().getConnection();
				String sql = "SELECT * FROM useraccount WHERE phoneNum=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, phone);
				rs = pstmt.executeQuery();
				if(rs==null){
					return null;
				}
				if(rs.next()){
					phone=rs.getString("phoneNum");
				}else{
					phone=null;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null)pstmt.close();
					if(con!=null)con.close();
					} 
				catch (SQLException e) {		
										}
			}
			return phone;
		}
}