package classesmodelos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.projetofinal.Login;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class BCDlocal extends SQLiteOpenHelper {

    public BCDlocal(@Nullable Context context,int version) {
        super(context, "BD_aplicativo",null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Para executar o comando SQL
        String criaTabela = "create table usuarios ("
                + "usuario varchar(60) not null ," +
                "senha varchar (64) not null );";//SHA256

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //metodo para cadastrar (insert) na tabela "acesso"
    public String cadastrarUsuario(Usuario u) {
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

            banco.insertOrThrow("usuarios", null, valores);
        }
        catch (SQLiteConstraintException ex){
                return "Erro ao cadastrar usuario";
        }

        return "Sucesso ao cadastrar";
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
    @SuppressLint("Range")
    public String Logar(Usuario u ) {
        try {
            //Abrir a conexão com o banco de dados local
            SQLiteDatabase banco = getReadableDatabase();
            String buscarUsuario = "SELECT * from usuarios WHERE usuario = " + "'" + u.getNome() + "'";
            Cursor c = banco.rawQuery(buscarUsuario, null);

            //enquanto tiver dados para percorrer
            while (c.moveToNext()) {
                //verifica o nome de usuario
                if (u.getNome().equals(c.getString(c.getColumnIndex("usuario")))) {
                    //verifica a senha
                    if (u.getSenha().equals(c.getString(c.getColumnIndex("senha")))) {
                        return "Login efeutado com sucesso";
                    }
                }
            }
            banco.close();
            c.close();
        } catch (Exception erro) {
            return "Login Falhou";
        }
        return "";

    }
}
