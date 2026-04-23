package com.catalogo.easylease.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

@Service
public class FtpIntegrationService {



    public FTPClient connect() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(5000);
        ftpClient.connect("easylease-stl.com", 21);
        ftpClient.login("jose@easylease-stl.com", "uV8xzaKXShMA4e94eS3d");
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.enterLocalPassiveMode();
        return ftpClient;
    }

    public void disconnect(FTPClient ftpClient) {
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
            	System.out.println("FtpIntegrationService.disconnect() Error al desconectar del FTP " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public boolean uploadFile(FTPClient ftpClient, InputStream is, String remotePath) {
        try {
            return ftpClient.storeFile(remotePath, is);
        } catch (IOException e) {
        	System.out.println("FtpIntegrationService.disconnect() Error subiendo fichero a ruta: " + remotePath + " " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
