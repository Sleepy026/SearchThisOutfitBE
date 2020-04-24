package com.searchoutfit.app.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class ImageController {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/img-upload", method = RequestMethod.POST)
    public String uploadImage(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(file.toString());
        return file.toString();
    }



}
