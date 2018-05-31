package io;

public abstract class OrderManagerFileSource implements FileSource {

    private String path;

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

}
