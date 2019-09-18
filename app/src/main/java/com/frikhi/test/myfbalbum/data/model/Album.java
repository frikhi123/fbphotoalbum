package com.frikhi.test.myfbalbum.data.model;


public class Album {

    private String albumId;
    private String albumName;
    private String albumCover;
    private String albumCount;

    public Album(String albumId, String albumName, String albumCover) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.albumCover = albumCover;
    }

    public Album(String albumId, String albumName, String albumCoverUrl, String albumCount) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.albumCover = albumCoverUrl;
        this.albumCount = albumCount;
    }

    public String getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumCover() {
        return albumCover;
    }

    public String getAlbumCount() {
        return albumCount;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setAlbumCover(String albumCoverUrl) {
        this.albumCover = albumCoverUrl;
    }

    public void setAlbumCount(String albumCount) {
        this.albumCount = albumCount;
    }

}
