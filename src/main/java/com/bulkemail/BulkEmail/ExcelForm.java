package com.bulkemail.BulkEmail;

import org.springframework.web.multipart.MultipartFile;

public class ExcelForm {

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
