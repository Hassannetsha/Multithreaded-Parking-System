import java.io.FileWriter;
import java.io.IOException;

public class FileContentWriter {
    private String filename;

    public FileContentWriter(String filename) {
        this.filename = filename;
    }

    public void setFilename(String filename){
        this.filename = filename;
    }

    public String getFilename(){
        return this.filename;
    }

    public void write(boolean append, String content) {
        try (FileWriter writer = new FileWriter(this.filename, append)) {
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
