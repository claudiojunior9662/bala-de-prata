/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastros.notas;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entidades.Faturamento;
import entidades.OrdemProducao;
import entidades.ProdOrcamento;
import exception.EnvioExcecao;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.dao.OrcamentoDAO;
import model.dao.OrdemProducaoDAO;
import ui.cadastros.clientes.ClienteBEAN;
import ui.cadastros.clientes.ClienteDAO;
import ui.cadastros.contatos.ContatoBEAN;
import ui.cadastros.enderecos.EnderecoBEAN;
import ui.cadastros.produtos.ProdutoDAO;
import ui.cadastros.servicos.ServicoDAO;
import ui.controle.Controle;
import ui.login.TelaAutenticacao;

/**
 *
 * @author spd3
 */
public class NotaBEAN {

    private int cod;
    private int serie;
    private int codOp;
    private int codOrcamento;
    private String codEmissor;
    private int quantidadeEntregue;
    private float valor;
    private String data;
    private String nomeCliente;
    private int codCliente;
    private int tipoPessoa;
    private int tipo;
    private int formaPagamento;
    private int codEndereco;
    private int codContato;
    private String observacoes;
    private byte fatFrete;
    private byte fatServicos;
    private int codProduto;

    public NotaBEAN(int cod,
            int serie,
            int codOp,
            int codOrcamento,
            String codEmissor,
            int quantidadeEntregue,
            float valor,
            String data,
            int codCliente,
            int tipoPessoa,
            int tipo,
            int codEndereco,
            int codContato,
            String observacoes,
            byte fatFrete,
            byte fatServicos,
            String nomeCliente,
            int codProduto) {
        this.cod = cod;
        this.serie = serie;
        this.codOp = codOp;
        this.codOrcamento = codOrcamento;
        this.codEmissor = codEmissor;
        this.quantidadeEntregue = quantidadeEntregue;
        this.valor = valor;
        this.data = data;
        this.codCliente = codCliente;
        this.tipoPessoa = tipoPessoa;
        this.tipo = tipo;
        this.codEndereco = codEndereco;
        this.codContato = codContato;
        this.observacoes = observacoes;
        this.fatFrete = fatFrete;
        this.fatServicos = fatServicos;
        this.nomeCliente = nomeCliente;
        this.codProduto = codProduto;
    }

    public NotaBEAN() {
    }

    public byte getFatFrete() {
        return fatFrete;
    }

    public void setFatFrete(byte fatFrete) {
        this.fatFrete = fatFrete;
    }

    public byte getFatServicos() {
        return fatServicos;
    }

