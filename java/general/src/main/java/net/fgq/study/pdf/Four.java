package net.fgq.study.pdf;

import com.alibaba.fastjson.JSONObject;
import net.fgq.study.pdf.PdfToJson;
import net.fgq.study.pdf.annoation.special.PingAnNewTable;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengguoqiang 2020/10/27
 */
public class Four {

    public static void main(String[] args) throws Exception {

        //String filename = "D:\\fgq\\temp\\999.pdf";

        File fileDirectory = new File("D:\\fgq\\temp\\新测试保单-3\\");

        File[] files = fileDirectory.listFiles();
        for (int i = 0; i < files.length; i++) {
//            String filename = "D:\\fgq\\temp\\222.pdf";
            System.out.println(files[i].getName());
            PDDocument document = PDDocument.load(files[i]);

            String content;

            PDFTextStripper pts = new PDFTextStripper();
            pts.setSortByPosition(true);
            content = pts.getText(document);
            document.close();

            String newFile = "";

            if (content.contains("中国平安")) {
                newFile = "平安";
            } else if (content.contains("人寿")) {
                newFile = "人寿";
            } else if (content.contains("太平财产保险")) {
                newFile = "太平";
            } else if (content.contains("PICC")) {
                newFile = "人保";
            } else if (content.contains("CPIC")) {
                newFile = "太平洋";
            } else {
                System.out.println(content);
                //throw new Exception("33333333333333");
                continue;
            }

            newFile += files[i].getName().replace("平安", "")
                    .replace("平安", "")
                    .replace("人保", "")
                    .replace("太平洋", "")
                    .replace("太平", "")
                    .replace("人寿", "");

            newFile = files[i].getParent() + File.separator + newFile;
            System.out.println(newFile);
            files[i].renameTo(new File(newFile));

        }

    }

}
