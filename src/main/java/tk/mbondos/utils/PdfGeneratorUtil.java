package tk.mbondos.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@Component
public class PdfGeneratorUtil {
    @Autowired
    private TemplateEngine templateEngine;
    public String createPdf(String templateName, Map map) throws Exception {
        Assert.notNull(templateName, "The templateName can not be null");
        Context context = new Context();
        if (map != null) {
            Iterator itMap = map.entrySet().iterator();
            while (itMap.hasNext()) {
                Map.Entry pair = (Map.Entry) itMap.next();
                context.setVariable(pair.getKey().toString(), pair.getValue());
            }
        }

        String processedHtml = templateEngine.process(templateName, context);
        FileOutputStream os = null;
        String fileName = "invoice_" + UUID.randomUUID().toString();
        try {
            final File outputFile = new File("src/main/resources/files/" + fileName + ".pdf");
            os = new FileOutputStream(outputFile);

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(os, false);
            renderer.finishPDF();
            System.out.println("PDF created successfully");

            return outputFile.getPath();
        }
        finally {
            if (os != null) {
                try {
                    os.close();

                } catch (IOException e) { /*ignore*/ }
            }

        }
    }

    public void clearDirectory(){
        File directory = new File("src/main/resources/files/");
        File[] files = directory.listFiles();
        if(files!=null) { //some JVMs return null for empty dirs
            for(File f : files){
                f.delete();
            }
        }
    }

}
