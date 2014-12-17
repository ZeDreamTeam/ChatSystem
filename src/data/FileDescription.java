package data;

import java.io.File;

public class FileDescription implements ReceivedStuff {
    private String name, path;
    private long size;

    public FileDescription(File f) {
        this.name = f.getName();
        this.size = f.length();
        this.path = f.getPath();
    }

    public FileDescription(String name, long size, String path) {
        this.name=name;
        this.size=size;
        this.path=path;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public String getPath() {
        return path;
    }

    public File getFile() {
        return new File(path);
    }

    @Override
    public String toString() {
        return path+":"+path+ " size :"+size;
    }
}
