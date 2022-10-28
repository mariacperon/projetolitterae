package com.cedup.projetolitterae.backend.emails;

import com.cedup.projetolitterae.backend.entities.Locacao;
import com.cedup.projetolitterae.backend.entities.Usuario;
import com.cedup.projetolitterae.backend.services.LocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MandarEmail {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private LocacaoService locacaoService;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public void emailLivroLocado(Locacao locacao){
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mail);
            helper.setTo(locacao.getUsuario().getEmail());
            helper.setSubject("Locação do livro "+ locacao.getLivro().getLivro().getNome() +" foi efetuada com sucesso!");
            helper.setText(mensagemEmailLivroLocado(locacao), true);
            mailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void emailLivroDevolvido(){}

    public void emailPendenciaLocacao(){}

    private String mensagemEmailLivroLocado(Locacao locacao){
        List<Locacao> locacoes = locacaoService.pesquisarPorUsuario(locacao.getUsuario().getId())
                .stream().sorted(Comparator.comparing(Locacao::getDataLocacao)).collect(Collectors.toList());

        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"width:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta content=\"telephone=no\" name=\"format-detection\">\n" +
                "    <title>livro locado</title>\n" +
                "<style type=\"text/css\">\n" +
                "#outlook a {\n" +
                "\tpadding:0;\n" +
                "}\n" +
                ".ExternalClass {\n" +
                "\twidth:100%;\n" +
                "}\n" +
                ".ExternalClass,\n" +
                ".ExternalClass p,\n" +
                ".ExternalClass span,\n" +
                ".ExternalClass font,\n" +
                ".ExternalClass td,\n" +
                ".ExternalClass div {\n" +
                "\tline-height:100%;\n" +
                "}\n" +
                ".es-button {\n" +
                "\tmso-style-priority:100!important;\n" +
                "\ttext-decoration:none!important;\n" +
                "}\n" +
                "a[x-apple-data-detectors] {\n" +
                "\tcolor:inherit!important;\n" +
                "\ttext-decoration:none!important;\n" +
                "\tfont-size:inherit!important;\n" +
                "\tfont-family:inherit!important;\n" +
                "\tfont-weight:inherit!important;\n" +
                "\tline-height:inherit!important;\n" +
                "}\n" +
                ".es-desk-hidden {\n" +
                "\tdisplay:none;\n" +
                "\tfloat:left;\n" +
                "\toverflow:hidden;\n" +
                "\twidth:0;\n" +
                "\tmax-height:0;\n" +
                "\tline-height:0;\n" +
                "\tmso-hide:all;\n" +
                "}\n" +
                ".es-button-border:hover a.es-button, .es-button-border:hover button.es-button {\n" +
                "\tbackground:#f82601!important;\n" +
                "\tborder-color:#f82601!important;\n" +
                "}\n" +
                ".es-button-border:hover {\n" +
                "\tborder-color:#666666 #666666 #666666 #666666!important;\n" +
                "\tbackground:#f82601!important;\n" +
                "}\n" +
                "[data-ogsb] .es-button {\n" +
                "\tborder-width:0!important;\n" +
                "\tpadding:10px 30px 10px 30px!important;\n" +
                "}\n" +
                "[data-ogsb] .es-button.es-button-1 {\n" +
                "\tpadding:10px 30px!important;\n" +
                "}\n" +
                "td .es-button-border:hover a.es-button-2 {\n" +
                "\tbackground:#fb9a12!important;\n" +
                "\tborder-color:#fb9a12!important;\n" +
                "}\n" +
                "td .es-button-border-3:hover {\n" +
                "\tbackground:#fb9a12!important;\n" +
                "}\n" +
                "@media only screen and (max-width:600px) {p, ul li, ol li { margin-bottom:0px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li { margin-bottom:0px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li { margin-bottom:0px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li { margin-bottom:9px!important } p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120% } h1 { font-size:28px!important; text-align:center; margin-bottom:17px } h2 { font-size:24px!important; text-align:center; margin-bottom:15px } h3 { font-size:18px!important; text-align:center; margin-bottom:11px } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:28px!important } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:24px!important } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:18px!important } .es-menu td a { font-size:16px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:16px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:16px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:13px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:block!important } .es-btn-fw { border-width:10px 0px!important; text-align:center!important } .es-adaptive table, .es-btn-fw, .es-btn-fw-brdr, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0!important } .es-m-p0r { padding-right:0!important } .es-m-p0l { padding-left:0!important } .es-m-p0t { padding-top:0!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } a.es-button, button.es-button { font-size:13px!important; display:block!important; border-left-width:0px!important; border-right-width:0px!important } .es-m-p5 { padding:5px!important } .es-m-p5t { padding-top:5px!important } .es-m-p5b { padding-bottom:5px!important } .es-m-p5r { padding-right:5px!important } .es-m-p5l { padding-left:5px!important } .es-m-p10 { padding:10px!important } .es-m-p10t { padding-top:10px!important } .es-m-p10b { padding-bottom:10px!important } .es-m-p10r { padding-right:10px!important } .es-m-p10l { padding-left:10px!important } .es-m-p15 { padding:15px!important } .es-m-p15t { padding-top:15px!important } .es-m-p15b { padding-bottom:15px!important } .es-m-p15r { padding-right:15px!important } .es-m-p15l { padding-left:15px!important } .es-m-p20 { padding:20px!important } .es-m-p20t { padding-top:20px!important } .es-m-p20r { padding-right:20px!important } .es-m-p20l { padding-left:20px!important } .es-m-p25 { padding:25px!important } .es-m-p25t { padding-top:25px!important } .es-m-p25b { padding-bottom:25px!important } .es-m-p25r { padding-right:25px!important } .es-m-p25l { padding-left:25px!important } .es-m-p30 { padding:30px!important } .es-m-p30t { padding-top:30px!important } .es-m-p30b { padding-bottom:30px!important } .es-m-p30r { padding-right:30px!important } .es-m-p30l { padding-left:30px!important } .es-m-p35 { padding:35px!important } .es-m-p35t { padding-top:35px!important } .es-m-p35b { padding-bottom:35px!important } .es-m-p35r { padding-right:35px!important } .es-m-p35l { padding-left:35px!important } .es-m-p40 { padding:40px!important } .es-m-p40t { padding-top:40px!important } .es-m-p40b { padding-bottom:40px!important } .es-m-p40r { padding-right:40px!important } .es-m-p40l { padding-left:40px!important } .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; max-height:inherit!important } }\n" +
                "</style>\n" +
                " </head>\n" +
                " <body style=\"width:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;font-family:arial, 'helvetica neue', helvetica, sans-serif;padding:0;Margin:0\">\n" +
                "  <div charset=\"UTF-8\" class=\"es-wrapper-color\"><!--[if gte mso 9]>\n" +
                "\t\t\t<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "\t\t\t\t<v:fill type=\"tile\"></v:fill>\n" +
                "\t\t\t</v:background>\n" +
                "\t\t<![endif]-->\n" +
                "   <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top\">\n" +
                "     <tr style=\"border-collapse:collapse\">\n" +
                "      <td valign=\"top\" style=\"padding:0;Margin:0\">\n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                "         <tr style=\"border-collapse:collapse\">\n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "           <table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">\n" +
                "             <tr style=\"border-collapse:collapse\">\n" +
                "              <td style=\"Margin:0;padding-top:15px;padding-bottom:15px;padding-left:15px;padding-right:15px;background-color:#fd1d1d\" bgcolor=\"#FD1D1D\" align=\"left\"><!--[if mso]><table style=\"width:570px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:252px\" valign=\"top\"><![endif]-->\n" +
                "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td class=\"es-m-p0r\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:252px\">\n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td class=\"es-m-p0l es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;padding-left:15px;font-size:0px\"><img src=\"https://lsegqi.stripocdn.email/content/guids/CABINET_3301b23369066fb085b22433667bd9d0/images/litterae_logo_1.png\" alt=\"Logo\" width=\"58\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" title=\"Logo\"></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td><td style=\"width:25px\"></td><td style=\"width:293px\" valign=\"top\"><![endif]-->\n" +
                "               <table cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:293px\">\n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td style=\"padding:0;Margin:0\">\n" +
                "                       <table class=\"es-menu\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                         <tr class=\"links\" style=\"border-collapse:collapse\">\n" +
                "                          <td style=\"Margin:0;padding-left:5px;padding-right:5px;padding-top:20px;padding-bottom:0px;border:0\" id=\"esd-menu-id-0\" width=\"50%\" bgcolor=\"transparent\" align=\"center\"><a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;font-family:arial, 'helvetica neue', helvetica, sans-serif;color:#ffffff;font-size:16px\" href=\"https://viewstripo.email/\">Página Inicial</a></td>\n" +
                "                          <td style=\"Margin:0;padding-left:5px;padding-right:5px;padding-top:20px;padding-bottom:0px;border:0\" id=\"esd-menu-id-2\" esdev-border-color=\"#000000\" width=\"50%\" bgcolor=\"transparent\" align=\"center\"><a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;font-family:arial, 'helvetica neue', helvetica, sans-serif;color:#ffffff;font-size:16px\" href=\"https://mail.google.com/mail/u/3/?fs=1&to=litteraeproject@gmail.com&tf=cm\">Entre em contato</a></td>\n" +
                "                         </tr>\n" +
                "                       </table></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "             </tr>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "       </table>\n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "         <tr style=\"border-collapse:collapse\">\n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "           <table class=\"es-content-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">\n" +
                "             <tr style=\"border-collapse:collapse\">\n" +
                "              <td align=\"left\" background=\"https://lsegqi.stripocdn.email/content/guids/CABINET_3301b23369066fb085b22433667bd9d0/images/inakidelolmonijueqw0rkgunsplash_Gx9.jpeg\" style=\"padding:0;Margin:0;background-image:url(inaki-del-olmo-NIJuEQw0RKg-unsplash.jpg);background-repeat:no-repeat;background-position:left top;background-color:#2d2626\" bgcolor=\"#2d2626\">\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:600px\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td align=\"center\" height=\"126\" style=\"padding:0;Margin:0\"></td>\n" +
                "                     </tr>\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0\"><h1 style=\"Margin:0;line-height:60px;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:50px;font-style:normal;font-weight:bold;color:#FFFFFF;margin-bottom:5px\">"+ locacao.getLivro().getBiblioteca().getNome() +"<br></h1></td>\n" +
                "                     </tr>\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td align=\"center\" style=\"Margin:0;padding-top:5px;padding-bottom:5px;padding-left:20px;padding-right:20px;font-size:0\">\n" +
                "                       <table border=\"0\" width=\"15%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                         <tr style=\"border-collapse:collapse\">\n" +
                "                          <td style=\"padding:0;Margin:0;border-bottom:3px solid #ffffff;background:none;height:1px;width:100%;margin:0px\"></td>\n" +
                "                         </tr>\n" +
                "                       </table></td>\n" +
                "                     </tr>\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:10px\"><h2 style=\"Margin:0;line-height:29px;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:24px;font-style:normal;font-weight:bold;color:#833ab4;margin-bottom:10px\">LITTERAE</h2></td>\n" +
                "                     </tr>\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td align=\"center\" height=\"141\" style=\"padding:0;Margin:0\"></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table>" +
                "              </td>\n" +
                "             </tr>\n" +
                "             <tr style=\"border-collapse:collapse\">\n" +
                "              <td align=\"left\" style=\"padding:20px;Margin:0\">\n" +
                "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0\"><h2 style=\"Margin:0;line-height:29px;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:24px;font-style:normal;font-weight:bold;color:#833ab4;margin-bottom:10px\">LIVRO LOCADO COM SUCESSO!</h2></td>\n" +
                "                     </tr>\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td align=\"center\" style=\"Margin:0;padding-top:5px;padding-bottom:5px;padding-left:20px;padding-right:20px;font-size:0\">\n" +
                "                       <table border=\"0\" width=\"15%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                         <tr style=\"border-collapse:collapse\">\n" +
                "                          <td style=\"padding:0;Margin:0;border-bottom:3px solid #fcb045;background:none;height:1px;width:100%;margin:0px\"></td>\n" +
                "                         </tr>\n" +
                "                       </table></td>\n" +
                "                     </tr>\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td class=\"es-m-p15b\" align=\"center\" style=\"padding:0;Margin:0;padding-top:15px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;margin-bottom:11px;color:#333333;font-size:14px\">Sua locação foi confirmada no dia "+ sdf.format(locacao.getDataLocacao())  +". Você deve devolver o livro no dia "+ sdf.format(locacao.getDataDevolucao()) +".</p></td>\n" +
                "                     </tr>\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0\"><!--[if mso]><a href=\"https://viewstripo.email/\" target=\"_blank\" hidden>\n" +
                "\t<v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" esdevVmlButton href=\"https://viewstripo.email/\"\n" +
                "                style=\"height:35px; v-text-anchor:middle; width:132px\" arcsize=\"14%\" stroke=\"f\"  fillcolor=\"#fcb045\">\n" +
                "\t\t<w:anchorlock></w:anchorlock>\n" +
                "\t\t<center style='color:#ffffff; font-family:arial, \"helvetica neue\", helvetica, sans-serif; font-size:12px; font-weight:700; line-height:12px;  mso-text-raise:1px'>testeW</center>\n" +
                "\t</v:roundrect></a>\n" +
                "    <![endif]--><!--[if !mso] --><span class=\"msohide es-button-border-3 es-button-border\" style=\"border-style:solid;border-color:#808080;background:#fcb045;border-width:0px;display:inline-block;border-radius:5px;width:auto;mso-hide:all\"><a href=\"https://viewstripo.email/\" class=\"es-button es-button-1\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;color:#FFFFFF;font-size:13px;border-style:solid;border-color:#fcb045;border-width:10px 30px;display:inline-block;background:#fcb045;border-radius:5px;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-weight:bold;font-style:normal;line-height:16px;width:auto;text-align:center\">Ver dados da locação completa</a></span><!--<![endif]--></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table></td>\n" +
                "             </tr>\n" +
                "             <tr style=\"border-collapse:collapse\">\n" +
                "              <td align=\"left\" style=\"Margin:0;padding-bottom:15px;padding-top:20px;padding-left:20px;padding-right:20px\"><!--[if mso]><table   style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:180px\" valign=\"top\"><![endif]-->\n" +
                "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td class=\"es-m-p20b\" align=\"left\" style=\"padding:0;Margin:0;width:180px\">\n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td class=\"es-infoblock\" align=\"center\" style=\"padding:0;Margin:0;line-height:0px;font-size:0px;color:#333333;margin-bottom:20px\"><a target=\"_blank\" href=\"https://viewstripo.email/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#599562;font-size:14px\"><img class=\"adapt-img\" src=\""+ locacao.getLivro().getLivro().getImagem() +"\" alt=\"Product Image\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"180\" title=\"Product Image\"></a></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:360px\" valign=\"top\"><![endif]-->\n" +
                "               <table cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:360px\">\n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td class=\"es-infoblock\" align=\"left\" style=\"padding:0;Margin:0;padding-top:10px;line-height:17px;font-size:14px;color:#333333;margin-bottom:20px\"><h3 style=\"Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:20px;font-style:normal;font-weight:bold;color:#333333;margin-bottom:10px\">"+ locacao.getLivro().getLivro().getNome() +"</h3></td>\n" +
                "                     </tr>\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td class=\"es-m-txt-c es-infoblock\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:5px;padding-top:10px;line-height:17px;font-size:14px;color:#333333;margin-bottom:20px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:17px;margin-bottom:20px;color:#333333;font-size:14px\">"+ locacao.getLivro().getLivro().getSinopse() +"</p></td>\n" +
                "                     </tr>\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td class=\"es-m-txt-c es-infoblock\" align=\"left\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:10px;line-height:17px;font-size:14px;color:#333333;margin-bottom:20px\"><h3 style=\"Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:20px;font-style:normal;font-weight:bold;color:#333333;margin-bottom:10px\">Data a devolver: "+ locacao.getDataDevolucao() +"</h3></td>\n" +
                "                     </tr>\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td align=\"left\" class=\"es-infoblock\" style=\"padding:0;Margin:0;line-height:17px;font-size:14px;color:#333333;margin-bottom:20px\"><!--[if mso]><a href=\"https://viewstripo.email/\" target=\"_blank\" hidden>\n" +
                "\t<v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" esdevVmlButton href=\"https://viewstripo.email/\"\n" +
                "                style=\"height:35px; v-text-anchor:middle; width:168px\" arcsize=\"14%\" stroke=\"f\"  fillcolor=\"#fcb045\">\n" +
                "\t\t<w:anchorlock></w:anchorlock>\n" +
                "\t\t<center style='color:#ffffff; font-family:arial, \"helvetica neue\", helvetica, sans-serif; font-size:12px; font-weight:700; line-height:12px;  mso-text-raise:1px'>Ver as avaliações</center>\n" +
                "\t</v:roundrect></a>\n" +
                "<![endif]--><!--[if !mso]> --><span class=\"msohide es-button-border-3 es-button-border\" style=\"border-style:solid;border-color:#808080;background:#fcb045;border-width:0px;display:inline-block;border-radius:5px;width:auto;mso-hide:all\"><a href=\"https://viewstripo.email/\" class=\"es-button es-button-2\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;color:#FFFFFF;font-size:13px;border-style:solid;border-color:#fcb045;border-width:10px 30px 10px 30px;display:inline-block;background:#fcb045;border-radius:5px;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-weight:bold;font-style:normal;line-height:16px;width:auto;text-align:center\">Ver as avaliações</a></span><!--<![endif]--></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "             </tr>\n" +
                "             <tr style=\"border-collapse:collapse\">\n" +
                "              <td align=\"left\" style=\"padding:0;Margin:0;padding-left:20px;padding-right:20px\">\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td align=\"center\" class=\"es-infoblock\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:5px;line-height:120%;font-size:0;color:#333333;margin-bottom:20px\">\n" +
                "                       <table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                         <tr style=\"border-collapse:collapse\">\n" +
                "                          <td style=\"padding:0;Margin:0;border-bottom:1px solid #cccccc;background:none;height:1px;width:100%;margin:0px\"></td>\n" +
                "                         </tr>\n" +
                "                       </table></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table></td>\n" +
                "             </tr>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "       </table>\n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "         <tr style=\"border-collapse:collapse\">\n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "           <table class=\"es-footer-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\" bgcolor=\"#FFFFFF\">\n" +
                "             <tr style=\"border-collapse:collapse\">\n" +
                "              <td align=\"left\" style=\"padding:20px;Margin:0\">\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td align=\"center\" class=\"es-infoblock made_with\" style=\"padding:0;Margin:0;line-height:120%;font-size:0;color:#333333;margin-bottom:20px\"><a target=\"_blank\" href=\"https://viewstripo.email/?utm_source=templates&utm_medium=email&utm_campaign=diwali_template&utm_content=diwali\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#599562;font-size:14px\"><img src=\"7911561025989373.png\" alt width=\"125\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table></td>\n" +
                "             </tr>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "       </table></td>\n" +
                "     </tr>\n" +
                "   </table>\n" +
                "  </div>\n" +
                " </body>\n" +
                "</html>";
    }

}
