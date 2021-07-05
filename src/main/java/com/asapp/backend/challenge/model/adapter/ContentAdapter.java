package com.asapp.backend.challenge.model.adapter;

import com.asapp.backend.challenge.model.Content;
import com.asapp.backend.challenge.model.Image;
import com.asapp.backend.challenge.model.Text;
import com.asapp.backend.challenge.model.Video;
import com.asapp.backend.challenge.resources.ContentResource;

public class ContentAdapter {
    public static ContentResource toApi(Content content) {
        if (content instanceof Image) {
            Image image = (Image) content;
            return new ContentResource(image.getType(), image.getUrl(), image.getWidth(), image.getHeight());
        } else if (content instanceof Video) {
            Video video = (Video) content;
            return new ContentResource(video.getType(), video.getUrl(), video.getSource().name());
        } else {
            Text text = (Text) content;
            return new ContentResource(text.getType(), text.getText());
        }
    }
}
