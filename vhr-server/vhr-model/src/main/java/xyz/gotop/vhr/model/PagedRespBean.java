package xyz.gotop.vhr.model;

import java.util.List;

/**
 * Demo PagedRespBean
 *
 * @author Wolf-Liu
 * @date 2020/2/8 19:38
 */
public class PagedRespBean {
    private int total;
    private List<?> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PagedRespBean{" +
                "total=" + total +
                ", data=" + data +
                '}';
    }
}
