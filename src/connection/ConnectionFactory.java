package connection;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import exception.EnvioExcecao;
import ui.cadastros.enderecos.EnderecoBEAN;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import org.json.JSONException;
import org.json.JSONObject;
import ui.controle.Controle;
import ui.principal.Estoque;
import ui.principal.OrcamentoFrame;

/**
 *
 * @author Seçao SPD3
 */
public class ConnectionFactory {

    /**
     * Variáveis para conexão com o banco de dados
     */
    private static String DRIVER_SQL;
    private static String URL;
    private static String USER;
    private static String PASS;

    //VARIÁVEIS PARA CONEXÃO COM O SERVIDOR FIREBIRD
    final static private String DRIVER_FIREBIRD = "org.firebirdsql.jdbc.FBDriver";
    private static String CHARSET = "?encoding=ISO8859_1";
    private static String SERVIDOR = null;
    private static String PORTA = null;
    private static String USUARIO = null;
    private static String SENHA = null;
    private static String ENDERECO_BD = null;

    public static Session session;
    public static boolean ready;
    public static Channel channel;
    public static InputStream commandOutput;

    private static void defineVar() {
        switch (Controle.getTipoVersao()) {
            case 1:
                DRIVER_SQL = "com.mysql.jdbc.Driver";
                URL = "jdbc:mysql://10.67.32.252:3306/bala_dev";
                USER = "remoto";
                PASS = "ALLAH@366";
                break;
            case 2:
                DRIVER_SQL = "com.mysql.jdbc.Driver";
                URL = "jdbc:mysql://10.67.32.252:3306/bala_dev_TESTE";
                USER = "remoto";
                PASS = "ALLAH@366";
                break;
            case 3:
                DRIVER_SQL = "com.mysql.jdbc.Driver";
                URL = "jdbc:mysql://localhost:3306/bala_dev";
                USER = "root";
                PASS = "";
                break;
            case 4:
                DRIVER_SQL = "com.mysql.jdbc.Driver";
                URL = "jdbc:mysql://10.67.32.252:3306/bala_dev_TESTE";
                USER = "remoto";
                PASS = "ALLAH@366";
                break;
        }
    }

    public static Connection getConnection() {
        try {
            defineVar();
            Class.forName(DRIVER_SQL);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException ex) {
            return null;
        }
    }

    public static void closeConnection(Connection con) {

        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt){

        try {

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
        closeConnection(con);
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){

        closeConnection(con, stmt);

        try {

            if (rs != null) {
                rs.close();
            }

        } catch (SQLException ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
    }

    public static Boolean verificaCnpj(String cnpj){
        Boolean retorno = false;

        String queryURL = "https://www.receitaws.com.br/v1/cnpj/" + cnpj;

        JSONObject object;

        SocketAddress addr = new InetSocketAddress("10.133.253.23", 3128);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);

        HttpURLConnection con = null;

        try {
            URL url = new URL(queryURL);
            con = (HttpURLConnection) url.openConnection(proxy);

            con.setRequestMethod("GET");

            StringBuilder result = new StringBuilder();

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }
            in.close();

            JSONObject obj = new JSONObject(result.toString());

            if (obj.getString("status").equals("OK")) {
                retorno = true;
            } else {
                retorno = false;
            }
        } catch (MalformedURLException ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        } catch (IOException ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        } finally {
            con.disconnect();
        }

        return retorno;
    }

    public static EnderecoBEAN retornaInformacoesCEP(String cep){
        EnderecoBEAN retorno = null;

        String queryURL = "http://viacep.com.br/ws/" + cep + "/json/";

        JSONObject object;

        SocketAddress addr = new InetSocketAddress("10.133.253.23", 3128);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);

        HttpURLConnection con = null;

        try {
            URL url = new URL(queryURL);
            con = (HttpURLConnection) url.openConnection(proxy);

            con.setRequestMethod("GET");

            StringBuilder result = new StringBuilder();

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }
            in.close();

            JSONObject obj = new JSONObject(result.toString());

