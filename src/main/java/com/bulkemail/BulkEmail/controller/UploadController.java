package com.bulkemail.BulkEmail.controller;

import com.bulkemail.BulkEmail.ExcelForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class UploadController {

    @Autowired
    ExcelReader excelReader;

    @GetMapping("/upload")
    public String showUploadForm(Model model) {
        model.addAttribute("excelForm", new ExcelForm());
        return "uploadFile.html";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            return "uploadFile.html";
        }

        try (InputStream in = file.getInputStream()) {
            List<String> emails = excelReader.readEmailColumn(in);
            model.addAttribute("emails", emails);
        } catch (IOException e) {
            model.addAttribute("message", "Failed to upload file: " + e.getMessage());
            return "uploadFile";
        }

        return "redirect:/upload";
    }
}
