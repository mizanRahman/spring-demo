package com.example.ep.config;

/**
 * Created by mac on 11/26/16.
 */
public enum EndPoint {

    Cards(Path.CARDS),
    ACard(Path.A_CARD);

    public interface Path {
        public final String CARDS = "/cards";
        public final String A_CARD = "/cards/{id}";
    }

    private String path;

    EndPoint(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }

    public String fullPath() {
        return "/api".concat(path);
    }
}