            try {
                retorno = new EnderecoBEAN(obj.getString("logradouro"),
                        obj.getString("complemento"),
                        obj.getString("bairro"),
                        obj.getString("localidade"),
                        obj.getString("uf"));
            } catch (JSONException ex) {
                if (obj.getBoolean("erro")) {
                    JOptionPane.showMessageDialog(null, "O 'CEP' DIGITADO NÃO EXISTE! " + ex);
                }
                return null;
            }
        } catch (MalformedURLException ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        } catch (IOException ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        } finally {
            con.disconnect();
        }

        return retorno;
    }

    /*
    @param operacao
    1 - upload
    2 - download
     */
    public static boolean connectSSH(byte operacao, byte className){
        try {
            String resposta = null;
            int retorno = 0;
            if (operacao == 1) {
                JLabel label = new JLabel("DIGITE A SENHA");
                JPasswordField jpf = new JPasswordField();
                retorno = JOptionPane.showConfirmDialog(null, new Object[]{label, jpf}, "SENHA MESTRA:", JOptionPane.OK_CANCEL_OPTION);
                resposta = String.valueOf(jpf.getPassword());
            } else if (operacao == 2) {
                resposta = Controle.SENHA_ESTOQUE;
                retorno = 0;
            }
            Controle controle = Controle.retornaDadosConexao(resposta);
            if (className == 1) {
                Estoque.loadingVisible("VERIFICANDO SENHA...");
            } else {
                OrcamentoFrame.loadingVisible("VERIFICANDO SENHA...");
            }

            if (retorno != 0 || controle == null) {
                JOptionPane.showMessageDialog(null, "SENHA INCORRETA!");
                if (className == 1) {
                    Estoque.loadingHide();
                } else {
                    OrcamentoFrame.loadingHide();
                }
                return false;
            } else {
                JSch ssh = new JSch();
                session = ssh.getSession(controle.getUsuario(), controle.getHost(), controle.getPorta());
                session.setPassword(controle.getSenha());
                session.setConfig("StrictHostKeyChecking", "no");
                session.connect(30000);
                ready = true;
                return true;
            }
        } catch (SQLException | JSchException ex) {
            if (className == 1) {
                Estoque.loadingHide();
            } else {
                OrcamentoFrame.loadingHide();
            }
            ready = false;
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
        return false;
    }

    public static void closeSSH() {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
        ready = false;
    }

    public static boolean uploadSSH(String origem, String destino, String dirDestino){
        try {
            if (connectSSH((byte) 1, (byte) 1)) {
                Estoque.loadingVisible("CARREGANDO ARQUIVO...");
                if (existFile()) {
                    deleteFile();
                }
                ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
                sftp.connect();
                sftp.cd(dirDestino);
                sftp.put(origem, destino);
                sftp.disconnect();
                closeSSH();
                return true;
            }
        } catch (JSchException ex) {
            ready = false;
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }catch(SftpException ex){
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        } catch (Exception ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
        return false;
    }

    /*
    @param className
    1 - estoque
    2 - orcamento
     */
    public static boolean downloadSSH(String dirLocal, byte className){
        try {
            connectSSH((byte) 2, className);
            if (className == 1) {
                Estoque.loadingVisible("EXCLUINDO ARQUIVOS EXISTENTES...");
            } else {
                OrcamentoFrame.loadingVisible("EXCLUINDO ARQUIVOS EXISTENTES...");
            }
            deleteLocalFile(dirLocal);
            if (className == 1) {
                Estoque.loadingVisible("FAZENDO O DOWNLOAD...");
            } else {
                OrcamentoFrame.loadingVisible("FAZENDO O DOWNLOAD...");
            }
            ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            sftp.get(Controle.retornaDirEstoque() + "/" + Controle.ESTOQUE_NAME + ".*", dirLocal);
            sftp.disconnect();
            closeSSH();
            return true;
        } catch (JSchException | SftpException | SQLException ex) {
            if (className == 1) {
                Estoque.loadingHide();
            } else {
                OrcamentoFrame.loadingHide();
            }
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        } catch (Exception ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
        return false;
    }

    public static String write(String comando) {
        try {
            BufferedReader reader = null;
            channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(comando);
            commandOutput = channel.getInputStream();
            reader = new BufferedReader(new InputStreamReader(commandOutput));
            channel.connect(30000);

            StringBuilder stringBuilder = new StringBuilder();
            String lido = reader.readLine();

            while (lido != null) {
                stringBuilder.append(lido);
                stringBuilder.append("\n");
                lido = reader.readLine();
            }

            return stringBuilder.toString();
        } catch (JSchException | IOException ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
        return null;
    }

    public static boolean existFile(){
        try {
            if (ready) {
                String retorno = write("cd " + Controle.retornaDirEstoque() + " && [ -f " + Controle.ESTOQUE_NAME + ".* ] && echo 1 || echo 0");
                if (retorno != null) {
                    retorno = retorno.replace("\n", "");
                    return Long.parseLong(retorno) > 0;
                }
            }
        } catch (Exception ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
        return false;
    }

    public static void deleteFile(){
        try {
            if (ready) {
                write("rm -f " + Controle.retornaDirEstoque() + "/" + Controle.ESTOQUE_NAME + ".*");
            }
        } catch (Exception ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
    }

    public static void deleteLocalFile(String dirLocal){
        try {
            File file = new File(dirLocal);
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; ++i) {
                    if (files[i].getName().contains(Controle.ESTOQUE_NAME)) {
                        files[i].delete();
                        return;
                    }
                }
            }
        } catch (Exception ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
    }

    public static void preencheDadosConexaoSimatex(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT SRV_SIMATEX, "
                    + "PORTA_SIMATEX, "
                    + "USR_SIMATEX, "
                    + "SENHA_SIMATEX, "
                    + "END_BD_SIMATEX "
                    + "FROM tabela_controle");
            rs = stmt.executeQuery();
            if (rs.next()) {
                SERVIDOR = rs.getString("SRV_SIMATEX");
                PORTA = String.valueOf(rs.getInt("PORTA_SIMATEX"));
                USUARIO = rs.getString("USR_SIMATEX");
                SENHA = rs.getString("SENHA_SIMATEX");
                ENDERECO_BD = rs.getString("END_BD_SIMATEX");
            }
        } catch (SQLException ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
    }

    public static Connection conectaSimatex(){
        preencheDadosConexaoSimatex();
        Connection connection = null;
        try {
            Class.forName(DRIVER_FIREBIRD);
            connection = DriverManager.getConnection("jdbc:firebirdsql:" + SERVIDOR + "/" + PORTA + ":" + ENDERECO_BD + CHARSET, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException ex) {
            EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
            EnvioExcecao.envio();
        }
        return connection;
    }
}
