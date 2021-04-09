/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import exception.EnvioExcecao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.routing.DefaultProxyRoutePlanner;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.impl.io.DefaultBHttpClientConnection;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import ui.controle.Controle;

/**
 *
 * @author claud
 */
public class IntegracaoLojaIntegrada {
    
    //VARIÁVEIS LOJA INTEGRADA
    private static final String CHAVE_APLICACAO = "f044287d-f069-49b8-887e-fb12df0107ff";
    private static final String CHAVE_API = "307df38d3a7189c3c4aa";
    private static final String LINK_API = "https://api.awsli.com.br";
    private static final String VERSAO_API = "v1";
    private static final String SEPARADOR = "/";
    private static final String FORMATO_SAIDA = "json";
    
    public static void main(String[] args) {
        try {
            realizaRequisicaoPOST("categoria");
        } catch (IOException ex) {
            Logger.getLogger(IntegracaoLojaIntegrada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(IntegracaoLojaIntegrada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void realizaRequisicaoGET(String requisicao){
        String queryURL = LINK_API 
                + SEPARADOR 
                + VERSAO_API 
                + SEPARADOR 
                + requisicao 
                + SEPARADOR 
                + "?format="
                + FORMATO_SAIDA
                + "&chave_api="
                + CHAVE_API
                + "&chave_aplicacao="
                + CHAVE_APLICACAO;
        
        //Define as configurações de proxy        
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.166.128.179", 3128));
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication(){
                return (new PasswordAuthentication("spd", "spd2020".toCharArray()));
            }
        };
        Authenticator.setDefault(auth);
        
        System.out.println(queryURL);
        JSONObject object;
        HttpURLConnection con = null;
        
        try {
            URL url = new URL(queryURL);
            con = (HttpURLConnection) url.openConnection(proxy);
            System.out.println(con.getResponseCode());
            //con = (HttpURLConnection) url.openConnection();
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
            for(int i = 0; i < obj.getJSONArray("objects").length(); i++){
                System.out.println(obj.getJSONArray("objects").getJSONObject(i).getString("nome"));
            }
            
            try {
                
//                retorno = new EnderecoBEAN(obj.getString("logradouro"),
//                        obj.getString("complemento"),
//                        obj.getString("bairro"),
//                        obj.getString("localidade"),
//                        obj.getString("uf"));
            } catch (JSONException ex) {
                
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
    }
    
    public static void realizaRequisicaoPOST(String requisicao) throws IOException, ParseException{
        String queryURL = LINK_API 
                + SEPARADOR 
                + VERSAO_API 
                + SEPARADOR 
                + requisicao 
                + SEPARADOR 
                + "?format="
                + FORMATO_SAIDA
                + "&chave_api="
                + CHAVE_API
                + "&chave_aplicacao="
                + CHAVE_APLICACAO;
        
        //Define as configurações de proxy        
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.166.128.179", 3128));
//        Authenticator auth = new Authenticator() {
//            @Override
//            public PasswordAuthentication getPasswordAuthentication(){
//                return (new PasswordAuthentication("spd", "spd2020".toCharArray()));
//            }
//        };
//        Authenticator.setDefault(auth);
        
        HttpHost proxy = new HttpHost("10.166.128.179");
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://api.awsli.com.br/v1/produto");
        List<NameValuePair> parametrosUrl = new ArrayList<>();
        
        parametrosUrl.add(new BasicNameValuePair("id_externo", null));
        parametrosUrl.add(new BasicNameValuePair("sku", "prod-simples"));
        parametrosUrl.add(new BasicNameValuePair("mpn", "prod-simples"));
        parametrosUrl.add(new BasicNameValuePair("ncm", "prod-simples"));
        parametrosUrl.add(new BasicNameValuePair("nome", "prod-teste"));
        parametrosUrl.add(new BasicNameValuePair("descricao_completa", "<strong>Produto teste</strong>"));
        parametrosUrl.add(new BasicNameValuePair("ativo", "false"));
        parametrosUrl.add(new BasicNameValuePair("destaque", "false"));
        parametrosUrl.add(new BasicNameValuePair("peso", "0.45"));
        parametrosUrl.add(new BasicNameValuePair("altura", "2"));
        parametrosUrl.add(new BasicNameValuePair("largura", "12"));
        parametrosUrl.add(new BasicNameValuePair("profundidade", "6"));
        parametrosUrl.add(new BasicNameValuePair("tipo", "normal"));
        parametrosUrl.add(new BasicNameValuePair("usado", "false"));
        parametrosUrl.add(new BasicNameValuePair("marca", null));
        parametrosUrl.add(new BasicNameValuePair("removido", "false"));
        
        post.setEntity(new UrlEncodedFormEntity(parametrosUrl));
        
        HttpResponse httpResponse = httpClient.execute(post);
        
        System.out.println(httpResponse.getCode());
    }
        
}
