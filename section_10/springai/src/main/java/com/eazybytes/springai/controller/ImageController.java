package com.eazybytes.springai.controller;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ImageController {
    private final ImageModel imageModel;

    public ImageController(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @GetMapping("/image")
    String generateImage(@RequestParam("message") String message) {
        var imageResponse = imageModel.call(new ImagePrompt(message));
        return imageResponse.getResults().getFirst().getOutput().getB64Json();
    }

    @GetMapping("/image-options")
    String generateImageWithOptions(@RequestParam("message") String message) {
        var imageResponse = imageModel.call(new ImagePrompt(message,
                OpenAiImageOptions.builder()
                        .n(1) //generate 1 picture
                        .model("gpt-image-2").build()));
        //return data encoded as Base64 text String
        return imageResponse.getResults().getFirst().getOutput().getB64Json();
    }
}
