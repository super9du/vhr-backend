package xyz.gotop.vhr.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * menu
 *
 * @author
 */
public class Menu implements Serializable {
    private Integer id;

    private String url;

    private String path;

    private String component;

    private String name;

    private String iconcls;

    private Meta meta;

    private Integer parentid;

    private Boolean enabled;

    private List<Role> roles;

    private List<Menu> children;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconcls() {
        return iconcls;
    }

    public void setIconcls(String iconcls) {
        this.iconcls = iconcls;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id) &&
                Objects.equals(url, menu.url) &&
                Objects.equals(path, menu.path) &&
                Objects.equals(component, menu.component) &&
                Objects.equals(name, menu.name) &&
                Objects.equals(iconcls, menu.iconcls) &&
                Objects.equals(meta, menu.meta) &&
                Objects.equals(parentid, menu.parentid) &&
                Objects.equals(enabled, menu.enabled) &&
                Objects.equals(roles, menu.roles) &&
                Objects.equals(children, menu.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, path, component, name, iconcls, meta, parentid, enabled, roles, children);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", name='" + name + '\'' +
                ", iconcls='" + iconcls + '\'' +
                ", meta=" + meta +
                ", parentid=" + parentid +
                ", enabled=" + enabled +
                ", roles=" + roles +
                ", children=" + children +
                '}';
    }
}