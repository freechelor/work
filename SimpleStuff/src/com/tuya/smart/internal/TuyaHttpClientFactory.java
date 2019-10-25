package com.tuya.smart.internal;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;

import org.apache.http.Consts;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
//import org.apache.http.config.ConnectionConfig.Builder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
//import org.apache.http.config.SocketConfig.Builder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import com.tuya.smart.config.ClientConfig;

@SuppressWarnings("deprecation")
public class TuyaHttpClientFactory
{
  protected static volatile TuyaHttpClientFactory instance;
  protected static RequestConfig requestConfig;
  protected PoolingHttpClientConnectionManager connManager;
  protected static Charset defaultEncoding = Consts.UTF_8;
  protected Registry<ConnectionSocketFactory> registry;
  
  public static TuyaHttpClientFactory getInstance()
  {
    synchronized (TuyaHttpClientFactory.class)
    {
      if (instance == null) {
        instance = new TuyaHttpClientFactory();
      }
      return instance;
    }
  }
  
  public CloseableHttpClient getDefaultClient(final ClientConfig clientConfig)
  {
    ConnectionConfig connConfig = ConnectionConfig.custom().setCharset(defaultEncoding).build();
    
    SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(clientConfig.getSocketTimeout()).build();
    
    RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
    
    ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
    registryBuilder.register("http", plainSF);
    try
    {
      KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
      
	SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, new TrustStrategy()
      {
        public boolean isTrusted(X509Certificate[] chain, String authType)
          throws CertificateException
        {
          return true;
        }
      }).build();

	LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      
      registryBuilder.register("https", sslSF);
    }
    catch (KeyStoreException e)
    {
      throw new RuntimeException(e);
    }
    catch (KeyManagementException e)
    {
      throw new RuntimeException(e);
    }
    catch (NoSuchAlgorithmException e)
    {
      throw new RuntimeException(e);
    }
    this.registry = registryBuilder.build();
    
    this.connManager = new PoolingHttpClientConnectionManager(this.registry);
    this.connManager.setDefaultConnectionConfig(connConfig);
    this.connManager.setDefaultSocketConfig(socketConfig);
    this.connManager.setMaxTotal(clientConfig.getMaxConnections());
    this.connManager.setDefaultMaxPerRoute(25);
    
    HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler()
    {
      public boolean retryRequest(IOException exception, int executionCount, HttpContext context)
      {
        if (executionCount >= clientConfig.getMaxErrorRetry()) {
          return false;
        }
        if ((exception instanceof InterruptedIOException)) {
          return false;
        }
        if ((exception instanceof UnknownHostException)) {
          return false;
        }
        if ((exception instanceof ConnectTimeoutException)) {
          return false;
        }
        if ((exception instanceof SSLException)) {
          return false;
        }
        HttpClientContext clientContext = HttpClientContext.adapt(context);
        
        HttpRequest request = clientContext.getRequest();
        boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
        if (idempotent) {
          return true;
        }
        return false;
      }
    };
    requestConfig = RequestConfig.custom().setConnectionRequestTimeout(clientConfig.getConnectionTimeout()).setConnectTimeout(clientConfig.getConnectionTimeout()).setSocketTimeout(clientConfig.getSocketTimeout()).build();
    
    BasicCookieStore cookieStore = new BasicCookieStore();
    
    CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(this.connManager).setRetryHandler(myRetryHandler).setDefaultCookieStore(cookieStore).build();
    
    return httpClient;
  }
}
