package com.searchoutfit.app.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@CrossOrigin(origins = "*")
@RestController
public class ImageController {

    @RequestMapping(value = "/img-upload", method = RequestMethod.POST)
    public byte[] uploadImage(@RequestParam MultipartFile file) throws IOException {
        System.out.println(Arrays.toString(file.getBytes()));
        return file.getBytes();
    }

}
