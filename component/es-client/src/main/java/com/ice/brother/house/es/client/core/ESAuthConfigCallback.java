package com.ice.brother.house.es.client.core;

import com.ice.brother.house.es.client.ESProperties;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClientBuilder;

class ESAuthConfigCallback implements RestClientBuilder.HttpClientConfigCallback {

  private HttpHost _host;
  private ESProperties esProperties;

  public ESAuthConfigCallback(HttpHost host, ESProperties esProperties) {
    this._host = host;
    this.esProperties = esProperties;
  }

  @Override
  public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
    //异步请求时设置并发连接数.
    if (esProperties.isUniqueConnectNumConfig()) {
      httpClientBuilder.setMaxConnTotal(esProperties.getMaxConnNum());
      httpClientBuilder.setMaxConnPerRoute(esProperties.getMaxConnPerRoute());
    }
    //账户权限.
//    BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//    credentialsProvider.setCredentials(new AuthScope(_host),
//        new UsernamePasswordCredentials(esProperties.getUsername(), esProperties.getPassword()));
//    httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);

    //如果配置请求服务为https，则设置校验.
    //TODO https部分代码逻辑待验证.
    if (StringUtils.equalsIgnoreCase("https", esProperties.getSchema())) {
      httpClientBuilder.setSSLHostnameVerifier(new HostnameVerifier() {
        @Override
        public boolean verify(String s, SSLSession sslSession) {
          return true;
        }
      });
      TrustManager[] trustAllCerts = new TrustManager[1];
      TrustManager tm = new ESTrustManager();
      trustAllCerts[0] = tm;
      try {
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        httpClientBuilder.setSSLContext(sc);
      } catch (KeyManagementException e) {
        e.printStackTrace();
      } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
      }
    }

    return httpClientBuilder; //备注：
  }
}