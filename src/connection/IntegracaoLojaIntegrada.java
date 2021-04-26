/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
    }

    public static void realizaRequisicaoGET(String requisicao) {
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
            public PasswordAuthentication getPasswordAuthentication() {
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
            for (int i = 0; i < obj.getJSONArray("objects").length(); i++) {
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

    /**
     * Realiza requisição POST para o e-commerce
     * @param tipo 1 - Cadastro categoria, 2 - Cadastro marca, 3 - Cadastro grade, 4 - Cadastro variação,
     * 5 - Cadastro produto pai, 6 - Cadastro produto filho, 7 - Cadastro imagem produto, 8 - Cadastro cliente
     * @throws IOException
     * @throws InterruptedException 
     */
    public static void realizaRequisicaoPOST(byte tipo, Object requisicao) throws IOException, InterruptedException {
        
        switch(tipo){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            default:
                break;
        }
        
        HashMap values = new HashMap<String, String>(){{
            put("id_externo", null);
            put("nome", "Teste Integracao API");
            put("descricao", "Canecas.");
            put("categoria_pai", null);
        }};
        
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(values);
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.awsli.com.br/v1/categoria/?" 
                        + FORMATO_SAIDA
                        + "&" 
                        + "chave_api=" 
                        + CHAVE_API
                        + "&"
                        + "chave_aplicacao=" 
                        + CHAVE_APLICACAO))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response = client.send(request, 
                HttpResponse.BodyHandlers.ofString());
    }
        
}
