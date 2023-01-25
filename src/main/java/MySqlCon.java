import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;
class MysqlCon{

    Dotenv dotenv = null;

    public MysqlCon(){
        dotenv = Dotenv.configure().load();
    }
    public boolean verifyLogin(String email, String password){
        try{
            Connection con=DriverManager.getConnection(
                    dotenv.get("DATABASE_URL"), dotenv.get("DATABASE_LOGIN"),dotenv.get("DATABASE_PASSWORD"));
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from utilizator where email = '" + email + "' and parola = '" + password + "'");
            if(rs.next()){
                return true;
            }
            con.close();
        }catch(Exception e){ System.out.println(e);}
        return false;
    }

}