package si.fri.rsobook.core.api.data.request.base;

public class ApiBaseRequest {

    protected String eTagHeader = null;
    protected boolean xContentHeader = false;

    public String geteTagHeader() {
        return eTagHeader;
    }

    public void seteTagHeader(String eTagHeader) {
        this.eTagHeader = eTagHeader;
    }

    public boolean isxContentHeader() {
        return xContentHeader;
    }

    public void setxContentHeader(boolean xContentHeader) {
        this.xContentHeader = xContentHeader;
    }
}
