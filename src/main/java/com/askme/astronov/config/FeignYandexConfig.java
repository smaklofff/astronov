package com.askme.astronov.config;

import feign.Client;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

@Component
public class FeignYandexConfig {

    private final ResourceLoader resourceLoader;

    public FeignYandexConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public Client feignYandexGptClient() throws Exception {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

        InputStream inputStream = resourceLoader.getResource("classpath:certs/russiantrustedca.pem").getInputStream();
        X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(inputStream);

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null); // Инициализация пустого хранилища

        keyStore.setCertificateEntry("russiantrustedca", certificate);

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
        return new Client.Default(sslContext.getSocketFactory(), SSLConnectionSocketFactory.getDefaultHostnameVerifier());
    }
}