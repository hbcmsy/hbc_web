package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.SqlHelper;
import model.File;
import model.User;

public class FileDao {
	public File getFile(int ID){
		
		return null;
	}
	public int addFile(File file) throws IOException, SQLException{
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            con = SqlHelper.connect();
            String sql = "insert into hbc_file (file_name,file_suffix,file_data,file_size)value(?,?,?,?)";// (2)写sql语句
            ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);// (3)建立预处理
            ps.setString(1, file.getName());
            ps.setString(1, file.getSuffix());
            ps.setBytes(3, this.getBufferr(file.getFile()));
            ps.setLong(4, file.getSize());
            ps.executeUpdate();
            int id = -1;
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            return id;
        }finally {// 4.释放资源
            SqlHelper.closeResult(rs);
            SqlHelper.closePreparedStatement(ps);
            SqlHelper.closeConneciton(con);  
            //return id;
        }
	}
	public byte[] getBufferr(java.io.File file) throws IOException{
		FileInputStream fin = new FileInputStream(file);
		ByteBuffer bbf = ByteBuffer.allocate((int)file.length());
		byte[] array = new byte[1024];
		int offset = 0,length = 0;
		while((length=fin.read(array))>0){
			if(length!=1024)
				bbf.put(array,0,length);
			else
				bbf.put(array);
			offset+=length;
		}
		fin.close();
		return bbf.array();
	}
}
