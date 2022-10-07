package classesmodelos;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class BCDlocal extends SQLiteOpenHelper {

    private final String criaTabela = "create table acesso ("
            + "usuario varchar(60) not null ," +
            "senha varchar (64) not null );";//SHA256

    public BCDlocal(@Nullable Context context,int version) {
        super(context, "BD_aplicativo",null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Para executar o comando SQL
        sqLiteDatabase.execSQL(criaTabela);

    }

    //metodo para cadastrar (insert) na tabela "acesso"
    public boolean cadastrarUsuario(Usuario u) {
        try {
            //para acessar o bando de dados local é so criar
            // um objeto da SQLitedatabase

            //Abrir a conexão com o banco de dados local
            SQLiteDatabase banco = getWritableDatabase();

            //criar um objeto da classe contentvalues para indicar os valores
            //que vão prencher cada coluna da tabela

            ContentValues valores = new ContentValues();
            valores.put("usuario", u.getNome()); //coluna_da_tabela , valor a ser inserido
            valores.put("senha", criptografar(u.getSenha()));
            valores.put("email", u.getEmail());

            //chamar o metodo inser() e verificar o retorno
            if(banco.insert("acesso ", null,valores) != -1){
                banco.close();
                return true;
            }
            else{
                banco.close();
                return false;

            }

        }
        catch (Exception erro){
                return false;

        }
    }

    //metodo para criptografar a senha
    private String criptografar (String senha){

        try{
            //objeto da classe messagedigest par indicar o algoritmo usado
            MessageDigest md = MessageDigest.getInstance("SHA-256");

        //converte senha para SHA-256
            byte[] senhaconvertida = md.digest(senha.getBytes(StandardCharsets.UTF_8));

            //converte para string
            StringBuilder stringBuilder= new StringBuilder();

            for(byte b :senhaconvertida){

                stringBuilder.append(String.format("%02X",0xFF & b));

            }

            return stringBuilder.toString();



        }
        catch(NoSuchAlgorithmException e){

        e.printStackTrace();

        }
                return "";

    }


    //metodo para logar
    public Usuario Logar( String u ) {
        Usuario usuario = null;

        try {
            //Abrir a conexão com o banco de dados local
            SQLiteDatabase banco = getWritableDatabase();





            ContentValues valores = new ContentValues();
            valores.put("usuario", u); //coluna_da_tabela , valor a ser inserido

            return usuario;
        }
        catch (Exception erro){
            return usuario;

        }
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
