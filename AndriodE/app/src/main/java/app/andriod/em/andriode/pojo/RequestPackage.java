package app.andriod.em.andriode.pojo;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RequestPackage {

    private String url;
    private String method = "GET";
    private Map<String, String> params = new HashMap<>();


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void setParams(String key, String value){
        params.put(key, value);
    }

    public String getEncodeParams(){
        StringBuilder stringBuilder = new StringBuilder();

        for (String key: params.keySet()) {
            String value = null;
            try {
                value = URLEncoder.encode(params.get(key), "UTF-8");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if(stringBuilder.length() > 0){
                stringBuilder.append("&");
                stringBuilder.append(key + "=" + value );

            }

        }
        return stringBuilder.toString();
    }

}
