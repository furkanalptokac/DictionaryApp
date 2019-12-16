package controller;

import java.io.*;
import javax.swing.*;

public class Functions {
    private final String path = "src/text/words.txt";
    private final String path2 = "src/text/meanings.txt";
    private FileWriter fw;
    private BufferedReader br, br2;
    
    public void saveWord(String word) throws IOException{
        fw = new FileWriter(path, true);
        try (PrintWriter pw = new PrintWriter(fw, true)) {
            pw.printf("%s\n", word);
        }
    }
    
    public void searchWord(JTextArea wordField, JTextArea meaningField) throws FileNotFoundException, IOException{
        br = null;
        br2 = null;
        
        try{
            String searchingWord = wordField.getText();
            String line;
            String line2;
            
            int count = 0;
            int meaningCount = 0;
            
            br = new BufferedReader(new FileReader(path));
            br2 = new BufferedReader(new FileReader(path2));
            
            while((line = br.readLine()) != null){
                count ++;
                if(line.equals(searchingWord)){
                    while((line2 = br2.readLine()) != null){
                        meaningCount++;
                        if(meaningCount == count){
                            wordField.setText(searchingWord);
                            meaningField.setText(line2);
                             break;
                        }
                    }
                }
            }    
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(br != null)
                    br.close();
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
    
    public void saveMeaning(String word) throws IOException{
        fw = new FileWriter(path2, true);
        try (PrintWriter pw = new PrintWriter(fw, true)) {
            pw.printf("%s\n", word);
        }
    }
    
    public void file() throws IOException{
        File file = new File(path);
        File file2 = new File(path2);
        file.createNewFile();
        file2.createNewFile();
    }
}
