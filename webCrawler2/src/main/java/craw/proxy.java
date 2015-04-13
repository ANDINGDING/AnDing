package craw;

/**
 * Created by ad10830 on 2015/4/10.
 */
public class proxy {

    private String ip;
    private String port;
    private int tag;
    public proxy() {
        super();
    }
    public proxy(int tag, String ip, String port) {
        super();
        this.tag = tag;
        this.ip = ip;
        this.port = port;
    }
    public int getTag() {
        return tag;
    }
    public void setTag(int tag) {
        this.tag = tag;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getPort() {
        return port;
    }
    public void setPort(String port) {
        this.port = port;
    }


}
