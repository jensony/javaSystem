import java.sql.*;
import javax.swing.table.*;
public class ConnectDB 
{
	private Connection connection;
   	public ConnectDB(String driver,String url) throws ClassNotFoundException,SQLException
	{
    	this.connection=null;
    	Class.forName(driver);
    	this.connection=DriverManager.getConnection(url);
    }
    public void finalize() throws SQLException
    {
    	connection.close();
    }
    public int dataUpdate(String sql) throws SQLException
    {
    	Statement statement=this.connection.createStatement();
    	int result=statement.executeUpdate(sql);
    	statement.close();
    	return result;
    }
    public int select(String sql,DefaultTableModel dtm)throws SQLException
    {
    	Statement statement=this.connection.createStatement();
    	ResultSet resultset=statement.executeQuery(sql);
    	ResultSetMetaData rsmd=resultset.getMetaData();
    	int c=rsmd.getColumnCount();
    	int r=0;
    	String []data=new String[c];
    	while(resultset.next())
    	{
    		for(int i=0;i<c;i++)
    		{
    			data[i]=resultset.getString(i+1);
    			if(data[i]!=null)
    				data[i]=data[i].trim();
    		}
    		dtm.addRow(data);
    		r++;
    	}
    	return r;
    }
}