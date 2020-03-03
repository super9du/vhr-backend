package xyz.gotop.vhr.model;

import java.util.Objects;

/**
 * Demo Meta
 *
 * @author Wolf-Liu
 * @date 2020/1/5 21:33
 */
public class Meta {

    private Boolean keepalive;

    private Boolean requireauth;

    public Boolean getKeepalive() {
        return keepalive;
    }

    public void setKeepalive(Boolean keepalive) {
        this.keepalive = keepalive;
    }

    public Boolean getRequireauth() {
        return requireauth;
    }

    public void setRequireauth(Boolean requireauth) {
        this.requireauth = requireauth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meta meta = (Meta) o;
        return Objects.equals(keepalive, meta.keepalive) &&
                Objects.equals(requireauth, meta.requireauth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keepalive, requireauth);
    }

    @Override
    public String toString() {
        return "Meta{" +
                "keepalive=" + keepalive +
                ", requireauth=" + requireauth +
                '}';
    }
}
