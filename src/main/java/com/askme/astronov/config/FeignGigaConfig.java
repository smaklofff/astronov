package com.askme.astronov.config;

import feign.Client;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.springframework.context.annotation.Bean;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class FeignGigaConfig {

    @Bean
    public Client feignClient() throws Exception {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

        // Загрузка .pem сертификата
        FileInputStream fis = new FileInputStream("src/main/resources/certs/russiantrustedca.pem");
        X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(fis);

        // Создание пустого хранилища сертификатов
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null); // Инициализация пустого хранилища

        // Добавление сертификата в хранилище
        keyStore.setCertificateEntry("russiantrustedca", certificate);

        // Настройка TrustManager с использованием созданного хранилища
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);

        // Настройка SSLContext
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
        return new Client.Default(sslContext.getSocketFactory(), SSLConnectionSocketFactory.getDefaultHostnameVerifier());
//        return new Client();
    }
}