    public void setFatServicos(byte fatServicos) {
        this.fatServicos = fatServicos;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public int getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(int codEndereco) {
        this.codEndereco = codEndereco;
    }

    public int getCodContato() {
        return codContato;
    }

    public void setCodContato(int codContato) {
        this.codContato = codContato;
    }

    public int getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(int formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(int tipo_pessoa) {
        this.tipoPessoa = tipo_pessoa;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodigoCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getCodOp() {
        return codOp;
    }

    public void setCodOp(int codOp) {
        this.codOp = codOp;
    }

    public int getCodOrc() {
        return codOrcamento;
    }

    public void setCodOrc(int codOrcamento) {
        this.codOrcamento = codOrcamento;
    }

    public String getCodEmissor() {
        return codEmissor;
    }

    public void setCodEmissor(String codEmissor) {
        this.codEmissor = codEmissor;
    }

    public int getQuantidadeEntregue() {
        return quantidadeEntregue;
    }

    public void setQuantidadeEntregue(int quantidadeEntregue) {
        this.quantidadeEntregue = quantidadeEntregue;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    /**
     * Gera o arquivo de nota de crédito
     *
     * @param numeroNota
     * @param formaPagamento
     */
    public void geraNotaCredito(int numeroNota,
            String formaPagamento) {
        new Thread() {
            @Override
            public void run() {
                try {

                    /**
                     * Instancia o documento e define proporções
                     */
                    com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4, 30, 20, 20, 30);
                    PdfWriter.getInstance(document, new FileOutputStream(System.getProperty("java.io.tmpdir") + "/notaCredito" + numeroNota + ".pdf"));
                    document.setMargins(20, 20, 20, 20);
                    document.setPageSize(PageSize.A4);
                    document.open();

                    /**
                     * Pesquisa pela nota
                     */
                    NotaBEAN nota = NotaDAO.selNotaVenda(numeroNota);

                    /**
                     * Pesquisa pelo cliente
                     */
                    ClienteBEAN cliente = ClienteDAO.selecionaInformacoes((byte) nota.getTipoPessoa(),
                            nota.getCodCliente());

                    /**
                     * Pesquisa pelo endereço
                     */
                    EnderecoBEAN endereco = ClienteDAO.selInfoEndereco(nota.getCodEndereco());

                    /**
                     * Pesquisa pelo contato
                     */
                    ContatoBEAN contato = ClienteDAO.selInfoContato(nota.getCodContato());

                    /**
                     * Define o formato de número de moeda
                     */
                    DecimalFormat df = new DecimalFormat("###,##0.00");

                    /**
                     * Define as tabelas que serão utilizadas
                     */
                    PdfPTable tblQuote = new PdfPTable(new float[]{3f, 8f, 3f});
                    tblQuote.setWidthPercentage(100);
                    PdfPTable tblDestinatario = new PdfPTable(new float[]{8f, 3f});
                    tblDestinatario.setWidthPercentage(100);
                    PdfPTable tblRementente = new PdfPTable(new float[]{5f, 5f, 5f, 5f});
                    tblRementente.setWidthPercentage(100);
                    PdfPTable tblValores = new PdfPTable(new float[]{5f, 5f});
                    tblValores.setWidthPercentage(100);
                    PdfPTable tblObservacoes = new PdfPTable(new float[]{5f});
                    tblObservacoes.setWidthPercentage(100);

                    /**
                     * Define as células e parágrafos que serão utilizados
                     */
                    PdfPCell cell1 = null;
                    PdfPCell cell2 = null;
                    PdfPCell cell3 = null;
                    PdfPCell cell4 = null;
                    Paragraph p = null;
                    StringBuilder sb = null;

                    /**
                     * Informações do 'quote'
                     */
                    if (nota.getTipoPessoa() == 1) {
                        cell1 = new PdfPCell(new Phrase("RECEBEMOS DE " + cliente.getNome()
                                + " AS QUANTIAS CONSTANTES DA NOTA FISCAL INDICADA AO LADO."
                                + "\nEMISSÃO: " + nota.getData() + " - DESTINATÁRIO: " + "GRÁFICA DO EXÉRCITO"
                                + " - VALOR TOTAL: R$ " + df.format(nota.getValor()), FontFactory.getFont("arial.ttf", 8)));
                    } else if (nota.getTipoPessoa() == 2) {
                        if (cliente.getNomeFantasia() == null) {
                            cell1 = new PdfPCell(new Phrase("RECEBEMOS DE " + cliente.getNome()
                                    + " AS QUANTIAS CONSTANTES DA NOTA FISCAL INDICADA AO LADO."
                                    + "\nEMISSÃO: " + nota.getData() + " - DESTINATÁRIO: " + "GRÁFICA DO EXÉRCITO"
                                    + " - VALOR TOTAL: R$ " + df.format(nota.getValor()), FontFactory.getFont("arial.ttf", 8)));
                        } else {
                            cell1 = new PdfPCell(new Phrase("RECEBEMOS DE " + cliente.getNome() + " (" + cliente.getNomeFantasia() + ") "
                                    + "AS QUANTIAS CONSTANTES DA NOTA FISCAL INDICADA AO LADO."
                                    + "\nEMISSÃO: " + nota.getData() + " - DESTINATÁRIO: " + "GRÁFICA DO EXÉRCITO"
                                    + " - VALOR TOTAL: R$ " + df.format(nota.getValor()), FontFactory.getFont("arial.ttf", 8)));
                        }
                    }
                    cell1.setColspan(2);
                    cell1.setHorizontalAlignment(1);
                    cell2 = new PdfPCell(new Phrase((nota.getSerie() == 1 ? "NV\nNº " : "NC\nNº ")
                            + numeroNota + "\nSÉRIE " + nota.getSerie(),
                            FontFactory.getFont("arial.ttf", 12, Font.BOLD)));
                    cell2.setHorizontalAlignment(1);
                    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell2.setRowspan(2);
                    tblQuote.addCell(cell1);
                    tblQuote.addCell(cell2);

                    cell1 = new PdfPCell(new Phrase("DATA DE RECEBIMENTO:\n\n\n", FontFactory.getFont("arial.ttf", 8)));
                    cell2 = new PdfPCell(new Phrase("IDENTIFICAÇÃO E ASSINATURA DO RECEBEDOR:\n\n\n", FontFactory.getFont("arial.ttf", 8)));
                    tblQuote.addCell(cell1);
                    tblQuote.addCell(cell2);

                    /**
                     * Adiciona as informações no documento
                     */
                    document.add(tblQuote);

                    document.add(new Paragraph("------------------------------------------------------------------------------------------------------------------------------------------\n\n"));

                    /**
                     * Informações do destinatario
                     */
                    tblDestinatario.addCell(createImageCell());
                    sb = new StringBuilder().append("GE\n")
                            .append(nota.getSerie() == 1 ? "NOTA DE VENDA\n " : "NOTA DE CRÉDITO\n")
                            .append("Nº " + nota.getCod() + "\n")
                            .append("SÉRIE 2");
                    cell2 = new PdfPCell(new Phrase(sb.toString(),
                            FontFactory.getFont("arial.ttf", 12, Font.BOLD)));
                    cell2.setHorizontalAlignment(1);
                    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tblDestinatario.addCell(cell2);
                    cell1 = new PdfPCell(new Phrase("NATUREZA DA OPERAÇÃO:\n\nPRODUTO PARA CONSUMIDOR",
                            FontFactory.getFont("arial.ttf", 9)));
                    cell2 = new PdfPCell(new Phrase("CNPJ:\n\n09.574.722/0001-24",
                            FontFactory.getFont("arial.ttf", 9)));
                    tblDestinatario.addCell(cell1);
                    tblDestinatario.addCell(cell2);

                    /**
                     * Adiciona as informações no documento
                     */
                    document.add(tblDestinatario);

                    document.add(new Paragraph("\n"));

                    /**
                     * Identificação do remetente
                     */
                    cell1 = new PdfPCell(new Phrase("REMETENTE",
                            FontFactory.getFont("arial.ttf", 12, com.itextpdf.text.Font.BOLD)));
                    cell1.setBackgroundColor(Controle.fundoDestaque);
                    cell1.setColspan(4);
                    cell1.setBorder(0);
                    cell1.setHorizontalAlignment(0);
                    tblRementente.addCell(cell1);

                    if (nota.getTipoPessoa() == 1) {
                        cell1 = new PdfPCell(new Phrase("NOME/RAZÃO SOCIAL - CÓDIGO\n\n" + cliente.getNome() + " - " + nota.getCodCliente(), FontFactory.getFont("arial.ttf", 9)));
                        cell2 = new PdfPCell(new Phrase("CNPJ/CPF\n\n" + cliente.getCpf(), FontFactory.getFont("arial.ttf", 9)));
                    } else if (nota.getTipoPessoa() == 2) {
                        if (cliente.getNomeFantasia() == null) {
                            cell1 = new PdfPCell(new Phrase("NOME/RAZÃO SOCIAL - CÓDIGO\n\n" + cliente.getNome() + " - " + nota.getCodCliente(), FontFactory.getFont("arial.ttf", 9)));
                        } else {
                            cell1 = new PdfPCell(new Phrase("NOME/RAZÃO SOCIAL - CÓDIGO\n\n" + cliente.getNome() + " (" + cliente.getNomeFantasia() + ") - " + nota.getCodCliente(), FontFactory.getFont("arial.ttf", 9)));
                        }
                        cell2 = new PdfPCell(new Phrase("CNPJ/CPF\n\n" + cliente.getCnpj(), FontFactory.getFont("arial.ttf", 9)));
                    }
                    cell1.setColspan(2);
                    cell3 = new PdfPCell(new Phrase("DATA DA EMISSÃO\n\n" + nota.getData(), FontFactory.getFont("arial.ttf", 9)));
                    tblRementente.addCell(cell1);
                    tblRementente.addCell(cell2);
                    tblRementente.addCell(cell3);

                    cell1 = new PdfPCell(new Phrase("LOGADOURO\n\n" + endereco.getLogadouro(), FontFactory.getFont("arial.ttf", 9)));
                    tblRementente.addCell(cell1);
                    cell2 = new PdfPCell(new Phrase("BAIRRO\n\n" + endereco.getBairro(), FontFactory.getFont("arial.ttf", 9)));
                    cell3 = new PdfPCell(new Phrase("CIDADE\n\n" + endereco.getCidade(), FontFactory.getFont("arial.ttf", 9)));
                    cell4 = new PdfPCell(new Phrase("CEP\n\n" + EnderecoBEAN.retornaCepFormatado(
                            endereco.getCep()), FontFactory.getFont("arial.ttf", 9)));
                    cell1 = new PdfPCell(new Phrase("UF\n\n" + endereco.getUf(), FontFactory.getFont("arial.ttf", 9)));

                    tblRementente.addCell(cell2);
                    tblRementente.addCell(cell3);
                    tblRementente.addCell(cell4);
                    tblRementente.addCell(cell1);

                    cell2 = new PdfPCell(new Phrase("FONE/FAX\n\n" + contato.getTelefone(), FontFactory.getFont("arial.ttf", 9)));
                    cell3 = new PdfPCell(new Phrase("CONTATO\n\n" + contato.getNomeContato(), FontFactory.getFont("arial.ttf", 9)));
                    cell3.setColspan(2);
                    tblRementente.addCell(cell2);
                    tblRementente.addCell(cell3);

                    /**
                     * Adiciona as informações no documento
                     */
                    document.add(tblRementente);

                    document.add(new Paragraph("\n"));

                    /**
                     * Adiciona as informações de valores
                     */
                    cell1 = new PdfPCell(new Phrase("DESCRIÇÃO DE VALORES",
                            FontFactory.getFont("arial.ttf", 12, com.itextpdf.text.Font.BOLD)));
                    cell1.setBackgroundColor(Controle.fundoDestaque);
                    cell1.setColspan(5);
                    cell1.setBorder(0);
                    cell1.setHorizontalAlignment(0);
                    tblValores.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase("FORMA DE PAGAMENTO\n\n" + formaPagamento, FontFactory.getFont("arial.ttf", 9)));
                    cell2 = new PdfPCell(new Phrase("VALOR TOTAL DA NOTA\n\n" + "R$ " + df.format(nota.getValor()), FontFactory.getFont("arial.ttf", 9, BaseColor.WHITE)));
                    cell2.setBackgroundColor(BaseColor.GRAY);
                    tblValores.addCell(cell1);
                    tblValores.addCell(cell2);

                    /**
                     * Adiciona as informações no documento
                     */
                    document.add(tblValores);

                    document.add(new Paragraph("\n"));

                    cell1 = new PdfPCell(new Phrase("OBSERVAÇÕES",
                            FontFactory.getFont("arial.ttf", 12, com.itextpdf.text.Font.BOLD)));
                    cell1.setBackgroundColor(Controle.fundoDestaque);
                    cell1.setColspan(5);
                    cell1.setBorder(0);
                    cell1.setHorizontalAlignment(0);
                    tblObservacoes.addCell(cell1);

                    if (nota.getObservacoes() == null || nota.getObservacoes().isEmpty()) {
                        cell1 = new PdfPCell(new Phrase("SEM OBSERVAÇÕES",
                                FontFactory.getFont("arial.ttf", 8)));
                        cell1.setHorizontalAlignment(1);
                    } else {
                        cell1 = new PdfPCell(new Phrase(nota.getObservacoes(),
                                FontFactory.getFont("arial.ttf", 8)));
                    }
                    tblObservacoes.addCell(cell1);

                    /**
                     * Adiciona as informações no documento
                     */
                    document.add(tblObservacoes);

                    /**
                     * Fecha a instância do documento
                     */
                    document.close();

                    /**
                     * Abre o documento
                     */
                    java.awt.Desktop.getDesktop().open(new File(System.getProperty("java.io.tmpdir")
                            + "/notaCredito" + numeroNota + ".pdf"));

                } catch (DocumentException | SQLException ex) {
                    EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
                    EnvioExcecao.envio();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,
                            "O ARQUIVO ESTÁ SENDO UTILIZADO POR OUTRO PROCESSO.\nVERIFIQUE E TENTE NOVAMENTE",
                            "ARQUIVO ABERTO",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }.start();
    }

    /**
     * Gera recibo do produto caso a entrega seja parcial
     *
     * @param fat Faturamento
     */
    public static void geraRecibo(Faturamento fat) {
        new Thread("Gera recibo faturamento") {
            @Override
            public void run() {
                try {

                    /**
                     * Define as dimensões do documento
                     */
                    com.itextpdf.text.Document document
                            = new com.itextpdf.text.Document(PageSize.A4, 30, 20, 20, 30);

                    /**
                     * Instancia o arquivo
                     */
                    PdfWriter writer = PdfWriter.getInstance(document,
                            new FileOutputStream(System.getProperty("java.io.tmpdir")
                                    + "/reciboEntrega" + fat.getCod() + ".pdf"));
                    document.setMargins(20, 20, 20, 20);
                    document.setPageSize(PageSize.A4);
                    document.open();

                    /**
                     * Procura a ordem de produção
                     */
                    OrdemProducao op = OrdemProducaoDAO.carregaPdfOp(fat.getCodOp());

                    /**
                     * Procura pelo endereço
                     */
                    EnderecoBEAN endereco = ClienteDAO.selInfoEndereco(op.getCodEndereco());

                    /**
                     * Procura as informações do cliente
                     */
                    ClienteBEAN cliente = ClienteDAO.selInfoOp(op.getTipoPessoa(), op.getCodCliente());

                    /**
                     * Procura as informações do contato
                     */
                    ContatoBEAN contato = ClienteDAO.selInfoContato(op.getCodContato());

                    /**
                     * Procura as informações do produto
                     */
                    ProdOrcamento prodOrc = null;
                    if (op.getCodProduto() != 0) {
                        prodOrc = OrcamentoDAO.retornaProdutoOrcamento(fat.getCodOrc(),
                                op.getCodProduto());
                    }

                    /**
                     * Define as tabelas que serão utilizadas
                     */
                    PdfPTable tblTitulo = new PdfPTable(new float[]{5f, 5f});
                    tblTitulo.setWidthPercentage(100);
                    PdfPTable tblDest = new PdfPTable(new float[]{5f, 5f, 5f, 5f});
                    tblDest.setWidthPercentage(100);
                    PdfPTable tblProduto = new PdfPTable(new float[]{5f, 5f});
                    tblProduto.setWidthPercentage(100);
                    PdfPTable tblValores = new PdfPTable(new float[]{5f, 5f, 5f, 5f, 5f});
                    tblValores.setWidthPercentage(100);
                    PdfPTable tblObservacoes = new PdfPTable(new float[]{5f});
                    tblObservacoes.setWidthPercentage(100);

                    /**
                     * Define as células e parágrafos que serão utilizados
                     */
                    PdfPCell cell1 = null;
                    PdfPCell cell2 = null;
                    PdfPCell cell3 = null;
                    PdfPCell cell4 = null;
                    PdfPCell cell5 = null;
                    PdfPCell cell6 = null;
                    Paragraph p = null;

                    /**
                     * Adiciona o cabeçalho
                     */
                    Image imagem = Image.getInstance(getClass()
                            .getResource("/ui/orcamentos/operacoes/cabecalhoPropostaPng.png"));
                    imagem.setAlignment(1);
                    imagem.scaleToFit(500, 1000);
                    document.add(imagem);

                    document.add(new Paragraph("\n\n"));

                    /**
                     * Título do documento
                     */
                    cell1 = new PdfPCell(new Phrase("RECIBO DE ENTREGA DE MATERIAL Nº",
                            FontFactory.getFont("arial.ttf", 12, Font.BOLD)));
                    cell1.setBackgroundColor(Controle.fundoDestaque);
                    cell1.setBorder(0);
                    cell1.setHorizontalAlignment(0);
                    tblTitulo.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase("REFERENTE A ORDEM DE PRODUÇÃO Nº",
                            FontFactory.getFont("arial.ttf", 12, Font.BOLD)));
                    cell1.setBackgroundColor(Controle.fundoDestaque);
                    cell1.setBorder(0);
                    cell1.setHorizontalAlignment(0);
                    tblTitulo.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase(String.valueOf(fat.getCod()),
                            FontFactory.getFont("arial.ttf", 11, Font.BOLD)));
                    cell1.setHorizontalAlignment(1);
                    cell2 = new PdfPCell(new Phrase(String.valueOf(fat.getCodOp()),
                            FontFactory.getFont("arial.ttf", 11, Font.BOLD)));
                    cell2.setHorizontalAlignment(1);
                    tblTitulo.addCell(cell1);
                    tblTitulo.addCell(cell2);

                    /**
                     * Informações do destinatário
                     */
                    cell1 = new PdfPCell(new Phrase("DESTINATÁRIO",
                            FontFactory.getFont("arial.ttf", 12, Font.BOLD)));
                    cell1.setBackgroundColor(Controle.fundoDestaque);
                    cell1.setColspan(4);
                    cell1.setBorder(0);
                    cell1.setHorizontalAlignment(0);
                    tblDest.addCell(cell1);
                    if (op.getTipoPessoa() == 1) {
                        cell1 = new PdfPCell(new Phrase("NOME/RAZÃO SOCIAL - CÓDIGO\n\n"
                                + cliente.getNome() + " - " + op.getCodCliente(),
                                FontFactory.getFont("arial.ttf", 9)));
                        cell2 = new PdfPCell(new Phrase("CNPJ/CPF\n\n" + cliente.getCpf(),
                                FontFactory.getFont("arial.ttf", 9)));
                    } else if (op.getTipoPessoa() == 2) {
                        if (cliente.getNomeFantasia() == null) {
                            cell1 = new PdfPCell(new Phrase("NOME/RAZÃO SOCIAL - CÓDIGO\n\n"
                                    + cliente.getNome() + " - " + op.getCodCliente(),
                                    FontFactory.getFont("arial.ttf", 9)));
                        } else {
                            cell1 = new PdfPCell(new Phrase("NOME/RAZÃO SOCIAL - CÓDIGO\n\n"
                                    + cliente.getNome() + " (" + cliente.getNomeFantasia() + ") - "
                                    + op.getCodCliente(), FontFactory.getFont("arial.ttf", 9)));
                        }
                        cell2 = new PdfPCell(new Phrase("CNPJ/CPF\n\n" + cliente.getCnpj(),
                                FontFactory.getFont("arial.ttf", 9)));
                    }
                    cell1.setColspan(2);
                    cell2.setColspan(2);
                    tblDest.addCell(cell1);
                    tblDest.addCell(cell2);

                    /**
                     * Adiciona as informações no documento
                     */
                    document.add(tblTitulo);

                    document.add(new Paragraph("\n"));

                    /**
                     * Preenche informações do endereço
                     */
                    cell1 = new PdfPCell(new Phrase("LOGADOURO\n\n" + endereco.getLogadouro(),
                            FontFactory.getFont("arial.ttf", 9)));
                    tblDest.addCell(cell1);
                    cell2 = new PdfPCell(new Phrase("BAIRRO\n\n" + endereco.getBairro(),
                            FontFactory.getFont("arial.ttf", 9)));
                    cell3 = new PdfPCell(new Phrase("CIDADE\n\n" + endereco.getCidade(),
                            FontFactory.getFont("arial.ttf", 9)));
                    cell4 = new PdfPCell(new Phrase("CEP\n\n" + EnderecoBEAN.retornaCepFormatado(
                            endereco.getCep()), FontFactory.getFont("arial.ttf", 9)));
                    cell1 = new PdfPCell(new Phrase("UF\n\n" + endereco.getUf(),
                            FontFactory.getFont("arial.ttf", 9)));

                    /**
                     * Preenche informações do contato
                     */
                    tblDest.addCell(cell2);
                    tblDest.addCell(cell3);
                    tblDest.addCell(cell4);
                    tblDest.addCell(cell1);
                    cell2 = new PdfPCell(new Phrase("FONE/FAX\n\n" + contato.getTelefone(),
                            FontFactory.getFont("arial.ttf", 9)));
                    cell3 = new PdfPCell(new Phrase("CONTATO\n\n" + contato.getNomeContato(),
                            FontFactory.getFont("arial.ttf", 9)));
                    cell3.setColspan(2);
                    tblDest.addCell(cell2);
                    tblDest.addCell(cell3);

                    /**
                     * Adiciona as informações no documento
                     */
                    document.add(tblDest);

                    document.add(new Paragraph("\n"));

                    /**
                     * Preenche informações do produto
                     */
                    cell1 = new PdfPCell(new Phrase("PRODUTO",
                            FontFactory.getFont("arial.ttf", 12, Font.BOLD)));
                    cell1.setBackgroundColor(Controle.fundoDestaque);
                    cell1.setColspan(5);
                    cell1.setBorder(0);
                    cell1.setHorizontalAlignment(0);
                    tblProduto.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase("CÓDIGO\n\n" + prodOrc.getCodProduto(),
                            FontFactory.getFont("arial.ttf", 9)));
                    cell2 = new PdfPCell(new Phrase("DESCRIÇÃO\n\n" + prodOrc.getDescricaoProduto(),
                            FontFactory.getFont("arial.ttf", 9)));
                    tblProduto.addCell(cell1);
                    tblProduto.addCell(cell2);

                    /**
                     * Adiciona as informações no documento
                     */
                    document.add(tblProduto);

                    document.add(new Paragraph("\n"));

                    /**
                     * Preenche os valores e quantidades
                     */
                    DecimalFormat df = new DecimalFormat("###,##0.00");

                    cell1 = new PdfPCell(new Phrase("VALORES E QUANTIDADES",
                            FontFactory.getFont("arial.ttf", 12, Font.BOLD)));
                    cell1.setBackgroundColor(Controle.fundoDestaque);
                    cell1.setColspan(5);
                    cell1.setBorder(0);
                    cell1.setHorizontalAlignment(0);
                    tblValores.addCell(cell1);

                    if (prodOrc.getCodProduto() == 2) {
                        cell1 = new PdfPCell(new Phrase("VALOR UNITÁRIO\n\nR$ "
                                + df.format(ProdutoDAO.retornaVlrPe(prodOrc.getCodProduto())),
                                FontFactory.getFont("arial.ttf", 9)));
                    } else {
                        cell1 = new PdfPCell(new Phrase("VALOR UNITÁRIO\n\nR$ " + df.format(
                                prodOrc.getPrecoUnitario()),
                                FontFactory.getFont("arial.ttf", 9)));
                    }
                    cell2 = new PdfPCell(new Phrase("QUANTIDADE\n\n" + fat.getQtdEntregue(),
                            FontFactory.getFont("arial.ttf", 9)));

                    cell3 = new PdfPCell(new Phrase("VALOR FRETE\n\n"
                            + "R$ " + (fat.getFreteFat() == 1
                            ? df.format(OrcamentoDAO.retornaValorFrete(fat.getCodOrc()))
                            : "0,00"),
                            FontFactory.getFont("arial.ttf", 9)));
                    cell4 = new PdfPCell(new Phrase("VALOR SERVIÇOS\n\nR$ " + (fat.getServicosFat() == 1
                            ? df.format(ServicoDAO.retornaVlrSvOrcExistente(fat.getCodOrc()))
                            : "0,00"), FontFactory.getFont("arial.ttf", 9)));
                    cell5 = new PdfPCell(new Phrase("VALOR FATURADO\n\n" + "R$ "
                            + df.format(fat.getVlrFat()),
                            FontFactory.getFont("arial.ttf", 9, BaseColor.WHITE)));
                    cell5.setBackgroundColor(BaseColor.GRAY);
                    tblValores.addCell(cell1);
                    tblValores.addCell(cell2);
                    tblValores.addCell(cell3);
                    tblValores.addCell(cell4);
                    tblValores.addCell(cell5);

                    /**
                     * Adiciona as informações no documento
                     */
                    document.add(tblValores);

                    document.add(new Paragraph("\n"));

                    /**
                     * Adiciona as observações
                     */
                    cell1 = new PdfPCell(new Phrase("OBSERVAÇÕES",
                            FontFactory.getFont("arial.ttf", 12, Font.BOLD)));
                    cell1.setBackgroundColor(Controle.fundoDestaque);
                    cell1.setBorder(0);
                    cell1.setHorizontalAlignment(0);
                    tblObservacoes.addCell(cell1);

                    if (fat.getObservacoes() == null || fat.getObservacoes().isEmpty()) {
                        cell1 = new PdfPCell(new Phrase("SEM OBSERVAÇÕES",
                                FontFactory.getFont("arial.ttf", 9)));
                        cell1.setHorizontalAlignment(1);
                    } else {
                        cell1 = new PdfPCell(new Phrase(fat.getObservacoes(),
                                FontFactory.getFont("arial.ttf", 9)));
                    }
                    tblObservacoes.addCell(cell1);

                    /**
                     * Adiciona as informações no documento
                     */
                    document.add(tblObservacoes);

                    document.add(new Paragraph("\n"));

                    /**
                     * Informações local, data
                     */
                    p = new Paragraph("Quartel em Brasília-DF, "
                            + Controle.dataPadrao.format(new Date()) + " "
                            + Controle.horaPadrao.format(new Date()),
                            FontFactory.getFont("arial.ttf", 9));
                    p.setAlignment(1);
                    document.add(p);

                    document.add(new Paragraph("\n"));

                    /**
                     * Campo de assinatura do emissor
                     */
                    p = new Paragraph("_________________________________________");
                    p.setAlignment(1);
                    document.add(p);
                    p = new Paragraph(OrcamentoDAO.carregaNomeVendedor(TelaAutenticacao.getUsrLogado().getCodigo()),
                            FontFactory.getFont("arial.ttf", 9));
                    p.setAlignment(1);
                    document.add(p);

                    document.add(new Paragraph("\n"));

                    /**
                     * Campo de assinatura do cliente
                     */
                    p = new Paragraph("_________________________________________");
                    p.setAlignment(1);
                    document.add(p);
                    p = new Paragraph(cliente.getTipoCliente() == 1
                            ? cliente.getNome()
                            : cliente.getNome() + " - " + cliente.getNomeFantasia(),
                            FontFactory.getFont("arial.ttf", 9));
                    p.setAlignment(1);
                    document.add(p);

                    /**
                     * Fecha a instância do documento
                     */
                    document.close();

                    /**
                     * Abre o documento
                     */
                    java.awt.Desktop.getDesktop().open(new File(System.getProperty("java.io.tmpdir")
                            + "/reciboEntrega" + fat.getCod() + ".pdf"));

                } catch (SQLException | DocumentException | IOException ex) {
                    EnvioExcecao envioExcecao = new EnvioExcecao(Controle.getDefaultGj(), ex);
                    EnvioExcecao.envio();
                }
            }
        }.start();
    }

    public PdfPCell createImageCell() throws DocumentException, IOException {

        Image imagem = Image.getInstance(getClass().getResource("/ui/cadastros/notas/cabecalhoNotaFiscal.png"));

        PdfPCell cell = new PdfPCell(imagem, true);
        cell.setPadding(5);

        return cell;

    }
}
