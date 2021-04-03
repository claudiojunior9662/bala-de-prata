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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
        realizaRequisicao("categoria");
    }
    
    public static void realizaRequisicao(String requisicao){
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
//        SocketAddress addr = new InetSocketAddress("10.166.128.179", 3128);
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
        System.out.println(queryURL);
        JSONObject object;
        HttpURLConnection con = null;
        
        try {
            URL url = new URL(queryURL);
            //con = (HttpURLConnection) url.openConnection(proxy);
            con = (HttpURLConnection) url.openConnection();
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
            System.out.println(obj.getJSONArray("objects").getJSONObject(0).getString("nome"));
            
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
        
}
