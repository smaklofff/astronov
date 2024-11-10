package com.askme.astronov.utils;

import lombok.SneakyThrows;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class SslPemCert {

    @SneakyThrows
    public static void setSslCert() {

        // Создание фабрики сертификатов для X.509
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

        // Установить SSLContext по умолчанию
        SSLContext.setDefault(sslContext);
    }
}

